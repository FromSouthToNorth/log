import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from "@/store";
import Photoswipe from "vue-pswipe";
import "vditor/dist/index.css"
import '@/assets/styles/reset.scss';
import '@/assets/styles/index.scss';
import 'highlight.js/styles/idea.css'
import './beforeEach';
import 'vue-git-comment/dist/vue-git-comment.css'
import '@/assets/icons';
import { formatTime } from "@/utils";
import { parseTime } from "@/utils/logutils";

Vue.prototype.formatTime = formatTime
Vue.prototype.parseTime = parseTime

Vue.config.productionTip = false;
Vue.use(Photoswipe);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App),
})
