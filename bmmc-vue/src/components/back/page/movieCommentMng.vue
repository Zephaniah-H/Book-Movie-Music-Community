<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-movie"></i> 电影管理
                </el-breadcrumb-item>
                <el-breadcrumb-item>影评管理</el-breadcrumb-item>
                <el-breadcrumb-item v-if="allMovies.length <= 0">《{{moviename}}》的所有影评</el-breadcrumb-item>
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
                <el-input v-model="commentSearch" placeholder="电影名" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchComment">搜索</el-button>
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
                        <el-tag :type="scope.row.moviestate === '正在上映' ? 'success' : (scope.row.moviestate === '即将上映' ? 'info' : 'danger')">{{scope.row.moviestate}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="primary"
                            size="small"
                            plain
                            @click="findMovieComment(scope.row.movieid,scope.row.moviename)"
                        >查看影评</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 影评 -->
            <el-table
                :data="allMovieComments.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="commentTable"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="moviecommentid" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="userid" label="用户名"></el-table-column>
                <el-table-column label="头像(查看大图)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.user.icon"
                            :preview-src-list="[scope.row.user.icon]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="title" label="标题"></el-table-column>
                <el-table-column prop="comment" label="内容" width="340px">
                    <template slot-scope="scope">
                        <div v-html="scope.row.comment.slice(0,350)">{{scope.row.comment}}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="ctime" label="评论时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteComment(scope.row.moviecommentid)"
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
                    :total="allMovies.length > 0 ? allMovies.length : allMovieComments.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import $ from 'jquery'
export default {
    data() {
        return {
            currentPage: 1,
            pageSize: 5,
            multipleSelection: [],
            editVisible: false,
            movieid:'',
            moviename:'',
            allMovieComments:[],
            allMovies:[],
            commentSearch:'',
        };
    },
    mounted() {
        this.findAllMovies();
    },
    methods: {
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val
        },
        //批量删除
        delAllSelection() {
            const length = this.multipleSelection.length;
            this.$confirm('确定要删除吗？', '提示', {type: 'warning'}).then(() => {
                for (let i = 0; i < length; i++) {
                    this.deleteComments(this.multipleSelection[i].bookcommentid)
                }
                this.$message.success("删除成功！")
                this.multipleSelection = [];
            })
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
                    this.allMovieComments = []
                    $('.commentTable').hide()
                    $('.movieTable').show()
                }
            })
        },
        // 查1电影的评论
        findMovieComment(movieid,moviename){
            this.movieid = movieid
            this.moviename = moviename
            let url = '/findMovieCommentBymovieid'
            let param = {movieid: movieid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.allMovieComments = resp.data
                    this.allMovies = []
                    $('.movieTable').hide()
                    $('.commentTable').show()
                }
            })
        },
        // 模糊查询书评
        searchComment(){
            if(this.commentSearch == ''){
                this.findAllBooks()
            }else{
                let url = "/findBCommentBySearch"
                let param = {comment: this.commentSearch}
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allBookComments = resp.data
                    }
                }) 
            }
        },
        // 删除章节
        deleteComment(moviecommentid){
            let url = '/deleteMovieComment'
            let param = {moviecommentid: moviecommentid}
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(()=>{
                this.common.requestData(url,param,'post').then(resp => {
                    console.log(resp)
                    if(resp && resp.status == 200){
                        this.findMovieComment(this.movieid,this.moviename)
                        this.$message.success('删除成功')
                    }
              })
            })
        },
        // 删除章节不提示
        deleteComments(bookcommentid){
            let url = '/deleteBookComment'
            let param = {bookcommentid: bookcommentid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp+'删且不提')
                if(resp && resp.status == 200){
                    this.findCommentBybookid(this.bookinfo.bookid,this.bookinfo.bookname)
                }
            })
        },
        goBack(){
            this.findAllBooks()
            this.reload()         
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
    height: 40px;
}
</style>
