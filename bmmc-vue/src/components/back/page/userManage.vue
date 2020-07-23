<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-user"></i> 用户管理
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
                <!-- <el-select v-model="query.address" placeholder="地址" class="handle-select mr10" size="small">
                    <el-option key="1" label="广东省" value="广东省"></el-option>
                    <el-option key="2" label="湖南省" value="湖南省"></el-option>
                </el-select> -->
                <el-input v-model="userSearch" placeholder="用户名、手机、邮箱等" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchUser">搜索</el-button>
            </div>
            <el-table
                :data="allUser.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="userid" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column label="账户余额">
                    <template slot-scope="scope">￥&nbsp;{{scope.row.balance}}</template>
                </el-table-column>
                <el-table-column label="头像(查看大图)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.icon"
                            :preview-src-list="[scope.row.icon]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>
                <el-table-column prop="gender" label="性别"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-edit"
                            @click="editUser(scope.row.userid)"
                        >编辑</el-button>
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteUser(scope.row.userid)"
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
                    :total="allUser.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="头像">
                    <img :src="userInfo.icon">
                </el-form-item>
                <el-form-item label="用户名">
                    <el-input v-model="userInfo.username"></el-input>
                </el-form-item>
                <el-form-item label="手机">
                    <el-input v-model="userInfo.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="userInfo.email"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-input v-model="userInfo.gender"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="updateUser" size="small">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    inject:['reload'],
    data() {
        return {
            currentPage: 1,
            pageSize: 6,
            allUser:[],
            userSearch:'',
            editVisible: false,
            selected_userid:'',
            userInfo:[],
            multipleSelection:[],
        };
    },
    mounted() {
        this.findAllUser()
    },
    methods: {
        //  批量删除
        delAllSelection(){
            const length = this.multipleSelection.length;
            this.$confirm('确定要删除吗？', '提示', {type: 'warning'}).then(() => {
                for (let i = 0; i < length; i++) {
                    console.log('shanchu'+this.multipleSelection[i].userid)
                    this.deleteUsers(this.multipleSelection[i].userid)
                }
                this.$message.success("删除成功！")
                this.multipleSelection = [];
                this.reload()
            })
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
            const length = this.multipleSelection.length;
            for (let i = 0; i < length; i++) {
                console.log(this.multipleSelection[i].userid)
            }
        },
        // 分页导航
        handlePageChange(currentPage) {
            console.log(currentPage)
            this.currentPage = currentPage
        },
        //  查询所有用户
        findAllUser(){
            let url = '/findAllUser'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allUser = resp.data
                }
            })
        },
        //  模糊查询用户
        searchUser(){
            if(this.userSearch == ''){
                this.findAllUser()
            }else{
                let url = "/findUserLikeSth"
                let param = {username: this.userSearch}
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allUser = resp.data
                    }
                }) 
            }
        },
        //  查一个用户的信息
        editUser(userid){
            this.editVisible = true
            let url = '/findByid'
            let param = {userid: userid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.userInfo = resp.data
                    console.log(this.userInfo)
                }
            })
        },
        //  修改用户信息
        updateUser(userid){
            let url = '/updateUserByid'
            let param = {
                userid: this.userInfo.userid,
                username: this.userInfo.username,
                phone: this.userInfo.phone,
                email: this.userInfo.email,
                gender: this.userInfo.gender,
                icon: this.userInfo.icon
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.reload()
                    this.$message.success('修改成功')
                }
            })
        },
        //  删除用户
        deleteUser(userid){
            let url = '/deleteUserByid'
            let param = {userid: userid}
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
        //  删除用户不提示
        deleteUsers(userid){
            let url = '/deleteUserByid'
            let param = {userid: userid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    // this.findAllOrder()
                    // this.$message.success('删除成功');
                }
            })
        }
    }
}
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
