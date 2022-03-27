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
import '@/assets/icons';
import { formatTime } from "@/utils";
import { parseTime } from "@/utils/logutils";
import Vssue from "vssue";
import 'vssue/dist/vssue.css'
import GithubV4 from "@vssue/api-github-v4";

Vue.prototype.formatTime = formatTime
Vue.prototype.parseTime = parseTime

Vue.config.productionTip = false;
Vue.use(Photoswipe);

Vue.use(Vssue, {
  api: GithubV4,
  clientId: '',
  clientSecret: '',
  owner: '',
  repo: '',
  locale: 'zh-CN'
})

new Vue({
  el: '#app',
  router,
  store,
  created() {
    store.dispatch('settings/getBlackAndWhite')
  },
  render: h => h(App),
})
