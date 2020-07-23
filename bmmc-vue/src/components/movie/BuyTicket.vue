<template>
    <div style="background-color:rgb(40,34,35)">
        <div class="buyTicket">
            <div class="ticket-movie">
                <div class="ticket-movie-cover">
                    <img width="126px" height="176px" :src="movieInfo.cover">
                </div>
                <div class="ticket-movie-info">
                    <div class="ticket-movie-name">{{movieInfo.moviename}}</div>
                    <div class="ticket-movie-nickname"></div>
                    <div class="ticket-movie-type">{{movietype}} | {{movieInfo.country}} | {{movieInfo.duration}}</div>
                    <div class="ticket-movie-showtime">{{movieInfo.releaseday}}上映</div>
                    <div class="ticket-movie-star">主演：{{movieInfo.leadingrole}}</div>
                </div>
            </div>
            <div class="movie-site">

            </div>
            <div class="movie-seat" @dblclick="modifyWidth" ref="movieseat">
                <div>
                     <el-select v-model="time" placeholder="2020-06-07 13:50:00">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </div>
                <div style="margin-bottom:40px;margin-left:365px;margin-right:306px;text-align:center;background-color:#999;padding-bottom:10px;">1号厅 银幕</div>
                <ul>
                    <li  v-for="(item,index) in seats" :key="item.index">
                        <button class="seat" :class="[seats1[index].s == 1 || item.is_reserve == 1 ? 'selected-seat':'']" @click="isActive(index)"></button>
                        <span style="color:#999;margin-left:-9px" v-if="index==11 || index==34 || index==57 || index==80 || index==103 || index==126 || index==149 || index==172 || index==195 || index==218">|&nbsp;</span>
                    </li>
                </ul>
                <div>当前已选(双击可看全座)：
                    <ul>
                        <li style="display:inline-block;" v-for="(item,index) in seats" :key="item.index">
                            <span v-if="item.is_reserve!=1 && seats1[index].s==1">{{item.row}}排{{item.col}}座,</span>
                        </li>
                    </ul>
                </div>
                <div style="text-align:center"><button @click="payVisible = true">提交</button><button>返回</button></div>
            </div>
            <el-dialog title="请选择:" :visible.sync="payVisible" width="20%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="支付方式">
                    <el-radio v-model="radio" label="1">支付宝</el-radio>
                </el-form-item>
                <el-form-item label="支付金额">
                    <div style="color:red;font-weight:bold;">￥{{movieInfo.movieprice}}</div>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="payVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="buyTicket" size="small">确 定</el-button>
            </span>
        </el-dialog>
        </div>
    </div>
</template>

