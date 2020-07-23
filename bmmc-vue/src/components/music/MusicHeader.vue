<template>
    <div class="conclude-read-header"><!-- 整块logo+sousuo -->
        <div class="read-header"><!-- 内容 -->
            <div class="read-logo"><font class="zhidu">芝士音乐</font></div><!-- logo -->
            <div class="read-search">
                <el-input class="read-search1" placeholder="歌曲名、歌手" suffix-icon="el-icon-search" v-model="input1">
                </el-input>
            </div><!-- 搜索 -->
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{}
    }
}
</script>

<style scoped>
.conclude-read-header{
    background-color: #FCF9EE;
}
.read-header{
    width: 1040px;
    height: 74px;
    margin: auto;
}
.read-logo,.read-search{
    float: left;
    padding-top: 20px;
}
.read-logo{
    width: 120px;
    height: 53px;
    margin-right: 35px;
}
.zhidu{
    color: rgb(241, 148, 41);
    font-size: 28px;
    font-weight: bold;
}
.read-search1{
    width: 230%;
}
</style>