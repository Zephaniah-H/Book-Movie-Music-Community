// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '../theme/index.css'
import store from './store'

import VideoPlayer from 'vue-video-player'
Vue.use(VideoPlayer);

import VueQuillEditor from 'vue-quill-editor' // 调用富文本编辑器
import 'quill/dist/quill.snow.css' // 富文本编辑器外部引用样式  三种样式三选一引入即可
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.bubble.css'
// import * as Quill from 'quill'; // 富文本基于quill
// import Quill from 'quill'
// Vue.use(Quill)
Vue.use(VueQuillEditor)

// 第一个是videoJs的样式，后一个是vue-video-player的样式
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')

//引入 utls.js
// import {domain} from './utls.js'

//引入 common.js
import common from './common.js'
Vue.prototype.common = common

// 设置反向代理
// var axios = require('axios')
//后台接口地址
// axios.defaults.baseM1URL = domain.Base_M1_URL
//网易云音乐接口地址
// axios.defaults.baseM2URL = domain.Base_M2_URL
// 全局注册，之后可在其他组件中通过 this.$axios 发送数据
// Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)

//首先判断访问的路径是否需要登录，如果需要，判断 store 里有没有存储 user 的信息，如果存在，则放行，
//否则跳转到登录页面，并存储访问的页面路径（以便在登录后跳转到访问页）。
router.beforeEach((to,from,next) => {
  if(to.meta.requireAuth){
    if(store.state.user.username){
      next()
    }else{
      next({
        path:'login',
        query:{redirect:to.fullPath}
      })
    }
  }else{
    next()
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store,
  components: { App },
  template: '<App/>'
})
