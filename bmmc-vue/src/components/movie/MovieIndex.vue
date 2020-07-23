<template>
    <div><!-- 背景 -->
        <logined-header></logined-header>
        <movie-header></movie-header>
        <div class="rb-content"><!-- 内容 -->
            <div class="right-book-list">
                <div style="font-size:16px;font-weight:400;margin-top: 20px;">一周口碑榜</div>
                <el-divider></el-divider>
                <table>
                    <tr v-for="(item,index) in highPointMovies.slice(0,10)" :key="item.index">
                        <td class="order">{{index+1}}</td>
                        <td class="title">{{item.moviename}}</td>
                    </tr>
                </table>
            </div>
            <div class="left-book-list">
                <div class="book-navi">
                    <font style="font-size:16px;font-weight:400;margin-right:10px;">最近热门电影</font>
                    <ul>
                        <li>热门</li>
                        <li>最新</li>
                        <li>高分</li>
                        <li>华语</li>
                        <li>欧美</li>
                        <li>韩国</li>
                        <li>日本</li>
                    </ul>
                    <font style="font-size:15px;float:right;padding-right:22px;">更多»</font>
                </div>
                <el-divider></el-divider>
                <div class="many-book">
                    <ul>
                        <li v-for="(item,index) in allMovies.slice(0,10)" :key="item.index">
                          <a href="javascript:void(0)" @click="jumpMovieinfo(index,'all')">
                            <el-tooltip placement="right">
                            <div slot="content">
                                <div style="font-size:16px;font-weight:bold;">{{item.moviename}}&nbsp;({{item.releaseday.slice(0,4)}})</div><br>
                                <div style="font-size:14px;width:300px;">
                                    {{item.duration}}&nbsp;/中国&nbsp;/{{item.movietype}}&nbsp;/{{item.director}}(导演)&nbsp;/{{item.leadingrole}}
                                </div><br>
                                <div style="font-size:14px;width:300px;">
                                    {{item.movieinfo}}
                                </div>
                            </div>
                                <div class="one-book">
                                    <div class="pic"><img :src="item.cover"></div>
                                    <div class="book-title"><font>{{item.moviename}}</font>&nbsp;<span style="color:rgb(224,144,21);">{{item.point}} </span></div>
                                    <!-- <div class="author"><font>〔澳〕莉安·莫...</font></div> -->
                                </div>
                            </el-tooltip>
                          </a>
                        </li>
                    </ul>
                </div>

                <div class="book-navi">
                    <font style="font-size:16px;font-weight:400;margin-right:10px;">正在上映</font>
                    <font style="font-size:15px;float:right;padding-right:22px;">正在上映»</font>
                </div>
                <el-divider></el-divider>
                <div class="many-book">
                    <ul>
                        <li v-for="(item,index) in allPlayingMovies.slice(0,10)" :key="item.index">
                          <a href="javascript:void(0)">
                            <el-tooltip placement="right">
                            <div slot="content">
                                <div style="font-size:16px;font-weight:bold;">{{item.moviename}}&nbsp;({{item.releaseday.slice(0,4)}})</div><br>
                                <div style="font-size:14px;width:300px;">
                                    {{item.duration}}&nbsp;/{{item.country}}&nbsp;/{{item.movietype}}&nbsp;/{{item.director}}(导演)&nbsp;/{{item.leadingrole}}
                                </div><br>
                                <div style="font-size:14px;width:300px;" v-html="item.movieinfo">
                                    {{item.movieinfo}}
                                </div>
                            </div>
                                <div class="one-book">
                                    <div class="pic"><img :src="item.cover" @click="jumpMovieinfo(index,'正在上映')"></div>
                                    <div class="book-title"><font>{{item.moviename}}</font>&nbsp;<span style="color:rgb(224,144,21);">8.1 </span></div>
                                    <div class="author"><font style="color:#999;"><button @click="jumpBuyTicket(index)">选座购票</button></font></div>
                                </div>
                            </el-tooltip>
                          </a>
                        </li>
                    </ul>
                </div>

                <div class="book-navi">
                    <font style="font-size:16px;font-weight:400;margin-right:10px;">即将上映</font>
                    <font style="font-size:15px;float:right;padding-right:22px;">即将上映»</font>
                </div>
                <el-divider></el-divider>
                <div class="many-book">
                    <ul>
                        <li v-for="(item,index) in allPlaySoonMovies.slice(0,10)" :key="item.index">
                          <a href="javascript:void(0)" @click="jumpMovieinfo(index,'即将上映')">
                            <el-tooltip placement="right">
                            <div slot="content">
                                <div style="font-size:16px;font-weight:bold;">{{item.moviename}}&nbsp;({{item.releaseday.slice(0,4)}})</div><br>
                                <div style="font-size:14px;width:300px;">
                                    {{item.duration}}&nbsp;/{{item.country}}&nbsp;/{{item.movietype}}&nbsp;/{{item.director}}(导演)&nbsp;/{{item.leadingrole}}
                                </div><br>
                                <div style="font-size:14px;width:300px;" v-html="item.movieinfo">
                                    {{item.movieinfo}}
                                </div>
                            </div>
                                <div class="one-book">
                                    <div class="pic"><img :src="item.cover"></div>
                                    <div class="book-title"><font>{{item.moviename}}</font></div>
                                    <div class="author"><font style="color:#999;">{{item.releaseday.slice(5,7)}}月{{item.releaseday.slice(8,10)}}日上映</font></div>
                                </div>
                            </el-tooltip>
                          </a>
                        </li>
                    </ul>
                </div>
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
            allMovies:[],
            highPointMovies:[],
            allPlayingMovies:[],
            allPlaySoonMovies:[],

        }
    },
    components:{
        MovieHeader,
        loginedHeader,
    },
    mounted(){
        this.findAllMovies()
        this.findHighPointMovies()
        this.findPlayingMovies()
        this.findPlaySoonMovies()
    },
    methods:{
        //跳转到电影详细信息
        jumpMovieinfo(idx,type){
            if(type === 'all'){
                this.$store.commit('onemovieinfo',this.allMovies[idx])
            }else if(type === '正在上映'){
                this.$store.commit('onemovieinfo',this.allPlayingMovies[idx])
            }else if(type === '即将上映'){
                this.$store.commit('onemovieinfo',this.allPlaySoonMovies[idx])
            }
            const {href} = this.$router.resolve({path: '/onemovie'})
            window.open(href, '_blank')
        },
        //跳转到选座购票
        jumpBuyTicket(idx){
            this.$store.commit('onemovieinfo',this.allPlayingMovies[idx])
            const {href} = this.$router.resolve({path: '/buyticket'})
            window.open(href, '_blank')
        },
        // 查所有电影
        findAllMovies(){
            let url = '/findAllMovie'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allMovies = resp.data
                }
            })
        },
        // 查高分电影
        findHighPointMovies(){
            let url = '/findHighPointMovies'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.highPointMovies = resp.data
                }
            })
        },
        // 查正在上映电影
        findPlayingMovies(){
            let url = '/findMovieByState'
            let param = {moviestate: '正在上映'}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.allPlayingMovies = resp.data
                    console.log(resp.data)
                }
            })
        },
        // 查即将上映电影
        findPlaySoonMovies(){
            let url = '/findMovieByState'
            let param = {moviestate: '即将上映'}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.allPlaySoonMovies = resp.data
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
    height: 160px;
}
.many-book li{
    display: inline-block;
    height: 180px;
    margin: 0 22px 40px 0;
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