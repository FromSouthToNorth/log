<template>
<section class="promo">
  <div class="container">
    <div class="promo__row">
      <a href="" class="promo__img" :style="{backgroundImage: `url(${topArticle.articleCover})`}"></a>
      <div class="promo__text">
        <h2>
          <a>
            {{ topArticle.articleTitle }}
          </a>
        </h2>
        <p>
          {{ topArticle.remark }}
        </p>
      </div>
    </div>
  </div>
</section>
</template>

<script>
export default {
  name: "Promo",
  props: {
    topArticle: {
      type: Object
    }
  },
  data() {
    return {
      promoImgStyleUrl: 'https://blog.jetbrains.com/wp-content/uploads/2021/12/Blog_Featured_image_1280x600-1.png'
    }
  }
}
</script>

<style lang="scss" scoped>
.promo {
  font-family: "JetBrains Mono";
  padding: 32px 0 80px 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 15px;
}

.promo__row {
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  position: relative;
  overflow: hidden;
}

.promo__img {
  background-position: 0 0;
  background-repeat: no-repeat;
  background-size: cover;
  min-height: 153px;
  width: 100%;
  height: 42vw;
  max-height: 500px;
  overflow: hidden;
  border-radius: 10px;
}

.promo__text {
  font-size: 19px;
  line-height: 1.474;
  font-weight: 350;
  padding: 16px 0;

  & h2 {
    font-size: 20px;
    line-height: 1.4;
    font-weight: 500;
    margin-top: 0;
    margin-bottom: 16px;
    & a {
      color: #27282C;
    }
  }

  & p {
    float: none;
    position: static;
    margin: 0 0 16px;
    opacity: .7;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    font-size: 18px;
  }
}

@media (min-width: 640px) {
  .promo__text {
    width: 75%;
    padding: 32px 0;
  }
}

@media (min-width: 641px) {
  .container {
    padding: 0 32px;
  }

  .promo__img {
    height: 32vh;
    min-height: 285px;
  }
}

@media (min-width: 1024px) {
  .promo__row {
    align-items: center;
    flex-direction: row;
  }

  .promo__img {
    width: 48.34%;
    min-height: 218px;
    height: 26vh;
  }

  .promo__text {
    width: 51.66%;
    max-width: 460px;
    padding: 0 32px;
  }
}

@media (min-width: 1200px) {
  .promo__img {
    width: 58.73%;
    min-height: 379px;
  }

  .promo__text {
    width: 41.27%;
    max-width: 420px;
  }
}
</style>