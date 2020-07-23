<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-movie"></i> 电影管理
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
                <!-- <el-select placeholder="电影状态" class="handle-select mr10" size="small">
                    <el-option key="1" label="全部" value="全部"></el-option>
                    <el-option key="2" label="正在上映" value="正在上映"></el-option>
                    <el-option key="3" label="即将上映" value="即将上映"></el-option>
                    <el-option key="4" label="已经下映" value="已经下映"></el-option>
                </el-select> -->
                <el-input v-model="movieSearch" placeholder="电影名、导演、主演" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchMovie">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-plus" @click="addVisible = true">添加</el-button>
            </div>
            <el-table
                :data="allMovies.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
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
                <el-table-column prop="leadingrole" label="主演"></el-table-column>
                <el-table-column prop="movietype" label="类型"></el-table-column>
                <el-table-column prop="movieinfo" label="简介">
                    <template slot-scope="scope">
                        <el-button plain type="primary" size="small" @click="seeMovieinfo(scope.row.movieinfo)">查看详情</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="releaseday" label="首映时间"></el-table-column>
                <el-table-column label="状态" align="center">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.moviestate === '正在上映' ? 'success' : (scope.row.moviestate === '即将上映' ? 'info' : 'danger')">{{scope.row.moviestate}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-edit"
                            @click="findOneMovie(scope.row.movieid)"
                        >编辑</el-button>
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteMovie(scope.row.movieid)"
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
                    :total="allMovies.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 简介弹出框 -->
        <el-dialog title="简介详情" :visible.sync="seeMovieintro">
            <div v-html="movieintro">{{movieintro}}</div>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="电影名">
                    <el-input v-model="movieInfo.moviename" size="small"></el-input>
                </el-form-item>
                <el-form-item label="封面">
                    <el-input v-model="movieInfo.cover" size="small"></el-input>
                </el-form-item>
                <el-form-item label="导演">
                    <el-input v-model="movieInfo.director" size="small"></el-input>
                </el-form-item>
                <el-form-item label="主演">
                    <el-input v-model="movieInfo.leadingrole" size="small"></el-input>
                </el-form-item>
                <el-form-item label="电影类型">
                    <el-input v-model="movieInfo.movietype" size="small"></el-input>
                </el-form-item>
                <el-form-item label="制片国家/地区">
                    <el-input v-model="movieInfo.country" size="small"></el-input>
                </el-form-item>
                <el-form-item label="语言">
                    <el-input v-model="movieInfo.language" size="small"></el-input>
                </el-form-item>
                <el-form-item label="电影时长">
                    <el-input v-model="movieInfo.duration" size="small"></el-input>
                </el-form-item>
                <el-form-item label="上映日期">
                    <el-input v-model="movieInfo.releaseday" size="small"></el-input>
                </el-form-item>
                <el-form-item label="价格(元)">
                    <el-input v-model="movieInfo.movieprice" size="small"></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-input v-model="movieInfo.moviestate" size="small"></el-input>
                </el-form-item>
                <el-form-item label="电影简介">
                    <quill-editor
                    v-model="movieInfo.movieinfo" 
                    ref="myQuillEditor">
                    </quill-editor>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="updateMovie" size="small">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 添加弹出框 -->
        <el-dialog title="新增" :visible.sync="addVisible" width="40%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="电影名">
                    <el-input v-model="newMovie.moviename" size="small"></el-input>
                </el-form-item>
                <el-form-item label="导演">
                    <el-input v-model="newMovie.director" size="small"></el-input>
                </el-form-item>
                <el-form-item label="主演">
                    <el-input v-model="newMovie.leadingrole" size="small" placeholder="多个主演请使用“ / ”隔开"></el-input>
                </el-form-item>
                <el-form-item label="封面">
                    <el-input v-model="newMovie.cover" size="small"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                    <el-input v-model="newMovie.movietype" size="small"></el-input>
                </el-form-item>
                <el-form-item label="上映日期">
                    <el-date-picker
                    v-model="newMovie.releaseday"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="制片国家/地区">
                    <el-input v-model="newMovie.country" size="small"></el-input>
                </el-form-item>
                <el-form-item label="语言">
                    <el-input v-model="newMovie.language" size="small"></el-input>
                </el-form-item>
                <el-form-item label="电影时长">
                    <el-input v-model="newMovie.duration" size="small"></el-input>
                </el-form-item>
                <el-form-item label="价格(元)">
                    <el-input v-model="newMovie.movieprice" size="small"></el-input>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="newMovie.moviestate" placeholder="请选择">
                        <el-option
                        v-for="item in state"
                        :key="item.value"
                        :label="item.value"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="电影简介">
                    <quill-editor
                    v-model="newMovie.movieinfo" 
                    ref="myQuillEditor"
                    :options="editorOption"
                    @blur="onEditorBlur($event)" 
                    @focus="onEditorFocus($event)"
                    @change="onEditorChange($event)">
                    </quill-editor>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="addMovie" size="small">确 定</el-button>
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
            pageSize: 5,
            multipleSelection: [],
            editVisible: false,
            addVisible: false,
            allMovies:[],
            movieInfo:[],
            newMovie:[],
            movieSearch:'',
            seeMovieintro:false,
            movieintro:'',
            editorOption:{},
            state:[{label:'正在上映',value:'正在上映'}, {label:'即将上映',value:'即将上映'}, {label:'已经下映',value:'已经下映'}]
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
                    this.deleteMovies(this.multipleSelection[i].movieid)
                }
                this.$message.success("删除成功！")
                this.multipleSelection = [];
                // this.reload()
            })
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
            const length = this.multipleSelection.length;
            for (let i = 0; i < length; i++) {
                console.log(this.multipleSelection[i].movieid)
            }
        },
        // 分页导航
        handlePageChange(currentPage) {
            this.currentPage = currentPage
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
        // 看电影的简介的详情
        seeMovieinfo(movieinfo){
            this.seeMovieintro = true
            this.movieintro = movieinfo
        },
        // 删除电影
        deleteMovie(movieid){
            let url = '/deleteMovieByid'
            let param = {movieid: movieid}
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(()=>{
                this.common.requestData(url,param,'post').then(resp => {
                    console.log(resp)
                    if(resp && resp.status == 200){
                        this.findAllMovies()
                        this.$message.success('删除成功')
                    }
              })
            })
        },
        // 删除电影不提示
        deleteMovies(movieid){
            let url = '/deleteMovieByid'
            let param = {movieid: movieid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    this.findAllMovies()
                }
            })
        },
        // 模糊查询
        searchMovie(){
            if(this.movieSearch == ''){
                this.findAllMovies()
            }else{
                console.log(this.movieSearch)
                let url = "/findMovieBySearch"
                let param = {moviename: this.movieSearch}
                console.log(param)
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allMovies = resp.data
                    }
                }) 
            }
        },
        // 查一部电影信息
        findOneMovie(movieid){
            this.editVisible = true
            let url = '/findOneMovieByid'
            let param = {movieid: movieid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.movieInfo = resp.data
                    console.log(this.movieInfo)
                }
            })
        },
        // 新增电影
        addMovie(){
            console.log(this.newMovie.moviestate)
            let url = '/addMovie'
            let param = {
                moviename: this.newMovie.moviename,
                director: this.newMovie.director,
                leadingrole: this.newMovie.leadingrole,
                releaseday: this.newMovie.releaseday,
                cover: this.newMovie.cover,
                point: '暂无',
                movieinfo: this.newMovie.movieinfo,
                movietype: this.newMovie.movietype,
                moviestate: this.newMovie.moviestate,
                language: this.newMovie.language,
                duration: this.newMovie.duration,
                country: this.newMovie.country,
                movieprice: this.newMovie.movieprice,
            }
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    this.reload()
                    this.$message.success('添加成功')
                }
            })
        },
        // 修改电影信息
        updateMovie(){
            // console.log(this.movieInfo)
            let url = '/updateMovie'
            let param = {
                movieid: this.movieInfo.movieid,
                moviename: this.movieInfo.moviename,
                director: this.movieInfo.director,
                leadingrole: this.movieInfo.leadingrole,
                releaseday: this.movieInfo.releaseday,
                cover: this.movieInfo.cover,
                point: this.movieInfo.point,
                movieinfo: this.movieInfo.movieinfo,
                movietype: this.movieInfo.movietype,
                moviestate: this.movieInfo.moviestate,
                language: this.movieInfo.language,
                duration: this.movieInfo.duration,
                country: this.movieInfo.country,
                movieprice: this.movieInfo.movieprice,
            }
            // console.log(param.movieinfo)
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    // this.editVisible = false
                    this.reload()
                    this.$message.success('修改成功')
                }
            })
        },
        onEditorBlur(editor){//失去焦点事件    
 	    },
        onEditorFocus(editor){//获得焦点事件
        },
        onEditorChange(event){//编辑器文本发生变化
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
    height: 52px;
}
</style>
<style>
.ql-container {
    min-height: 200px;
}
</style>
