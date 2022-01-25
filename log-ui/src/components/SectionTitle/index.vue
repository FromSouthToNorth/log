<template>
  <div class="hy-col-3">
    <div class="hy-section-title" v-text="title"></div>
  </div>
</template>

<script>
export default {
  name: "SectionTitle",
  props: {
    title: {
      type: String,
      default: 'title'
    }
  }
}
</script>

<style scoped>
.hy-section-title {
  font: 600 14px/15px Gotham SSm A, Gotham SSm B, Helvetica, Arial, sans-serif;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: end;
  -ms-flex-align: end;
  align-items: flex-end;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  width: 80px;
  height: 80px;
  margin-top: -130px;
  margin-bottom: 64px;
  padding: 12px;
  background: #161616;
  text-transform: uppercase;
  color: #fff;
}
</style>