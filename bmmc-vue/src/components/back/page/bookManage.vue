<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-book"></i> 阅读管理
                </el-breadcrumb-item>
                <el-breadcrumb-item>书籍管理</el-breadcrumb-item>
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
                <el-input v-model="bookSearch" placeholder="书名、作者、类型等" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchBook">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-plus" @click="addVisible = true">添加</el-button>
            </div>
            <el-table
                :data="allBooks.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="bookid" label="ID" width="55" align="center"></el-table-column>
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
                <el-table-column prop="booktypeid" label="类型"></el-table-column>
                <el-table-column prop="point" label="评分"></el-table-column>
                <el-table-column label="简介" align="center">
                    <template slot-scope="scope">
                        <el-button plain type="primary" size="small" @click="seeBookinfo(scope.row.bookinfo)">查看详情</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="press" label="出版社"></el-table-column>
                <el-table-column prop="publication_year" label="出版时间"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-edit"
                            @click="findOneBook(scope.row.bookid)"
                        >编辑</el-button>
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteBook(scope.row.bookid)"
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
                    :total="allBooks.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 简介弹出框 -->
        <el-dialog title="简介详情" :visible.sync="seeBookintro">
            <div v-html="bookintro">{{bookintro}}</div>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="书名">
                    <el-input v-model="bookInfo.bookname" size="small"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                    <el-input v-model="bookInfo.author" size="small"></el-input>
                </el-form-item>
                <el-form-item label="封面">
                    <el-input v-model="bookInfo.cover" size="small"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                    <el-input v-model="bookInfo.booktypeid" size="small"></el-input>
                </el-form-item>
                <el-form-item label="出版社">
                    <el-input v-model="bookInfo.press" size="small"></el-input>
                </el-form-item>
                <el-form-item label="出版时间">
                    <el-input v-model="bookInfo.publication_year" size="small"></el-input>
                </el-form-item>
                <el-form-item label="简介">
                    <quill-editor
                    v-model="bookInfo.bookinfo" 
                    ref="myQuillEditor">
                    </quill-editor>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="updateBook" size="small">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 添加弹出框 -->
        <el-dialog title="新增书籍" :visible.sync="addVisible" width="40%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="书名">
                    <el-input v-model="newBook.bookname" size="small"></el-input>
                </el-form-item>
                <el-form-item label="作者">
                    <el-input v-model="newBook.author" size="small"></el-input>
                </el-form-item>
                <el-form-item label="封面">
                    <el-input v-model="newBook.cover" size="small"></el-input>
                </el-form-item>
                <el-form-item label="类型">
                    <el-input v-model="newBook.booktypeid" size="small"></el-input>
                </el-form-item>
                <el-form-item label="出版社">
                    <el-input v-model="newBook.press" size="small"></el-input>
                </el-form-item>
                <el-form-item label="出版时间">
                    <el-input v-model="newBook.publication_year" size="small"></el-input>
                </el-form-item>
                <el-form-item label="简介">
                    <quill-editor
                    v-model="newBook.bookinfo" 
                    ref="myQuillEditor">
                    </quill-editor>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="addBook" size="small">确 定</el-button>
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
            seeBookintro:false,
            allBooks:[],
            bookintro:'',
            bookInfo:[],
            newBook:[],
            bookSearch:'',
        };
    },
    mounted() {
        this.findAllBooks()
    },
    methods: {
        //  批量删除
        delAllSelection(){
            const length = this.multipleSelection.length;
            this.$confirm('确定要删除吗？', '提示', {type: 'warning'}).then(() => {
                for (let i = 0; i < length; i++) {
                    console.log('shanchu'+this.multipleSelection[i].bookid)
                    this.deleteBooks(this.multipleSelection[i].bookid)
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
                console.log(this.multipleSelection[i].bookid)
            }
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
        // 看书的简介的详情
        seeBookinfo(bookinfo){
            this.seeBookintro = true
            this.bookintro = bookinfo
            // console.log(this.bookintro+'111111')
            // $('#bookintrodction').html(this.bookintro)
            
        },

        // 删除书
        deleteBook(bookid){
            let url = '/deleteBookByid'
            let param = {bookid: bookid}
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

        // 删除书不提示
        deleteBooks(bookid){
            let url = '/deleteBookByid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status != 200){
                    this.$message.danger('删除失败');
                }
            })
        },

        // 模糊查询
        searchBook(){
            if(this.bookSearch == ''){
                this.findAllBooks()
            }else{
                let url = "/findBookBySearch"
                let param = {bookname: this.bookSearch}
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.allBooks = resp.data
                    }
                }) 
            }
        },

        // 查一本书信息
        findOneBook(bookid){
            this.editVisible = true
            let url = '/findOneBookByid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.bookInfo = resp.data
                }
            })
        },

        // 新增书
        addBook(){
            let url = '/addBooks'
            let param = {
                bookname: this.newBook.bookname,
                author: this.newBook.author,
                press: this.newBook.press,
                publication_year: this.newBook.publication_year,
                cover: this.newBook.cover,
                point: '暂无',
                bookinfo: this.newBook.bookinfo,
            }
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    this.reload()
                    this.$message.success('添加成功')
                    this.newBook = []
                }
            })
        },

        // 修改书信息
        updateBook(){
            let url = '/updateBookByid'
            let param = {
                bookid: this.bookInfo.bookid,
                bookname: this.bookInfo.bookname,
                author: this.bookInfo.author,
                press: this.bookInfo.press,
                publication_year: this.bookInfo.publication_year,
                cover: this.bookInfo.cover,
                bookinfo: this.bookInfo.bookinfo,
            }
            console.log(this.bookInfo.author)
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.reload()
                    this.$message.success('修改成功')
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
