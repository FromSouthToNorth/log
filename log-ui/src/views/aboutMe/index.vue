<template>
  <section class="hy-section hy-about-section space-overview-container" >
    <div class="hy-container hy-aboutMe-container">
      <div class="hy-col-4 hy-col-md-6 hy-col-sm-12 hy-offset-top-48 about cards-animation_enabled cards-animation_animated">
        <a href="" class="space-banner cards-animation__banner">
          <div class="space-blue-card space-blue-card__blur space-banner__card">
            <div
              class="hy-row hy-row_size_s hy-row_align-items_center hy-row_direction_row hy-row-sm_align-items_start hy-row_nowrap hy-row-sm_direction_column space-banner__inner">
                            <div class="space-banner__circles">
                              <div class="space-banner__circles-item space-banner__circles-item_4"></div>
                              <div class="space-banner__circles-item space-banner__circles-item_3"></div>
                              <div class="space-banner__circles-item space-banner__circles-item_2"></div>
                              <div class="space-banner__circles-item space-banner__circles-item_1"></div>
                            </div>
              <div class="hy-col-inline hy-col-sm-12">
                <span
                  class="_main_cxywu_25 _sizeM_cxywu_68 _alignIconLeft_cxywu_46 _uppercase_cxywu_116 _sizeM_ahbmu2_17 space-banner__badge"
                  style="color: white; background-color: rgb(242,36,131)">
                  <svg-icon icon-class="stars" class-name="space-banner__badge-icon"/>
                  new
                </span>
              </div>
              <div class="hy-col-auto hy-offset-top-sm-12">
                <h2 class="hy-h3 hy-h3_theme_dark">My</h2>
              </div>
            </div>
          </div>
        </a>
        <div class="hy-row hy-row_size_xs hy-row_align-items_stretch hy-offset-top-24 cards-animation__cards">
          <div class="space-blue-card space-blue-card__blur hy-col-6 hy-col-md-12 space-video-preview">
            <div class="jb-video-preview space-video-preview__inner">
              <div class="video-preview">
                <img class="video-preview__img"
                     src="https://202007002.oss-cn-chengdu.aliyuncs.com/images/preview.svg?versionId=CAEQTRiBgMCk5LHg.hciIDU1NmJhOTUzOGM3ZjRjMmZiNzgyYzZlNmM3NWMxZmMy"
                     alt="">
              </div>
            </div>
          </div>
          <div class="hy-col-6 hy-col-md-12 hy-offset-top-md-12 space-card space-card_bg">
            <div class="space-blue-card space-blue-card__blur space-card__card">
              <div class="space-card__inner">
                <div class="space-card__content">
                  <figure class="space-card__top">
                    <img loading="eager" ref="spaceCardIcon" class="space-card__icon"
                         src="https://202007002.oss-cn-chengdu.aliyuncs.com/images/software-development.png?versionId=CAEQTRiBgICs5LHg.hciIGJiNWEzYTBjZmY3ZjRhY2RiMzJmYWUwOWJjM2Q4MWE0"
                         alt="">
                    <figcaption>
                      <h3 class="hy-h2 hy-h2_theme_dark space-card__title">Hy</h3>
                    </figcaption>
                  </figure>
                  <p class="hy-text-1 hy-text-2_theme_dark hy-offset-top-12 space-card__text">蒲公英和炊烟都在等你</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>

