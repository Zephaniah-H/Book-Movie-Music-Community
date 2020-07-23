<template>
  <div>
    <logined-header></logined-header>
    <read-header></read-header>
    <div class="conclude-read-content"><!-- 整块关于书的一些内容 -->
      <div class="before-read-content"><!-- 内容 -->
        <h1>{{bookInfo.bookname}}</h1> <!-- 这里是书名 -->
        <div class="read-content"><!-- 书的封面+信息+评分 -->
            <div class="read-cover">
                <img :src="bookInfo.cover" alt="活着">
            </div>
            <div class="read-info">
                <span>作者:&nbsp;</span>{{bookInfo.author}}<br>
                <span>出版社:&nbsp;</span>{{bookInfo.press}}<br>
                <span>出版年:&nbsp;</span>{{bookInfo.publication_year}}<br>
                <!-- <span>定价:&nbsp;</span>余华<br> -->
                <span>装帧:&nbsp;</span>平装<br>
                <span>ISBN:&nbsp;</span>9787506365437<br>
            </div>
            <div class="divider">
                <el-divider direction="vertical" style="height:50px;"></el-divider>
            </div>
            <div class="read-point">
                <div><font style="font-size:15px;color:rgb(124, 120, 120)">芝士评分</font></div>
                <div><font style="font-size:30px;">{{avgBookpoint}}</font></div>
                <div>
                    <el-rate
                    v-model="value"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value}">
                    </el-rate>
                </div>
                <div><font style="font-size:13px;color:rgb(105,101,126);">(477751人评价)</font></div>
            </div>
        </div>
        <div class="read-button"><!-- 试读+你的评分 -->
            <div class="read-action">
                <el-button type="primary">想读</el-button>
                <el-button type="primary" @click="goReadBook">试读</el-button>
                <el-button type="primary">读过</el-button>
            </div>
            <div class="read-rate">
                <span>评分:&nbsp;</span>
                <el-rate
                style="display:inline;"
                v-model="myPoint"
                :texts="rate_word"
                show-text
                @change="pointChange($event)">
                </el-rate>
            </div>
        </div>
        <div class="read-introduction"><!-- 内容简介 -->
            <h2>
                内容简介&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
            </h2>
            <div class="read-intro-content" style="font-size:13px;" v-html="bookInfo.bookinfo">{{bookInfo.bookinfo}}</div>
        </div>
        <div class="read-catalog"><!-- 目录 -->
            <h2>
                目录&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
            </h2>
            <div class="read-catalog-content">
                <ul>
                    <li>前言</li>
                    <li>第一章</li>
                    <li>第二章</li>
                    <li>第三章</li>
                    <li>第四章</li>
                    <li>第五章</li>
                </ul>
            </div>
            <div>
                ·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·
                <span style="font-size:13px">（<a href="javascript:void(0)">更多</a>）</span>
            </div>
        </div>
        <div class="read-comment"><!-- 书评 -->
            <h2>
                <span>活着的书评&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·</span>
                <span class="comment-button">
                    <el-button type="danger" icon="el-icon-edit" plain @click="dialogFormVisible = true">我要写书评</el-button>
                    <el-dialog title="添加书评：我读过这本书" :visible.sync="dialogFormVisible">
                        <el-form>
                            <el-form-item label="给个评价吧">
                                <el-rate
                                style="display:inline;"
                                v-model="myPoint"
                                :texts="rate_word"
                                show-text>
                                </el-rate>
                            </el-form-item>
                            <el-form-item>
                                <el-input placeholder="添加标题" v-model="newComment.comment_title"></el-input>
                                <el-divider></el-divider>
                            </el-form-item>
                            <el-form-item>
                                <quill-editor 
                                v-model="newComment.comment" 
                                ref="myQuillEditor" 
                                :options="editorOption" 
                                @blur="onEditorBlur($event)" 
                                @focus="onEditorFocus($event)"
                                @change="onEditorChange($event)">
                                </quill-editor>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogFormVisible = false">取 消</el-button>
                            <el-button type="primary" @click="addBookComment">确 定</el-button>
                        </div>
                    </el-dialog>
                </span>
            </h2>
            <div class="read-comment-content">
                <div class="read-comment-content-classify">
                    热门&nbsp;/&nbsp;最新
                </div>
                <el-divider></el-divider>
                <ul style="list-style:none;">
                    <li v-for="(item,index) in allBookComments" :key="item.index">
                        <div class="read-comment-content-user">
                            <div class="comment-userinfo">
                                <div class="user-icon">
                                    <img width="25px" height="25px" :src="item.user.icon">
                                </div>
                                <div class="user-name">
                                    {{item.user.username}}
                                </div>
                                <div class="user-rate">
                                    <el-rate
                                    v-model="item.bookpoint"
                                    disabled>
                                    </el-rate>
                                </div>
                                <div class="user-comment-time">
                                    {{item.comment_time}}
                                </div>
                            </div>
                        </div>
                        <div class="read-comment-content-title">
                            {{item.comment_title}}
                        </div>
                        <div class="read-comment-content-main" v-html="item.comment.slice(0,unfold[index].a)">
                            {{item.comment}} 
                        </div>
                        <div class="read-comment-content-all-main" style="font-size:13px" v-if="unfold[index].a === '300'">
                            ...(<a href="javascript:void(0)" @click="fold(index)">展开</a>)
                        </div>
                        <div class="read-comment-content-all-main" style="font-size:13px" v-if="unfold[index].a === '10000'">
                            ...(<a href="javascript:void(0)" @click="fold(index)">收起</a>)
                        </div>
                        <div class="read-comment-content-updown">
                            <div class="read-comment-content-up" @click="greatANDstep('great',index)">
                                <img width="12px" height="9px" src="https://img3.doubanio.com/f/zerkalo/536fd337139250b5fb3cf9e79cb65c6193f8b20b/pics/up.png">{{item.great}} 
                            </div>
                            <div class="read-comment-content-down" @click="greatANDstep('step',index)">
                                <img width="12px" height="9px" src="https://img3.doubanio.com/f/zerkalo/68849027911140623cf338c9845893c4566db851/pics/down.png">{{item.step}}
                            </div>
                        </div>
                        <el-divider></el-divider>
                    </li>
                </ul>
                
            </div>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
