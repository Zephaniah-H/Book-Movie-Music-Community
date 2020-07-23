<template>
    <div>
    <div class="book-section"><!-- 颜色 -->
        <div class="read-book"><!-- 内容 -->
            <div class="side-left">
                <div class="title">
                    <h2 class="title-read">读书</h2>
                </div>
                <div class="side-links">
                    <ul>
                        <li>分类</li>
                        <li>作者</li>
                        <li>书评</li>
                    </ul>
                </div>
            </div>
            <div class="main">
                <div class="top-book">
                    <h2>
                        新书速递&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                        <span>（<a href="javascript:void(0)" @click="goBookIndex">更多</a>）</span>
                    </h2>
                    <div class="book-list">
                        <ul>
                            <li v-for="item in allBooks.slice(0,8)" :key="item.index">
                                <div class="pic"><img :src="item.cover"></div>
                                <div class="book-title"><font>{{item.bookname}}</font></div>
                                <div class="author"><font>{{item.author}}</font></div>
                                <el-button type="info" @click="findOneBookChapter">免费试读</el-button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="side-right">
                <h2>
                    热门标签&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                    <span>（<a href="javascript:void(0)">更多</a>）</span>
                </h2>
            </div>
        </div>
    </div>
    <div class="music-section"><!-- 颜色 -->
        <div class="listen-music"><!-- 内容 -->
            <div class="side-left">
                <div class="title">
                    <h2 class="title-read">音乐</h2>
                </div>
                <div class="side-links">
                    <ul>
                        <li>分类</li>
                        <li>歌手</li>
                        <li>乐评</li>
                    </ul>
                </div>
            </div>
            <div class="main">
                <div class="top-book">
                    <h2>
                        今日流行歌单&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                        <span>（<a href="#">更多</a>）</span>
                    </h2>
                    <div class="music-list">
                        <ul>
                            <li v-for="item in allSonglists.slice(0,8)" :key="item.index">
                                <div class="music-pic"><img :src="item.coverImgUrl"></div>
                                <div class="book-title"><font>{{item.name}}</font></div>
                                <!-- <div class="author"><font>{{item.author}}</font></div> -->
                                <!-- <el-button type="info" @click="findOneBookChapter">免费试读</el-button> -->
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="side-right">
                <h2>
                    热门排行&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                    <span>（<a href="#">更多</a>）</span>
                </h2>
            </div>
        </div>
    </div>
    <div class="book-section"><!-- 颜色 -->
        <div class="read-book"><!-- 内容 -->
            <div class="side-left">
                <div class="title">
                    <h2 class="title-read">电影</h2>
                </div>
                <div class="side-links">
                    <ul>
                        <li>分类</li>
                        <li>正在上映</li>
                        <li>影评</li>
                    </ul>
                </div>
            </div>
            <div class="main">
                <div class="top-book">
                    <h2>
                        高分电影&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                        <span>（<a href="#">更多</a>）</span>
                    </h2>
                    <div class="book-list">
                        <ul>
                            <li v-for="item in allMovies.slice(0,8)" :key="item.index">
                                <div class="pic"><img :src="item.cover"></div>
                                <div class="book-title"><font>{{item.moviename}}</font></div>
                                <div class="author">
                                    <el-rate
                                    style="float:left;"
                                    v-model="item.point"
                                    disabled
                                    score-template="{value}">
                                    </el-rate>
                                    <span style="float:left;color: rgb(224, 144, 21);">{{(item.point)*2}}</span>
                                </div>
                                <!-- <el-button type="info" @click="findOneBookChapter">免费试读</el-button> -->
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="side-right">
                <h2>
                    热门排行&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                    <span>（<a href="#">更多</a>）</span>
                </h2>
            </div>
        </div>
    </div>
    </div>
</template>

<script>
export default {
    name:'Content',
    data(){
        return{
            allBooks:[],
            allSonglists:[],
            allMovies:[],
        }
    },
    mounted(){
        this.findAllBooks()
        this.findAllSonglists()
        this.findAllMovies()
    },
    methods:{
        findOneBookChapter(){},
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
        // 查所有歌单
        findAllSonglists(){
            let url = '/top/playlist'
            // let param = {}
            this.common.requestMusicData(url).then(resp => {
                if(resp && resp.status == 200){
                    this.allSonglists = resp.data.playlists
                    console.log(resp.data.playlists)
                }
            })
        },
        // 查所有电影
        findAllMovies(){
            let url = '/findAllMovie'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allMovies = resp.data
                    for(let i = 0; i < this.allMovies.length; i++){
                        let a = parseFloat(this.allMovies[i].point) / 2
                        this.allMovies[i].point = Number(a)
                      }
                }
            })
        },
        // 跳转到新页面
        goBookIndex(){
            const {href} = this.$router.resolve({ path: '/readbookindex', })
            window.open(href, '_blank')
        }
    }
}
</script>

<style scoped>
.book-section,.music-section{
    margin-bottom: 30px;
    padding: 20px 0;
}
.music-section{
    background-color: #FCF9EE;
}
.read-book{
    width: 950px;
    height: 530px;
    margin: 0 auto;
}
.listen-music{
    width: 950px;
    height: 400px;
    margin: 0 auto;
}
.side-left{
    float: left;
    width: 100px;
    margin-right: 20px;
}
.side-links li{
    font-size: 14px;
    margin-top: 5px;
}
.side-right{
    float: left;
    width: 265px;
    margin-left: 30px;
}
.title h2{
    margin:0;
    font-weight: 400;
}
.main{
    width: 535px;
    float: left;
}
.book-list li{
    display: inline-block;
    width: 24.3%;
    text-align: center;
    font-size: 12px;
    margin-bottom: 15px;
    height: 208px;
    overflow: hidden;
}
.music-list li{
    display: inline-block;
    width: 24.3%;
    text-align: center;
    font-size: 12px;
    margin-bottom: 15px;
    height: 150px;
    overflow: hidden;
}
.pic,.book-title,.author{
    margin-bottom: 3px;
}
img {
    width: 100px;
    height: 140px;
}
.music-pic img{
    width: 100px;
    height: 100px;
}
.top-book h2 {
    font: 15px Arial, Helvetica, sans-serif;
    color: #EDBC10;
    margin: 0 0 12px 0;
    padding-top: 5px;
    line-height: 150%;
}
.side-right h2 {
    font: 15px Arial, Helvetica, sans-serif;
    color: #EDBC10;
    margin: 0 0 12px 0;
    padding-top: 5px;
    line-height: 150%;
}
ul{
    list-style: none;
    padding: 0;
}
</style>
<style>
.book-list .el-button{
    padding: 5px 9px;
    font-size: 12px;
}
.el-rate {
    margin-left: 20px;
    margin-right: 5px;
}
.el-rate__icon {
    margin-right: -5px;
}
</style>