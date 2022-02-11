<template>
  <section class="article-section">
    <aside class="sidebar" v-if="article" :class="hiddenSidebar ? 'hidden' : ''">
      <div class="sidebar__holder" ref="sidebarHolder">
        <img v-if="article.articleCover" class="sidebar__logo" :src="article.articleCover" :alt="article.articleTitle">
        <svg-icon v-else class-name="card__logo card__logo--small sidebar__logo" style="width: 40px; height: 40px;" :icon-class="'idea_icon'" />
        <span class="sidebar__title">{{ article.articleTitle }}</span>
        <div class="tag-list" id="toc">
        </div>
      </div>
    </aside>
    <div class="content" v-if="article">
      <a href="" class="tag">{{ article.articleType.typeName }}</a>
      <h1 id="articleTitle">{{ article.articleTitle }}</h1>
      <div class="post-info">
        <img class="avatar" :src="article.user.avatar"
             loading="lazy"
             alt="user_avatar">
        <div class="post-info__text">
          <a>{{ article.user.realName }}</a>
          <time class="publish-date" :datetime="parseTime(article.createTime, '{y}-{m}-{d}')">{{ parseTime(article.createTime, '{y}年{m}月{d}日') }}</time>
        </div>
      </div>
      <article id="write" ref="article" v-if="article" v-html="article.articleContent" class="article markdown-body"/>
    </div>
    <div class="section light-gray-bg" v-if="discoverMore">
      <div class="container">
        <div class="section__head">
          <h2>发现更多</h2>
        </div>
        <div class="row">
          <a href="#articleTitle" @click="getArticle(item.articleId)" class="col" :key="index" v-for="(item, index) in discoverMore">
            <Card :data="item" />
          </a>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import {getArticleIngo} from "@/api/article";
import SectionTitle from "@/components/SectionTitle";
import Card from "@/components/Card";
import MarkdownIt from 'markdown-it'
import highlight from 'highlight.js'
import Clipboard from 'clipboard'
import tocbot from 'tocbot'
import MarkdownItKatex from "markdown-it-latex2img";

