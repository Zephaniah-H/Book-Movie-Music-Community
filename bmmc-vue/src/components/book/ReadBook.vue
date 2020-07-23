<template>
    <div style="background-color:rgb(245,247,250);">
        <div class="main-read-book">
           <el-tabs type="border-card" :tab-position="tabPosition" style="background-color:rgb(246,244,236);height:743px" v-model="selected_tab">
            <el-tab-pane>
                <span slot="label"><a href="javascript:void(0)" title="正文"><i class="el-icon-notebook-2"></i></a></span>
                <div class="book-article">
                    <div class="book-article-chapter1">
                        {{this.$store.state.book.bookname}}: {{bookChapters[chapter].title}}
                        <a href="javascript:void(0)" style="float:right;font-size:14px">
                            <i class="el-icon-zf-shelf" v-if="!inBookShelf" @click="addInBookShelf">&nbsp;加入书架</i>
                            <i class="el-icon-check" v-else @click="goBookShelf">&nbsp;已在书架</i>
                        </a>
                    </div>
                    <el-divider></el-divider>
                    <div class="book-article-chapter2">{{bookChapters[chapter].title}}</div>
                    <div class="book-article-body" v-html="bookChapters[chapter].content">
                        {{bookChapters[chapter].content}}
                    </div>
                    <div class="chapter-button">
                        <el-button type="info" plain @click="preChapter">上一章</el-button>
                        <el-button type="info" plain @click="nextChapter">下一章</el-button>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><a href="javascript:void(0)" title="目录"><i class="el-icon-s-fold"></i></a></span>
                <div class="book-catalog">
                    <div class="book-catalog-tilte">目录</div>
                    <div class="book-catalog-content">
                        <ul>
                            <li v-for="(item,index) in bookChapters.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="item.index" @click="gotoChapter(index)"><a href="javascript:void(0)">{{item.title}}</a><span>{{(currentPage-1)*pageSize + index + 1}}</span></li>
                        </ul>
                    </div>
                    <div class="catalog-page">
                        <el-pagination
                        background-color="rgb(246,244,236)"
                        small
                        layout="prev, pager, next"
                        :current-page="currentPage"
                        :page-size="pageSize"
                        :total="bookChapters.length"
                        @current-change="handlePageChange">
                        </el-pagination>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><a href="javascript:void(0)" title="书签"><i class="el-icon-collection"></i></a></span>
                <div class="book-tag">
                    <div class="book-tag-title">我的书签</div>
                    <div class="book-tag-content">
                        <ul>
                            <li>
                                <div class="tag-title">前言</div>
                                <el-divider></el-divider>
                                <div class="tag-sign">
                                    <i class="el-icon-collection-tag"></i>
                                    <span style="color:#999;font-size:12px;float:right"> 2020-05-04 17:02:05 </span>
                                </div>
                                <div class="tag-content">
                                    <a href="javascript:void(0)">
                                    <p>
                                        <span>…替不了我的写作，正因为此，我在很长一段时间里是一个愤怒和冷漠的作家。</span>
                                    </p> 
                                    </a>
                                </div>
                                <div class="tag-delete"><a href="javascript:void(0)" style="font-size:12px;rgb(89, 89, 89);">删除</a></div>
                                <el-divider></el-divider>
                            </li>
                            <li>
                                <div class="tag-title">前言</div>
                                <el-divider></el-divider>
                                <div class="tag-sign">
                                    <i class="el-icon-collection-tag"></i>
                                    <span style="color:#999;font-size:12px;float:right"> 2020-05-04 17:02:05 </span>
                                </div>
                                <div class="tag-content">
                                    <a href="javascript:void(0)">
                                    <p>
                                        <span>…替不了我的写作，正因为此，我在很长一段时间里是一个愤怒和冷漠的作家。</span>
                                    </p> 
                                    </a>
                                </div>
                                <div class="tag-delete"><a href="javascript:void(0)" style="font-size:12px;rgb(89, 89, 89);">删除</a></div>
                                <el-divider></el-divider>
                            </li>
                            <li>
                                <div class="tag-title">前言</div>
                                <el-divider></el-divider>
                                <div class="tag-sign">
                                    <i class="el-icon-collection-tag"></i>
                                    <span style="color:#999;font-size:12px;float:right"> 2020-05-04 17:02:05 </span>
                                </div>
                                <div class="tag-content">
                                    <a href="javascript:void(0)">
                                    <p>
                                        <span>…替不了我的写作，正因为此，我在很长一段时间里是一个愤怒和冷漠的作家。</span>
                                    </p> 
                                    </a>
                                </div>
                                <div class="tag-delete"><a href="javascript:void(0)" style="font-size:12px;rgb(89, 89, 89);">删除</a></div>
                                <el-divider></el-divider>
                            </li>
                            <li>
                                <div class="tag-title">前言</div>
                                <el-divider></el-divider>
                                <div class="tag-sign">
                                    <i class="el-icon-collection-tag"></i>
                                    <span style="color:#999;font-size:12px;float:right"> 2020-05-04 17:02:05 </span>
                                </div>
                                <div class="tag-content">
                                    <a href="javascript:void(0)">
                                    <p>
                                        <span>…替不了我的写作，正因为此，我在很长一段时间里是一个愤怒和冷漠的作家。</span>
                                    </p> 
                                    </a>
                                </div>
                                <div class="tag-delete"><a href="javascript:void(0)" style="font-size:12px;rgb(89, 89, 89);">删除</a></div>
                                <el-divider></el-divider>
                            </li>
                            <li>
                                <div class="tag-title">前言</div>
                                <el-divider></el-divider>
                                <div class="tag-sign">
                                    <i class="el-icon-collection-tag"></i>
                                    <span style="color:#999;font-size:12px;float:right"> 2020-05-04 17:02:05 </span>
                                </div>
                                <div class="tag-content">
                                    <a href="javascript:void(0)">
                                    <p>
                                        <span>…替不了我的写作，正因为此，我在很长一段时间里是一个愤怒和冷漠的作家。</span>
                                    </p> 
                                    </a>
                                </div>
                                <div class="tag-delete"><a href="javascript:void(0)" style="font-size:12px;rgb(89, 89, 89);">删除</a></div>
                                <el-divider></el-divider>
                            </li>
                            <li>
                                <div class="tag-title">前言</div>
                                <el-divider></el-divider>
                                <div class="tag-sign">
                                    <i class="el-icon-collection-tag"></i>
                                    <span style="color:#999;font-size:12px;float:right"> 2020-05-04 17:02:05 </span>
                                </div>
                                <div class="tag-content">
                                    <a href="javascript:void(0)">
                                    <p>
                                        <span>…替不了我的写作，正因为此，我在很长一段时间里是一个愤怒和冷漠的作家。</span>
                                    </p> 
                                    </a>
                                </div>
                                <div class="tag-delete"><a href="javascript:void(0)" style="font-size:12px;rgb(89, 89, 89);">删除</a></div>
                                <el-divider></el-divider>
                            </li>
                        </ul>
                    </div>
                </div>
            </el-tab-pane>
            <el-tab-pane>
                <span slot="label"><a href="javascript:void(0)" title="批注"><i class="el-icon-tickets"></i></a></span>
                <div class="postil"></div>
            </el-tab-pane>
           </el-tabs> 
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            selected_tab:'0',
            tabPosition:'right',
            bookChapters:[],
            chapter:0,
            currentPage: 1,
            pageSize: 13,
            inBookShelf: false,
        }
    },
    mounted(){
        this.findChapterByBookid(this.$store.state.book.bookid)
        this.findMyBooksUserid()
    },
    methods:{
        // 查1书的章节
        findChapterByBookid(bookid){
            // console.log(bookid)
            // this.bookinfo.bookid = bookid
            // this.bookinfo.bookname = bookname
            let url = '/findChapterByBookid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.bookChapters = resp.data
                    console.log(this.bookChapters)
                }
            })
        },
        //  查书架
        findMyBooksUserid(){
            let url = '/findMyBooksUserid'
            let param = {userid: this.$store.state.user.userid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    for(let i = 0; i < resp.data.length; i++){
                        console.log(resp.data[i].allBooks.bookid)
                        console.log(this.$store.state.book.bookid)
                        console.log(resp.data[i].allBooks.bookid == this.$store.state.book.bookid)
                        if(resp.data[i].allBooks.bookid == this.$store.state.book.bookid){
                            this.inBookShelf = true
                            console.log(111)
                        }
                    }
                }
            })
        },
        //  加入书架
        addInBookShelf(){
            let url = '/addInBookShelf'
            let param = {
                userid: this.$store.state.user.userid,
                bookid: this.$store.state.book.bookid
            }
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    this.findMyBooksUserid()
                }
            })
        },
        //去书架
        goBookShelf(){
            const {href} = this.$router.resolve({ path: '/bookshelf', })
            window.open(href, '_blank')
        },
        nextChapter(){
            if(this.chapter < this.bookChapters.length-1){
                this.chapter = this.chapter + 1
            }else
                this.$message.warning("已经是最后一章！")
        },
        preChapter(){
            if(this.chapter > 0){
                this.chapter = this.chapter - 1
            }else
                this.$message.warning("已经是第一章！")
        },
        gotoChapter(idx){
            this.chapter = idx
            this.selected_tab = '0'
        },
        // 分页导航
        handlePageChange(currentPage) {
            this.currentPage = currentPage
        },
    }
}
</script>

