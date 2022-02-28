<template>
  <div class="page__content">
    <section class="hy-section">
      <div class="hy-container">
        <div class="hy-row hy-row_size_m">
          <div class="hy-col hy-col-9 hy-col-md-11 hy-col-sm-12">
            <h1 class="hy-hero hy-offset-top-96">寻找标签</h1>
            <h2 class="hy-subtitle-1 hy-offset-top-24">
              无论您喜欢什么技术，这里都有一款适合您的标签
            </h2>
          </div>
        </div>
        <div class="hy-offset-top-96">
          <div class="hy-row hy-row-sm_direction_column-reverse hy-row_size_m">
            <div class="hy-col-9 hy-col-md-10 hy-col-sm-12">
              <div class="hy-row hy-row_size_m hy-offset-top-sm-24">
                <div :key="index" v-for="index in 8" class="_hy-card_ru6f9_1 _hy-card_theme_light_ru6f9_15 hy-col hy-col-4 hy-col-md-6 hy-col-sm-12 jb-product">
                  <div class="_hy-card__section_ru6f9_133 jb-product__section">
                    <div>
                      <div class="_full-block jetbrains-logo _size-1">
                        <svg-icon icon-class="java"></svg-icon>
                      </div>
                      <h3 class="hy-h3 hy-offset-top-24">
                        <a class="hy-link hy-link_hardness_hard" href="">Java</a>
                      </h3>
                      <p class="hy-text2 hy-text-2_hardness_average hy-offset-top-12">
                        Java是一种广泛使用的计算机编程语言，拥有跨平台、面向对象、泛型编程的特性，广泛应用于企业级Web应用开发和移动应用开发。
                      </p>
                      <div class="hy-row hy-row_size_s hy-offset-top-12">
                        <a class="hy-link hy-col-inline" href="https://zh.wikipedia.org/wiki/Java">
                          了解详情
                        </a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="hy-col-3 hy-col-md-2 hy-col-sm-12">
              <div v-if="screenWidth <= 1000 && showFilters"
                   class="filter-trigger hy-row hy-row-md_justify_end hy-row-sm_justify_start">
                <button @click="filtersSwitch"
                        class="_hy-button_1xl2g87_1 _hy-button_mode_contrast_1xl2g87_277
                        _hy-button_size_m_1xl2g87_92 _hy-button_theme_light_1xl2g87_74
                        _hy-button_with-icon_1xl2g87_161 _hy-button_without-text_1xl2g87_134
                        hy-button_align-icon_left">
                  <svg enable-background="new 0 0 24 24" viewBox="0 0 24 24"
                       class="hy-icon hy-icon_size_m _hy-button__icon_1xl2g87_687">
                    <path d="m14.4 13.8c-1.3 0-2.5.9-2.9 2.1h-9.5v2.1h9.4c.4 1.2 1.6 2.1 2.9 2.1s2.5-.9 2.9-2.1h4.8v-2.1h-4.7c-.5-1.2-1.6-2.1-2.9-2.1zm-2.7-7.7c-.4-1.2-1.5-2.1-2.8-2.1-1.4 0-2.5.9-2.9 2.1h-4v2.1h4c.4 1.2 1.6 2.1 2.9 2.1s2.5-.9 2.9-2.1h10.2v-2.1z"></path>
                  </svg>
                </button>
              </div>
              <div :class="screenWidth <= 1000 && showFilters ? 'hy-display-none':'' " class="filter">
                <div class="hy-row hy-row_justify_between hy-row_align-items_center">
                  <h3 class="hy-h3 hy-h3_theme_light">筛选器</h3>
                  <span class="hy-row hy-row_align-items_center">
                    <button
                      @click="filterClose"
                      v-if="screenWidth <= 1000"
                      class="_hy-button_1xl2g87_1 _hy-button_mode_nude_1xl2g87_596
                       _hy-button_size_s_1xl2g87_104 _hy-button_theme_light_1xl2g87_74
                       _hy-button_with-icon_1xl2g87_161 _hy-button_without-text_1xl2g87_134
                       hy-button_align-icon_left filter__close">
                      <svg viewBox="0 0 24 24"
                           class="hy-icon hy-icon_size_m _hy-button__icon_1xl2g87_687">
                        <path d="M19.707 5.707l-1.414-1.414L12 10.586 5.707 4.293 4.293 5.707 10.586 12l-6.293 6.293 1.414 1.414L12 13.414l6.293 6.293 1.414-1.414L13.414 12l6.293-6.293z"></path>
                      </svg>
                    </button>
                  </span>
                </div>
                <div class="_hy-accordion__container_11gcjc2_1">
                  <div :key="type" v-for="type in 3" class="filter__block-wrapper">
                    <div :class="filters === type ? '_hy-accordion-item_open_11gcjc2_46' : ''"
                         class="_hy-accordion-item_11gcjc2_5 _hy-accordion-item_size_m_11gcjc2_131
                         hy-accordion-item_theme_light _hy-accordion-item_closed_11gcjc2_45 filter__block">
                      <div class="_hy-accordion-item__trigger_11gcjc2_11">
                        <div :class="filters === type ? '_hy-accordion-item__icon_open_11gcjc2_100' : ''" class="_hy-accordion-item__icon_11gcjc2_12" @click="onFilters(type)">
                          <svg viewBox="0 0 24 24" class="hy-icon hy-icon_size_m"><path d="M21 12a9 9 0 1 1-9-9 9 9 0 0 1 9 9zm-4.235-3.351l-1.414-1.414L12 10.585 8.635 7.222 7.221 8.635 10.586 12l-3.257 3.257 1.414 1.414L12 13.414l3.288 3.289 1.415-1.415L13.414 12z"></path></svg>
                        </div>
                        <div class="_hy-accordion-item__title_11gcjc2_10">
                          <div class="filter__title-block">
                            <div class="filter__title">语言</div>
                          </div>
                        </div>
                      </div>
                      <div class="_hy-accordion-item__content-container_11gcjc2_13"
                           :style="filters === type ?  '' : showFiltersCartoon">
                        <div class="_hy-accordion-item__content_11gcjc2_13">
                          <div class="filter__block-inner">
                            <label class="_hy-input_5l5z2x_1 _hy-input_theme_light_5l5z2x_14
                            _hy-input_size_s_5l5z2x_80 hy-input_empty hy-input_icon-position_right filter__search">
                              <div class="_hy-input__wrapper_5l5z2x_5">
                                <div class="_hy-input__field_5l5z2x_53">
                                  <input placeholder="搜索" class="_hy-input__inner_5l5z2x_92" type="search">
                                </div>
                              </div>
                            </label>
                            <div class="filter__list">
                              <div>
                                <label :key="index" v-for="index in 14" class="_hy-list-item_fhkgz_1
                                hy-list-item_placement_left _hy-list-item_mode_nude_fhkgz_77
                                _hy-list-item_size_m_fhkgz_65 _hy-list-item_theme_light_fhkgz_25">
                                <span class="_hy-checkbox_bmy2y_8 _hy-checkbox_theme_light_bmy2y_155
                                _hy-checkbox_size_m_bmy2y_17 _hy-list-item__icon_fhkgz_289
                                _hy-list-item__icon_placement_left_fhkgz_297">
                                  <input @change="onCheckbox($event)" class="_hy-checkbox__input_bmy2y_42" type="checkbox" :id="'checkboxId-' + index">
                                  <label :for="'checkboxId-' + index" class="_hy-checkbox__icon_bmy2y_53">
                                    <svg height="16" viewBox="0 0 16 16" width="16" xmlns="http://www.w3.org/2000/svg"
                                         class="_hy-checkbox__checkmark_bmy2y_126" data-test="checkmark"
                                    ><path d="m6.306 12.454-4.035-4.294 1.458-1.369 2.588 2.755 5.96-6.237 1.446 1.382z"></path>
                                    </svg>
                                  </label>
                                </span>
                                  <span class="_hy-list-item__content">JAVA</span>
                                </label>
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "Tag",
  data() {
    return {
      showFiltersCartoon: {
        height: '0px',
        overflow: 'hidden',
        display: 'none'
      },
      filters: undefined,
      showFilters: true,
      screenWidth: document.body.clientWidth,
    }
  },
  mounted() {
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth;
      })();
    }
  },
  methods: {
    filtersSwitch() {
      this.showFilters = false
    },
    onCheckbox(type) {
      console.log(type.target)
      console.log(type.target.checked)
    },
    filterClose() {
      this.showFilters = true
    },
    onFilters(type) {
      if (this.filters) {
        this.filters = undefined
      } else {
        this.filters = type
      }
    }
  }
}
</script>

<style scoped>

</style>