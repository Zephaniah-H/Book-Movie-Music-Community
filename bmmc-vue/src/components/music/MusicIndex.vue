<template>
    <div><!-- 最外层 -->
        <logined-header></logined-header>
        <music-header></music-header>
        <div class="music-content"><!-- 内容（居中） -->
            <div class="music-left">
                <div class="music-left-scroll">
                    <el-carousel height="250px">
                        <el-carousel-item v-for="item in scroll_pic" :key="item.index">
                            <h3 class="small"><img :src="item.pic"></h3>
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <div class="music-left-musician">
                    <div class="music-left-musician-title">今日流行音乐人</div>
                    <div class="music-left-musician1">
                        <ul>
                            <li v-for="item in hotSingers.slice(0,8)" :key="item.index">
                                <a href="javascript:void(0)">
                                   <div class="musician">
                                        <div class="musician-cover"><img :src="item.img1v1Url"></div>
                                        <div class="musician-name">{{item.name}}</div>
                                        <div class="musician-hot">
                                            <!-- <img :src="hot"><div style="float:left;">6558852</div> -->
                                        </div> 
                                   </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="music-left-songlist">
                    <div class="music-left-musician-title">
                        <span>精品歌单</span>
                        <span class="hotlist">
                            <a v-if="songlist_selected[0]==0" href="javascript:void(0)" ref="hot" @click="select_songlist(1)" class="before-select">热门</a>
                            <a v-if="songlist_selected[0]==1" href="javascript:void(0)" ref="hot" @click="select_songlist(1)" class="after-select">热门</a>
                        </span>
                        <ul>
                            <li>
                                &nbsp;|&nbsp;
                                <a v-if="songlist_selected[1]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(2)" class="before-select">华语</a>
                                <a v-if="songlist_selected[1]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(2)" class="after-select">华语</a>
                            </li>
                            <li>
                                &nbsp;|&nbsp;
                                <a v-if="songlist_selected[2]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(3)" class="before-select">流行</a>
                                <a v-if="songlist_selected[2]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(3)" class="after-select">流行</a>
                            </li>
                            <li>
                                &nbsp;|&nbsp;
                                <a v-if="songlist_selected[3]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(4)" class="before-select">民谣</a>
                                <a v-if="songlist_selected[3]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(4)" class="after-select">民谣</a>
                            </li>
                            <li>
                                &nbsp;|&nbsp;
                                <a v-if="songlist_selected[4]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(5)" class="before-select">摇滚</a>
                                <a v-if="songlist_selected[4]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(5)" class="after-select">摇滚</a>
                            </li>
                        </ul>
                    </div>
                    <div class="music-left-songlist1">
                        <ul>
                            <li class="music-left-songlist1-li" v-for="item in allSonglists.slice(0,4)" :key="item.index">
                                <a href="javascript:void(0)">
                                   <div class="asonglist">
                                        <div class="songlist-cover"><img :src="item.coverImgUrl"></div>
                                        <div class="songlist-content">
                                            <div class="songlist-content-title">{{item.name}}<span style="color:grey;font-size:12px">(28)</span></div>
                                            <div class="songlist-content1">
                                                <ul>
                                                    <li>1.You Know How To Love Me</li>
                                                    <li>2.Living All Alone</li>
                                                    <li>3.I Ain't Asking</li>
                                                    <li>4.I Ain't Asking</li>
                                                    <li>5.I Ain't Asking</li>
                                                </ul>
                                            </div>
                                            <div class="ellipsis" style="color:black;">...</div>
                                        </div>
                                   </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="music-right">
                <div class="rank-list-title">单曲排行榜</div>
                <div class="rank-list-class">
                    <span>
                            <a v-if="songlist_selected[0]==0" href="javascript:void(0)" ref="hot" @click="select_songlist(1)" class="before-select">热歌榜</a>
                            <a v-if="songlist_selected[0]==1" href="javascript:void(0)" ref="hot" @click="select_songlist(1)" class="after-select">热歌榜</a>
                        </span>
                        <ul>
                            <li>
                                <span style="font-size:13px;color:rgb(192, 191, 190)">|</span>
                                <a v-if="songlist_selected[1]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(2)" class="before-select">飙升榜</a>
                                <a v-if="songlist_selected[1]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(2)" class="after-select">飙升榜</a>
                            </li>
                            <li>
                                <span style="font-size:13px;color:rgb(192, 191, 190)">|</span>
                                <a v-if="songlist_selected[2]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(3)" class="before-select">新歌榜</a>
                                <a v-if="songlist_selected[2]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(3)" class="after-select">新歌榜</a>
                            </li>
                            <li>
                                <span style="font-size:13px;color:rgb(192, 191, 190)">|</span>
                                <a v-if="songlist_selected[3]==0" href="javascript:void(0)" ref="Chinese" @click="select_songlist(4)" class="before-select">欧美热歌榜</a>
                                <a v-if="songlist_selected[3]==1" href="javascript:void(0)" ref="Chinese" @click="select_songlist(4)" class="after-select">欧美热歌榜</a>
                            </li>
                        </ul>
                </div>
                <div class="rank-list-content">
                    <ul>
                        <li v-for="(item,index) in allRanklists.slice(0,10)" :key="item.index">
                            <div @click="playMusic(index,ranklist)">
                                <div class="ranklist-song-cover">
                                    <img width="48" style="border-radius: 50%;" :src="item.al.picUrl">
                                </div>
                                <div class="ranklist-song-name">
                                    <div style="color: rgb(240, 184, 64);font-size:15px;line-height: 25px;width: 200px;">{{item.name}}</div>
                                    <div style="font-size: 13px;color: #999;">{{item.ar[0].name}}</div>
                                </div>
                                <div class="ranklist-song-rank">{{index+1}}</div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <music-player ref="musicPlayer"></music-player>
    </div>