export default {
  name: "index",
  mounted() {
    let spaceCardIcon = this.$refs.spaceCardIcon;
    setInterval(() => {
      console.log();
      spaceCardIcon.style.transform = 'rotate(0deg) scale(1)'
    },10)
  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
.space-overview-container {
  min-height: calc(100vh - (129px + 215px));
  background: -webkit-gradient(linear,left top, left bottom,from(rgba(0,0,0,.7)),color-stop(5%, transparent),to(transparent)),url("https://202007002.oss-cn-chengdu.aliyuncs.com/images/space-overview-bg.svg?versionId=CAEQTRiBgIDdgeXg.hciIDA3YjZmZDA4Y2RlYTQyMmY4YzgzM2MxNTBiM2M0MmVh") top -72px center/100% no-repeat,-webkit-gradient(linear,left top, left bottom,from(rgba(0,71,255,0)),color-stop(20%, #021a72),color-stop(43%, rgba(2,26,114,.24)),color-stop(73%, rgba(0,71,255,.5)),to(rgba(0,71,255,0))),#000;
  background: linear-gradient(180deg,rgba(0,0,0,.7),transparent 5%,transparent),url("https://202007002.oss-cn-chengdu.aliyuncs.com/images/space-overview-bg.svg?versionId=CAEQTRiBgIDdgeXg.hciIDA3YjZmZDA4Y2RlYTQyMmY4YzgzM2MxNTBiM2M0MmVh") top -72px center/100% no-repeat,linear-gradient(180deg,rgba(0,71,255,0),#021a72 20%,rgba(2,26,114,.24) 43%,rgba(0,71,255,.5) 73%,rgba(0,71,255,0)),#000;
  background: -webkit-gradient(linear,left top, left bottom,from(rgba(0,0,0,.7)),color-stop(5%, transparent),to(transparent)),url("https://202007002.oss-cn-chengdu.aliyuncs.com/images/space-overview-bg.svg?versionId=CAEQTRiBgIDdgeXg.hciIDA3YjZmZDA4Y2RlYTQyMmY4YzgzM2MxNTBiM2M0MmVh") top -72px center/100% no-repeat,-webkit-gradient(linear,left top, left bottom,from(rgba(0,71,255,0)),color-stop(20%, #021a72),color-stop(43%, rgba(2,26,114,.24)),color-stop(73%, rgba(0,71,255,.5)),to(rgba(0,71,255,0))),var(--space-color-bg);
  background: linear-gradient(180deg,rgba(0,0,0,.7),transparent 5%,transparent),url("https://202007002.oss-cn-chengdu.aliyuncs.com/images/space-overview-bg.svg?versionId=CAEQTRiBgIDdgeXg.hciIDA3YjZmZDA4Y2RlYTQyMmY4YzgzM2MxNTBiM2M0MmVh") top -72px center/100% no-repeat,linear-gradient(180deg,rgba(0,71,255,0),#021a72 20%,rgba(2,26,114,.24) 43%,rgba(0,71,255,.5) 73%,rgba(0,71,255,0)),var(--space-color-bg);
}
.space-blue-card {
  overflow: hidden;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border: 1px solid hsla(0,0%,100%,.2);
  border: 1px solid var(--hy-color-white-20);
  border-radius: 8px;
  background-image: linear-gradient(145deg,rgba(15,54,195,.8),rgba(15,54,195,.4));
  -webkit-box-shadow: 0 100px 80px rgb(0 17 79 / 16%), 0 30px 24px rgb(0 17 79 / 10%), 0 12px 10px rgb(0 17 79 / 8%), 0 4px 3px rgb(0 17 79 / 5%);
  box-shadow: 0 100px 80px rgb(0 17 79 / 16%), 0 30px 24px rgb(0 17 79 / 10%), 0 12px 10px rgb(0 17 79 / 8%), 0 4px 3px rgb(0 17 79 / 5%);
}

.space-blue-card__blur {
  -webkit-backdrop-filter: blur(50px);
  backdrop-filter: blur(50px);
}

.space-banner__card {
  position: relative;
  padding: 15px;
  -webkit-transition: border .1s ease-in 0s;
  transition: border .1s ease-in 0s;
}

.hy-row_nowrap {
  -ms-flex-wrap: nowrap;
  flex-wrap: nowrap;
}

.hy-row_direction_row {
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -ms-flex-direction: row;
  flex-direction: row;
}

.space-banner {
  display: block;
}

.space-banner__inner {
  position: relative;
  z-index: 1;
}

.space-banner__circles {
  position: absolute;
  top: 50%;
  left: 60%;
  opacity: .5;
  width: 60%;
  padding-top: 60%;
  -webkit-transition: opacity .2s ease-in 0s;
  transition: opacity .2s ease-in 0s;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

.space-banner__circles:hover {
  opacity: 1;
}

.space-banner__circles > * {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 17%;
  padding-top: 17%;
  border-radius: 50%;
  background-color: hsla(0,0%,100%,.1);
  -webkit-transform: translate(-50%, -50%) scale(1);
  transform: translate(-50%, -50%) scale(1);
}

.cards-animation_enabled .space-banner__circles-item_1, .cards-animation_enabled .space-banner__circles-item_2, .cards-animation_enabled .space-banner__circles-item_3 {
  -webkit-transform: translate(-50%, -50%) scale(1);
  transform: translate(-50%, -50%) scale(1);
}

.cards-animation_animated .space-banner__circles-item_3 {
  -webkit-animation: zoom3 1.2s ease-out 1.2s 1 forwards;
  animation: zoom3 1.2s ease-out 1.2s 1 forwards;
}

.cards-animation_animated .space-banner__circles-item_2 {
  -webkit-animation: zoom2 1s ease-out 1s 1 forwards;
  animation: zoom2 1s ease-out 1s 1 forwards;
}

.cards-animation_animated .space-banner__circles-item_1 {
  -webkit-animation: zoom1 .8s ease-out .8s 1 forwards;
  animation: zoom1 .8s ease-out .8s 1 forwards;
}

@-webkit-keyframes zoom1 {
  0% {
    -webkit-transform: translate(-50%, -50%) scale(1);
    transform: translate(-50%, -50%) scale(1)
  }
  to {
    -webkit-transform: translate(-50%, -50%) scale(5.8);
    transform: translate(-50%, -50%) scale(5.8)
  }
}

@keyframes zoom1 {
  0% {
    -webkit-transform: translate(-50%, -50%) scale(1);
    transform: translate(-50%, -50%) scale(1)
  }
  to {
    -webkit-transform: translate(-50%, -50%) scale(5.8);
    transform: translate(-50%, -50%) scale(5.8)
  }
}

@-webkit-keyframes zoom2 {
  0% {
    -webkit-transform: translate(-50%, -50%) scale(1);
    transform: translate(-50%, -50%) scale(1)
  }
  to {
    -webkit-transform: translate(-50%, -50%) scale(4.2);
    transform: translate(-50%, -50%) scale(4.2)
  }
}

@keyframes zoom2 {
  0% {
    -webkit-transform: translate(-50%, -50%) scale(1);
    transform: translate(-50%, -50%) scale(1)
  }
  to {
    -webkit-transform: translate(-50%, -50%) scale(4.2);
    transform: translate(-50%, -50%) scale(4.2)
  }
}

@-webkit-keyframes zoom3 {
  0% {
    -webkit-transform: translate(-50%, -50%) scale(1);
    transform: translate(-50%, -50%) scale(1)
  }
  to {
    -webkit-transform: translate(-50%, -50%) scale(2.2);
    transform: translate(-50%, -50%) scale(2.2)
  }
}

@keyframes zoom3 {
  0% {
    -webkit-transform: translate(-50%, -50%) scale(1);
    transform: translate(-50%, -50%) scale(1)
  }
  to {
    -webkit-transform: translate(-50%, -50%) scale(2.2);
    transform: translate(-50%, -50%) scale(2.2)
  }
}

.space-banner__badge-icon {
  width: 20px;
  height: 24px;
  margin-right: 12px;
}

._main_cxywu_25 {
  position: relative;
  display: -webkit-inline-box;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border: 1px solid transparent;
  outline: none;
  text-align: center;
  white-space: nowrap;
  text-decoration: none;
  background: transparent;
}

._alignIconLeft_cxywu_46 {
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -ms-flex-direction: row;
  flex-direction: row;
}

._sizeM_cxywu_68 {
  padding: 5px 11px;
}

._uppercase_cxywu_116 {
  text-transform: uppercase;
  font-weight: 530;
}

._sizeM_ahbmu2_17 {
  border-radius: 6px;
}

.space-banner__badge {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-shadow: 0 100px 80px rgb(0 17 79 / 16%), 0 30px 24px rgb(0 17 79 / 10%), 0 12px 10px rgb(0 17 79 / 8%), 0 4px 3px rgb(0 17 79 / 5%);
  box-shadow: 0 100px 80px rgb(0 17 79 / 16%), 0 30px 24px rgb(0 17 79 / 10%), 0 12px 10px rgb(0 17 79 / 8%), 0 4px 3px rgb(0 17 79 / 5%);
}

.hy-row_align-items_stretch {
  -webkit-box-align: stretch;
  -ms-flex-align: stretch;
  align-items: stretch;
}

.space-blue-card {
  overflow: hidden;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  border-radius: 8px;
  -webkit-box-shadow: 0 100px 80px rgb(0 17 79 / 16%), 0 30px 24px rgb(0 17 79 / 10%), 0 12px 10px rgb(0 17 79 / 8%), 0 4px 3px rgb(0 17 79 / 5%);
  box-shadow: 0 100px 80px rgb(0 17 79 / 16%), 0 30px 24px rgb(0 17 79 / 10%), 0 12px 10px rgb(0 17 79 / 8%), 0 4px 3px rgb(0 17 79 / 5%);
}

.space-blue-card__blur {
  -webkit-backdrop-filter: blur(50px);
  backdrop-filter: blur(50px);
}

.cards-animation_enabled .cards-animation__cards > :nth-child(-n+2) {
  opacity: 0;
  -webkit-transition: opacity .8s ease-out .6s, -webkit-transform .8s ease-out .6s;
  transition: opacity .8s ease-out .6s, -webkit-transform .8s ease-out .6s;
  transition: transform .8s ease-out .6s, opacity .8s ease-out .6s;
  transition: transform .8s ease-out .6s, opacity .8s ease-out .6s, -webkit-transform .8s ease-out .6s;
  -webkit-transform: translateY(-10px);
  transform: translateY(-10px);
}

.cards-animation_animated .cards-animation__cards > :nth-child(n) {
  opacity: 1;
  -webkit-transform: translate(0);
  transform: translate(0);
}

.video-preview {
  position: relative;
  min-height: 1px;
  cursor: pointer;
}

.space-video-preview .video-preview, .space-video-preview__inner {
  height: 100%;
}

.video-preview__img {
  max-width: 100%;
}

.space-video-preview .video-preview .video-preview__img {
  width: 100%;
}

.space-card__card {
  height: 100%;
}

.space-card__inner {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  height: 100%;
  padding: 16px;
}

.space-card_bg .space-card__content {
  position: relative;
  z-index: 1;
}

.space-card_bg .space-card__icon {
  position: absolute;
  z-index: -1;
  top: -55%;
  left: 30%;
  width: 110%;
  max-width: none;
}

.space-card_bg .space-card__text, .space-card_bg .space-card__title {
  position: relative;
  z-index: 1;
  width: 60%;
}

.cards-animation_enabled .space-card_bg .space-card__icon {
  -webkit-transition: -webkit-transform 1.2s ease-out .8s;
  transition: -webkit-transform 1.2s ease-out .8s;
  transition: transform 1.2s ease-out .8s;
  transition: transform 1.2s ease-out .8s, -webkit-transform 1.2s ease-out .8s;
  transition: transform 1.2s ease-out .8s,-webkit-transform 1.2s ease-out .8s;
  -webkit-transform: rotate(-60deg) scale(1.4);
  transform: rotate(-60deg) scale(1.4);
}

//.cards-animation_animated .space-card_bg .space-card__icon {
//  -webkit-transform: rotate(0deg) scale(1);
//  transform: rotate(0deg) scale(1)
//}

</style>