<template>
    <div>
        <logined-header></logined-header>
        <div class="head" ref="head">
            <v-header></v-header> 
        </div>
        <div>
            <v-content></v-content>
        </div>
    </div>
</template>

<script>
import loginedHeader from './loginedHeader'
import vHeader from './Header'
import vContent from './Content'
export default {
    data(){
        return{}
    },
    components:{
        vHeader,
        vContent,
        loginedHeader
    },
    mounted(){
    },
    methods:{
    }
}
</script>

<style scoped>
.before-header{
    height: 28px;
    background-color: rgb(146, 133, 109);
    display: none;
}
.before-header ul{
    list-style: none;
    margin: 0;
    padding: 0;
}
.before-header li{
    display: inline-block;
    font-size: 14px;
    color: rgb(248, 237, 217);
    padding: 0 7px;
}
.left-navi{
    float: left;
}
.right-pinfo{
    float: right;
}
.head{
    /* height: 87px; */
    background-color: #FCF9EE;
}
</style>