<style scoped>
.main-read-book{
    width: 750px;
    margin: auto;
}
.main-read-book a{
    color: teal;
}
.book-article{
    width: 550px;
    margin: auto;
}
.book-article-chapter1{
    font-size: 13px;
    color: rgb(160, 147, 97);
}
.book-article-chapter2{
    text-align: center;
    font-size: 23px;
    font-weight: bold;
    margin: 35px 0;
}
.book-article-body{
    height: 550px;
    overflow: scroll;
    overflow-x: hidden;
    overflow-y: auto;
}
.chapter-button{
    text-align: center;
    margin-top: 8px;
}
.book-catalog{
    width: 527px;
    margin: auto;
}
.book-catalog-tilte{
    font-size: 18px;
    font-weight: bold;
    color: rgb(99, 73, 7);
    text-align: center;
    margin: 25px 0;
}
.book-catalog-content ul{
    list-style: none;
    margin: 0;
    padding: 0;
}
.book-catalog-content li{
    border-top: 1px solid #e9eadf;
    color: #666;
    font-weight: bold;
    
}
.book-catalog-content span{
    float: right;
    font: .8125em Georgia;
    margin-right: 1em;
    margin-top: -2em;
}
.book-catalog-content a{
    display: block;
    color: rgb(56, 53, 53);
    text-decoration: none;
    padding: 10px 3em 10px 1em;
}
.book-catalog-content a:hover{
    text-decoration: none;
    outline: none;
    background-color: rgb(230, 228, 228);
}
.catalog-page{
    text-align: center;
}
.book-tag{
    width: 527px;
    margin: auto;
}
.book-tag-content{
    height: 550px;
    overflow: scroll;
    overflow-x: hidden;
    overflow-y: auto;
}
.book-tag-title{
    font-size: 28px;
    font-weight: bold;
    margin: 20px 0;
}
.book-tag ul{
    list-style: none;
    padding: 0;
    margin: 0;
}
.tag-title{
    font-weight: 600;
    font-size: 17px;
    margin: 20px 0 10px;
}
.tag-content a{
    text-decoration: none;
    font-size: 12px;
    color: rgb(89, 89, 89);
}
.tag-content a:hover{
    color: rgb(179, 174, 174);
}
.tag-content p{
    margin: 8px 0;
}
.tag-delete a{
    text-decoration: none;
    color: rgb(89, 89, 89);
}
.tag-delete a:hover{
    color: white;
    background-color: rgb(89, 89, 89);
}
</style>

<style>
.el-tabs__nav-prev,.el-tabs__nav-next{
    display: none;
}
.el-tabs--border-card {
    background: #FFF;
    border: 1px solid rgb(245,247,250);
    -webkit-box-shadow: none;
    box-shadow: none;
}
.el-divider--horizontal {
    margin: 10px 0;
}
.main-read-book .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active {
    background-color: rgb(246,244,236);
}
.el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: rgb(246,244,236);
    color: #606266;
}
</style>