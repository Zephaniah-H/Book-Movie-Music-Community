<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-order"></i> 订单管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    size="small"
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-input v-model="searchOrder" placeholder="订单号、订单名、订单状态等" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="orderSearch">搜索</el-button>
            </div>
            <el-table
                :data="allOrder.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="order_id" label="订单号" align="center"></el-table-column>
                <el-table-column prop="userid" label="用户ID" width="100"></el-table-column>
                <el-table-column prop="order_name" label="订单名"></el-table-column>
                <el-table-column prop="order_type" label="订单类型" width="120"></el-table-column>
                <el-table-column label="价格" width="120">
                    <template slot-scope="scope">
                        ￥&nbsp;{{scope.row.order_price}}.00
                    </template>
                </el-table-column>
                <el-table-column prop="order_time" label="下单时间"></el-table-column>
                <el-table-column label="订单状态" align="center" width="120">
                    <template slot-scope="scope">
                        <el-tag
                            :type="scope.row.order_state==='已支付'?'success':(scope.row.order_state==='未支付'?'danger':'')"
                        >{{scope.row.order_state}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="120" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteOrderByid(scope.row.order_id)"
                        >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="currentPage"
                    :page-size="pageSize"
                    :total="allOrder.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    inject:['reload'],
    data() {
        return {
            currentPage: 1,
            pageSize: 6,
            allOrder:[],
            searchOrder:'',
            orderCount:'',
            multipleSelection:[],
        };
    },
    mounted() {
        this.findAllOrder();
    },
    methods: {
        // 触发搜索按钮
        orderSearch() {
            if(this.searchOrder == ''){
                this.findAllOrder()
            }else{
                let url = "/findOrderLikeSth"
                console.log(this.searchOrder)
                let param = {order_name: this.searchOrder}
                console.log(param)
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allOrder = resp.data
                    }
                })
            }
            
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
            const length = this.multipleSelection.length;
            for (let i = 0; i < length; i++) {
                console.log(this.multipleSelection[i].order_id)
            }
        },
        //  多选批量删除
        delAllSelection() {
            const length = this.multipleSelection.length;
            this.$confirm('确定要删除吗？', '提示', {type: 'warning'}).then(() => {
                for (let i = 0; i < length; i++) {
                    console.log('shanchu'+this.multipleSelection[i].order_id)
                    this.deleteOrders(this.multipleSelection[i].order_id)
                }
                this.$message.success("删除成功！")
                this.multipleSelection = [];
                this.reload()
            })
        },
        // 分页导航
        handlePageChange(currentPage) {
            console.log(currentPage)
            this.currentPage = currentPage
        },
        //  查询所有订单
        findAllOrder(){
            let url1 = '/findAllOrder'
            let param1 = {}
            // let url2 = '/countOrder'
            // let param2 = {}
            this.common.requestData(url1,param1,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allOrder = resp.data
                }
            })
            // this.reqM1Service(url2,param2,'get').then(resp => {
            //     if(resp && resp.status == 200){
            //         this.orderCount = resp.data
            //     }
            // })
        },
        // 按订单id删除订单
        deleteOrderByid(orderid){
            let url = '/deleteOrderByid'
            let param = {order_id: orderid}
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(()=>{
                this.common.requestData(url,param,'post').then(resp => {
                    console.log(resp)
                    if(resp && resp.status == 200){
                        this.reload()
                        this.$message.success('删除成功')
                    }
              })
            })
        },
        //  批量删除
        deleteOrders(orderid){
            let url = '/deleteOrderByid'
            let param = {order_id: orderid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    // this.findAllOrder()
                    // this.$message.success('删除成功');
                }
            })
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
