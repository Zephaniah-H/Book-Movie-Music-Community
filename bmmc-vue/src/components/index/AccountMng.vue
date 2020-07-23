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
                <el-form ref="form" label-width="70px" style="margin-top:30px" class="form">
                    <el-form-item label="头像">
                        <img src="userInfo.cover" width="50" height="50">
                    </el-form-item>
                    <el-form-item label="昵称">
                        <el-input v-model="userInfo.username" size="small"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <!-- <span></span> -->
                        <a href="javascript:void(0)" style="color:#3577a9;font-size:13px;text-decoration:none" @click="changeVisible = true">修改密码</a>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio v-model="userInfo.gender" label="男">男</el-radio>
                        <el-radio v-model="userInfo.gender" label="女">女</el-radio>
                    </el-form-item>
                    <el-form-item label="手机">
                        <span style="font-size:13px;">{{userInfo.phone.slice(0,3)}}****{{userInfo.phone.slice(7,11)}}</span>
                    </el-form-item>
                    <el-form-item label="邮箱">
                        <el-input v-model="userInfo.email" size="small"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary">更改设置</el-button>
                    </el-form-item>
                </el-form>
                <!-- 修改密码弹出框 -->
                <el-dialog title="修改密码" :visible.sync="changeVisible" width="35%">
                    <el-form ref="form" label-width="70px">
                        <el-form-item label="旧密码">
                            <el-input v-model="userInfo.password" size="small"></el-input>
                        </el-form-item>
                        <el-form-item label="新密码">
                            <el-input v-model="userInfo.newpsw1" size="small"></el-input>
                        </el-form-item>
                        <el-form-item label="再次确认">
                            <el-input v-model="userInfo.newpsw2" size="small"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="changeVisible = false" size="small">取 消</el-button>
                        <el-button type="primary" @click="changeVisible = false" size="small">保存并重新登录</el-button>
                    </span>
                </el-dialog>
            </div>
        </div>
    </div>
</template>

<script>
import loginedHeader from './loginedHeader'
export default {
    data(){
        return{
            userInfo:
                {phone:'13888888888',}
            ,
            changeVisible:false,
        }
    },
    components:{
        loginedHeader,
    }
}
</script>

<style>
.el-input--small .el-input__inner {
    width: 400px;
}
</style>