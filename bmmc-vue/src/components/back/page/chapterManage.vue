<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-zf-book"></i> 阅读管理
                </el-breadcrumb-item>
                <el-breadcrumb-item>章节管理</el-breadcrumb-item>
                <el-breadcrumb-item v-if="allBooks.length <= 0">《{{bookinfo.bookname}}》的全部章节</el-breadcrumb-item>
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
                <el-input v-model="chapterSearch" placeholder="书名、章节名" class="handle-input mr10" size="small"></el-input>
                <el-button size="small" type="primary" icon="el-icon-search" @click="searchChapter">搜索</el-button>
                <el-button size="small" type="primary" icon="el-icon-plus" @click="addVisible = true" v-if="allBooks.length <= 0">添加</el-button>
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
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                            plain=""
                            size="small"
                            type="primary"
                            @click="findChapterByBookid(scope.row.bookid,scope.row.bookname)"
                        >查看章节</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 章节表 -->
            <el-table
                v-if="bookChapters.length > 0"
                :data="bookChapters.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <!-- <el-table-column prop="chapterid" label="ID" width="55" align="center"></el-table-column> -->
                <!-- <el-table-column prop="bookid" label="书名"></el-table-column> -->
                <el-table-column label="章节" width="55">
                    <template slot-scope="scope">{{scope.$index + (currentPage-1)*pageSize +1}}</template>
                </el-table-column>
                <el-table-column prop="title" label="章节标题"></el-table-column>
                <el-table-column label="操作" width="300" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-edit"
                            @click="findOneChapter(scope.row.chapterid)"
                        >章节编辑</el-button>
                        <!-- <el-button
                            size="small"
                            type="text"
                            icon="el-icon-edit"
                            @click="editChapterContent(scope.row.chapterid)"
                        >内容编辑</el-button> -->
                        <el-button
                            size="small"
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="deleteChapter(scope.row.chapterid)"
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
                    :total="bookChapters.length > 0 ? bookChapters.length : allBooks.length"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="70%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="章节名">
                    <el-input v-model="chapterInfo.title" size="small"></el-input>
                </el-form-item>
                <el-form-item>
                    <quill-editor 
                    height="100px"
                    v-model="chapterInfo.content" 
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
                <el-button type="primary" @click="updateChapter" size="small">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 添加弹出框 -->
        <el-dialog title="新增" :visible.sync="addVisible" width="70%">
            <el-form ref="form" label-width="70px">
                <el-form-item label="章节标题">
                    <el-input v-model="newChapter.title" size="small"></el-input>
                </el-form-item>
                <el-form-item label="章节内容">
                    <quill-editor 
                    height="100px"
                    v-model="newChapter.content" 
                    ref="myQuillEditor"
                    :options="editorOption"
                    @blur="onEditorBlur($event)" 
                    @focus="onEditorFocus($event)"
                    @change="onEditorChange($event)">
                    </quill-editor>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false" size="small">取 消</el-button>
                <el-button type="primary" @click="addChapter" size="small">确 定</el-button>
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
            allBooks:[],
            bookinfo:{
                bookid:'',
                bookname:''
            },
            bookChapters:[],
            chapterInfo:[],
            newChapter:[],
            chapterSearch:'',
            editorOption: { //  富文本编辑器配置
                // modules: {
                //     toolbar: '#toolbar'
                // },
                // placeholder: '请输入正文'
            },
            words:'',
        };
    },
    mounted() {
        this.findAllBooks()
    },
    methods: {
        // 触发搜索按钮
        searchChapter() {
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val
            console.log(this.multipleSelection)
        },
        //  批量删除
        delAllSelection() {
            const length = this.multipleSelection.length;
            this.$confirm('确定要删除吗？', '提示', {type: 'warning'}).then(() => {
                for (let i = 0; i < length; i++) {
                    console.log('shanchu'+this.multipleSelection[i].chapterid)
                    this.deleteChapters(this.multipleSelection[i].chapterid)
                    // this.findChapterByBookid(this.bookinfo.bookid,this.bookinfo.bookname)
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
                    this.bookChapters = []
                }
            })
        },
        // 查1书的章节
        findChapterByBookid(bookid,bookname){
            // console.log(bookid)
            this.bookinfo.bookid = bookid
            this.bookinfo.bookname = bookname
            let url = '/findChapterByBookid'
            let param = {bookid: bookid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.bookChapters = resp.data
                    console.log('查1书的章节')
                    console.log(resp.data)
                    this.allBooks = []
                }
            })
        },
        // 看章节的内容的详情
        // seeChapterContent(content){
        //     this.seeContent = true
        //     this.bookintro = bookinfo
        //     // console.log(this.bookintro+'111111')
        //     // $('#bookintrodction').html(this.bookintro)
            
        // },
        // 删除章节
        deleteChapter(chapterid){
            let url = '/deleteChapterByid'
            let param = {chapterid: chapterid}
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(()=>{
                this.common.requestData(url,param,'post').then(resp => {
                    console.log(resp)
                    if(resp && resp.status == 200){
                        this.findChapterByBookid(this.bookinfo.bookid,this.bookinfo.bookname)
                        this.$message.success('删除成功')
                    }
              })
            })
        },
        // 删除章节不提示
        deleteChapters(chapterid){
            let url = '/deleteChapterByid'
            let param = {chapterid: chapterid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp+'删且不提')
                if(resp && resp.status == 200){
                    this.findChapterByBookid(this.bookinfo.bookid,this.bookinfo.bookname)
                }
            })
        },
        // 模糊查询章节
        searchChapter(){
            if(this.chapterSearch == ''){
                this.findAllBooks()
            }else{
                let url = "/findChapterBySearch"
                let param = {title: this.chapterSearch}
                this.common.requestData(url,param,"post").then(resp => {
                    if(resp && resp.status == 200){
                        this.bookChapters = resp.data
                    }
                }) 
            }
        },
        // 查一个章节信息
        findOneChapter(chapterid){
            this.editVisible = true
            let url = '/findOneChapterByid'
            let param = {chapterid: chapterid}
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    this.chapterInfo = resp.data
                }
            })
        },
        // 新增章节
        addChapter(){
            let currentTime = this.common.getCurrentTime()
            let url = '/addChapter'
            let param = {
                title: this.newChapter.title,
                content: this.newChapter.content,
                bookid: this.bookinfo.bookid,
                ctime: currentTime,
                words: this.words,
            }
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    // this.reload()
                    this.findChapterByBookid(this.bookinfo.bookid,this.bookinfo.bookname)
                    this.addVisible = false
                    this.$message.success('添加成功')
                    this.newChapter = []
                }
            })
        },
        // 修改章节信息
        updateChapter(){
            let url = '/updateChapterByid'
            let param = {
                chapterid: this.chapterInfo.chapterid,
                title: this.chapterInfo.title,
                content: this.chapterInfo.content,
                ctime: this.chapterInfo.ctime,
                words: this.words,
            }
            this.common.requestData(url,param,'post').then(resp => {
                if(resp && resp.status == 200){
                    // this.reload()
                    this.editVisible = false
                    this.findChapterByBookid(this.bookinfo.bookid,this.bookinfo.bookname)
                    this.$message.success('修改成功')
                }
            })
        },
        goBack(){
            this.findAllBooks()
            this.reload()         
        },
        onEditorBlur(editor){//失去焦点事件    
 	    },
        onEditorFocus(editor){//获得焦点事件
        },
        onEditorChange(event){//编辑器文本发生变化
            //this.content可以实时获取到当前编辑器内的文本内容
            this.words = event.quill.getLength()-1
            console.log(this.words);
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
