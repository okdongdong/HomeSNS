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
              v-model="select"
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
          <v-col>
            <v-combobox
              v-model="model"
              :filter="filter"
              :hide-no-data="!search"
              :items="hashtagitems"
              :search-input.sync="search"
              hide-selected
              label="Search for an option"
              multiple
              small-chips
              solo
            >
            <template v-slot:no-data>
              <v-list-item>
                <span class="subheading">Create</span>
                <v-chip
                  :color="`${colors[nonce - 1]} lighten-3`"
                  label
                  small
                >
                  {{ search }}
                </v-chip>
              </v-list-item>
            </template>
            <template v-slot:selection="{ attrs, item, parent, selected }">
              <v-chip
                v-if="item === Object(item)"
                v-bind="attrs"
                :color="`${item.color} lighten-3`"
                :input-value="selected"
                label
                small
              >
                <span class="pr-2">
                  {{ item.text }}
                </span>
                <v-icon
                  small
                  @click="parent.selectItem(item)"
                >
                  $delete
                </v-icon>
              </v-chip>
            </template>
            <template v-slot:item="{ index, item }">
              <v-text-field
                v-if="editing === item"
                v-model="editing.text"
                autofocus
                flat
                background-color="transparent"
                hide-details
                solo
                @keyup.enter="edit(index, item)"
              ></v-text-field>
              <v-chip
                v-else
                :color="`${item.color} lighten-3`"
                dark
                label
                small
              >
                {{ item.text }}
              </v-chip>
              <v-spacer></v-spacer>
              <v-list-item-action @click.stop>
                <v-btn
                  icon
                  @click.stop.prevent="edit(index, item)"
                >
                  <v-icon>{{ editing !== item ? 'mdi-pencil' : 'mdi-check' }}</v-icon>
                </v-btn>
              </v-list-item-action>
            </template>
          </v-combobox>
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
        bod:null,
      })
      
      return {
        form: Object.assign({}, defaultForm),
        rules: {
          name: [val => (val || '').length > 0 || '제목을 입력해주세요'],
        },

        conditions: false,
        snackbar: false,
        defaultForm,
        items: [
          '강동옥',
          '김태현',
          '박상준',
          '임창현',
          '최이삭',
        ],
        ////////////// 해쉬태그 영역 ................////////////////
        activator: null,
        attach: null,
        colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
        editing: null,
        editingIndex: -1,
        hashtagitems: [
          { header: 'Select an option or create one' },
          {
            text: 'Foo',
            color: 'blue',
          },
          {
            text: 'Bar',
            color: 'red',
          },
        ],
        nonce: 1,
        menu: false,
        model: [
          {
            text: 'Foo',
            color: 'blue',
          },
        ],
        x: 0,
        search: null,
        y: 0,
        ////////////////////////////////////////////////
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
      // 해쉬태그 영역
      edit (index, item) {
        if (!this.editing) {
          this.editing = item
          this.editingIndex = index
        } else {
          this.editing = null
          this.editingIndex = -1
        }
      },
      filter (item, queryText, itemText) {
        if (item.header) return false

        const hasValue = val => val != null ? val : ''

        const text = hasValue(itemText)
        const query = hasValue(queryText)

        return text.toString()
          .toLowerCase()
          .indexOf(query.toString().toLowerCase()) > -1
      },
      //해쉬태그 영역
    },

    // 해쉬태그 영역 //
    watch: {
      model (val, prev) {
        if (val.length === prev.length) return

        this.model = val.map(v => {
          if (typeof v === 'string') {
            v = {
              text: v,
              color: this.colors[this.nonce - 1],
            }

            this.items.push(v)

            this.nonce++
          }

          return v
        })
      },
      // ///////// //
    },
  }
</script>


<style scoped>

</style>