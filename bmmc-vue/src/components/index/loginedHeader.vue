<template>
    <div>
        <!-- 登录后显示↑ -->
        <div class="before-header" ref="before_header">
            <div class="left-navi">
                <ul>
                    <li @click="go(1)">芝士</li>
                    <li @click="go(2)">读书</li>
                    <li @click="go(3)">电影</li>
                    <li @click="go(4)">音乐</li>
                    <li>创作</li>
                </ul>
            </div>
            <div class="right-pinfo">
                <ul>
                    <!-- <li>提醒</li> -->
                    <li>
                        <el-dropdown @command="handleCommand">
                        <span class="el-dropdown-link" v-show="$store.state.user.username">
                            {{$store.state.user.username}}的账号<i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item command="myorder">我的订单</el-dropdown-item>
                            <el-dropdown-item command="accountmng">账号管理</el-dropdown-item>
                            <el-dropdown-item command="myshelf">我的书架</el-dropdown-item>
                            <el-dropdown-item command="loginout">退出</el-dropdown-item>
                        </el-dropdown-menu>
                        </el-dropdown>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 登录后显示↑ -->
    </div>
</template>

<script>
export default {
    data(){
        return{}
    },
    mounted(){
    },
    methods:{
        // 用户名下拉菜单选择事件
        handleCommand(command) {
            if (command == 'loginout') {
                console.log(this.$store.state.user)
                this.$store.commit('login', [])
                console.log(this.$store.state.user)
                // this.$router.push('/login');
                // this.$emit('backIndex')
                this.$router.replace({path: '/index'})
            }else if (command == 'myorder') {
                const {href} = this.$router.resolve({path: '/order',})
                window.open(href, '_blank')
            }else if (command == 'myshelf') {
                const {href} = this.$router.resolve({path: '/bookshelf',})
                window.open(href, '_blank')
            }else if (command == 'accountmng') {
                const {href} = this.$router.resolve({path: '/accountmng',})
                window.open(href, '_blank')
            }
        },
        go(id){
            if(id === 1){
                const {href} = this.$router.resolve({ path: '/index' })
                window.open(href, '_blank')
            }
            if(id === 2){
                const {href} = this.$router.resolve({ path: '/readbookindex' })
                window.open(href, '_blank')
            }
            if(id === 3){
                const {href} = this.$router.resolve({ path: '/movieindex' })
                window.open(href, '_blank')
            }
            if(id === 4){
                const {href} = this.$router.resolve({ path: '/musicindex' })
                window.open(href, '_blank')
            }
        },
    },
}
</script>

<style scoped>
.before-header{
    height: 28px;
    background-color: rgb(146, 133, 109);
    /* display: none; */
}
.before-header ul{
    list-style: none;
    margin: 0;
    padding: 0;
}
.before-header li{
    display: inline-block;
    font-size: 13px;
    color: rgb(248, 237, 217);
    padding: 0 7px;
}
.left-navi{
    float: left;
}
.right-pinfo{
    float: right;
}
.el-dropdown-link {
    font-size: 13px;
    cursor: pointer;
    color: rgb(248, 237, 217);
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>