</template>

<script>
import hotpic from '../../assets/fire.png'
import MusicPlayer from '../musicplayer/player'
import MusicHeader from './MusicHeader'
import loginedHeader from '../index/loginedHeader'
export default {
    data(){
        return{
            scroll_pic:[
                {pic:'http://localhost:8888/api/musicCover/2.jpg'},
                {pic:'http://localhost:8888/api/musicCover/4.jpg'},
                {pic:'http://localhost:8888/api/musicCover/5.jpg'},
            ],
            hot: hotpic,
            songlist_selected:['1','0','0','0','0'],
            hotSingers:[],
            allSonglists:[],
            allRanklists:[],
            ranklist:'',//记录当前的排行榜id
        }
    },
    components:{
        MusicPlayer,
        MusicHeader,
        loginedHeader,
    },
    methods:{
        selected(){
            console.log(this.songlist_selected[0])
            if (this.songlist_selected[0] == 1) {
                // console.log(this.$refs.hot.style.fontSize)
                this.$refs.hot.style.fontWeight = "600"
                this.$refs.hot.style.color = "rgb(236, 157, 9)"
            }else if (this.songlist_selected[1] == 1) {
                this.$refs.Chinese.style.fontWeight = "600"
                this.$refs.Chinese.style.color = "rgb(236, 157, 9)"
            }  
        },
        select_songlist(sort){
            if(sort==1){
                let selected = ['1','0','0','0','0']
                this.songlist_selected = selected
                this.findRanklist('1')
            }else if(sort==2){
                let selected = ['0','1','0','0','0']
                this.songlist_selected = selected
                this.findRanklist('3')
            }else if(sort==3){
                let selected = ['0','0','1','0','0']
                this.songlist_selected = selected
                this.findRanklist('0')
            }else if(sort==4){
                let selected = ['0','0','0','1','0']
                this.songlist_selected = selected
                this.findRanklist('31')
            }else if(sort==5){
                let selected = ['0','0','0','0','1']
                this.songlist_selected = selected
            }
        },
        // 查热门歌手
        findHotSingers(){
            let url = '/top/artists'
            let param = {}
            this.common.requestMusicData(url,param).then(resp => {
                if(resp && resp.status == 200){
                    this.hotSingers = resp.data.artists
                    // console.log(resp.data.artists)
                }
            })
        },
        // 查所有歌单
        findAllSonglists(){
            let url = '/top/playlist'
            let param = {}
            this.common.requestMusicData(url,param).then(resp => {
                if(resp && resp.status == 200){
                    this.allSonglists = resp.data.playlists
                    // console.log(resp.data.playlists)
                }
            })
        },
        // 查排行榜
        findRanklist(idx){
            this.ranklist = idx
            let url = '/top/list'
            let param = idx
            this.common.requestMusicData(url,param).then(resp => {
                if(resp && resp.status == 200){
                    this.allRanklists = resp.data.playlist.tracks
                    console.log(resp.data.playlist.tracks)
                }
            })
        },
        // 播放音乐
        playMusic(index,listid){
            // this.$refs.musicPlayer.thisMusicIndex = index
            this.$refs.musicPlayer._getMusicType(listid,'rank')
            this.$refs.musicPlayer.ListPlay(index)
            console.log(index)
            console.log(111)
            console.log(this.$refs.musicPlayer.playState)
            // console.log(this.$refs.musicPlayer.thisMusicIndex)
            // this.$refs.musicPlayer._getInfo()
            this.$refs.musicPlayer.Player()
        },
    },
    mounted(){
        // this.selected()
        this.findHotSingers()
        this.findAllSonglists()
        this.findRanklist('1')
    }
}
</script>

