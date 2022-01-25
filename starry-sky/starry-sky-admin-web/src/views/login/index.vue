<template>
  <div style="width: 100%; height: 100%; top: 20%; position: fixed;">
    <div id="login_box">
      <h2>太极系统后台登录</h2>
      <div id="input_box">
        <input type="text" placeholder="请输入用户名"  v-model="userName">
      </div>
      <div class="input_box">
        <input type="password" placeholder="请输入密码" v-model="password">
      </div>
      <div class="input_box">
        <input type="text" placeholder="请输验证码" v-model="verifyCode">
      </div>
      <button @click="login">登录</button>
      <br>
    </div>
  </div>
</template>

<script>
  import {doLogin} from "@/api/user";
  import commonConstants from "@/utils/constants/CommonConstants";


  export default {
    name: 'login_box',
    data() {
      return {
        bodyBgImage: 'url(' + require('../../assets/login/login-bg.jpg') + ')',
        userName: "wax",
        password: "123456",
        verifyCode: "6666",
      }
    },
    methods: {
      login() {
        // this.$store.commit('tokenStore/setToken',"aaaaaaaaaaa");
        this.$logUtils.printLog("login getToken :" + this.$store.getters.getToken);
        this.$logUtils.printLog("查看store ");
        this.$logUtils.printLog(this.$store);
        let dataFrom = {};
        dataFrom.username = this.userName;
        dataFrom.password = this.password;
        dataFrom.code = this.verifyCode;
        doLogin(dataFrom).then((res)=>{
          if (res.status === 200){
            if (res.data.code == 0){
              this.$logUtils.printLog(res.data.data);
              let token = res.data.data.token;
              this.$logUtils.printLog(token);
              if (typeof token != "undefined" && token.length > 0){
                this.$store.commit('setToken',token);
                this.$logUtils.printLog(this.$store.getters.getToken);
                this.$router.push({path: this.customRouterConstants.pathWelCome()});
              }
            }else {
              this.$alert(res.data.msg);
            }
          }else{
            this.$alert(commonConstants.getNetworkErrorMsg());
          }
        }).catch((err) =>{
          this.$logUtils.printLog(err);
          this.$alert(commonConstants.getNetworkErrorMsg());
        });
      },
      setBodyBackGround(){
        document.body.style.backgroundSize = '100%';
        document.body.style.backgroundImage = this.bodyBgImage
      },
      clearBodyBackGround(){
        document.body.style.backgroundImage = ''
      }
    },
    mounted() {
      this.setBodyBackGround()
    },

    created() {
      this.$logUtils.printLog("login created run sessionStorage:\t");
      this.$logUtils.printLog(JSON.parse(sessionStorage.getItem(commonConstants.getSessionStoresKey())));
      sessionStorage.getItem(commonConstants.getSessionStoresKey()) && this.$store.replaceState(JSON.parse(sessionStorage.getItem(commonConstants.getSessionStoresKey())));
    },
    beforeDestroy() {
      this.clearBodyBackGround()
    }
  }
</script>

<style scoped>

  .login-bg {
    display: flex;
    justify-content: center;
    align-items: center;
    background: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg') no-repeat;
    background-size: 100% 100%;
    height: 100%;
  }

  #login_box {
    width: 20%;
    height: 400px;
    background-color: #00000060;
    margin: auto;
    text-align: center;
    border-radius: 10px;
    padding: 50px 50px;
    /*top: 25%;*/
    /*position:relative;*/
  }

  h2 {
    color: #ffffff90;
    margin-top: 5%;
  }

  #input-box {
    margin-top: 5%;
  }

  span {
    color: #fff;
  }

  input {
    border: 0;
    width: 60%;
    font-size: 15px;
    color: #fff;
    background: transparent;
    border-bottom: 2px solid #fff;
    padding: 5px 10px;
    outline: none;
    margin-top: 10px;
  }

  button {
    margin-top: 50px;
    width: 60%;
    height: 30px;
    border-radius: 10px;
    border: 0;
    color: #fff;
    text-align: center;
    line-height: 30px;
    font-size: 15px;
    background-image: linear-gradient(to right, #30cfd0, #330867);
  }

  #sign_up {
    margin-top: 45%;
    margin-left: 60%;
  }

  a {
    color: #b94648;
  }

</style>
