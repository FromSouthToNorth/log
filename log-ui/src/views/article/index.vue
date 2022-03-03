<template>
  <section class="article-section">
    <div id="outline" :style="hiddenSidebar ? {display: 'none'} : {display: 'block'}">
    </div>
    <aside class="sidebar" v-if="article" :class="hiddenSidebar ? 'hidden' : ''">
      <div class="sidebar__holder" ref="sidebarHolder">
        <img v-if="article.articleCover" class="sidebar__logo" :src="article.articleCover" :alt="article.articleTitle">
        <svg-icon v-else class-name="card__logo card__logo--small sidebar__logo" style="width: 40px; height: 40px;"
                  :icon-class="'idea_icon'"/>
        <span class="sidebar__title">{{ article.articleTitle }}</span>

      </div>
    </aside>
    <section class="content" v-if="article">
      <a href="" class="tag">{{ article.articleType.typeName }}</a>
      <h1 id="articleTitle">{{ article.articleTitle }}</h1>
      <div class="post-info">
        <img class="avatar" :src="article.user.avatar"
             loading="lazy"
             alt="user_avatar">
        <div class="post-info__text">
          <a>{{ article.user.realName }}</a>
          <time class="publish-date" :datetime="parseTime(article.createTime, '{y}-{m}-{d}')">
            {{ parseTime(article.createTime, '{y}年{m}月{d}日') }}
          </time>
        </div>
      </div>
      <article id="article" ref="article" v-if="article"/>
    </section>
    <div class="section light-gray-bg" v-if="discoverMore.length > 0">
      <div class="container">
        <div class="section__head">
          <h2>发现更多</h2>
        </div>
        <div class="row">
          <router-link tag="a" :to="'/article/' + item.articleId" class="col" :key="index"
                       v-for="(item, index) in discoverMore">
            <Card :data="item"/>
          </router-link>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import "vditor/dist/index.css"
import {getArticleIngo} from "@/api/article";
import SectionTitle from "@/components/SectionTitle";
import Card from "@/components/Card";
import Vditor from "vditor/dist/method.min"

export default {
  name: "index",
  components: {SectionTitle, Card},
  data() {
    return {
      article: null,
      discoverMore: [],
      clipboard: null,
      images: [],
      hiddenSidebar: false
    }
  },
  watch: {
    $route(to, from) {
      let articleId = to.params.articleId;
      if (articleId) {
        this.getArticle(articleId)
      }
    }
  },
  destroyed() {
    this.scrollingDestroy()
  },
  created() {
    const articleId = this.$route.params && this.$route.params.articleId
    this.getArticle(articleId)
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
        this.$nextTick(() => {
          this.markdownPreview(this.article.articleContent)
        })
      })
    },
    markdownPreview(markdown) {
      const own = this
      Vditor.preview(document.getElementById('article'),
        markdown,
        {
          hljs: {
            lineNumber: true,
            enable: true
          },
          after() {
            own.scrolling()
            own.constructorImages()
            const article = document.getElementById('article');

            if (window.innerWidth <= 768) {
              return
            }
            const outlineElement = document.getElementById('outline')
            Vditor.outlineRender(article, outlineElement)
            if (outlineElement.innerText.trim() !== '') {
              outlineElement.style.display = 'block'
              own.initOutline()
            }
          }
        })
    },
    /** 目录渲染 */
    initOutline() {
      const headingElements = []
      Array.from(document.getElementById('article').children).forEach((item) => {
        if (item.tagName.length === 2 && item.tagName !== 'HR' && item.tagName.indexOf('H') === 0) {
          headingElements.push(item)
        }
      })

      let toc = []
      window.addEventListener('scroll', () => {
        const scrollTop = window.scrollY
        toc = []
        headingElements.forEach((item) => {
          toc.push({
            id: item.id,
            offsetTop: item.offsetTop,
          })
        })

        const currentElement = document.querySelector('.vditor-outline__item--current')
        for (let i = 0, iMax = toc.length; i < iMax; i++) {
          if (scrollTop < toc[i].offsetTop - 30) {
            if (currentElement) {
              currentElement.classList.remove('vditor-outline__item--current')
            }
            let index = i > 0 ? i - 1 : 0
            document.querySelector('span[data-target-id="' + toc[index].id + '"]').classList.add('vditor-outline__item--current')
            break
          }
        }
      })
    },
    /** 查看图片 */
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
  box-sizing: border-box;
}

.light-gray-bg {
  background: #F4F4F4;
}

.container {
  max-width: 1534px;
  margin: 0 auto;
  padding: 0 15px;
}

.content #article.vditor-reset {
  font-family: JetBrainsMono;


  pre > code {
    font-family: JetBrainsMono;
  }

  .katex {
    font-size: 16px;
  }
}

.vditor-reset {
  overflow: hidden;
}

.sidebar {
  transition: opacity 0.15s ease-in-out, visibility 0.15s ease-in-out;
  display: none;
}

#outline {
  display: none;
  position: fixed;
  width: 186px;
  top: 196px;
  bottom: 86px;
  overflow: auto;
  font-size: 14px;
  line-height: 20px;
}

#outline ul {
  margin-left: 16px;
  list-style: none;
}

#outline > ul {
  margin-left: 0;
}

#outline li > span {
  cursor: pointer;
  border-left: 1px solid transparent;
  display: block;
  padding-left: 8px;
}

#outline li > span.vditor-outline__item--current {
  border-left: 1px solid #4285f4;
  color: #4285f4;
  background-color: #f6f8fa;
}

#outline li > span:hover {
  color: #4285f4;
  background-color: #f6f8fa;
}

@media (max-width: 768px) {
  #outline {
    display: none !important;
  }
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