<style scoped>
.music-content{
    width: 1040px;
    margin: 40px auto;
}
.music-left{
    float: left;
    width: 675px;
}
.music-right{
    float: right;
    width: 300px;
}
.music-left-scroll img{
    width: 675px;
    /* height: 250px; */
}
.music-left-musician{
    margin: 25px 0;
}
.music-left-musician-title{
    font-size: 17px;
    border-bottom: 2px solid rgb(236, 157, 9);
    line-height: 30px;
}
.music-left-musician1{
    background: #F5F7F8;
}
.music-left-musician1 ul{
    list-style: none;
    padding: 15px 0 0 30px;
    margin: 0;
}
.music-left-musician1 li{
    display: inline-block;
    margin-right: 20px;
}
.music-left-musician1 a{
    text-decoration: none;
    color: rgb(207, 143, 4);
}
.musician-name{
    width: 130px;
    text-align: center;
}
.musician-cover{
    height: 125px;
    width: 125px;
    overflow: hidden;
    background: rgba(85,83,83,0.2);
    background: #FFFFFF;
    background-repeat: no-repeat;
    background-size: contain;
    border: 5px solid #FFFFFF;
    box-shadow: 0 0 0 1px #DFDCDC;
    margin-bottom: 10px;
}
.musician-cover img{
    height: 125px;
    width: 125px;
}
.musician-hot{
    color: rgb(192, 184, 184);
    font-size: 14px;
    margin:10px auto 20px;
    width: 75px;
    height: 19px;
}
.musician-hot img{
    width: 13px ;
    height: 18px ;
    float: left;
    margin-right: 3px;
    margin-top: -1px;
}
.music-left-musician-title ul{
    display: inline;
    list-style: none;
    padding: 0;
    margin: 0;
    font-size: 13px;
}
.hotlist{
    margin-left: 20px;
}
.before-select{
    font-size: 14px;
    color: rgb(112, 108, 108);
}
.after-select{
    font-size: 14px;
    font-weight: 600;
    color: rgb(236, 157, 9);
}
.music-left-musician-title li{
    display: inline-block;
}
.music-left-musician-title a{
    text-decoration: none;
}
.music-left-musician-title a:hover{
    color: rgb(207, 143, 4);
}
.music-left-songlist1{
    background: #F5F7F8;
}
.music-left-songlist1 ul{
    list-style: none;
    padding: 15px 0px 15px 15px;
    margin: 0;
}
.music-left-songlist1-li{
    display: inline-block;
    width: 315px;
    height: 166px;
    padding: 0 10px 15px 0;
}
.asonglist{
    display: inline-block;
    background-color: white;
    padding: 16px 13px 16px 13px;
    width: 289px;
    height: 134px;
}
.songlist-cover img{
    width: 41px;
    height: 41px;
}
.songlist-cover{
    float: left;
}
.songlist-content{
    float: left;
    margin-left: 10px;
}
.songlist-content1 ul{
    padding: 0;
    margin: 0;
}
.songlist-content1 li{
    display: block;
    font-size: 13px;
    color: black;
    line-height: 18px;
}
.songlist-content-title{
    margin-bottom: 10px;
    width: 200px;
    height: 20px;
    overflow: hidden;
    font-size: 15px;
    color: rgb(207, 143, 4);
}
.rank-list-title{
    font-size: 16px;
    border-bottom: 1px solid rgb(192, 191, 190);
    line-height: 30px;
}
.rank-list-class{
    margin: 5px 0;
}
.rank-list-class ul{
    list-style: none;
    padding: 0;
    margin: 0;
    display: inline;
}
.rank-list-class li{
    display: inline-block;
}
.rank-list-class a{
    text-decoration: none;
}
.rank-list-class a:hover{
    color: rgb(207, 143, 4);
}
.ranklist-song-cover{
    float: left;
}
.ranklist-song-name{
    float: left;
    margin-left: 10px;
}
.ranklist-song-rank{
    float: right;
    font-size: 35px;
    color: #E6E6E6;
    line-height: 45px;
}
.rank-list-content ul{
    margin-top: 10px;
}
.rank-list-content li{
    margin-bottom: 10px;
    height: 60px;
}
</style>

<style>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
  .el-carousel__button {

    width: 5px;
    height: 5px;
    border-radius: 5px;
}
</style>