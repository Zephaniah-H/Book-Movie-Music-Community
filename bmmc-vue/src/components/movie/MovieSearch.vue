<template>
    <div>
        <logined-header></logined-header>
        <movie-header></movie-header>
        <div style="width: 1040px;margin: auto;">
            <h1 style="font-size:26px;margin-top:40px;">搜索&nbsp;活着</h1>
            <div style="margin-top:40px;">
                <ul>
                    <li v-for="item in 1" :key="item.index">
                        <div class="read-content"><!-- 书的封面+信息+评分 -->
                            <div class="read-cover">
                                <img src="http://localhost:8888/api/bookCover/2.jpg" alt="活着">
                            </div>
                            <div class="read-info">
                                <div style="margin-bottom:5px"><a href="javascript:void(0)">活着</a></div>
                                <div>
                                    <el-rate
                                    style="float:left;"
                                    v-model="value"
                                    disabled
                                    text-color="#ff9900"
                                    score-template="{value}">
                                    </el-rate>
                                    <span style="float:left;font-size:13px;margin-left:10px;color: #e09015;">{{value*2}}</span>
                                </div>
                                <div style="height:20px;float:left;color: #999;font-size:13px">中国大陆 / 剧情 / 爱情 / 犯罪 / 少年的你，如此美丽 / Better Days / 135分钟
曾国祥 / 周冬雨 / 易烊千玺 / 尹昉 / 周也 / 吴越 / 黄觉 / 张艺凡 / 张耀</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import MovieHeader from './MovieHeader'
import loginedHeader from '../index/loginedHeader'
export default {
    data(){
        return{
            value:4.7,
        }
    },
    components:{
        MovieHeader,
        loginedHeader,
    },
}
</script>

<style scoped>
.read-cover img{
    width: 48px;
}
.read-content{
    width: 1040px;
    height: 80px;
    margin-bottom: 40px;
}
.read-cover{
    float: left;
    margin-right: 20px;
}
.read-info{
    font-size: 15px;
    float: left;
}
.read-info a{
    color: #37a;
    text-decoration: none;
}
</style>

<style>
.el-rate__icon {
    margin-right: -5px;
}
</style>