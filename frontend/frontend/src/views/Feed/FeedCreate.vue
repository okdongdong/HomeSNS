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
              v-model="form.date"
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
          <v-col
            v-for="(file,i) in form.files"
            :key="i"
            class="d-flex child-flex"
            cols="4"
          >
            <video 
              v-if="file.type == 'video'"
              autoplay
              muted
              :src="file.previewImage"
              @click="deleteFile(i)"
              >
            </video>
            <v-img
              v-else
              :src="file.previewImage"
              :lazy-src="`https://picsum.photos/200/300`"
              aspect-ratio="1"
              class="grey lighten-2"
              @click="deleteFile(i)"
            >
            
              <template v-slot:placeholder>
                <v-row
                  class="fill-height ma-0"
                  align="center"
                  justify="center"
                >
                  <!-- <v-progress-circular
                    indeterminate
                    color="grey lighten-5"
                  ></v-progress-circular> -->
                </v-row>
              </template>
            </v-img>
          </v-col>
          <v-col cols="12">
            <v-file-input multiple type="file" accept="image/*,video/*" @change="selectFile" class="form-control-file" id="profile_path" enctype="multipart/form-data"></v-file-input> 
          </v-col>
          <!-- 장소레이블 -->
          <v-col cols="11">
            <v-combobox
              v-model="form.locaLabel"
              :items="locaLabels"
              label="장소 이름"
            >
            <v-btn 
              slot="append"
              icon
              @click.stop="toggleCurrLocaFavBtn()"
              v-bind:color="form.currLocaFav ? 'orange' : 'gray'"
              >
              <v-icon>mdi-star</v-icon>
              </v-btn>
              <template slot="item" slot-scope="data">
                  <v-btn
                    icon
                    @click.stop="toggleFavBtn(data)"
                    v-bind:color="data.item.fav ? 'orange' : 'gray'"
                  >
                  <v-icon>mdi-star</v-icon>
                  </v-btn>
                  <v-col cols="11" @click.stop="selectFavLoca(data)">
                    {{data.item.item}}
                  </v-col>
              </template>
            </v-combobox>
          </v-col>
          <v-col cols="1" class="d-flex align-center" style="padding:0px">
            <v-btn icon @click="show = !show">
              <v-icon large>room</v-icon>
            </v-btn>
          </v-col>
          <!-- 구글 api -->
          <v-col :style=" show? 'padding:12px':'padding:0px'">
            <v-expand-transition >
              <v-card v-show="show">
                <v-btn
                  small    
                  @click="getCurrLocation()"
                >현재위치 가지고오기
                </v-btn>

                  <div class="d-flex justify-center">
                    <GmapAutocomplete
                      @place_changed='setPlace'
                    />
                    <button
                      @click='addMarker'
                    >
                      Add
                    </button>
                  </div>
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
                    <br><br>
                </v-card>
              </v-expand-transition>
            </v-col>
          <!-- 참석자 명단-->
          <v-col cols="12">
            <v-combobox
              v-model="form.attendPeople"
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
          @click="$router.go(-1)"
        >
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          :disabled="!formIsValid"
          text
          color="primary"
          @click="submitFeed"
        >
          Register
        </v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
