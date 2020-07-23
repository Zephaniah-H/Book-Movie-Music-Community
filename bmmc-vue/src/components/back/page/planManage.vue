<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-movie"></i> 电影管理
                </el-breadcrumb-item>
                <el-breadcrumb-item>排片管理</el-breadcrumb-item>
                <el-breadcrumb-item v-if="allSchedules.length > 0">《{{moviename}}》的所有排片</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    v-if="allMovies.length <= 0"
                    size="small"
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-input v-model="search" placeholder="电影名" class="handle-input mr10" size="small"></el-input>
                <!-- <el-button size="small" type="primary" icon="el-icon-search" @click="searchMovie" v-if="allMovies.length > 0">搜索</el-button> -->
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchSchedule">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-plus" @click="addVisible = true" v-if="allMovies.length <= 0">添加</el-button>
                <el-button size="small" type="primary" icon="el-icon-d-arrow-left" @click="goBack" v-if="allMovies.length <= 0">返回</el-button>
            </div>
            <!-- 电影 -->
            <el-table
                :data="allMovies.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="movieTable"
                ref="movieTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <!-- <el-table-column type="selection" width="55" align="center"></el-table-column> -->
                <el-table-column prop="movieid" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="moviename" label="电影名"></el-table-column>
                <el-table-column prop="director" label="导演"></el-table-column>
                <el-table-column prop="cover" label="封面(查看大图)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.cover"
                            :preview-src-list="[scope.row.cover]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="电影状态" align="center">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.moviestate === '正在上映' ? 'success' : 'info'">{{scope.row.moviestate}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="primary"
                            size="small"
                            plain
                            @click="findMovieSchedule(scope.row.movieid,scope.row.moviename)"
                        >查看排片</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 排片 -->
            <el-table
                :data="allSchedules.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="scheduleTable"
                ref="scheduleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="schedule_id" label="排片ID" width="70" align="center"></el-table-column>
                <el-table-column label="电影名">{{moviename}}</el-table-column>
                <el-table-column prop="release_time" label="放映时间"></el-table-column>
                <el-table-column label="影厅">
                    <template slot-scope="scope">
                        {{scope.row.hallid}}号厅
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-edit"
                            @click="findOneSchedule(scope.row.schedule_id)"
                        >编辑</el-button>
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteSchedule(scope.row.schedule_id)"
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
                    :total="allMovies.length > 0 ? allMovies.length : allSchedules.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="上映时间">
                    <el-input v-model="scheduleInfo.release_time" size="small"></el-input>
                </el-form-item>
                <el-form-item label="影厅">
                    <el-input v-model="scheduleInfo.hallid" size="small"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="updateSchedule" size="small">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 添加弹出框 -->
        <el-dialog title="新增" :visible.sync="addVisible" width="30%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="上映时间">
                    <el-date-picker
                    v-model="newSchedule.release_time"
                    type="datetime"
                    placeholder="选择日期时间"
                    value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="影厅">
                    <el-select v-model="newSchedule.hallid" placeholder="请选择">
                        <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="addSchedule" size="small">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import $ from 'jquery'
export default {
    inject:['reload'],
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            multipleSelection: [],
            editVisible: false,
            addVisible: false,
            allMovies:[],
            allSchedules:[],
            scheduleInfo:[],
            newSchedule:[],
            search:'',
            movieid:'',
            moviename:'',
            options:[{
                label:'1号厅',
                value: 1
            }]
        };
    },
    mounted(){
        this.findAllMovies()
    },
    methods: {
        //  批量删除
        delAllSelection(){
            const length = this.multipleSelection.length;
            this.$confirm('确定要删除吗？', '提示', {type: 'warning'}).then(() => {
                for (let i = 0; i < length; i++) {
                    this.deleteSchedules(this.multipleSelection[i].schedule_id)
                }
                this.$message.success("删除成功！")
                this.multipleSelection = [];
            })
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        // 分页导航
        handlePageChange(currentPage) {
            this.currentPage = currentPage
        },
        // 返回
        goBack(){
            this.findAllMovies()
            this.reload()         
        },
        // 查所有正在上映和即将上映电影
        findAllMovies(){
            let url = '/findMovieByState1'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allSchedules = []
                    this.allMovies = resp.data
                    // this.$refs.scheduleTable.style.display = "none"
                    // this.$refs.logined.style.display = "none"
                    $('.scheduleTable').hide()
                    $('.movieTable').show()
                }
            })
        },
        // 模糊查询电影
        searchMovie(){
            if(this.search == ''){
                this.findAllMovies()
            }else{
                console.log(this.search)
                let url = "/findMovieBySearch"
                let param = {moviename: this.search}
                console.log(param)
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allMovies = resp.data
                    }
                }) 
            }
        },
        // 模糊查询排片
        searchSchedule(){
            if(this.search == ''){
                this.findAllMovies()
            }else{
                let url = "/findScheduleBySearch"
                let param = {release_time: this.search}
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allSchedules = resp.data
                    }
                }) 
            }
        },
        // 查一部电影的排片
        findMovieSchedule(movieid,moviename){
            this.movieid = movieid
            this.moviename = moviename
            let url = '/findScheduleBymovieid'
            let param = { movieid: movieid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    if(resp.data.length == 0){
                        this.$message.warning("该电影暂无排片信息！")
                    }
                    this.allMovies = []
                    console.log(resp.data)
                    // this.$refs.movieTable.style.display = 'none'
                    $('.movieTable').hide()
                    $('.scheduleTable').show()
                    this.allSchedules = resp.data
                }
            })
        },
        // 查一条的排片
        findOneSchedule(schedule_id){
            this.editVisible = true
            let url = '/findScheduleByid'
            let param = {schedule_id: schedule_id}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.scheduleInfo = resp.data
                }
            })
        },
        //修改排片
        updateSchedule(){
            let url = '/updateSchedule'
            let param = {
                movieid: this.scheduleInfo.movieid,
                schedule_id: this.scheduleInfo.schedule_id,
                release_time: this.scheduleInfo.release_time,
                hallid: this.scheduleInfo.hallid,
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    // this.reload()
                    this.editVisible = false
                    this.findMovieSchedule(this.movieid,this.moviename)
                    this.$message.success('修改成功')
                }
            })
        },
        //删除排片
        deleteSchedule(schedule_id){
            let url = '/deleteScheduleByid'
            let param = {schedule_id: schedule_id}
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(()=>{
                this.common.requestData(url,param,'post').then(resp => {
                    if(resp && resp.status == 200){
                        this.findMovieSchedule(this.movieid,this.moviename)
                        this.$message.success('删除成功')
                    }
              })
            })
        },
        //删除排片不提示
        deleteSchedules(schedule_id){
            let url = '/deleteScheduleByid'
            let param = {schedule_id: schedule_id}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.findMovieSchedule(this.movieid,this.moviename)
                }
            })
        },
        //添加排片
        addSchedule(){
            let url = '/addSchedule'
            let param = {
                movieid: this.movieid,
                release_time: this.newSchedule.release_time,
                hallid: this.newSchedule.hallid,
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.findMovieSchedule(this.movieid,this.moviename)
                    this.addVisible = false
                    this.$message.success('添加成功')
                    this.newSchedule = []
                }
            })
        },
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
/* .table {
    width: 100%;
    font-size: 14px;
} */
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
    height: 52px;
}
</style>
