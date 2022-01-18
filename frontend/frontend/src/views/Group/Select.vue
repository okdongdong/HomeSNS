<template>
  <v-app class="my-background">
    <v-spacer></v-spacer>
    <div class="container box">
      <div class="d-flex justify-center" style="height:20%; width=auto;">
        <img src="@/assets/logo1.png" alt="logo">
      </div>
      <div>
        <h1>그룹 생성</h1>
      </div>
      <v-form ref="form">
        <v-text-field
            clearable
            :counter="20"
            background-color="white"
            solo
            label="그룹 이름을 입력하세요."
            required
          ></v-text-field>

        <div class="d-flex justify-center">
          <v-btn text dark large rounded>
            <h1>뒤로가기</h1>            
          </v-btn>
          <v-btn text dark large rounded>
            <h1>생성하기</h1>
          </v-btn>
        </div>
      </v-form>
    </div>
  </v-app>
</template>

<script>
export default {
  name: '02SubPjtSelect',

  data() {
    return {
      
    };
  },

  mounted() {
    
  },

  methods: {
    
  },
};
</script>

<style scoped>
  .my-background {
    background-color: rgb(98, 101, 232);
  }
  .box {
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    color: white;
  }
</style>
