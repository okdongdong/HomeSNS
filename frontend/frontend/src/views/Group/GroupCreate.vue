<template>
  <v-app class="my-background">
    <div class="container">
        <v-card
            flat
            color="rgb(98, 101, 232)"
        >
            <div class="d-flex justify-center">
                <v-img 
                    class="logo-img" 
                    src="@/assets/logo1.png"
                    alt="logo"
                    max-width="200"
                    max-height="200"
                    >
                </v-img>
            </div>
            <v-card-title class="main-text justify-center text-h4 font-weight-bold">그룹 생성</v-card-title>
            <br />
        </v-card>
    </div>
    <div class="container">
        <v-row justify="center">
            <v-form class="form-data" ref="form" v-model="valid" lazy-validation>
                <div class="border">
                    <template v-if="previewImage" class="d-flex align-items-center">
                        <v-img :src="previewImage" class="group-img" />
                        <p class="mb-1">file name: {{ image.name }}</p>
                        <p class="mb-1">size: {{ image.size/1024 }}KB</p>
                    </template>
                    <template v-else>
                        <v-img src="@/assets/emptyImg.png" alt="그룹 프로필" class="group-img" />
                    </template>
                </div>
                <v-file-input type="file" accept="image/*" @change="selectFile" class="form-control-file" id="profile_path" enctype="multipart/form-data"></v-file-input>
                
                <v-text-field
                    clearable
                    background-color="white"
                    :counter="20"
                    maxlength="20"
                    :rules="rules.groupNameRules"
                    solo
                    v-model="credentials.groupname"
                    label="그룹 이름을 입력하세요."
                    required
                ></v-text-field>
                <v-btn
                    rounded
                    dark
                    large
                    :disabled="!valid"
                    color="black"
                    class="mr-4 my-3"
                    @click="selectGroup()"
                    width="100%"
                    >
                    생성하기
                    </v-btn>
                <v-btn
                    text
                    rounded
                    dark
                    large
                    color="white"
                    class="mr-4 my-3"
                    @click="back()"
                    width="100%"
                    >
                    뒤로가기
                </v-btn>
            </v-form>
        </v-row>
    </div>
  </v-app>
</template>

<script>
export default {
  name: 'Select',

    data() {
        return {
        valid :true,
        credentials :{
            groupname : null,
        },
        image:null,
        previewImage: undefined,
        rules:{
            groupNameRules :[
                (v) => !!v || "그룹명을 입력해주세요.",
                (v) => !(v && v.length >= 20) || "그룹명은 20자 이상 입력할 수 없습니다.",
            ],
        }
        };
    },

    mounted() {
        
    },

    methods: {
        back : function(){
            this.$router.push({name : 'Select'})
        },
        selectGroup : function(){
            this.$store.dispatch('')
        },
        selectFile:function(file){
            this.image=file
            this.previewImage = URL.createObjectURL(this.image);
        },
    },
};
</script>

<style scoped>
    .my-background {
    background-color: rgb(98, 101, 232);
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    }
    .form-data {
        width: 80%;
    }
    .main-text {
    color: #fff;
    }
    .group-img {
        display: block;
        margin : 0px auto;
        /* height: 70%; */
        width: 50%;
        object-fit: cover;
        border: 4px solid white;
        border-radius: 20%;
        box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
        padding-bottom: 5%;
    }
</style>