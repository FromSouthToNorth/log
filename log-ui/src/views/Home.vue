<template>
  <div class="page__content">
    <Promo v-if="topArticle" :top-article="topArticle" />
    <section class="section">
      <div class="hy-container">
        <SectionTitle title="文章" />
        <div class="row card_container" v-if="articleDataSource">
          <router-link class="col" tag="div" :key="index" :to="'/article/' + item.articleId" v-for="(item, index) in articleDataSource">
            <Card :data="item" />
          </router-link>
        </div>
        <div class="more load-more" v-if="total > queryParams.pageSize">
          <a @click="this.getArticleList" class="loadMoreBtn">
            {{load?'Loading...':'Load more'}}
          </a>
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
import {scrollTo} from "@/utils/scroll-to";

export default {
  name: "Home",
  components: {Promo, Card, SectionTitle, ArticleBox},
  data() {
    return {
      articleDataSource: undefined,
      topArticle: null,
      total: 0,
      load: true,
      queryParams: {
        pageNum: 1,
        pageSize: 0,
      }
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
      this.load = true
      this.queryParams.pageSize += 8
      articleList(this.queryParams).then(result => {
        this.total = result.total
        this.articleDataSource = result.rows
        this.load = false
      })
    }
  }
}
</script>

<style scoped>

</style>