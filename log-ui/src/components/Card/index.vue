<template>
  <div class="col">
    <a class="card eaps">
      <Photoswipe auto>
        <img v-show="data.imgHeader" width="2560" height="1440" class="" :src="data.imgHeader" alt="">
      </Photoswipe>
      <div class="card__header">
        <svg-icon v-show="data.iconHeader" class-name="card__logo card__logo--small" :icon-class="data.iconHeader"/>
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
  name: "index",
  props: {
    data: {
      type: Object
    }
  }
}
</script>

<style lang="scss" scoped>
.light-gray-bg .card {
  background: #fff;
}

.card {
  transition: transform 0.15s ease-in-out;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  border: 1px solid rgba(39, 40, 44, 0.15);
  cursor: pointer;
  padding: 0 0 12px;
  margin: 0 0 32px;
  border-radius: 10px;
  overflow: hidden;
  width: 100%;
}

.card h3 {
  font-family: "JetBrains Mono";
  font-size: 20px;
  line-height: 1.6;
  margin: 0 0 7px;
}

.card:hover {
  text-decoration: none;
  transform: translateY(-2%);
}

.card__header {
  position: relative;
  padding: 16px 16px 0;
}

.svg-icon.card__logo {
  width: 56px;
  margin-bottom: 8px;
}

.svg-icon.card__logo.card__logo--small {
  width: 40px;
  height: 43px;
}

.card svg,
.card img {
  flex-shrink: 0;
}

img, svg {
  max-width: 100%;
  height: auto;
  vertical-align: top;
}

.card time {
  display: block;
  font-size: 13px;
  line-height: 1.54;
  font-weight: 300;
  font-family: "JetBrains Mono";
  margin: 0 0 9px;
  color: #27282c;
}

.card.eaps .tag {
  font-family: "JetBrains Mono";
  font-size: 13px;
  line-height: 1.54;
  font-weight: 300;
  position: absolute;
  top: 11px;
  right: 5px;
  text-transform: uppercase;
}

.card.eaps .tag:hover {
  background-color: rgba(39, 40, 44, 0.05);
  color: #27282C;
}

.card__body {
  color: #27282c;
  padding: 0 16px 12px;
}

.card__body p {
  font-family: "JetBrains Mono";
}

.card__footer {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  font-size: 13px;
  line-height: 1.54;
  font-weight: 300;
  margin-top: auto;
  padding: 0 16px;
}

.card .author {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  width: 70%;
}

.card .avatar {
  width: 32px;
  height: 32px;
  overflow: hidden;
  border-radius: 50%;
}

.card .author img {
  margin-right: 16px;
}

.card .author img.avatar {
  width: 32px;
  height: 32px;
}

.card .author span {
  font-family: "JetBrains Mono";
  color: #27282c;
  width: calc(100% - 48px);
}
</style>