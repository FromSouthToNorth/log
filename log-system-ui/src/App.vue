<template>
  <div id="app" :class="isBlackAndWhite ? 'BlackAndWhite' : ''">
    <router-view />
  </div>
</template>

<script>
import {blackAndWhite} from "@/api/system/config";

export default {
  name: 'App',
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
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
    getBlackAndWhite() {
      blackAndWhite().then(result => {
        this.isBlackAndWhite = result.data
      })
    }
  }
}
</script>
