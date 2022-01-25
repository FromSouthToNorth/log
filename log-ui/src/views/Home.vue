<template>
  <div class="page__content">
    <Promo />
    <section class="section">
      <div class="hy-container">
        <SectionTitle title="文章" />
        <div class="row">
          <Card :data="item" :key="index" v-for="(item, index) in articleCards" />
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

export default {
  name: "Home",
  components: {Promo, Card, SectionTitle, ArticleBox},
  data() {
    return {
      articleCards: [
        {
          imgHeader: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          iconHeader: '',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: '',
          iconHeader: 'jetbrains_icon',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: '',
          iconHeader: 'idea_icon',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: '',
          iconHeader: 'goland_icon',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: '',
          iconHeader: 'phpstorm_icon',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: '',
          iconHeader: 'rubymine_icon',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: '',
          iconHeader: 'teamcity_icon',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        },
        {
          imgHeader: require('@/assets/image/lego.jpg'),
          iconHeader: '',
          title: '二叉树 binary tree',
          describe: '本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。',
          avatar: require('@/assets/image/edwin-chen-JQUlYfOvh-E-unsplash.jpg'),
          author: 'hy'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>