<script>
import {getUrlKey} from '../../common.js'
export default {
    inject:['reload'],
    data(){
        return{
            radio:'1',
            payVisible: false,
            movieInfo: [],
            movietype:'',
            idx: 0,
            seats: [], //里面有数据库的座位是否被选
            seats1: [], //里面有页面的座位是否被选
            selectedSeat: [],//被选的座位，最多同时选四个座位
            myOrder: [],
            out_trade_no:'', //地址栏上面的订单号
            options:[
                {lebel:'2020-06-07 13:50:00',value:'2020-06-07 13:50:00'},
                {lebel:'2020-06-07 19:00:00',value:'2020-06-07 19:00:00'}
            ],
            time:'',
        }
    },
    mounted(){
        this.findOneMovie(this.$store.state.movie.movieid)
        this.findSeatBysidhid('1','1')
        console.log(Number(this.$store.state.order.order_id) > 0)
        if(Number(this.$store.state.order.order_id) > 0){
            this.paySuccess()
        }
    },
    methods:{
        modifyWidth(){
            if(this.$refs.movieseat.style.width == "1190px"){
                this.$refs.movieseat.style.width = "943px"
                this.$refs.movieseat.style.marginLeft = "0px"
            }else{
                this.$refs.movieseat.style.width = "1190px"
                this.$refs.movieseat.style.marginLeft = "-120px"
            }
        },
        isActive(index){
            this.seats1[index].s = this.seats1[index].s == 0 ? 1 : 0
            // for(let i = 0; i < this.$store.state.seat.length; i++){
                        console.log(this.$store.state.seat)
                        console.log(this.$store.state.order.order_id != '')
                    // }
                    let order={
                        order_id: '2020060513402829555'
                    }
                    this.$store.commit('buy',order)
                    this.$store.commit('buy',order)
        },
        // 查一部电影信息
        findOneMovie(movieid){
            let url = '/findOneMovieByid'
            let param = {movieid: movieid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.movieInfo = resp.data
                    this.value = parseFloat(this.movieInfo.point) / 2
                    let a = new RegExp("/","g")
                    this.movietype = this.movieInfo.movietype.replace(a,'|')
                }
            })
        },
        //查影厅座位
        findSeatBysidhid(hallid,sid){
            let url = '/findSeatBysidhid'
            let param = {
                hallid: hallid,
                schedule_id: sid,
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.seats = resp.data
                    if(this.seats1.length != this.seats.length){
                        for(let i = 0; i < this.seats.length; i++){
                            this.seats1.push({s:'0'})
                        }
                        // console.log(this.seats1)
                    }
                }
            })
        },
        //座位被选了
        updataHallSeat(seatid){
            let url = '/updataHallSeat'
            let param = {
                seatid: seatid
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.findSeatBysidhid(1,1)
                }
            })
        },
        //转到支付宝页面并创建未支付订单
        buyTicket(){
            this.selectedSeat = []
            for(let i = 0; i < this.seats.length; i++){
                if(this.seats[i].is_reserve != this.seats1[i].s && this.seats[i].is_reserve != '1'){
                    this.selectedSeat.push(this.seats[i].seatid)
                }
            }
            // console.log(this.selectedSeat)
            let seat = {
                seat1: this.selectedSeat[0],
                seat2: this.selectedSeat[1],
                seat3: this.selectedSeat[2],
                seat4: this.selectedSeat[3],
            }
            this.$store.commit('selectSeat',seat)//把选好的座位存到store里
            // console.log(this.$store.state.seat)
            let order = {
                order_id: this.common.getRandom()
            }
            this.$store.commit('buy',order)
            console.log(this.$store.state.order)
            let param = {
                order_id: order.order_id,
                order_state:'',
                userid: this.$store.state.user.userid,
                order_type: '电影',
                order_time: this.common.getCurrentTime(),
                order_price: this.movieInfo.movieprice,
                order_name: this.movieInfo.moviename + '电影票'
            }
            console.log(param)
            this.common.requestData('/alipay',param,'post').then(resp => {
                if(resp && resp.status == 200){
                    var newwindow = window.open("#","_blank");
                    newwindow.document.write(resp.data);
                }
            })
        },
        // 支付成功
        paySuccess(){
            let url = '/paySuccess'
            let param = {
                order_id: this.$store.state.order.order_id
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.out_trade_no = getUrlKey('out_trade_no')
                    if(param.order_id == this.out_trade_no){
                        this.updataHallSeat(this.$store.state.seat.seat1)
                        this.updataHallSeat(this.$store.state.seat.seat2)
                        this.updataHallSeat(this.$store.state.seat.seat3)
                        this.updataHallSeat(this.$store.state.seat.seat4)
                        this.$store.commit('selectSeat',[])
                        this.$store.commit('buy',[])
                        this.$confirm('支付成功！', '提示', {
                            confirmButtonText: '确定',
                            cancelButtonText: '取消',
                            type: 'success'
                        }).then(() => {
                                this.reload()
                            })
                    }
                }
            })
        },
    }
}
</script>

<style scoped>
.buyTicket{
    width: 943px;
    margin: auto;
}
.ticket-movie{
    background-color: rgb(95,103,105);
    height: 212px;
}
.ticket-movie-cover{
    float: left;
    margin-top: 20px;
    margin-left: 20px;
}
.ticket-movie-info{
    float: left;
    margin-top: 30px;
    margin-left: 20px;
}
.ticket-movie-name{
    color: #fff;
    font-size: 19px;
    font-weight: bold;
}
.ticket-movie-nickname{
    font-size: 15px;
    color: hsla(0,0%,100%,.8);
    margin-bottom: 25px;
}
.ticket-movie-type,.ticket-movie-showtime,.ticket-movie-star{
    font-size: 13px;
    color: hsla(0,0%,100%,.8);
    line-height: 25px;
}
.movie-seat{
    background-color: white;
    overflow-x: scroll;
    overflow-y: hidden;
    /* width: 1190px;
    margin-left: -120px; */
}
.movie-seat ul{
    margin-top: 11px;
    margin-left: 15px;
    width: 1180px;
}
.movie-seat li{
    display: inline-block;
}
.seat{
    width: 40px;
    height: 40px;
    margin-right: 11px;
    margin-bottom: 11px;
}
.selected-seat{
    width: 40px;
    height: 40px;
    margin-right: 11px;
    margin-bottom: 11px;
    background-color: tomato;
}
.seat:hover{
    background-color: tomato;
}
</style>
<style>

</style>