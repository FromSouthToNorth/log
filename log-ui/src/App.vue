<template>
  <div id="app" class="page" @click="headerClick">
    <page-header ref="pageHeader"/>
    <router-view/>
    <page-footer/>
  </div>
</template>

<script>

import PageHeader from "@/components/PageHeader";
import PageFooter from "@/components/PageFooter";

export default {
  name: 'App',
  components: {
    PageFooter,
    PageHeader
  },
  methods: {
    headerClick(event) {
      this.$refs.pageHeader.headerClick(event);
    }
  }
}
</script>

<style>
</style>
