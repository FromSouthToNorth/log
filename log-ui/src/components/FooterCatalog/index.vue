<template>
  <div class="hy-footer__top hy-footer__top_theme_dark">
    <ul class="jb-footer-catalog hy-row hy-row_size_s">
      <li class="hy-offset-top-48 hy-col-2 hy-col-md-4 hy-col-sm-6" :key="index" v-for="(item, index) in catalog">
        <h5 class="hy-text-3 hy-text-3_hardness_hard hy-text-3_theme_dark" v-text="item.title">
          友情链接
        </h5>
        <ul class="hy-offset-top-12">
          <li class="hy-text-3 hy-text-3_flow hy-text-3_theme_dark" :key="index" v-for="(catalog, index) in item.catalog">
            <a :href="catalog.link" class="hy-link hy-link_hardness_average hy-link_theme_dark" v-text="catalog.title">IDE</a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "FooterCatalog",
  props: {
    catalog: {
      type: Array
    }
  }
}
</script>

<style scoped>

</style>