import ReadHeader from './ReadHeader'
import loginedHeader from '../index/loginedHeader'
  export default {
    components:{
        ReadHeader,
        loginedHeader,
    },
    data() {
      return {
        avgBookpoint:'',
        myPoint:'',
        bookpoint:[],
        newComment:[],
        unfold:[],
        // unfold1:'500',
        // unfold2:'10000',
        allBookComments:[],
        bookInfo:[],
        value: '',
        value2:'',
        rate_word:['很差', '较差', '还行', '推荐', '力荐'],
        uservalue:4,
        value3:'',
        dialogFormVisible: false,
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        content:'',
        editorOption:{
            placeholder: ''
        },
      }
    },
    mounted(){
        this.findOneBook(this.$store.state.book.bookid)
        this.findCommentBybookid(this.$store.state.book.bookid)
        this.getAvgBookpoint(this.$store.state.book.bookid)
    },
    methods:{
        getAvgBookpoint(bookid){
            let url = '/getAvgBookPoint'
            let params = {
                // order:'hot'
                bookid: bookid
            }
            this.common.requestData(url,params,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.avgBookpoint = resp.data
                }
            })
        },
        pointChange($event){
            this.dialogFormVisible = true
        },
        onEditorBlur(editor){//失去焦点事件    
 	    },
        onEditorFocus(editor){//获得焦点事件
        },
        onEditorChange({editor,html,text}){//编辑器文本发生变化
            //this.content可以实时获取到当前编辑器内的文本内容
            console.log(this.content);
        },
        // 查一本书信息
        findOneBook(bookid){
            let url = '/findOneBookByid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.bookInfo = resp.data
                    this.value = parseFloat(this.bookInfo.point) / 2
                }
            })
        },
        // 查1书的评论
        findCommentBybookid(bookid){
            // this.bookinfo.bookid = bookid
            // this.bookinfo.bookname = bookname
            let url = '/findBookCommentBybookid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.allBookComments = resp.data
                    // this.allBooks = []
                    for(let i = 0; i < this.allBookComments.length; i++){//数据库查出来的评分/2
                        this.allBookComments[i].bookpoint = parseFloat(this.allBookComments[i].bookpoint) / 2
                    }
                    if(this.unfold.length == this.allBookComments.length){
                        console.log(this.unfold)
                    }else{
                        for(let i = 0; i < this.allBookComments.length; i++){//500是读评论的字数
                            this.unfold.push({a: '300'})
                            // console.log(this.unfold.length)
                            // console.log(this.unfold)
                       }
                    }
                }
            })
        },
        // 跳转到在线阅读
        goReadBook(){
            const {href} = this.$router.resolve({ path: '/readbook', })
            window.open(href, '_blank')
        },
        // 评论折叠
        fold(index){
            this.unfold[index].a = this.unfold[index].a === '300'? '10000' : '300'
            // this.allBookComments[index].fold = this.allBookComments[index].fold === '500'? '10000' : '500'
            // console.log(this.unfold[index])
            // console.log(this.unfold1[0])
            // this.allBookComments[index].fold = '10000'
        },
        // 添加评论
        addBookComment(){
            let currentTime = this.common.getCurrentTime()
            let url = '/addBookComment'
            let param = {
                bookid: this.$store.state.book.bookid,
                userid: this.$store.state.user.userid,
                comment: this.newComment.comment,
                comment_title: this.newComment.comment_title,
                bookpoint: (this.myPoint)*2,
                comment_time: currentTime,
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.dialogFormVisible = false
                    this.myPoint = ''
                    this.newComment = []
                    this.$message.success('评论成功')
                }
            })
        },
        // 赞踩
        greatANDstep(type,idx){
            if(type === 'great'){
                let url = '/bookgreat'
                let param = {
                    bookcommentid: this.allBookComments[idx].bookcommentid,
                    great: parseInt(this.allBookComments[idx].great) + 1,
                }
                console.log(param.great)
                this.common.requestData(url,param,'post').then(resp => {
                    if(resp && resp.status == 200){
                        this.findCommentBybookid(this.$store.state.book.bookid)
                    }
                })
            }else if(type === 'step'){
                let url = '/bookstep'
                let param = {
                    bookcommentid: this.allBookComments[idx].bookcommentid,
                    step: parseInt(this.allBookComments[idx].step) + 1,
                }
                this.common.requestData(url,param,'post').then(resp => {
                    if(resp && resp.status == 200){
                        this.findCommentBybookid(this.$store.state.book.bookid)
                    }
                })
            }
        },
    }
  }
