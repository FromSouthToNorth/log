<template>
  <div class="page__header">
    <div class="language-suggest-bar"></div>
    <header ref="header" class="menu-main header menu-main__mobile">
      <div :class="hamburger ? 'menu-main__container-mobile _active' : ''" class="hy-container menu-main__container">
        <div :class="hamburger ? '_mobile-open' : ''"
             class="hy-row hy-row_size_0 hy-row_wide hy-row_wrap hy-row_align-items_center menu-main__items-wrapper">
          <div v-show="!hamburger"
               :class="screenWidth >= 1000 ? ['hy-col-auto-fill', 'hy-col_align-self_stretch'] : 'hy-col'">
            <router-link to="/" class="menu-main__logo-link menu-main__logo-link-mobile">
              <svg ref="logoSvg" class="logoSvg" xmlns="http://www.w3.org/2000/svg"
                   xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 600 600">
                <defs>
                  <filter id="glow" y="-50%" height="180%" color-interpolation-filters="sRGB">
                    <feGaussianBlur stdDeviation="4" result="coloredBlur"/>
                    <feMerge>
                      <feMergeNode in="coloredBlur"/>
                      <feMergeNode in="SourceGraphic"/>
                    </feMerge>
                  </filter>
                </defs>
                <circle ref="bubble" class="bubble" cx="0" cy="0" r="0"/>
                <path class="face" fill="#27282c" d="M250.1,477.1c0,0,17.9-37.5,28.3-47.5c8.3-8,10.9-12.4,40.4,0c29.5,12.4,38.1,8.5,45.1,3.1
            	c7-5.4,8.2-19.8,6.9-23.8c-1.3-4.1,3.4-9.3,6.3-9c2.9,0.3,7.7-3.1,8-6.6s0.4-3.2-0.9-8.6c2.8-1,16.2-3.4,9.3-15
            	c-6.8-11.7,3.8-15.1,3.8-15.1s19.2-0.2,19.4-11.7c0.2-11.5-17.1-33.2-17.9-40.6c-0.8-7.4,3.5-18.1,3.5-18.1s15.1-38.8-3.5-75.7
            	c-18.6-36.9-69.1-52-88.2-53.6c-40-3.3-86.6,12.4-103.7,65.3s-4.2,92.3-4.7,108.8c-1.6,56-45.6,91.3-45.6,91.3L250.1,477.1z"/>
              </svg>
            </router-link>
          </div>
          <div class="hy-col-auto-fill hy-display-sm-none"></div>
          <div
            @click="toRouter(item.path)"
            :key="index"
            v-for="(item, index) in router"
            v-if="screenWidth >= 1000 || menuMainItem"
            :class="hamburger ? 'menu-main__item-mobile' : ''"
            class="hy-text-1 ht-text-1_theme_dark menu-main__item">{{ item.title }}
          </div>
          <div v-if="!hamburger"
               class="hy-col-auto-fill hy-h4 hy-h4-theme_dark menu-main__action-link menu-main__logo-link-mobile">
            {{ title }}
          </div>
          <div @click="hamburgerClick" v-if="screenWidth <= 1000 && !hamburger"
               class="hy-col-inline menu-main__action-icon menu-main__action-icon-mobile _hamburger">
            <svg viewBox="0 0 24 24" class="hy-icon hy-icon_size_m">
              <path d="M4 5h16v2H4zm0 6h16v2H4zm0 6h16v2H4z"></path>
            </svg>
          </div>
        </div>
      </div>
      <div v-if="hamburger" class="hy-col-inline menu-main__action-icon menu-main__action-icon-mobile _close">
        <svg-icon class-name="_mobile-close" @click="hamburgerClick" icon-class="close"/>
      </div>
    </header>
    <div class="menu-second" :style="searchFixed ? fixed : ''">
      <div class="hy-container">
        <div class="menu-second-desktop menu-second-mobile hy-row hy-row_size_0 hy-row_align_center">
          <div class="hy-col-auto-fill menu-second-mobile__trigger" v-if="screenWidth >= 1000">
            <router-link to="/" class="menu-second-title-box">
            <span class="menu-second-title-box__logo _fixed hy-logo _hy-text-20">
              <h3 class="_hy-text-20">
                {{ title }}
              </h3>
            </span>
            </router-link>
          </div>
          <div ref="menuSecond">
            <div class="menu-search" v-show="isSearchInput">
              <div class="quick-search">
                <div class="search__input">
                  <label class="_hy-input_1pdmso7_1 _hy-input_size_s_1pdmso7_80">
                    <div class="_hy-input__wrapper_1pdmso7_5">
                      <div class="_hy-input-field_ipdmso7_53">
                        <input v-model="keywords" v-focus="focusState" @invalid="searchInvalid"
                               placeholder="搜索关键字" type="text" class="_hy-input__inner_1pdmso7_92">
                        <svg @click="clear" v-show="clearIcon" viewBox="0 0 24 24"
                             class="hy-icon _hy-input_theme_dark hy-icon_size_s _hy-input__icon _hy-input__icon_right _hy-input__icon_action">
                          <path
                            d="M21 12a9 9 0 1 1-9-9 9 9 0 0 1 9 9zm-4.235-3.351l-1.414-1.414L12 10.585 8.635 7.222 7.221 8.635 10.586 12l-3.257 3.257 1.414 1.414L12 13.414l3.288 3.289 1.415-1.415L13.414 12z"></path>
                        </svg>
                      </div>
                    </div>
                  </label>
                </div>
                <div class="quick-search__results" v-if="searchResults">
                  <div v-if="searchDataSource.length > 0" class="quick-search__results-header">
                    <div class="quick-search__results-title">
                      搜索结果
                      <span class="quick-search__results-query">{{ keywords }}</span>»
                    </div>
                  </div>
                  <div v-if="searchDataSource.length > 0">
                    <ul class="search-results">
                      <a @click="toRouterArticle(data.articleId)" class="hy-link"
                                   :key="data.articleId" v-for="data in searchDataSource">
                        <li class="hy-link-item">
                        <span class="_hy-list-item__content">
                          <div class="quick-search__item">
                            <h2 class="quick-search__title">{{ data.articleTitle }}</h2>
                            <div class="quick-search__snippet" v-html="data.articleContent">
                            </div>
                          </div>
                        </span>
                        </li>
                      </a>
                    </ul>
                  </div>
                  <h3 v-if="searchDataSource.length === 0" class="quick-search__no-results" data-test="no-results">
                    我们很抱歉！我们找不到结果 «{{ keywords }}»</h3>
                </div>
              </div>
            </div>
            <a @click="searchInput" class="hy-col-inline menu-main__action-icon" v-show="!isSearchInput">
              <svg-icon icon-class="search"/>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {gsap, Linear, Power2, TweenMax} from 'gsap'
