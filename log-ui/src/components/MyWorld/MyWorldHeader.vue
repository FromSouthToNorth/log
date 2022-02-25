<template>
  <div class="my-world-header"></div>
</template>

<script>
import { init } from "@/components/MyWorld/myWorld";

export default {
  name: 'MyWorldHeader',
  data() {
    return {

    }
  },
  mounted() {
  },
  methods: {
    initWorld() {
      init(".my-world-header")
    }
  }
}
</script>

<style scoped>
.my-world-header {
  padding-bottom: 100px;
  overflow: hidden;
}
</style>