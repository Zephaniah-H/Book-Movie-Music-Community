<template>
    <div>
        <button @click="getMusic">获取</button>
        <ul>
            <li v-for="item in music.slice(0,10)" :key="item">{{item.name}}，{{item.id}}</li>
        </ul>
        <button @click="getaMusic">获
        </button>
        <a :href="listen">听</a>
        <button @click="open">播放</button>
        <i class="el-icon-zf-good"></i>
        <video id="music" autoplay="autoplay" :src="listen" name="media"></video>
        <button @click="a"></button>
        <input id = 'button1'  type="button" name="copycontent" @click="copy1" title="点击复制文字" value="点击复制文字内容1">
        <br>
        <div @mouseup="a">哒哒哒哒哒哒多多多多多多多多多多多多多多多多多多多多多多多多多多多多多多</div>
        <template>
            <div @dblclick="getSelectText">鼠标双击文本全部选择</div>  
        </template>

        <br>
        <!--方法二：自定义浮层-->
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id = 'button2' type="button" name="copycontent" @click="copy2" @mouseover="overShow" @mouseout="outHide" value="点击复制文字内容2">
        
        <div id="showDiv" style="position: absolute; background-color: yellow; border: 1px solid black;"></div>
        <div id="aaa" v-html="data">{{data}}</div>
        <el-input
        type="textarea"
        :rows="2"
        placeholder="请输入内容"
        >
        </el-input>
        <el-pagination
            layout="prev, pager, next"
            :total="50">
        </el-pagination>
        <button @click="pay">支付</button>
        <div v-html="form" id="paymentSubmit">{{form}}</div>
        <div>{{ran}}</div>
        <div>{{avgBookpoint}}</div>
    </div>
</template>

<script>
import $ from 'jquery'
export default {
    data(){
        return{
            ran:'',
            form:'',
            music:[],
            listen:'',
            aaa:[],
            data:'<p>hello</p>',
            htmls:'',
            avgBookpoint:'',
        }
    },
    mounted(){
        // $('#aaa').html("<p>hello</p>")
        this.ran = this.common.getRandom()
        this.getAvgBookpoint(1)
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
        pay(){
            let url = '/alipay'
            let param = {}
            this.common.requestData(url,param,'get').then(res => {
                if(res && res.status === 200){
                     //返回参数 
                //  let routerData = this.$router.resolve({path:'/b',query:{htmls:res.data.result}})
                //  this.htmls = res.data.result

                //  //打开新页面
                //  window.open(routerData.href,'_ blank')
                //   const div = document.createElement('div');
                //   div.innerHTML = this.htmls;
                //  document.body.appendChild(div);
                //  document.forms[0].submit();
                console.log(res)
                var newwindow = window.open("#","_blank");
                newwindow.document.write(res.data);

                }
            })
        },
        getMusic(){
            let url = '/top/list'
            let params = {
                // order:'hot'
                idx:'1'
            }
            this.reqM2Service(url,params).then(resp => {
                console.log(resp.data)
                this.music = resp.data.playlist.tracks
            })
        },
        getaMusic(){
            let url = '/comment/playlist'
            let params = {
                id:'2819914042',
                limit:70,
                // offset:0
            }
            this.reqM2Service(url,params).then(resp => {
                console.log(resp)
                this.listen = resp.data.data[0].url
                console.log(this.listen)
            })
        },
        open(){
            window.open(this.listen)
        },
        returnMain()
    {
        window.returnValue = "====return from sub====";
        window.close();
    },
        copy1()
    {
        console.log("btn1")
        var btn1 = document.getElementById('button1');
        console.log(btn1)
        console.log("btn1")
        //复制文字内容
        window.clipboardData().setData("Text",btn1.value);
        alert("复制成功!");
    },
        copy2()
    {
        var btn2 = document.getElementById('button2');
        //复制文字内容
        window.clipboardData().setData("Text",btn2.value);
        alert("复制成功!");
    },
        overShow() {
        var showDiv = document.getElementById('showDiv');
        showDiv.style.left = event.clientX;
        showDiv.style.top = event.clientY;
        showDiv.style.display = 'block';
        showDiv.innerHTML = '点击复制文字';
    },
        outHide() {
        var showDiv = document.getElementById('showDiv');
        showDiv.style.display = 'none';
        showDiv.innerHTML = '';
    },
    a (){
        console.log(111)
        this.aaa[0] = window.getSelection().toString()
        this.aaa[1] = window.getSelection().anchorOffset; //开始位置
        this.aaa[2] = window.getSelection().focusOffset;  //结束位置
        console.log(this.aaa)
        window.getSelection
    },
    getSelectText (e) {
            if  (document.body.createTextRange)  {
                // IE兼容
                var range = document.body.createTextRange();
                console.log(range)
                range.moveToElementText(e.target);
                range.select();
            } else {
                let selection = window.getSelection();
                let range = document.createRange();
                console.log(range)
                console.log(selection)
                range.selectNodeContents(e.target);
                selection.removeAllRanges();
                selection.addRange(range);  
            }
       }
    }
}
</script>
<style scoped>
ul{
    list-style: none;
}
</style>