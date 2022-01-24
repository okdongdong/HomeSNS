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
          <v-col>
            <v-text-field
              label="Append outer"
              append-outer-icon="mdi-map-marker"
              @click="show = true"
            ></v-text-field>  
          </v-col>
          <v-dialog v-model="show" width="50%" max-width="450px">
            <v-card>
              <v-card-title>
                장소 찍기
              </v-card-title>
              <!-- 구글 api -->
              <div style="text-align:right">
                <v-btn
                  small    
                  @click="getCurrLocation()"
                >현재위치 가지고오기</v-btn>
              </div>
              <v-col>
                <div>
                  <h2>Search and add a pin</h2>
                    <GmapAutocomplete
                      @place_changed='setPlace'
                    />
                    <button
                      @click='addMarker'
                    >
                      Add
                    </button>
                </div>
              <br>
                <div> 
                  <GmapMap
                    :center='center'
                    :zoom='12'
                    style='width:100%;  height: 400px;'
                    @click="mark"
                    
                  >
                  <GmapMarker
                    :key="index"
                    v-for="(m, index) in markers"
                    :position="m.position"
                    @click="center=m.position"
                    :clickable="true"
                    :draggable="true"
                    @drag="updateMarker(index,$event.latLng)"
                  />
                  </GmapMap>
                </div>
              </v-col>
              <v-card-actions>
                <v-btn text>확인</v-btn>
                <v-btn text @click.stop="show=false">취소</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
          <!-- 참석자 명단-->
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
              maxlength="200"
              :counter="200"
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
import {gmapApi} from 'vue2-google-maps'
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
        show : false,
        // 구글 맵
        center: { lat: 37.5642135, lng: 127.0016985 },
        currentPlace: null,
        markers: [],
        places: [],
      }
    },

    computed: {
      formIsValid () {
        return (
          this.form.title &&
          this.form.content
        )
      },
      google: gmapApi,
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
      },
      remove (id){
        let idx = this.form.hashtag.indexOf(id)
        this.form.hashtag.splice(idx,1)
        this.form.hashtag = [...this.form.hashtag]
        },
        // 현재위치 들고오기
        getCurrLocation(){
          navigator.geolocation.getCurrentPosition(position => {
          const currLocation = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
            }
          this.center = currLocation
          this.markers=[{ position: currLocation }]
          });
        },
        setPlace(place) { // 검색해서 찾은 것
          this.currentPlace = place
        },
        addMarker() {
          if (this.currentPlace) {
            const marker = {
              lat: this.currentPlace.geometry.location.lat(),
              lng: this.currentPlace.geometry.location.lng(),
            };
            this.markers=[{ position: marker }]
            this.places = [this.currentPlace]
            this.center = marker
            // this.currentPlace = null;
          }
          console.log(this.markers)
          console.log(this.places)
        },
        mark (event){
          console.log(event)
          console.log(event.latLng.lat())
          console.log(event.latLng.lng())
          const marker = {
            lat : event.latLng.lat(),
            lng : event.latLng.lng()
          }
          this.markers=[{ position: marker }]
            this.places = [this.currentPlace]
            this.center = marker
        },
        updateMarker(index,location){
          console.log(location)
          const marker ={
            lat: location.lat(),
            lng: location.lng()
          }
          this.markers=[{ position: marker }]
          this.places = [this.currentPlace]
          // this.center = marker
        },
    }
  }
</script>


<style scoped>

</style>