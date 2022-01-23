<template>
  <div
    :class="className"
    class="hy-row hy-row_size_m">
    <div class="hy-col-6 hy-col-md-10 hy-col-sm-12 hy-offset-top-48">
      <h2 class="hy-h1 hy-h1_theme-light" v-html="article.title">二叉树<br/>binary tree</h2>
      <p class="hy-text-1 hy-text-1_flow hy-text-1_theme_light" v-text="article.subhead">
      </p>
    </div>
    <div class="hy-col-6 hy-col-md-12 hy-offset-top-48">
      <div class="hy-youtube-player">
        <div class="hy-img__link">
          <Photoswipe auto>
            <img :src="article.image" alt="">
          </Photoswipe>
        </div>
        <div class="hy-img__player"></div>
      </div>
    </div>
    <slot />
  </div>
</template>

<script>
export default {
  name: "ArticleBox",
  props: {
    article: {
      type: Object,
      default: () => {},
    },
    className: {
      type: String,
      default: ''
    }
  }
}
</script>

<style scoped>

</style>