import {searchArticles} from "@/api/article";
import {mapState} from "vuex";

export default {
  name: 'PageHeader',
  computed: {
    ...mapState({
      title: state => state.settings.title
    })
  },
  data() {
    return {
      screenWidth: document.body.clientWidth,
      screenHeight: undefined,
      isSearchInput: false,
      focusState: false,
      oldScrollTop: 0,
      searchFixed: false,
      hamburger: false,
      fixed: {
        position: 'fixed',
        top: '0px',
        zIndex: '991',
        bottom: 'auto',
        width: document.body.clientWidth + 'px',
        backdropFilter: 'saturate(180%) blur(20px)',
        backgroundColor: 'rgba(255,255,255,0.72)'
      },
      timer: false,
      menuMainItem: false,
      router: [
        // {title: "标签", path: "/tag"},
        // {title: "分类", path: "/type"},
        {title: "归档", path: "/archive"},
        // {title: "关于我", path: "/aboutMe"}
      ],
      keywords: undefined,
      searchResults: false,
      searchDataSource: [],
      clearIcon: false
    }
  },
  directives: {
    focus: {
      update: function (el, {value}) {
        if (value) {
          el.focus()
        }
      }
    }
  },
  watch: {
    keywords(val) {
      this.searchResults = true
      this.searchResults = !!val
      this.clearIcon = !!val
      if (val) {
        this.searchArticle()
      }
    }
  },
  mounted() {
    this.logoSvg()
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth;
        this.screenHeight = document.body.clientHeight;
        this.fixed.width = document.body.clientWidth + 'px'
      })();
    }
    window.addEventListener("scroll", () => {
      window.requestAnimationFrame(() => {
        this.scrolling()
      })
    })
  },
  destroyed() {
    this.scrollingDestroy()
  },
  methods: {
    toRouterArticle(id) {
      let url = '/article/' + id
      this.$router.push(url)
      this.clear()
    },
    headerClick(event) {
      let isShowSearch = this.$refs.menuSecond !== event.target && this.$refs.menuSecond.contains(event.target)
      if (!isShowSearch) {
        this.clear()
      }
    },
    /** 清理搜索输入框 */
    clear() {
      this.keywords = undefined
      this.isSearchInput = false
      this.searchResults = false
      this.clearIcon = false
    },
    searchMouseleave() {
      this.isSearchInput = true
      this.searchResults = true
    },
    /** 搜索文章 */
    searchArticle() {
      searchArticles(this.keywords).then(result => {
        this.searchDataSource = result.data
      })
    },
    onBlur() {
      this.isSearchInput = !this.isSearchInput
      this.searchResults = !this.searchResults
      this.keywords = undefined
    },
    /** 移除滚动监听事件 */
    scrollingDestroy() {
      document.removeEventListener("scroll", this.scrolling)
    },
    /* 导航路由跳转 */
    toRouter(link) {
      if (this.hamburger && this.menuMainItem) {
        this.hamburgerClick()
      }
      if (this.$route.path.indexOf(link)) {
        this.$router.push(link)
      }
    },
    /* 汉堡按钮点击事件 */
    hamburgerClick() {
      this.hamburger = !this.hamburger
      this.menuMainItem = !this.menuMainItem
    },
    /* 监听滚动条 */
    scrolling() {
      this.oldScrollTop = window.scrollY
      this.searchFixed = this.oldScrollTop >= this.$refs.header.offsetHeight;
    },
    /* 输入框 */
    searchInput() {
      console.log("输入框");
      this.isSearchInput = !this.isSearchInput
      this.focusClick()
    },
    /* 输入框设置焦点 */
    focusClick() {
      this.focusState = true
    },
    searchInvalid() {
      console.log("searchInvalid")
    },
    /* logo 动画 */
    logoSvg() {
      let logSvg = this.$refs.logoSvg
      let bubble = this.$refs.bubble
      let mainTimeline = gsap.timeline({paused: true});

      let particleColorArray = ['#f5222d', '#2f54eb', '#fa541c', '#9254de'];
      let numBubbles = 100;

      for (let i = 0; i < numBubbles / 2; i++) {
        let colorId = Math.floor(Math.random() * particleColorArray.length);
        let p = bubble.cloneNode(true);
        logSvg.insertBefore(p, logSvg.firstChild);
        let startRadius = gsap.utils.random(2, 32);

        TweenMax.set(p, {
          attr: {
            cx: gsap.utils.random(200, 300),
            cy: gsap.utils.random(400, 400),
            r: 0
          },
          filter: 'url(#glow)',
          fill: particleColorArray[colorId]
        })

        p.startRadius = startRadius;

        let dur = gsap.utils.random(8, 10);
        let angle = gsap.utils.random(0, 90);
        let tl = gsap.timeline({repeat: 3});

        tl.to(p, dur / 2, {
          attr: {
            r: startRadius
          }
        })
          .to(p, dur, {
            transformOrigin: '30% 50%',
            rotation: -180,
            opacity: 1,
            attr: {
              cy: 700
            },
            physics2D: {
              velocity: -0,
              angle: angle,
              acceleration: 0,
              gravity: -2,
              accelerationAngle: 0
            }
          }, '-=' + (dur / 2))
          .to(p, dur / 2, {
            attr: {
              r: 0
            }
          }, '-=' + (dur / 2))

        mainTimeline.add(tl, i / 10);
      }

      //in front
      for (let i = 0; i < numBubbles; i++) {

        let colorId = Math.floor(Math.random() * particleColorArray.length);

        let p = bubble.cloneNode(true)
        logSvg.appendChild(p);
        let startRadius = gsap.utils.random(1, 20);
        TweenMax.set(p, {
          attr: {
            cx: gsap.utils.random(250, 350),
            cy: gsap.utils.random(350, 500),
            r: 0
          },
          fill: particleColorArray[colorId]
        })

        let dur = gsap.utils.random(10, 12);
        let angle = gsap.utils.random(0, -90);
        let tl = gsap.timeline({repeat: 3});
        tl.to(p, dur / 2, {
          attr: {
            r: startRadius
          }
        })
          .to(p, dur, {
            rotation: 280,
            opacity: 1,
            attr: {
              cy: 650
            },
            physics2D: {
              velocity: -20,
              angle: angle,
              acceleration: -2,
              gravity: -10,
              accelerationAngle: 0
            }
          }, '-=' + (dur / 2))
          .to(p, dur / 2, {
            attr: {
              r: 0
            }
          }, '-=' + (dur / 2))

        mainTimeline.add(tl, i / 8);

      }

      //top of head
      for (let i = 0; i < numBubbles; i++) {

        let colorId = Math.floor(Math.random() * (particleColorArray.length - 1)) + 1;

        let p = bubble.cloneNode(true)
        logSvg.insertBefore(p, logSvg.firstChild);
        let startRadius = gsap.utils.random(2, 10);
        TweenMax.set(p, {
          attr: {
            cx: gsap.utils.random(250, 350),
            cy: gsap.utils.random(190, 210),
            r: startRadius
          },
          fill: particleColorArray[colorId]
        })

        let dur = gsap.utils.random(6, 10);
        let angle = gsap.utils.random(0, 90);

        let t = TweenMax.fromTo(p, {
          duration: dur,
          opacity: 1,
          attr: {
            r: startRadius
          }
        }, {
          transformOrigin: '30% 650%',
          rotation: -0,
          repeat: 3,
          opacity: 1,
          attr: {
            r: 0,
            cx: '+=30'
          },
          physics2D: {
            velocity: 0,
            angle: angle,
            acceleration: 0,
            gravity: -20,
            accelerationAngle: 90
          }
        });

        mainTimeline.add(t, i / 6);

      }

      logSvg.removeChild(bubble);

      let allTl = gsap.timeline({repeat: -1, yoyo: false, paused: false});
      allTl.timeScale(1.1)
      allTl.to(mainTimeline, {
        duration: 12,
        time: mainTimeline.duration(),
        ease: Linear.easeNone

      })
      allTl.to(mainTimeline, 20, {
        time: 3,
        ease: Power2.easeInOut

      })
        .to(mainTimeline, 15, {
          time: 5,
          ease: Power2.easeInOut

        })
        .to(mainTimeline, 10, {
          time: 7,
          ease: Power2.easeInOut

        })
        .to(mainTimeline, 20, {
          time: 20,
          ease: Power2.easeInOut

        })
        .to(mainTimeline, 20, {
          time: 30,
          ease: Power2.easeInOut

        })

        .to(mainTimeline, 20, {
          time: 40,
          ease: Power2.easeInOut

        })

      function updateHue() {

        TweenMax.set('.colorMatrix', {
          attr: {
            values: (allTl.time() / allTl.duration())
          }
        })
      }
    },
  }
}
</script>

<style scoped>

</style>