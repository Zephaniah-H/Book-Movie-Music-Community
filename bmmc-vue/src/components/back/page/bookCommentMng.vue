<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-book"></i> 阅读管理
                </el-breadcrumb-item>
                <el-breadcrumb-item>书评管理</el-breadcrumb-item>
                <el-breadcrumb-item v-if="allBooks.length <= 0">《{{bookinfo.bookname}}》的全部书评</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    v-if="allBooks.length <= 0"
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
                <el-input v-model="commentSearch" placeholder="用户名" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchComment">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-d-arrow-left" @click="goBack" v-if="allBooks.length <= 0">返回</el-button>
            </div>
            <!-- 书表 -->
            <el-table
                v-if="allBooks.length > 0"
                :data="allBooks.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column label="ID" width="55" align="center">
                    <template slot-scope="scope">{{scope.$index + (currentPage-1)*pageSize +1}}</template>
                </el-table-column>
                <el-table-column prop="bookname" label="书名"></el-table-column>
                <el-table-column prop="author" label="作者">
                </el-table-column>
                <el-table-column label="封面(查看大图)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.cover"
                            :preview-src-list="[scope.row.cover]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope" v-if="allBooks.length > 0">
                        <el-button
                            plain
                            size="small"
                            type="primary"
                            @click="findCommentBybookid(scope.row.bookid,scope.row.bookname)"
                        >查看书评</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 书评表 -->
            <el-table
                v-if="allBookComments.length > 0"
                :data="allBookComments.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="bookcommentid" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="userid" label="用户名"></el-table-column>
                <el-table-column label="头像(查看大图)" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.thumb"
                            :preview-src-list="[scope.row.thumb]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="标题">
                    <template slot-scope="scope">
                        <div v-html="scope.row.comment_title">{{scope.row.comment_title}}</div>
                    </template>
                </el-table-column>
                <el-table-column label="内容" width="540%">
                    <template slot-scope="scope">
                        <div v-html="scope.row.comment">{{scope.row.comment}}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="comment_time" label="评论时间"  width="100"></el-table-column>
                <el-table-column label="操作" width="100" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteComment(scope.row.bookcommentid)"
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
                    :total="allBookComments.length > 0 ? allBookComments.length : allBooks.length"
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
            pageSize: 5,
            multipleSelection: [],
            bookinfo:{
                bookid:'',
                bookname:''
            },
            allBooks:[],
            allBookComments:[],
            commentSearch:'',
        };
    },
    mounted() {
        this.findAllBooks()
    },
    methods: {
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val
            console.log(this.multipleSelection)
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
        // 查所有书
        findAllBooks(){
            let url = '/findAllBooks'
            let param = {}
            this.common.requestData(url,param,'get').then(resp => {
                if(resp && resp.status == 200){
                    this.allBooks = resp.data
                }
            })
        },
        // 查1书的评论
        findCommentBybookid(bookid,bookname){
            this.bookinfo.bookid = bookid
            this.bookinfo.bookname = bookname
            let url = '/findBookCommentBybookid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.allBookComments = resp.data
                    this.allBooks = []
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
        deleteComment(bookcommentid){
            let url = '/deleteBookComment'
            let param = {bookcommentid: bookcommentid}
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(()=>{
                this.common.requestData(url,param,'post').then(resp => {
                    console.log(resp)
                    if(resp && resp.status == 200){
                        this.findCommentBybookid(this.bookinfo.bookid,this.bookinfo.bookname)
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
    height: 52px;
}
</style>
