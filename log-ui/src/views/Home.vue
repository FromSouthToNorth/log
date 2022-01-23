<template>
  <div class="page__content">
    <section class="section section_bg_dark">
      <div class="hy-container hy-offset-top-48">
        <div class="banner-tiles">
          <div class="hy-row hy-row_size_s">
            <div class="flex-container hy-col-8 hy-col-md-12">
              <a href="/" class="banner-tile banner-tile_teamcity webinar-banner__background hy-row hy-row_size_0">
                <div class="hy-col-8 hy-col-lg-9 hy-col-md-8 hy-col-sm-12">
                  <div class="jb-vertical-flex">
                    <div class="logo_primary">
                      <svg-icon icon-class="counter"/>
                    </div>
                    <div class="hy-offset-top-48 hy-offset-top-lg-24 hy-hero hy-hero_theme_dark">初级算法</div>
                    <div class="hy-offset-top-24 jb-vertical-flex__grow hy-subtitle-2 hy-subtitle-2_theme_dark">
                      LeetCode 官方推出的经典面试题目清单 —— 「初级算法 - 帮助入门」
                    </div>
                    <button
                      type="button"
                      class="_hy-button_1xl2g87_1 _hy-button_size_m_1xl2g87_92
                      _hy-button_mode_black_1xl2g87_276 hy-offset-top-48
                      _hy-button_theme_dark_1xl2g87_76 hy-offset-top-lg-24 hy-offset-top-sm-48">
                      了解详情</button>
                  </div>
                </div>
              </a>
            </div>
            <div class="flex-container hy-col-4 hy-col-md-12">
              <div class="flex-container__grow hy-row hy-row_size_s">
                <div class="flex-container hy-col-12 hy-col-md-6 hy-col-sm-12">
                  <a href="/" class="hy-offset-top-md-24 banner-tile banner-tile_secondary banner-tile_idea">
                    <div class="hy-row hy-row_size_m">
                      <div class="banner-tile__logo-wrapper hy-col-inline">
                        <div class="logo_secondary _full-block jetbrains-logo _size-0">
                          <svg-icon icon-class="game"/>
                        </div>
                      </div>
                    </div>
                    <div class="hy-offset-top-12 hy-text-2 hy-text-2_theme_dark">队列 & 栈</div>
                    <div class="hy-offset-top-12 jb-vertical-flex__grow hy-h3 hy-h3_theme_dark">
                      队列和栈是两种不同的线性数据结构
                    </div>
                    <button
                      type="button"
                      class="_hy-button_1xl2g87_1  _hy-button_mode_outline_1xl2g87_436
                       _hy-button_size_s_1xl2g87_104 _hy-button_theme_dark_1xl2g87_76
                        _hy-button_align-icon_left hy-offset-top-12">了解详情</button>
                  </a>
                </div>
                <div class="flex-container hy-col-12 hy-col-md-6 hy-col-sm-12">
                  <a href="/" class="hy-offset-top-12 hy-offset-top-md-24 banner-tile banner-tile_secondary banner-tile_cwm">
                    <div class="banner-tile__logo-wrapper">
                      <div class="banner-tile__logo logo_secondary">
                        <svg-icon icon-class="setting"/>
                      </div>
                    </div>
                    <div class="hy-offset-top-12 hy-h3 hy-h3_theme_dark">二叉树</div>
                    <div class="jb-vertical-flex__grow hy-text-2 hy-text-2_hardness_hard hy-text-2_theme_dark">
                      熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。
                    </div>
                    <button
                      type="button"
                      class="_hy-button_1xl2g87_1  _hy-button_mode_outline_1xl2g87_436
                       _hy-button_size_s_1xl2g87_104 _hy-button_theme_dark_1xl2g87_76
                        _hy-button_align-icon_left hy-offset-top-12">了解详情</button>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
<!--    <section class="section hy-section_bg_gray-light">-->
<!--      <div class="hy-container z-index-1 customers-container">-->
<!--        <div class="hy-row wt-row_size_m">-->
<!--          <SectionTitle title="Blog" />-->
<!--          <ArticleBox v-for="item in 2" :article="article" :class-name="item === 1 ? '':'hy-offset-top-48'" >-->
<!--            <hr class="hy-hr hy-hr_theme_light hy-offset-top-48" v-if="item!==2">-->
<!--          </ArticleBox>-->
<!--        </div>-->
<!--      </div>-->
<!--    </section>-->
    <section class="section">
      <div class="hy-container">
        <div class="section__head">
          <h2>文章</h2>
        </div>
        <div class="row">
          <Card :data="item" :key="index" v-for="(item, index) in articleCards" />
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import ArticleBox from "@comp/ArticleBox";
import SectionTitle from "@comp/SectionTitle";
import Card from "@comp/Card/Card";

export default {
  name: "Home",
  components: {Card, SectionTitle, ArticleBox},
  data() {
    return {
      article: {
        title: `二叉树<br/>binary tree`,
        subhead: `本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。`,
        image: require('@/assets/image/michael.jpg')
      },
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