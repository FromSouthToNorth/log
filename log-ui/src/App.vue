<template>
  <div id="app" class="page">
    <page-header/>
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
  }
}
</script>

<style>
</style>
