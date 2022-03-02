<template>
  <div id="app" class="page" @click="headerClick">
    <page-header ref="pageHeader"/>
    <main>
      <router-view/>
    </main>
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

<style scoped>
main {
  min-height: calc(100vh - 444px);
}
</style>
