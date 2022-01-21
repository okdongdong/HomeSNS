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
            >
              <template v-slot:label>
                <div>
                  내용
                </div>
              </template>
            </v-textarea>
          </v-col>
          <!-- 해쉬태그 -->

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
        bod:null,
      })
      
      return {
        form: Object.assign({}, defaultForm),
        rules: {
          name: [val => (val || '').length > 0 || '제목을 입력해주세요'],
        },
        attendPeople : [], // 참석자 명단
        // conditions: false,
        // snackbar: false,
        defaultForm,
        items: [
          '강동옥',
          '김태현',
          '박상준',
          '임창현',
          '최이삭',
        ],
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
        // this.snackbar = true
        this.resetForm()
      },
      deleteHashTag (item) {
        console.log(this.hashtagitems)
        console.log(this.model)
        console.log(item)
      }
    },
  }
</script>


<style scoped>

</style>