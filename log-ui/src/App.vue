<template>
  <div id="app" class="page" @click="headerClick">
    <page-header ref="pageHeader"/>
    <main>
      <keep-alive>
        <router-view/>
      </keep-alive>
    </main>
    <page-footer/>
  </div>
</template>

<script>

import PageHeader from "@/components/PageHeader";
import PageFooter from "@/components/PageFooter";
import {mapState} from "vuex";

export default {
  computed: {
    ...mapState({
      preloaderHidden: state => state.settings.preloaderHidden
    })
  },
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
  min-height: calc(100vh - (129px + 200px));
}
</style>
