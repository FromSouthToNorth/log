<template>
  <div id="app" :class="isBlackAndWhite ? 'BlackAndWhite' : ''" class="page" @click="headerClick">
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
import {blackAndWhite} from "@/api/config";

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
  data() {
    return {
      isBlackAndWhite: undefined
    }
  },
  created() {
    this.getBlackAndWhite()
  },
  methods: {
    headerClick(event) {
      this.$refs.pageHeader.headerClick(event);
    },
    /* 查询网页是否黑白 */
    getBlackAndWhite() {
      blackAndWhite().then(result => {
        this.isBlackAndWhite = result.data
      })
    }
  }
}
</script>

<style scoped>
main {
  min-height: calc(100vh - (129px + 200px));
}
</style>
