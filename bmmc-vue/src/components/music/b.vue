<template>
    <div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            apply:'',
        }
    },
    mounted(){
        this.paySuccess()
    },
    methods:{
        paySuccess(){
            let url = '/paySuccess'
            let param = {
                order_id: this.$store.state.order.order_id
            }
            this.common.requestData(url,param,'post').then(resp => {

            })
        }
    },
}
</script>