export default {
  name: "index",
  components: {SectionTitle, Card},
  data() {
    return {
      article: null,
      discoverMore: null,
      clipboard: null,
      images: [],
      hiddenSidebar: false
    }
  },
  destroyed() {
    this.clipboard.destroy()
    tocbot.destroy()
  },
  created() {
    const articleId = this.$route.params && this.$route.params.articleId
    this.getArticle(articleId)
    this.scrollingDestroy()
  },
  methods: {
    /** 移除滚动监听事件 */
    scrollingDestroy() {
      document.removeEventListener("scroll", this.scrolling)
    },
    scrolling() {
      window.addEventListener("scroll", () => {
        window.requestAnimationFrame(() => {
          let article = this.$refs.article.offsetHeight;
          let sidebar = this.$refs.sidebarHolder.offsetHeight;
          this.hiddenSidebar = window.scrollY >= article - sidebar
        })
      })
    },
    /** 文章 ID 查询文章 */
    getArticle(articleId) {
      getArticleIngo(articleId).then(result => {
        document.title = result.data.articleTitle
        this.article = result.data
        this.discoverMore = result.discoverMore
        this.markdownToHtml(this.article.articleContent);
        this.$nextTick(() => {
          this.generateContent()
          this.constructorImages()
          this.clipboardBtn()
          this.scrolling()
        })
      })
    },
    /* 查看图片 */
    constructorImages() {
      const images = this.$refs.article.getElementsByTagName('img');
      for (let i = 0; i < images.length; i++) {
        images[i].addEventListener('click', e => {
          this.previewImages(e.target.currentSrc, i)
        })
        this.images.push({src: images[i].src})
      }
    },
    /** 点击图片全屏展示 */
    previewImages(image, index) {
      this.$Pswp.open({
        items: this.images,
        options: {
          index: index,
          bgOpacity: 0.9
        }
      })
    },
    /* 目录生产 */
    generateContent() {
      let nodes = this.$refs.article.children
      if (nodes.length) {
        for (let i = 0; i < nodes.length; i++) {
          let node = nodes[i];
          let reg = /^H[1-4]{1}$/
          if (reg.exec(node.tagName)) {
            node.id = i
          }
        }
      }

      tocbot.init({
        tocSelector: '#toc',                // 要把目录添加元素位置，支持选择器
        contentSelector: '.article',        // 获取html的元素
        headingSelector: 'h1, h2, h3',  // 要显示的id的目录
        hasInnerContainers: true,
        onClick: function (e) {
          e.preventDefault();
        }
      })
    },
    /* 复制代码按钮 */
    clipboardBtn() {
      this.clipboard = new Clipboard('.copy-btn');
      this.clipboard.on('success', () => {
        console.log('clipboard success')
      })
    },
    /* 渲染 Markdown fo Html */
    markdownToHtml(article) {
      const md = new MarkdownIt({
        html: true,
        linkify: true,
        typographer: true,
        highlight: function (str, lang) {
          let date = new Date().getTime();
          if (window.performance && typeof window.performance.now === "function") {
            date += performance.now();
          }
          const codeIndex = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(
            /[xy]/g,
            function (c) {
              let r = (date + Math.random() * 16) % 16 | 0;
              date = Math.floor(date / 16);
              return (c === "x" ? r : (r & 0x3) | 0x8).toString(16);
            }
          );
          // 复制功能主要使用的是 clipboard.js
          let html = `<button class="copy-btn" type="button" data-clipboard-action="copy" data-clipboard-target="#copy${codeIndex}"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M11.3 8.3H19a3 3 0 0 1 3 3V19a3 3 0 0 1-3 3h-7.7a3 3 0 0 1-3-3v-7.7a3 3 0 0 1 3-3zm0 2a1 1 0 0 0-1 1V19a1 1 0 0 0 1 1H19a1 1 0 0 0 1-1v-7.7a1 1 0 0 0-1-1h-7.7zm-5.6 3.4a1 1 0 0 1 0 2h-.9A2.8 2.8 0 0 1 2 12.9V4.8A2.8 2.8 0 0 1 4.8 2h8.1a2.8 2.8 0 0 1 2.8 2.8v.9a1 1 0 0 1-2 0v-.9a.8.8 0 0 0-.8-.8H4.8a.8.8 0 0 0-.8.8v8.1a.8.8 0 0 0 .8.8h.9z" fill="#fff" fill-rule="evenodd"/></svg></button>`;
          const linesLength = str.split(/\n/).length - 1;
          // 生成行号
          let linesNum = '<span aria-hidden="true" class="line-numbers-rows">';
          for (let index = 0; index < linesLength; index++) {
            linesNum = linesNum + "<span></span>";
          }
          linesNum += "</span>";
          if (!lang) {
            lang = "markdown";
          }
          if (lang && highlight.getLanguage(lang)) {
            // highlight.js 高亮代码
            const preCode = highlight.highlight(str, {language: lang}).value;
            html = html + preCode;
            // if (linesLength) {
            //   html += '<b class="name">' + lang + "</b>";
            // }
            // 将代码包裹在 textarea 中，由于防止textarea渲染出现问题，这里将 "<" 用 "<" 代替，不影响复制功能
            return `<pre class="hljs"><code>${html}</code>${linesNum}</pre><textarea style="position: absolute;top: -9999px;left: -9999px;z-index: -9999;" id="copy${codeIndex}">${str.replace(
              /<\/textarea>/g,
              "</textarea>"
            )}</textarea>`;
          }
        }
      }).use(MarkdownItKatex)
      article = md.render(article)
      this.article.articleContent = article
    }
  }
}
</script>

<style lang="scss">
.article-section {

  .content {
    font-size: 20px;
    line-height: 1.6;
    font-weight: 300;
    font-family: JetBrainsMono;
    padding: 64px 15px;
    min-height: 600px;
  }

  .tag {
    font-size: 13px;
    line-height: 1.54;
    font-weight: 300;
    margin: 0 8px 16px 0;
  }

  h1 {
    margin: 0 0 28px;
  }
}

.post-info {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  font-weight: 300;
  font-size: 16px;
  line-height: 1.5;
  margin: 0 0 48px;

  .avatar {
    width: 48px;
    border-radius: 50%;
    overflow: hidden;
    border: 1px solid rgba(0, 0, 0, 0.1);
    margin: 0 16px 0 0;
  }

  a {
    display: inline-block;
    vertical-align: middle;
    margin: 0 0 5px;

    &:hover {
      text-decoration: underline;
    }
  }

  time {
    display: block;
  }
}

.section {
  padding: 25px 0;
}

.light-gray-bg {
  background: #F4F4F4;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 15px;
}

@media (min-width: 641px) {
  .container {
    padding: 0 32px;
  }
}

@media (min-width: 769px) {
  .article-section .content {
    padding: 64px 32px;
  }

  h1 {
    font-size: 40px;
    line-height: 1.090909;
    letter-spacing: -1px;
    font-weight: 400;
  }

  .section {
    padding: 48px 0;
  }
}

