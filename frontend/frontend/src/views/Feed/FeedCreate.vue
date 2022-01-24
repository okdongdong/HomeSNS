<template>
  <v-card flat>
    <v-form
      ref="form"
      @submit.prevent="submit"
      >
      <v-container fluid>
        <h1>💛피드쓰자~!~!💛</h1>
        <v-row>
          <!-- 날짜 -->
          <v-col cols="12">
            <v-text-field
              clearable
              single-line
              v-model="form.bod"
              label="생년월일"
              type="date"
            ></v-text-field>
          </v-col>
          <!-- 제목 -->
          <v-col cols="12">
            <v-text-field
              v-model="form.title"
              :rules="rules.name"
              maxlength="20"
              :counter="20"
              color="rgb(98,101,232)"
              label="제목"
              required
            ></v-text-field>
          </v-col>
          <!-- 사진 -->


          <!-- 장소 -->


          <!-- 주소 -->


          <!-- 참석자 명단 인원?-->
          <v-col cols="12">
            <v-combobox
              v-model="attendPeople"
              :items="items"
              label="참석자 명단"
              multiple
              chips
            ></v-combobox>
          </v-col>
          <!-- 내용 -->
          <v-col cols="12">
            <v-textarea
              v-model="form.content"
              color="rgb(98,101,232)"
              maxlength="100"
              :counter="100"
            >
              <template v-slot:label>
                <div>
                  내용
                </div>
              </template>
            </v-textarea>
          </v-col>
          <!-- 해쉬태그 -->
          <v-col cols="12">
            <v-text-field
              v-model="tmphashtag"
              color="rgb(98,101,232)"
              label="해시태그"
              @keyup.enter="addHashTag()"
              @keyup.space="addHashTag()"
              auto-grow
            >
            <template v-slot:prepend-inner>
              <div v-for="(tag , index) in form.hashtag" :key="index">
                <v-chip class="ma-1" close @click:close="remove(tag)">{{tag}}</v-chip>
              </div>
            </template>
            </v-text-field>
          </v-col>
        </v-row>
      </v-container>
      <v-card-actions>
        <v-btn
          text
          @click="resetForm"
        >
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          :disabled="!formIsValid"
          text
          color="primary"
          type="submit"
        >
          Register
        </v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
  export default {
    name: 'Feedcreate',
    data () {
      const defaultForm = Object.freeze({
        title: null,
        content: null,
        hashtag:[],
      })
      
      return {
        form: Object.assign({}, defaultForm),
        rules: {
          name: [val => (val || '').length > 0 || '제목을 입력해주세요'],
        },
        tmphashtag:null,
        snackbar: false,
        defaultForm,
        items: [
          '강동옥',
          '김태현',
          '박상준',
          '임창현',
          '최이삭',
        ],
        attendPeople : [],
      }
    },

    computed: {
      formIsValid () {
        return (
          this.form.title &&
          this.form.content
        )
      },
    },
    methods: {
      resetForm () {
        this.form = Object.assign({}, this.defaultForm)
        this.$refs.form.reset()
      },
      submit () {
        this.snackbar = true
        this.resetForm()
      },
      addHashTag () { // 해시태그 많아지면 옆으로 넘어가게 하고싶은데 ..
        let flag = 0
        if (this.tmphashtag !== null) {
          if(this.form.hashtag.length === 0){
            this.form.hashtag.push(this.tmphashtag)
          }
          else {
            for(let i=0;i<this.form.hashtag.length; i++){
            if (this.form.hashtag[i] === this.tmphashtag){
              flag = 1
              }
            }
            if(flag === 0){
              this.form.hashtag.push(this.tmphashtag)
            }
          }
        }
        this.tmphashtag = null
        console.log(this.form.hashtag)
        console.log(this.tmphashtag)
      },
      remove (id){
        let idx = this.form.hashtag.indexOf(id)
        this.form.hashtag.splice(idx,1)
        this.form.hashtag = [...this.form.hashtag]
}
    },
  }
</script>


<style scoped>

</style>