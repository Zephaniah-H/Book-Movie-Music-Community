<template>
    <div><!-- 背景 -->
        <logined-header></logined-header>
        <read-header @search='booksearch'></read-header>
        <div class="rb-content"><!-- 内容 -->
            <div class="right-book-list">
                <div style="font-size:16px;font-weight:400;margin-top: 20px;">高分图书</div>
                <el-divider></el-divider>
                <table>
                    <tr v-for="(item,index) in highPointBooks.slice(0,10)" :key="item.index">
                        <td class="order">{{index+1}}</td>
                        <td class="title" @click="goBookinfo(index,'right')">{{item.bookname}}</td>
                    </tr>
                </table>
            </div>
            <div class="left-book-list">
                <div class="book-navi">
                    <font style="font-size:16px;font-weight:400;margin-right:10px;">最近热门书籍</font>
                    <ul>
                        <li>热门</li>
                        <li>文学</li>
                        <li>流行</li>
                        <li>文化</li>
                        <li>生活</li>
                        <li>经管</li>
                        <li>科技</li>
                    </ul>
                    <font style="font-size:15px;float:right;padding-right:22px;">更多»</font>
                </div>
                <el-divider></el-divider>
                <div class="many-book">
                    <ul>
                        <li v-for="(item,index) in allBooks.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="item.index">
                          <a href="javascript:void(0)" @click="goBookinfo(index,'left')">
                            <el-tooltip placement="right">
                                <div slot="content">
                                    <div style="font-size:16px;font-weight:bold;">{{item.bookname}}</div><br>
                                    <div style="font-size:14px;">{{item.author}}&nbsp;/&nbsp;{{item.publication_year}}&nbsp;/&nbsp;{{item.press}}</div><br>
                                    <div style="font-size:13px;width:300px" v-html="item.bookinfo">{{item.bookinfo}}...</div>
                                </div>
                                <div class="one-book">
                                    <div class="pic"><img :src="item.cover"></div>
                                    <div class="book-title"><font>{{item.bookname}}</font></div>
                                    <div class="author"><font>{{item.author.slice(0,12)}}</font></div>
                                </div>
                            </el-tooltip>
                          </a>
                        </li>
                    </ul>
                </div>
                <div class="book-page">
                    <el-pagination
                        layout="prev, pager, next"
                        :current-page="currentPage"
                        :page-size="pageSize"
                        :total="allBooks.length"
                        @current-change="handlePageChange">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ReadHeader from './ReadHeader'
import loginedHeader from '../index/loginedHeader'
export default {
    data(){
        return{
            currentPage: 1,
            pageSize: 15,
            allBooks:[],
            highPointBooks:[],
            booksch:''
        }
    },
    components:{
        ReadHeader,
        loginedHeader,
    },
    mounted(){
        this.findAllBooks()
        this.findHighPointBooks()
    },
    methods:{
        booksearch(val){
            this.booksch = val
            let search={
                searchthing: this.booksch
            }
            this.$store.commit('searchbook',search)
            const {href} = this.$router.resolve({
                path: '/search',
                // query: {
                // pdf: JSON.stringify(this.pdf)
                // }
            })
            window.open(href, '_blank')
        },
        goBookinfo(index,pos){
            if(pos === 'left'){
                console.log(this.allBooks[index])
                this.$store.commit('onebookinfo',this.allBooks[index])
            }else if(pos === 'right'){
                this.$store.commit('onebookinfo',this.highPointBooks[index])
            }
            // this.$router.push({path: '/ReadBook'})
            const {href} = this.$router.resolve({
                path: '/onebook',
                // query: {
                // pdf: JSON.stringify(this.pdf)
                // }
            })
            window.open(href, '_blank')
        },
        // 分页导航
        handlePageChange(currentPage) {
            this.currentPage = currentPage
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
        // 查高分书
        findHighPointBooks(){
            let url = '/findHighPointBooks'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.highPointBooks = resp.data
                }
            })
        },
    }
}
</script>

<style scoped>
a{
    color: black;
    text-decoration: none;
}
.rb-content{
    width: 1040px;
    margin: auto;
}
.left-book-list{
    float: left;
    width: 705px;
    /* padding-right: 50px; */
}
.right-book-list{
    float: right;
    width: 300px;
}
.book-navi{
    margin-top: 20px;
}
.left-book-list ul{
    display: inline;
    list-style:none;
    font-size:15px;
    margin: 0;
    padding: 0;
}
.book-navi li{
    display: inline-block;
    margin-right:5px;
    color: rgb(160, 157, 157);
}
.one-book{
    width: 115px;
}
.pic,.book-title,.author{
    margin-bottom: 3px;
    font-size: 12px;
    text-align: center;
}
img {
    width: 115px;
    height: 161px;
}
.many-book li{
    display: inline-block;
    margin: 0 22px 10px 0;
}
.order,.title{
    padding:12px 3px;
    font-size: 14px;
}
.order{
    width: 5%;
}
.title{
    color: rgb(80, 124, 206);
}
table{
    border-collapse:collapse;
    width: 300px;
}
tr{
    border-bottom:1px solid rgb(223, 221, 221);
}
.book-page{
    margin-top: 10px;
    margin-bottom: 20px;
    margin-left: -40px;
    text-align: center;
}
</style>
<style>
.left-book-list .el-divider--horizontal {
    margin: 15px 0;
    width: 682px;
}
.right-book-list .el-divider--horizontal {
    margin: 15px 0;
}
</style>