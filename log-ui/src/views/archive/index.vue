<template>
  <div class="page__content">
    <div  class="news-content">
      <div class="bg-beam hy-section_bg_dark hy-section">
        <div class="hy-container hy-offset-top-96">
          <div class="hy-h2 hy-h2_theme_dark hy-offset-top-96">所有文章</div>
          <div class="hy-offset-top-96"  v-for="(value, key) in articleArchiveMap" :key="key">
            <h2 class="hy-h2 hy-h2_theme_dark">
              {{ key }}
            </h2>
            <div class="hy-row hy-row_size_1">
              <router-link :to="'/article/' + article.articleId" :key="article.articleId" v-for="article in value" class="_hy-card_ru6f9_1 _hy-card_theme_light_ru6f9_22 hy-offset-top-48 hy-col-4">
                <div class="_hy-card__section_ru6f9_133">
                  <p class="hy-text-2_theme_dark hy-text-2">
                    {{ parseTime(article.createTime, '{y}年{m}月{d}日') }}
                  </p>
                  <h3 class="hy-h3 hy-h3_theme_dark hy-offset-top-12">{{ article.articleTitle }}</h3>
                  <p class="hy-text-2 hy-text-2_theme_dark blog-post__description hy-offset-top-12">
                    <span>{{ article.remark }}</span>
                  </p>
                  <div class="hy-text-2 hy-text-2_theme_dark hy-offset-top-12">
                    <span class="hy-link hy-link_external hy-link_theme_dark">
                      阅读更多
                    </span>
                  </div>
                </div>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {articleArchive} from "@/api/article";

export default {
  name: "index",
  data() {
    return {
      articleArchiveMap: null
    }
  },
  created() {
    this.getArchive()
  },
  methods: {
    /** 查询归档 */
    getArchive() {
      articleArchive().then(result => {
        this.articleArchiveMap = result.data
      })
    }
  }
}
</script>

<style scoped>
.bg-beam {
  position: relative;
  z-index: 0;
  overflow: hidden;
}
.bg-beam:after {
  content: "";
  position: absolute;
  top: -678px;
  left: 50%;
  -webkit-transform: translateX(-50%);
  transform: translateX(-50%);
  margin-left: 600px;
  width: 948px;
  height: 1391px;
  z-index: -1;
  background: url("https://202007002.oss-cn-chengdu.aliyuncs.com/images/archive.svg?versionId=CAEQTRiBgMDYye.Q.hciIGZhYzUzNGY3ZGI5MTQ2NTg4M2Y4MWFmYzY3OWU4OWRi") no-repeat 0 0;
  background-size: 100% auto;
}
</style>