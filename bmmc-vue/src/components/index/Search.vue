<template>
    <div>
        <logined-header></logined-header>
        <read-header></read-header>
        <div style="width: 1040px;margin: auto;">
            <h1 style="font-size:26px;margin-top:40px;">搜索&nbsp;{{this.$store.state.search.searchthing}}</h1>
            <div style="margin-top:40px;">
                <ul>
                    <li v-for="item in allBooks" :key="item.index">
                        <div class="read-content"><!-- 书的封面+信息+评分 -->
                            <div class="read-cover">
                                <img :src="item.cover" alt="活着">
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
                                    <span style="float:left;font-size:13px;margin-left:10px;color: #e09015;">{{item.bookpoint}}</span>
                                </div>
                                <div style="height:20px;float:left;color: #999;font-size:13px">{{item.author}} / {{item.press}} / {{item.publication_year}}</div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import ReadHeader from '../book/ReadHeader'
import loginedHeader from './loginedHeader'
export default {
    data(){
        return{
            value:4.7,
            allBooks:[],
            // bookSearch:'',
        }
    },
    components:{
        ReadHeader,
        loginedHeader,
    },
    mounted(){
        this.searchBook()
    },
    methods:{
        // 模糊查询
        searchBook(){
            let bSearch = this.$store.state.search.searchthing
                let url = "/findBookBySearch"
                let param = {bookname: bSearch}
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allBooks = resp.data
                    }
                }) 
            
        },
        // 查所有书
        findAllBooks(){
            let url = '/findAllBooks'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allBooks = resp.data
                }
            })
        },
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