import axios from 'axios'
import {gmapApi} from 'vue2-google-maps'
  export default {
    name: 'Feedcreate',
    data () {
      return {
        form :{
          date : null, // 날짜
          title: null, // 제목
          files : [], // 사진/ 영상
          locaLabel : null, // 장소 이름
          currLocaFav : false, // 장소 즐겨찾기
          //장소위도 -> markers에 있다
          //장소경도 -> markers에 있다.
          attendPeople : [], // 참석한 사람
          content: null, // 내용
          hashtag:[], // 해시태그
          
        },
        rules: {
          name: [val => (val || '').length > 0 || '제목을 입력해주세요'],
        },
        tmphashtag:null,
        snackbar: false,
        items: [ // 그룹 명단
          '강동옥',
          '김태현',
          '박상준',
          '임창현',
          '최이삭',
        ],
        show : false,
        // 구글 맵
        center: { lat: 37.5642135, lng: 127.0016985 }, // 처음 센터 값 (서울)
        currentPlace: null, // 현재위치
        markers: [],
        // places: [],
        geocodingService : {},
        // 장소레이블
        locaLabels : [
          {
            item : '내 집',
            fav : true,
            idx : 0
          },
          {
            item : '학교',
            fav : true,
            idx : 1
          },
          {
            item : '울산바위',
            fav : false,
            idx : 2
          },
          {
            item : '대구존맛막창집',
            fav : false,
            idx : 3
          },

        ],
      }
    },


    methods: {
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
      remove (id){ // 해시태그 없애기
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
        setPlace(place) {
          this.currentPlace = place
        },
        addMarker() {
          if (this.currentPlace) {
            const marker = {
              lat: this.currentPlace.geometry.location.lat(),
              lng: this.currentPlace.geometry.location.lng(),
            };
            this.markers=[{ position: marker }]
            // this.places = [this.currentPlace]
            this.center = marker
            // this.currentPlace = null;
          }
        },
        mark (event){
          const marker = {
            lat : event.latLng.lat(),
            lng : event.latLng.lng()
          }
          this.markers=[{ position: marker }]
            // this.places = [this.currentPlace]
            this.center = marker
        },
        updateMarker(index,location){
          const marker ={
            lat: location.lat(),
            lng: location.lng()
          }
          this.markers=[{ position: marker }]
          // this.places = [this.currentPlace]
        },
        toggleFavBtn(data){ // 목록에 있는 데이터 즐겨찾기 토글
          this.locaLabels[data.item.idx].fav = !this.locaLabels[data.item.idx].fav
          if(this.form.locaLabel === data.item.item){
            this.form.currLocaFav = this.locaLabels[data.item.idx].fav
          }
        },
        selectFavLoca(data){ // 목록에 있는 데이터를 장소정보로 쓸려고할때
          this.form.locaLabel = data.item.item
          this.form.currLocaFav = data.item.fav
        },
        toggleCurrLocaFavBtn(){ // 현재 장소 즐겨찾기 등록 여부
          this.form.currLocaFav = !this.form.currLocaFav
        },
        selectFile:function(data){ 
          for(let i=0;i<data.length;i++){
            if(data[i].type.includes('video')){ // 나는 비디오
              this.form.files.push({
                type : 'video',
                content : data[i],
                previewImage : URL.createObjectURL(data[i])
              })
            }
            else{
              this.form.files.push({
                type : 'img',
                content : data[i],
                previewImage : URL.createObjectURL(data[i])
              })
            }
          }
          },
        deleteFile(i){
          this.form.files.splice(i,1)
        },

        //데이터 쏘기
        submitFeed(){
          const token = localStorage.getItem('jwt')
          let data = new FormData()
          data.append('feedTitle',this.form.title)
          data.append('feedEventDate', this.form.date)
          let file = []
          for(let i = 0; i<this.form.files.length ;i++){
            file.push(this.form.files.content)
          }
          data.append('fileList',file)
          if(this.form.localLabel === 0 || this.markers.length === 0){
            data.append('feedLocation', null)// front에서 그냥 넘겨주기때문에 back에서 목록에 원래 있는건지 확인하고 값 넣기
            data.append('Lat', null)
            data.append('Lng',null)
            data.append('Fav',false) // 장소 즐겨찾기 여부
          }
          else{
            data.append('feedLocation', this.form.locaLabel)
            data.append('Lat', this.markers[0].lat) // 위도
            data.append('Lng',this.markers[0].lng) // 경도
            data.append('Fav',this.form.currLocaFav) // 장소 즐겨찾기 여부
          }
          data.append('attendPpl',this.form.attendPeople) // 참석인 변수 모룸,,
          data.append('feedContent', this.form.content)
          data.append('hashtagList', this.form.hashtagList)
          axios({
            method : 'POST',
            url : `${process.env.VUE_APP_MCS_URL}/feed`,
            data : data,
            headers : { Authorization : `JWT ${token}`}
          })
          .then(()=>{
            console.log('피드작성 성공')
            this.$router.go(-1) // 이전 페이지로 보내기
          })
          .catch((err)=>{
            console.log(err)
          })
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
  }
</script>


<style scoped>
  .preview-img {
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