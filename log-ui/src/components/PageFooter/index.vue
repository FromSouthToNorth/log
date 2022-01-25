<template>
<div class="page__footer">
  <footer class="hy-footer hy-footer_mode_full hy-footer_theme_dark jb-footer">
    <div class="hy-container">
<!--      <FooterCatalog :catalog="footerCatalog" />-->
      <div class="hy-footer__main">
        <div class="hy-footer__social hy-offset-top-24 hy-offset-top-sm-32">
          <div class="hy-social-list hy-row hy-row_size_xs">
            <IconButton :key="index" :icon-button="item" v-for="(item, index) in iconButton" />
          </div>
        </div>
        <div class="hy-footer__legal hy-offset-top-24 hy-offset-top-sm-12">
          <div class="hy-row hy-row_size_s hy-text-3 hy-text-3_theme_dark">
            <a href="/" class="hy-link_hardness_banner hy-link_theme_dark hy-col-inline">
              <svg-icon icon-class="main" />
              蒲公英和炊烟都在等你
            </a>
          </div>
        </div>
        <div class="hy-footer__copyright hy-offset-top-24 hy-offset-top-sm-12">
          <a href="http://www.beian.miit.gov.cn/" class="hy-text-3 hy-text-3_hardness_pale hy-text-3_theme_dark">蜀ICP备20019889号</a>
        </div>
        <div class="hy-footer__motto hy-offset-top-24 hy-offset-top-sm-0">
          <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=51012202000694" class="hy-text-3 hy-text-3_hardness_pale hy-text-3_theme_dark">
            <img class="ali-report-img" src="../../assets/image/for-the-record-icon.png" alt=""> 川公网安备 51012202000694号
          </a>
        </div>
      </div>
    </div>
  </footer>
</div>
</template>

<script>
import FooterCatalog from "@/components/FooterCatalog";
import IconButton from "@/components/IconButton";
export default {
  name: 'PageFooter',
  components: {IconButton, FooterCatalog},
  data () {
    return {
      footerCatalog: [
        {
          title: '友情链接',
          catalog: [
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},

          ]
        },
        {
          title: '友情链接',
          catalog: [
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
          ]
        },
        {
          title: '友情链接',
          catalog: [
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
          ]
        },
        {
          title: '友情链接',
          catalog: [
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
            {title: 'IDE', link: 'https://www.jetbrains.com/zh-cn/idea/'},
          ]
        }
      ],
      iconButton: [
        {link: '', iconClass: 'github-fill', title: 'github'},
        {link: '', iconClass: 'wechat-fill', title: 'wechat'},
        {link: '', iconClass: 'qq', title: 'qq'},
      ]
    }
  }
}
</script>

<style scoped>

</style>