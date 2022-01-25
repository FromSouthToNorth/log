<template>
  <section class="section hy-section_bg_gray-light">
    <div class="hy-container z-index-1 customers-container">
      <div class="hy-row wt-row_size_m">
        <SectionTitle title="Blog" />
        <ArticleBox v-for="item in 2" :article="article" :class-name="item === 1 ? '':'hy-offset-top-48'" >
          <hr class="hy-hr hy-hr_theme_light hy-offset-top-48" v-if="item!==2">
        </ArticleBox>
      </div>
    </div>
  </section>
</template>

<script>
import SectionTitle from "@comp/SectionTitle";
import ArticleBox from "@comp/ArticleBox";

export default {
  name: "index",
  components: {SectionTitle, ArticleBox},
  data() {
    return {
      article: {
        title: `二叉树<br/>binary tree`,
        subhead: `本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。`,
        image: require('@/assets/image/michael.jpg')
      },
    }
  }
}
</script>

<style scoped>

</style>