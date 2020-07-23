<template>
    <div style="background-color:rgb(248,249,249);padding-top:1px;height:765px;">
        <logined-header></logined-header>
        <div><h1 style="font-size:23px;margin-left:20px;">我的书架</h1></div>
        <el-divider></el-divider>
        <div style="width:1060px;margin:auto;">
            <ul style="margin-top:50px;">
                <li class="shelf-li" v-for="item in myShelf" :key="item.index">
                    <div class="book">
                        <i class="el-icon-circle-close" style="float:right;" @click="deleteBookInShelf(item.allBooks.bookid)"></i>
                        <el-card>
                            <img :src="item.allBooks.cover" class="image" @click="goReadBook(item.allBooks)" >
                        </el-card>
                        <div style="width:120px;font-weight:bold;margin-top:10px;margin-left:3px;">{{item.allBooks.bookname}}</div>
                        <div style="font-size:12px;color:#777;margin-top:5px;margin-left:3px;">{{item.allBooks.author}}</div>
                    </div>
                    
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
import loginedHeader from './loginedHeader'
export default {
    data(){
        return{
            myShelf:[],
        }
    },
    components:{
        loginedHeader,
    },
    mounted(){
        this.findMyBooksUserid()
    },
    methods:{
        //  查书架
        findMyBooksUserid(){
            let url = '/findMyBooksUserid'
            let param = {userid: this.$store.state.user.userid}
            this.common.requestData(url,param,'post').then(resp => {
                console.log(resp)
                if(resp && resp.status == 200){
                    this.myShelf = resp.data
                }
            })
        },
        // 跳转到在线阅读
        goReadBook(allbooks){
            let book = {
                bookid: allbooks.bookid,
            }
            this.$store.commit('onebookinfo',allbooks)
            const {href} = this.$router.resolve({ path: '/readbook', })
            window.open(href, '_blank')
        },
        //删除书架的书
        deleteBookInShelf(bookid){
            let url = '/deleteBookInShelf'
            let param = {bookid: bookid}
            this.$confirm('要移出书架吗？', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                      }).then(() => {
                          this.common.requestData(url,param,'post').then(resp => {
                                console.log(resp)
                                if(resp && resp.status == 200){
                                    this.findMyBooksUserid()
                                }
                            })
                      })
        }
    }
}
</script>

<style scope>
.image{
    width: 120px;
    height: 180px;
}
.shelf-li{
    display: inline-block;
    margin-right: 50px;
    margin-bottom: 20px;
}
.book .el-icon-circle-close{
    display: none;
}
.book:hover .el-icon-circle-close{
    display: block;
}
</style>
<style>
.el-card {
    width: 120px;
    height: 180px;
}
.el-card__body {
    padding: 0;
}
</style>
