<template>
    <v-app class="box" style="background-color:rgba(98,101,232);">
        <div class="logo-and-title">
            <img class="logo-img" src="@/assets/logo1.png" alt="logo">
            <h1 style="color: white;">우리집SNS</h1>
        </div>
        <div class="container d-flex">
            <!-- 그룹 추가하는 곳 -->
            <div class="row">
                <div class="col-6">
                    <img src="@/assets/test1.png" alt="로고 사진입니다." class="group-img">
                    <h3>그룹 추가</h3>
                </div>
                    <!-- data받아오면 for문으로 돌리기 -->
                <!-- <div class="col-6" v-for="" :key="">
                    <img :src="${}" alt="그룹 사진입니다." class="group-img" @click="groupMainPage(그룹아이디넣기)">
                    <h3 style="color:white">{{}}</h3>
                </div> -->
                <div class="col-6">
                    <img src="@/assets/logo2.png" alt="그룹 사진입니다." class="group-img">
                    <h3>우가우가</h3>
                </div>
                <div class="col-6">
                    <img src="@/assets/logo2.png" alt="그룹 사진입니다." class="group-img">
                    <h3>우가우가</h3>
                </div>
            </div>
        </div>
    </v-app>
</template>

<script>
export default {
    name: 'Groupcreate',

    data() {
        return {
            
        };
    },

    methods: {
        // groupMainPage : function(groupId){
        //     this.$router.push({name:'Main'})
        // }
    },
};
</script>

<style scoped>
    .box {
        margin-left: auto;
        margin-right: auto;
        text-align: center;
    }
    .group-img {
        height: 80%;
        width: 70%;
        object-fit: cover;
        border: 4px solid white;
        border-radius: 20%;
        box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
    }
    .logo-img {
        height: 40%;
        width: auto;
        object-fit: contain;
    }
    .logo-and-title{
        /* height: 30%; */
        display: flex;
        flex-direction: column;
        justify-content: center;
        /* align-items: center; */
    }
    h1 {
        color : white;
    }
    h3 {
        color : white;
    }

</style>