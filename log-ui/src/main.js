import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Photoswipe from "vue-pswipe";
import 'github-markdown-css';
import 'highlight.js/styles/github-dark.css';
import '@/assets/styles/reset.scss';
import '@/assets/styles/index.scss';

import '@/assets/icons';

Vue.config.productionTip = false;
Vue.use(Photoswipe);

new Vue({
  el: '#app',
  router,
  render: h => h(App),
})
