<template>
  <div id="app" class="page">
    <page-header/>
    <router-view/>
    <page-footer/>
  </div>
</template>

<script>

import PageHeader from "@comp/PageHeader";
import PageFooter from "@comp/PageFooter";

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
