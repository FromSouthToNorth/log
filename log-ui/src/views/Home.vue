<template>
  <div class="page__content">
    <Promo v-if="topArticle" :top-article="topArticle" />
    <section class="section">
      <div class="hy-container">
        <SectionTitle title="文章" />
        <div class="row" v-if="articleCards">
          <router-link class="col" tag="div" :key="index" :to="'/article/' + item.articleId" v-for="(item, index) in articleCards">
            <Card :data="item" />
          </router-link>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import ArticleBox from "@/components/ArticleBox";
import SectionTitle from "@/components/SectionTitle";
import Card from "@/components/Card";
import Promo from "@/components/Promo/Promo";
import { topArticle, articleList } from "@/api/article";

export default {
  name: "Home",
  components: {Promo, Card, SectionTitle, ArticleBox},
  data() {
    return {
      articleCards: undefined,
      topArticle: null,
    }
  },
  created() {
    this.getTopArticle()
    this.getArticleList()
  },
  methods: {
    /** 获取置顶文章 */
    getTopArticle() {
      topArticle().then(result => {
        this.topArticle = result.data
      })
    },
    /** 获取文章列表 */
    getArticleList() {
      articleList().then(result => {
        this.articleCards = result.data
      })
    }
  }
}
</script>

<style scoped>

</style>