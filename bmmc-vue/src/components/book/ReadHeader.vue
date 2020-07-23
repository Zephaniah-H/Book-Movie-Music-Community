<template>
    <div class="conclude-read-header"><!-- 整块logo+sousuo -->
        <div class="read-header"><!-- 内容 -->
            <div class="read-logo"><font class="zhidu">芝士读书</font></div><!-- logo -->
            <div class="read-search">
                <el-input class="read-search1" placeholder="书名、作者" v-model="bookSearch" size="small">
                    <el-button slot="append" icon="el-icon-search" @click="searchBook"></el-button>
                </el-input>
            </div><!-- 搜索 -->
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            allBooks:[],
            bookSearch:'',
        }
    },
    methods:{
        // 模糊查询
        searchBook(){
                // let url = "/findBookBySearch"
                // let param = {bookname: this.bookSearch}
                // this.common.requestData(url,param,"post").then(resp => {
                //     if(resp && resp.status == 200){
                //         this.allBooks = resp.data
                //     }
                // }) 
                this.$emit('search',this.bookSearch)
            
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
    },
}
</script>

<style scoped>
.conclude-read-header{
    background-color: #FCF9EE;
}
.read-header{
    width: 1040px;
    height: 74px;
    margin: auto;
}
.read-logo,.read-search{
    float: left;
    padding-top: 20px;
}
.read-logo{
    width: 120px;
    height: 53px;
    margin-right: 35px;
}
.zhidu{
    color: rgb(85, 70, 20);
    font-size: 28px;
    font-weight: bold;
}
.read-search1{
    margin-top: 5px;
    width: 230%;
}
</style>