</script>

<style scoped>
.before-read-content{
    width: 1040px;
    margin: auto;
}
h1{
    font-size: 26px;
}
.read-cover img{
    width: 135px;
}
.read-content{
    width: 1040px;
    height: 205px;
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
.read-info span{
    color: rgb(124, 120, 120);
}
.read-point{
    float: left;
    /* margin-left: 350px; */
}
.divider{
    float: left;
    margin-left: 350px;
    margin-right: 10px;
}
.read-action{
    float: left;
    margin-right: 15px;
}
.read-action .el-button--primary {
    color: #757272;
    background-color: #f7df8c;
    border-color: #988646;
}
.read-action .el-button{
    padding: 5px 11px;
    font-size: 15px;
}
.read-rate{
    float: left;
    font-size: 15px;
    padding-top: 3px;
}
.read-button{
    height: 27px;
    margin-bottom: 30px;
}
.conclude-read-content{
    margin-top: 50px;
}
.conclude-read-content h2 {
    font: 17px Arial, Helvetica, sans-serif;
    color: rgb(211, 168, 12);
    margin: 12px 0 12px 0;
    padding-top: 5px;
    line-height: 150%;
}
.conclude-read-content ul{
    list-style: none;
    padding: 0;
    margin-bottom: 0;
}
.conclude-read-content li{
    font-size: 13px;
}
.comment-button{
    float: right;
}
.read-comment-content-classify{
    font-size: 13px;
}
.user-icon,.user-name,.user-rate,.user-comment-time{
    float: left;
    margin-right: 10px;
    font-size: 13px;
}

.user-comment-time{
    margin-left: 20px;
}
.read-comment-content-user{
    height: 28px;
    margin-bottom: 5px;
    margin-top: 5px;
}
.read-comment-content-title{
    font-size: 13px;
    margin-bottom: 5px;
    margin-top: 5px;
    color: rgb(80, 126, 212);
}
.read-comment-content-main{
    font-size: 12px;
}
/* .read-comment-content-main2{
    height: 100%;
    overflow: hidden;
    
} */
.read-comment-content-main p{
    margin:0;
}
.read-comment-content-all-main{
    margin-bottom: 10px;
}
.read-comment-content-updown{
    height: 17px;
    margin: 10px 0;
}
.read-comment-content-up,.read-comment-content-down{
    /* width:50px; */
    height:17px;
    float:left;
    font-size:11px;
    background-color: rgb(227, 233, 245);
    color: rgb(28, 83, 184);
    margin-right: 10px;
}
</style>
<style>
.read-point .el-rate__text {
    display: none;
}
.el-divider--vertical {
    height: 6.5em;
}
.el-divider {
    background-color: rgb(216, 213, 213);
}
.el-rate__icon {
    margin-right: -5px;
}
.comment-button .el-button{
    padding: 5px 9px;
    font-size: 15px;
}
.comment-button .el-dialog__header {
    padding: 15px;
    background-color: #FCF9EE;
}
.comment-button .el-dialog__title {
    line-height: 24px;
    font-size: 17px;
    color: rgb(207, 143, 4);
}
.comment-button .el-rate {
    margin-left: -8px;
}
.comment-button .el-input__inner {
    border: transparent;
    padding: 0;
    font-size: 19px;
    font-weight: bold;
}
.comment-button .el-divider--horizontal {
    margin: 0;
}
.comment-button .ql-container.ql-snow {
    border: 0;
}
.comment-button .ql-toolbar.ql-snow {
    border: 0;
    padding: 0px;
}
.comment-button .ql-editor {
    padding: 20px 5px;
}
.comment-button .el-dialog__footer {
    padding: 20px 20px 20px;
    background-color: #FCF9EE;
}
</style>