.markdown-body pre.hljs {
  padding: 12px 2px 12px 30px;
  border-radius: 5px;
  position: relative;
  font-size: 13px;
  line-height: 24px;
  overflow: hidden;

  &:hover .copy-btn {
    display: flex;
    justify-content: center;
    align-items: center;
    opacity: 1;
  }

  code {
    display: block;
    margin: 0 10px;
    overflow-x: auto;

    &::-webkit-scrollbar {
      z-index: 11;
      width: 6px;
    }

    &::-webkit-scrollbar:horizontal {
      height: 6px;
    }

    &::-webkit-scrollbar-thumb {
      border-radius: 5px;
      width: 6px;
      background: #666;
    }

    &::-webkit-scrollbar-corner,
    &::-webkit-scrollbar-track {
      background: #1e1e1e;
    }

    &::-webkit-scrollbar-track-piece {
      background: #1e1e1e;
      width: 6px;
    }
  }

  .line-numbers-rows {
    position: absolute;
    pointer-events: none;
    top: 12px;
    bottom: 12px;
    left: 0;
    font-size: 12px;
    width: 30px;
    text-align: center;
    letter-spacing: -1px;
    border-right: 1px solid rgba(0, 0, 0, 0.66);
    user-select: none;
    counter-reset: linenumber;

    span {
      pointer-events: none;
      display: block;
      counter-increment: linenumber;

      &:before {
        content: counter(linenumber);
        color: #999;
        display: block;
        text-align: center;
      }
    }
  }

  b.name {
    position: absolute;
    top: 7px;
    right: 45px;
    z-index: 1;
    color: #999;
    pointer-events: none;
  }

  .copy-btn {
    padding: 4px;
    position: absolute;
    top: 6px;
    right: 6px;
    z-index: 1;
    color: #ccc;
    background-color: #525252;
    border-radius: 4px;
    opacity: 0;
    width: 24px;
    height: 24px;
    outline: none;
    border: none;
    cursor: pointer;
    transition: opacity 0.3s ease-in-out 0s;

    & svg {
      display: inline-block;
      width: 100%;
      height: 100%;
    }
  }
}

.markdown-body ol, ul {
  list-style-type: disc;
}

#app .markdown-body {

  font-family: "JetBrainsMono";

  & pre.hljs {
    font-family: "JetBrainsMono";
    background: #262626;

    & code {
      font-family: "JetBrainsMono";
    }
  }
}

.tag-list ol {
  display: flex;
  flex-wrap: wrap;
  align-items: baseline;

  li {
    a {
      max-width: 88px;
      overflow:hidden; //超出的文本隐藏
      text-overflow:ellipsis; //溢出用省略号显示
      white-space:nowrap; //溢出不换行
    }
  }
}

.sidebar {
  transition: opacity 0.15s ease-in-out, visibility 0.15s ease-in-out;
  display: none;
}

@media (min-width: 1025px) {
  .article-section {

    .content {
      max-width: 700px;
      padding: 64px 0;
      margin: 0 32px;
      min-height: 600px;
    }
  }

  .sidebar {
    display: flex;
    flex-direction: row-reverse;
    width: 100%;
    max-width: 1536px;
    margin: 0 auto;

    &.hidden {
      opacity: 0;
      visibility: visible;
    }

    .tag-list {
      font-size: 13px;
      line-height: 1.54;
      font-weight: 300;
    }

    a {
      transition: background-color 0.15s ease-in-out, color 0.15s ease-in-out;
      padding: 5px 13px;
      background-color: rgba(39, 40, 44, 0.05);
      display: inline-block;
      border-radius: 4px;
      margin: 0 8px 8px 0;
      white-space: nowrap;
      cursor: pointer;
    }

    a:hover {
      background-color: rgba(22, 125, 255, 0.1);
      color: #167DFF;
      text-decoration: none;
    }
  }

  .sidebar__holder {
    position: fixed;
    top: 25vh;
    max-width: 272px;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 32px 32px 0 0;
  }

  .sidebar__logo {
    width: 60px;
    margin: 0 0 8px;
  }

  .sidebar__title {
    display: block;
    font-size: 16px;
    line-height: 1.5;
    font-weight: 300;
    opacity: .8;
    margin: 0 0 16px;
  }
}

@media (min-width: 1201px) {
  .article-section .content {
    max-width: 750px;
    margin-left: 12%;
  }
}

@media (min-width: 1441px) {
  .article-section .content {
    max-width: 900px;
  }
}

@media (min-width: 1541px) {
  .article-section .content {
    margin: 0 auto;
  }
}
</style>