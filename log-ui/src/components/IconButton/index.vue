<template>
  <a
    :href="iconButton.link"
    :title="iconButton.title"
    class="_hy-button_1xl2g87_1 _hy-button_mode_nude_1xl2g87_596 _hy-button_size_s_1xl2g87_104 _hy-button_theme_dark_1xl2g87_76 _hy-button_with-icon_1xl2g87_161 _hy-button_without-text_1xl2g87_134 hy-button_align-icon_left hy-col-inline">
    <svg-icon class-name="hy-icon hy-icon_size_m hy-text-3 hy-text-3_hardness_average hy-text-3_theme_dark _hy-button__icon_1xl2g87_687" :icon-class="iconButton.iconClass" />
  </a>
</template>

<script>
export default {
  name: "IconButton",
  props: {
    iconButton: {
      type: Object
    }
  }
}
</script>

<style scoped>

</style>