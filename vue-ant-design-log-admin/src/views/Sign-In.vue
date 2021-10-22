<!-- 
	This is the sign in page, it uses the dashboard layout in: 
	"./layouts/Default.vue" .
 -->

<template>
  <div class="sign-in">

    <a-row type="flex" :gutter="[24,24]" justify="space-around" align="middle">

      <!-- Sign In Form Column -->
      <a-col :span="24" :md="12" :lg="{span: 12, offset: 0}" :xl="{span: 6, offset: 2}" class="col-form">
        <h1 class="mb-15">登 录</h1>
        <h5 class="font-regular text-muted">输入您的用户名和密码以登录</h5>

        <!-- Sign In Form -->
        <a-form
            id="components-form-demo-normal-login"
            :form="form"
            class="login-form"
            ref="formLogin"
            @submit="handleSubmit"
            :hideRequiredMark="true"
        >
          <a-form-item class="mb-10" label="用户名" :colon="false">
            <a-input
                v-decorator="['username', validatorRules.username]"
                placeholder="username"/>
          </a-form-item>
          <a-form-item class="mb-5" label="密码" :colon="false">
            <a-input-password
                v-decorator="['password', validatorRules.password]"
                type="password"
                placeholder="Password"/>
          </a-form-item>
          <a-form-item class="mb-10" label="验证码" :colon="false">
            <div class="login-code">
              <a-input
                  v-decorator="['code', validatorRules.code]"
                  placeholder="Verification code"/>
              <img :src="codeUrl" @click="getCode" class="login-code-img" />
            </div>
          </a-form-item>
          <a-form-item class="mb-5">
            <a-switch v-model="loginForm.rememberMe"/>
            记住账号
          </a-form-item>
          <a-form-item>
            <a-button type="primary" block html-type="submit" class="login-form-button">
              O K
            </a-button>
          </a-form-item>
        </a-form>
        <!-- / Sign In Form -->

        <p class="font-semibold text-muted">没有账户？
          <router-link to="/sign-in" class="font-bold text-dark">注册</router-link>
        </p>
      </a-col>
      <!-- / Sign In Form Column -->

      <!-- Sign In Image Column -->
      <a-col :span="24" :md="12" :lg="12" :xl="12" class="col-img">
        <img src="images/img-signin.jpg" alt="">
      </a-col>
      <!-- Sign In Image Column -->

    </a-row>

  </div>
</template>

<script>
import { getCodeImages } from "@/api/login";
import { mapActions } from "vuex";

export default ({
  data() {
    return {
      // 登录用的验证码 src
      codeUrl: "",
      // form: this.$form.createForm(this),
      validatorRules: {
        username:{rules: [{ required: true, message: "请输入用户名!" }]},
        password:{rules: [{ required: true, message: "请输入密码!" }]},
        code:{rules: [{ required: true, message: "请输入验证码！" }]}
      },
      loginForm: {
        username: undefined,
        password: undefined,
        rememberMe: true,
        code: "",
        uuid: ""
      },
      // 验证码开关
      captchaOnOff: true
    }
  },
  beforeCreate() {
    // Creates the form and adds to it component's "form" property.
    this.form = this.$form.createForm(this, {name: 'normal_login'});
  },
  created() {
    this.getCode();
  },
  methods: {
    ...mapActions(["Login"]),
    /** 获取登录的验证码 */
    getCode() {
      getCodeImages().then(result => {
        this.captchaOnOff = result.captchaOnOff ? result.captchaOnOff : true;
        if (this.captchaOnOff) {
          this.codeUrl = "data:image/gtf;base64," + result.img;
          this.loginForm.uuid = result.uuid;
        }
      })
    },
    // Handles input validation after submission.
    handleSubmit(e) {
      e.preventDefault();
      const oneself = this;
      this.form.validateFields((err, values) => {
        this.loginForm.username = values.username;
        this.loginForm.password = values.password;
        this.loginForm.code = values.code;
        if (!err) {
          oneself.Login(oneself.loginForm).then(() => {
            this.$router.push({path: "/"}).catch(() => {});
          })
          .catch(() => {
            this.getCode();
          })
        }
      });
    },
  },
})

</script>

<style lang="scss">
body {
  background-color: #ffffff;
  .login-code {
    display: flex;
    flex-flow: row wrap;
    input {
      width: 70%;
    }
    .login-code-img {
      margin-left: 20px;
      height: 40px;
    }
  }
}
</style>