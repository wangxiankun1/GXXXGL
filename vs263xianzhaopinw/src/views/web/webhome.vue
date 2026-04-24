<template>
    <div>
        <div>
            <webheader/>
        </div>
        <router-view/>
        <div>
            <webfooter/>
        </div>
    </div>
</template>

<style>

</style>

<script>
    import webheader from "../web/webheader";
    import webfooter from "../web/webfooter";
    export default {
        name:"webhome",
        components:{
            webheader,
            webfooter,
        },
        data() {
            return {               
            }
        },
    }





</script>  
<style scoped>
@import "../../assets/wangzhan21/css/style.css";
</style>