<template>
    <div>
        <logined-header></logined-header>
        <div style="background-color:#FCF9EE;">
            <div style="width:1040px;margin:auto">
               <img src="http://localhost:8888/api/bookCover/zhishi.png"> 
            </div>
        </div>
        <div>
            <div style="width:1040px;margin:auto">
                 <div style="font-size:23px;color: #909399;margin:20px 0 20px 0">我的订单</div>
                 <el-tabs v-model="activeName">
                    <el-tab-pane label="已支付" name="first">
                        <el-table
                            ref="singleTable"
                            :data="paid"
                            highlight-current-row
                            style="width: 100%">
                            <el-table-column
                            prop="order_id"
                            width="220"
                            label="订单号">
                            </el-table-column>
                            <el-table-column
                            prop="order_name"
                            label="订单名">
                            </el-table-column>
                            <el-table-column
                            prop="order_type"
                            label="订单类型">
                            </el-table-column>
                            <el-table-column
                            prop="order_price"
                            width="100"
                            label="价格(元)">
                            </el-table-column>
                            <el-table-column
                            prop="order_time"
                            label="下单时间">
                            </el-table-column>
                            <el-table-column property="order_state" label="订单状态">
                                <template slot-scope="scope">
                                    <el-tag :type="scope.row.order_state==='已支付'?'success':(scope.row.order_state==='未支付'?'danger':'')">
                                        {{scope.row.order_state}}
                                    </el-tag>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                    <el-tab-pane label="未支付" name="second">
                        <el-table
                            ref="singleTable"
                            :data="unpaid"
                            highlight-current-row
                            style="width: 100%">
                            <el-table-column
                            width="220"
                            prop="order_id"
                            label="订单号">
                            </el-table-column>
                            <el-table-column
                            prop="order_name"
                            label="订单名">
                            </el-table-column>
                            <el-table-column
                            prop="order_type"
                            label="订单类型">
                            </el-table-column>
                            <el-table-column
                            width="100"
                            prop="order_price"
                            label="价格(元)">
                            </el-table-column>
                            <el-table-column
                            prop="order_time"
                            label="下单时间">
                            </el-table-column>
                            <el-table-column label="操作">
                                <template slot-scope="scope">
                                    <el-button plain type="primary" size="small" @click="buyTicket(scope.$index)">去支付</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                </el-tabs>
                 
            </div>
        </div>
    </div>
</template>

<script>
import loginedHeader from './loginedHeader'
// import {getUrlKey} from '../../common.js'
export default {
    inject:['reload'],
    data(){
        return{
            activeName:'first',
            paid: [],
            unpaid: [],
            // out_trade_no:'' //地址栏上面的订单号
        }
    },
    components:{
        loginedHeader,
    },
    mounted(){
        // if(this.$store.state.order.length > 0){
        //     this.paySuccess()
        // }
        let a = this.$store.state.user.userid
        this.findOrderByUseridType(a,'已支付')
        this.findOrderByUseridType(a,'未支付')
    },
    methods:{
        findOrderByUseridType(userid,order_state){
            let url = '/findOrderByUseridState'
            let param = {
                userid: userid,
                order_state: order_state,
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    // console.log(order_state)
                    if(order_state == '已支付'){
                        this.paid = resp.data
                    }else if(order_state == '未支付'){
                        this.unpaid = resp.data
                    }
                }
            })
        },
        //转到支付宝页面并创建未支付订单
        buyTicket(idx){
            let order = {
                order_id: this.unpaid[idx].order_id
            }
            this.$store.commit('buy',order)
            let param = {
                order_id: order.order_id,
                order_price: this.unpaid[idx].order_price,
                order_name: this.unpaid[idx].order_name,
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
        // paySuccess(){
        //     let url = '/paySuccess'
        //     let param = {
        //         order_id: this.$store.state.order.order_id
        //     }
        //     this.common.requestData(url,param,'post').then(resp => {
        //         if(resp && resp.status == 200){
        //             this.out_trade_no = getUrlKey('out_trade_no')
        //             if(param.order_id == this.out_trade_no){
        //                 this.$store.commit('buy',[])
        //             }
        //             console.log(this.total_amount)
        //             this.$confirm('支付成功！', '提示', {
        //                 confirmButtonText: '确定',
        //                 cancelButtonText: '取消',
        //                 type: 'success'
        //               }).then(() => {
        //                     this.reload()
        //                 })
        //         }
        //     })
        // },
    }
}
</script>

<style>
</style>