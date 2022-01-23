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

</style>