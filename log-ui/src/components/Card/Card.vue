<template>
<div class="col">
  <a class="card eaps">
    <Photoswipe auto>
     <img v-show="data.imgHeader" width="2560" height="1440" class="" :src="data.imgHeader" alt="">
    </Photoswipe>
    <div class="card__header">
      <svg-icon v-show="data.iconHeader" class-name="card__logo card__logo--small" :icon-class="data.iconHeader" />
      <time class="publish-date">2021年6月23日</time>
      <h3 style="float: none; position: static;" v-text="data.title">二叉树 binary tree</h3>
      <span v-show="!data.imgHeader" class="tag">了解详情</span>
    </div>
    <div class="card__body">
      <p style="float: none; position: static;" v-text="data.describe">
        本 LeetBook 将会帮助你掌握树和二叉树的概念、熟悉不同的遍历方法、并能够运用递归解决二叉树相关题目。
      </p>
    </div>
    <div class="card__footer">
      <div class="author">
        <img class="avatar" :src="data.avatar" alt="">
        <span v-text="data.author">hy</span>
      </div>
    </div>
  </a>
</div>
</template>

<script>
export default {
  name: "Card",
  props: {
    data: {
      type: Object
    }
  }
}
</script>

<style scoped>
</style>