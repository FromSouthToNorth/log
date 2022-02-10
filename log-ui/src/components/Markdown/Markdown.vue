<template>
  <article id="write" ref="article" v-if="article" v-html="articleContent" class="article markdown-body"/>
</template>

<script>
import MarkdownIt from 'markdown-it'
import highlight from 'highlight.js'
import Clipboard from 'clipboard'
import tocbot from 'tocbot'

export default {
  name: 'Markdown',
  props: {
    article: {
      type: String
    }
  },
  data() {
    return {
      clipboard: null,
      images: [],
      articleContent: undefined,
    }
  },
  destroyed() {
    this.clipboard.destroy()
    tocbot.destroy()
  },
  created() {
    this.markdownToHtml(this.article)
  },
  mounted() {
    this.clipboardBtn()
    // this.generateContent()
    this.constructorImages()
  },
  methods: {
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
        tocSelector: '#toc',            // 要把目录添加元素位置，支持选择器
        contentSelector: '.article',    // 获取html的元素
        headingSelector: 'h1, h2, h3, h4',  // 要显示的id的目录
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
      })
      article = md.render(article)
      this.articleContent = article
    }
  }
}
</script>

<style lang="scss">
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
</style>