<template>
    <div class="entirelogin">
        <div class="zslogin"><img :src="zslogin" width="420px" height="250px"></div>
        <div class="login">
            <el-tabs v-model="activeName">
            <el-tab-pane label="注册" name="first">
               <div class="msgframe1">
                   <font style="font-size:12px">请仔细阅读</font>&nbsp;<el-link :underline="false" type="warning" style="font-size:12px;margin-top:-1px">芝士使用协议、隐私政策</el-link>
               </div>
               <div class="msgframe">
                  <el-input placeholder="手机号" v-model="msgLoginForm.phone" class="input-with-select">
                    <el-select v-model="msgLoginForm.areacode" slot="prepend" placeholder="请选择">
                      <el-option selected label="+86　　中国大陆" value="1"></el-option>
                      <el-option label="+852　　中国香港" value="2"></el-option>
                      <el-option label="+853　　中国澳门" value="3"></el-option>
                      <el-option label="+886　　中国台湾" value="4"></el-option>
                      <el-option label="+81　　日本" value="5"></el-option>
                      <el-option label="+82　　韩国" value="6"></el-option>
                      <el-option label="..." value="7"></el-option>
                    </el-select>
                  </el-input>
               </div>
               <div class="msgframe">
                   <el-input v-model="msgLoginForm.yanzhengma" placeholder="验证码" class="input-with-select yanzm1"></el-input>
                   <el-link class="yanzm" :underline="false" type="warning">获取验证码</el-link>
               </div>
               <div>
                   <el-button type="primary" @click="msgLogin">登录芝士</el-button>
               </div>
            </el-tab-pane>
            <el-tab-pane label="密码登录" name="second">
               <div class="msgframe1">
                   <el-link class="zmima" :underline="false" type="warning">找回密码</el-link>
               </div>
               <div class="msgframe">
                   <el-input v-model="pswLoginForm.phone" placeholder="手机号" class="input-with-select"></el-input>
               </div>
               <div class="msgframe">
                   <el-input type="password" v-model="pswLoginForm.password" placeholder="密码" class="input-with-select"></el-input>
               </div>
               <div>
                   <el-button type="primary" @click="pswLogin">登录芝士</el-button>
               </div>
            </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
import zslogin from '../../assets/zslogin.png'
export default {
    name:'Login',
    data(){
        return{
            zslogin,
            activeName:'second',
            msgLoginForm:{
                phone:'',
                yanzhengma:'',
                areacode:'+86　　中国大陆'
            },
            pswLoginForm:{
                phone:'13888888888',
                password:'123456'
            },
            user:[]
        }
    },
    methods:{
        msgLogin(){
        },
        pswLogin () {
        var _this = this
        // console.log(this.$store.state)
        let url = "/findByPhonePsw"
        let params = {
            phone: this.pswLoginForm.phone,
            password: this.pswLoginForm.password
        }
        this.common.requestData(url,params).then(resp => {
            if (resp && resp.status === 200) {
              _this.user = resp.data
              _this.$store.commit('login', _this.user)
            //   var path = this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
              if(_this.$store.state.user.role == '普通用户'){
                this.$router.replace({path: '/loginedindex'})
              }
            }
          })
      }
    }
}
</script>

<style scoped>
.entirelogin{
    background-color: #F9F2D6;
    padding: 25px 0;
    height: 205px;
}
.zslogin{
    float: left;
    margin-left: 20%;
    margin-top: -22px;
}
.login{
    width: 250px;
    float: left;
    margin-left: 11%;
}
.msgframe{
    margin-bottom: 10px;
}
.msgframe1{
    height: 30px;
}
.zmima{
    float: right;
}
.yanzm{
    position: absolute;
    top: 75px;
    left: 170px;
    z-index: 2;
    /* left: -90px; */
}
.yanzm1{
    position: relative;
    z-index: 1;
}
</style>
<style>
.el-tabs__item:hover {
    color: black;
    cursor: pointer;
}
.el-tabs__item.is-active {
    color: black;
    font-weight: bold;
}
  .el-select {
    width: 80px;
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
  }
  .input-with-select,.login .el-button{
      width: 250px;
  }
.el-tabs__nav{
      margin-left: 30px;
  }
.el-input__inner{ /* 输入框的宽度 */
    height: 30px;
}
</style>