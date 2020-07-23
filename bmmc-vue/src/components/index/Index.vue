<template>
    <div>
        <!-- 登录后显示↓ -->
        <!-- <div class="before-header" ref="before_header"> -->
        <!-- <logined-header @backIndex="loginOut" v-if="this.$store.state.user.role === '普通用户'"></logined-header> -->
        <!-- </div> -->
        <!-- 登录后显示↑ -->
        <div class="head" ref="head">
            <v-header></v-header> 
        </div>
        <!-- <div class="logined" ref="logined"> -->
            <!-- <login @loginHidden="lhidden" ref="login"></login> -->
        <login ref="login"></login>
        <!-- </div> -->
        <div>
            <v-content></v-content>
        </div>
    </div>
</template>

<script>
import loginedHeader from './loginedHeader'
import vHeader from './Header'
import Login from './Login'
import vContent from './Content'
export default {
    data(){
        return{}
    },
    components:{
        vHeader,
        Login,
        vContent,
        loginedHeader
    },
    mounted(){
        // if(this.$store.state.user.role === '普通用户'){
        //     console.log(this.$store.state.user)
        //     this.lhidden()
        // }
    },
    methods:{
        lhidden(){
            console.log('ddddd')
            this.$refs.logined.style.display = "none"
            // this.$refs.before_header.style.display = "block"
            this.$refs.head.style.background = "#FCF9EE"
        },
        loginOut(){
            // this.$store.state.user = []
            // router.go(0)
            console.log('abcaaa')
            this.$refs.logined.style.display = "block"
            // this.$refs.before_header.style.display = "none"
            this.$refs.head.style.background = "#FFF"
        }
    }
}
</script>

<style scoped>
.before-header{
    height: 28px;
    background-color: rgb(146, 133, 109);
    display: none;
}
.before-header ul{
    list-style: none;
    margin: 0;
    padding: 0;
}
.before-header li{
    display: inline-block;
    font-size: 14px;
    color: rgb(248, 237, 217);
    padding: 0 7px;
}
.left-navi{
    float: left;
}
.right-pinfo{
    float: right;
}
/* .logined{
    display: none;
} */
.head{
    height: 87px;
    /* background-color: #FCF9EE; */
}
</style>