<template>
    <div class="head">
        <div class="logo1"><font class="zhi" @click="go(1)">芝士</font></div>
        <div class="logo2"  @click="go(1)">
            <span class="z1">z</span>
            <span class="z2">h</span>
            <font class="z3">i</font>
            <font class="z1">s</font>
            <font class="z2">h</font>
            <font class="z3">i</font>
        </div>
        <div class="search">
            <el-input class="search1" placeholder="书籍、电影、音乐" suffix-icon="el-icon-search">
            </el-input>
        </div>
        <div class="navibar">
            <ul>
                <li><font class="barItem1" @click="go(2)">读书</font></li>
                <li><font class="barItem2" @click="go(3)">电影</font></li>
                <li><font class="barItem3" @click="go(4)">音乐</font></li>
                <li><font class="barItem4">阅读</font></li>
                <li><font class="barItem5">创作</font> </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name:'Header',
    data(){
        return{

        }
    },
    methods:{
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
    }
}
</script>

<style scoped>
.head{
    width: 1080px;
    height: 42px;
    margin: 0 auto 25px;
    padding-top: 20px;
    padding-bottom: 20px;
}
.logo1,.search{
    float: left;
    padding-right: 5px;
}
.logo2{
    float: left;
    padding-right: 30px;
    padding-top: 10px;
}
.search{
    padding-top: 10px;
}
.navibar{
    float: right;
}
.search1{
    width: 130%;
}
.zhi{
    color: gold;
    font-size: 25px;
    font-weight: bold;
}
.z1{
    color: gold;
    font-size: 25px;
    font-weight: 400;
    font-family: fantasy;
}
.z2{
    color:salmon;
    font-size: 25px;
    font-weight: 400;
    font-family: fantasy;
}
.z3{
    color:lightgreen;
    font-size: 25px;
    font-weight: 400;
    font-family: fantasy;
}
ul{
    list-style: none;
    margin-bottom: 10px;
    margin-top: 10px;
}
li{
    display: inline-block;
}
.barItem1{
    color: black;
    font-size: 20px;
    font-weight: bold;
    padding-right: 12px;
}
.barItem2{
    color:rgb(71, 140, 197);
    font-size: 20px;
    font-weight: bold;
    padding-right: 12px;
    font-family: "Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu;
}
.barItem3{
    color:rgb(241, 148, 41);
    font-size: 20px;
    font-weight: bold;
    padding-right: 12px;
}
.barItem4{
    color:rgb(209, 59, 49);
    font-size: 20px;
    font-weight: bold;
    padding-right: 12px;
}
.barItem5{
    color:rgb(54, 179, 37);
    font-size: 20px;
    font-weight: bold;
    padding-right: 12px;
}
</style>