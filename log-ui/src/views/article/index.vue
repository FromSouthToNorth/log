<template>
  <section class="article-section">
    <div class="content" v-if="article">
      <h1>{{ article.articleTitle }}</h1>
      <div class="post-info">
        <img class="avatar" :src="article.user.avatar"
             loading="lazy"
             alt="user_avatar">
        <div class="post-info__text">
          <a>{{ article.user.realName }}</a>
          <time class="publish-date" :datetime="parseTime(article.createTime, '{y}-{m}-{d}')">{{ parseTime(article.createTime, '{y}年{m}月{d}日') }}</time>
        </div>
      </div>
      <Markdown :article="article.articleContent" />
    </div>
  </section>
</template>

<script>
import Markdown from "@/components/Markdown/Markdown";
import {getArticleIngo} from "@/api/article";
export default {
  name: "index",
  components: {Markdown},
  data() {
    return {
      article: undefined
    }
  },
  created() {
    const articleId = this.$route.params && this.$route.params.articleId
    this.getArticle(articleId)
  },
  methods: {
    getArticle(articleId) {
      getArticleIngo(articleId).then(result => {
        this.article = result.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.article-section {

  .content {
    font-size: 20px;
    line-height: 1.6;
    font-weight: 300;
    font-family: JetBrainsMono;
    padding: 64px 15px;
    min-height: 600px;
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
  }

  time {
    display: block;
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
}

@media (min-width: 1025px) {
  .article-section .content {
    max-width: 700px;
    padding: 64px 0;
    margin: 0 32px;
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