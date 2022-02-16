<template>
  <div>
  <v-card flat>
    <v-overlay :value="nowLoading">
      <v-progress-circular
        :size="100"
        :width="10"
        color="white"
        indeterminate
      ></v-progress-circular>
    </v-overlay>

    <v-form ref="form" @submit.prevent="submit">
      <v-container fluid>
        <h1>피드작성</h1>
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
          <v-col v-for="(file, i) in form.files" :key="i" class="d-flex child-flex" cols="4">
            <!-- 피드 새로 작성 시 보이는 사진,동영상-->
            <video
              v-if="file.type == 'video' && feedId == -1"
              autoplay
              muted
              :src="file.previewImage"
              @click="deleteFile(i,file)"
            ></video>
            <v-img
              v-else-if="file.type == 'img' && feedId == -1"
              :src="file.previewImage"
              :lazy-src="`https://picsum.photos/200/300`"
              aspect-ratio="1"
              class="grey lighten-2"
              @click="deleteFile(i,file)"
            />
            <!-- 피드 수정 시 보이는 사진,동영상(file.fileUrl로 로딩할 것) -->
            <video
              v-else-if="file.type == 'video' && feedId != -1"
              autoplay
              muted
              :src="`https://i6e205.p.ssafy.io/${file.fileUrl}`"
              @click="deleteFile(i,file)"
            ></video>
            <v-img
              v-else-if="file.type == 'img' && feedId != -1"
              :src="`https://i6e205.p.ssafy.io/${file.fileUrl}`"
              :lazy-src="`https://picsum.photos/200/300`"
              aspect-ratio="1"
              class="grey lighten-2"
              @click="deleteFile(i,file)"
            >
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular
                    indeterminate
                    color="grey lighten-5"
                  ></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>
          <v-col cols="12">
            <v-file-input
              :rules="rules.file"
              multiple
              type="file"
              accept="image/*,video/*"
              @change="selectFile"
              class="form-control-file"
              id="profile_path"
              enctype="multipart/form-data"
            ></v-file-input>
          </v-col>
          <!-- 장소레이블 -->
          <v-col cols="11">
            <v-combobox
              :rules="rules.localName"
              v-model="form.locaLabel"
              :items="locaLabels"
              label="장소 이름"
              @change="locaLabelInput"
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
                <v-col cols="11" @click.stop="selectLoca(data)">
                  {{ data.item.item }}
                </v-col>
              </template>
            </v-combobox>
          </v-col>
          <v-col cols="1" class="d-flex align-center" style="padding: 0px">
            <v-btn icon @click="show = !show" :disabled="roomAbled">
              <v-icon large>room</v-icon>
            </v-btn>
          </v-col>
          <!-- 구글 api -->
          <v-col :style="show ? 'padding:12px' : 'padding:0px'">
            <v-expand-transition>
              <v-card v-show="show">
                <v-btn small @click="getCurrLocation()">현재위치 가지고오기 </v-btn>

                <div class="d-flex justify-center">
                  <GmapAutocomplete @place_changed="setPlace" />
                  <button @click="addMarker">Add</button>
                </div>
                <div>
                  <GmapMap
                    :center="center"
                    :zoom="12"
                    style="width: 100%; height: 400px"
                    @click="mark"
                  >
                    <GmapMarker
                      :key="index + '1'"
                      v-for="(m, index) in markers"
                      :position="m.position"
                      @click="center = m.position"
                      :clickable="true"
                      :draggable="true"
                      @dragend="updateMarker(index, $event.latLng)"
                    />
                    <GmapInfoWindow
                      @closeclick="window_open = false"
                      :key="index"
                      v-for="(m, index) in markers"
                      :opened="window_open"
                      :position="m.position"
                      :options="{
                        pixelOffset: {
                          width: 0,
                          height: -35,
                        },
                      }"
                    >
                      <p
                        style="text-align: center; margin-bottom: 0px"
                        v-html="setActualAddress"
                      ></p>
                    </GmapInfoWindow>
                  </GmapMap>
                </div>
                <br /><br />
              </v-card>
            </v-expand-transition>
          </v-col>
          <!-- 참석자 명단-->
          <v-col cols="12">
            <v-select
              v-model="form.attendPeople"
              :items="items"
              item-text="userName"
              label="참석자 명단"
              multiple
              chips
              return-object
            ></v-select>
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
                <div>내용</div>
              </template>
            </v-textarea>
          </v-col>
          <!-- 해쉬태그 -->
          <v-col cols="12" style="padding-bottom:0px;">
            <v-text-field
              v-model="tmphashtag"
              color="rgb(98,101,232)"
              label="해시태그"
              @keyup.enter="addHashTag()"
              @keyup.space="addHashTag()"
              
            >
            </v-text-field>
          </v-col>
          <v-col cols="12" style="padding-top:0px;">
            <span v-for="(tag, index) in form.hashtag" :key="index">
              <v-chip
                class="ma-1"
                color="deep-purple accent-3"
                outlined
                close
                @click:close="remove(tag)"
                >{{ tag.hashtagContent }}</v-chip
              >
            </span>
          </v-col>
        </v-row>
      </v-container>
      <v-card-actions>
        <v-btn text @click="$router.go(-1)"> Cancel </v-btn>
        <v-spacer></v-spacer>
        <v-btn :disabled="!formIsValid" text color="primary" @click="submitFeed"> Register </v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
  <!-- <Snackbar></Snackbar> -->
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import axios from "axios";
import { gmapApi } from "vue2-google-maps";
// import Snackbar from "@/components/Snackbar.vue";
export default {
  name: "Feedcreate",
  // components:{
  //   Snackbar,
  // },
  props: {
    feedId: Number,
  },
  data() {
    return {
      nowLoading: false,
      form: {
        date: null, // 날짜
        title: null, // 제목
        files: [], // 사진/ 영상
        locaLabel: null, // 장소 이름
        currLocaFav: false, // 장소 즐겨찾기
        //장소위도 -> markers에 있다
        //장소경도 -> markers에 있다.
        attendPeople: [], // 참석한 사람
        content: null, // 내용
        hashtag: [], // 해시태그
      },
      rules: {
        name: [(val) => (val || "").length > 0 || "제목을 입력해주세요"],
        file :[(v) => !!v || "사진 및 동영상을 입력해주세요.",],
        localname:[(v) => !!v || "장소이름 및 장소를 입력해주세요.",],
      },
      tmphashtag: null,
      snackbar: false,

      // 그룹 명단
      items: [],
      show: false,
      roomAbled: false,

      // 구글 맵
      center: { lat: 37.5642135, lng: 127.0016985 }, // 처음 센터 값 (서울)
      currentPlace: null, // 현재위치
      markers: [],
      window_open: false, //실제 주소 띄워주기 popup
      actualAddress: null,
      geocodingService: {},

      // 장소레이블
      locaLabels: [],
      locationId :0, // 장소 본래 있던 것인지 새로운건지 인지하기 용

      deleteFiles :[] // 삭제 파일
    };
  },
  methods: {
    ...mapActions("notice", ["send"]),
    // 장소,
    getFeedInfo() {
      let groupId = this.nowGroup.groupId;
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/feed/info/${groupId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res);
          console.log("=====================");
          this.items = res.data.members;
          res.data.locations.forEach((location) => {
            this.locaLabels.push({
              idx: location.locationId,
              item: location.locationName,
              lat: location.lat,
              lng: location.lng,
              fav: location.favorite,
            });
          });
          console.log('locaLabel 들어오는지 확인')
          console.log(this.locaLabels);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    ////////// feed update시 들고올 데이터 ////////////
    FeedUpdateInfo() {
      const token = localStorage.getItem("jwt");
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/feed/${this.feedId}`,
        headers: { Authorization: token },
      })
        .then((res) => {
          console.log(res.data);
          const originData = res.data.feedDto;
          /////////// 문자열 -> 날짜 최종 포맷 (ex - '2021-10-08') ///////////////
          let dateString =
            originData.feedEventDate.year +
            "-" +
            originData.feedEventDate.month +
            "-" +
            originData.feedEventDate.day;
          let dateDateForm = new Date(dateString);
          let year = dateDateForm.getFullYear();
          let month = dateDateForm.getMonth() + 1;
          let day = dateDateForm.getDate();
          if (month < 10) {
            month = "0" + month;
          }
          if (day < 10) {
            day = "0" + day;
          }
          this.form.date = year + "-" + month + "-" + day;
          this.form.title = originData.feedTitle;
          this.form.locaLabel = originData.locationDto.locationName;
          this.form.currLocaFav = originData.locationDto.favorite;
          this.markers = [
            { position: { lat: originData.locationDto.lat, lng: originData.locationDto.lng } },
          ];
          this.center = { lat: originData.locationDto.lat, lng: originData.locationDto.lng };
          this.actualAddress = this.getActualAddress({
            lat: originData.locationDto.lat,
            lng: originData.locationDto.lng,
          });
          this.form.content = originData.feedContent;
          for (let i = 0; i < originData.fileList.length; i++) {
            if (originData.fileList[i].fileContentType.includes("image")) {
              this.form.files.push({
                fileId : originData.fileList[i].fileId,
                type: "img",
                content: originData.fileList[i].fileName,
                fileUrl : originData.fileList[i].fileUrl,
              });
            } else {
              this.form.files.push({
                fileId : originData.fileList[i].fileId,
                type: "video",
                content: originData.fileList[i].fileName,
                fileUrl : originData.fileList[i].fileUrl,
              });
            }
          }
          this.form.hashtag = originData.hashtagList;
          this.form.attendPeople = originData.userList;
          this.locationId = originData.locationDto.locationId; // 수정할때 기본 선택되어있는 locationId
          // 구글맵쪽 설정해줄 것 설정해주기..!
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submit() {
      this.snackbar = true;
      // this.resetForm();
    },
    locaLabelInput(value) {
      console.log(value)
      if (value === null) {
        if (this.locaLabel !== null) {
          value = true;
        }
      }
      // else{

      // }
    },
    addHashTag() {
      // 해시태그 많아지면 옆으로 넘어가게 하고싶은데 ..
      let flag = 0;
      if (this.tmphashtag !== null) {
        if (this.form.hashtag.length === 0) {
          this.form.hashtag.push({ hashtagContent: this.tmphashtag.trim() });
        } else {
          for (let i = 0; i < this.form.hashtag.length; i++) {
            if (this.form.hashtag[i].hashtagContent === this.tmphashtag) {
              flag = 1;
            }
          }
          if (flag === 0) {
            this.form.hashtag.push({ hashtagContent: this.tmphashtag.trim() });
          }
        }
      }
      this.tmphashtag = null;
    },
    remove(id) {
      // 해시태그 없애기

      let idx = this.form.hashtag.indexOf(id);
      this.form.hashtag.splice(idx, 1);
      this.form.hashtag = [...this.form.hashtag];
    },

    getCurrLocation() {
      // 현재위치 들고오기
      navigator.geolocation.getCurrentPosition((position) => {
        const currLocation = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
        this.center = currLocation;
        this.markers = [{ position: currLocation }];
      });
    },
    setPlace(place) {
      // console.log('----------------')
      // console.log(place)
      const marker = {
        lat: place.geometry.location.lat(),
        lng: place.geometry.location.lng(),
      };
      this.currentPlace = place;
      this.addMarker();
      this.getActualAddress(marker);
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.markers = [{ position: marker }];
        // this.places = [this.currentPlace]
        this.center = marker;
        // this.currentPlace = null;
      }
    },
    getActualAddress(data) {
      axios({
        method: "GET",
        url: `/map-reversegeocode/v2/gc?coords=${data.lng},${data.lat}&sourcecrs=epsg:4326&orders=roadaddr&output=json`,
        headers: {
          "X-NCP-APIGW-API-KEY-ID": `${process.env.VUE_APP_NAVER_CLIENT_ID}`,
          "X-NCP-APIGW-API-KEY": `${process.env.VUE_APP_NAVER_CLIENT_SECRET}`,
        },
      })
        .then((res) => {
          if (res.data.status.code === 3) {
            this.actualAddress = "주소가 조회되지 않습니다.\n위치를 다시 지정해주세요.";
          } else {
            let data = res.data.results[0];
            let city = data.region.area1.name; // 시
            let add1 = data.region.area2.name; // 구,군
            let add2 = data.region.area4.name; // 면
            let road = data.land.name; // 남대문로 10길
            let num1 = data.land.number1; // 17
            let num2 = data.land.number2; // ''
            let building = data.land.addition0.value; // 건물명
            if (num2 === "") {
              if (building === "") {
                this.actualAddress = city + " " + add1 + " " + add2 + "\n" + road + " " + num1;
              } else {
                this.actualAddress =
                  city + " " + add1 + " " + add2 + "\n" + road + " " + num1 + "\n" + building;
              }
            } else {
              if (building !== "") {
                this.actualAddress =
                  city + " " + add1 + " " + add2 + "\n" + road + " " + num1 + "-" + num2;
              }
              this.actualAddress =
                city +
                " " +
                add1 +
                " " +
                add2 +
                "\n" +
                road +
                " " +
                num1 +
                "-" +
                num2 +
                "\n" +
                building;
            }
          }
          this.window_open = true; // 실제주소 popup true
        })
        .catch((error) => {
          console.log(error);
        });
    },
    mark(event) {
      const marker = {
        lat: event.latLng.lat(),
        lng: event.latLng.lng(),
      };
      this.markers = [{ position: marker }];
      this.center = marker;
      this.getActualAddress(marker);
    },
    updateMarker(index, location) {
      // 업데이트 마커 by 드래그
      const marker = {
        lat: location.lat(),
        lng: location.lng(),
      };
      this.markers = [{ position: marker }];
      this.getActualAddress(marker);
    },

    toggleFavBtn(data) {
      // 목록에 있는 데이터 즐겨찾기 토글
      // 목록안의 idx를 가지고와서 토글하면 엉뚱한게 토글되므로, 그 index를 가진 위치(index)를 찾아야함.
      let toggleIdx = data.item.idx;
      for (let i = 0; i < this.locaLabels.length; i++) {
        if (this.locaLabels[i].idx === toggleIdx) {
          console.log(this.locaLabels[i].idx);
          toggleIdx = i;
          console.log(toggleIdx);
        }
      }
      this.locaLabels[toggleIdx].fav = !this.locaLabels[toggleIdx].fav;
      if (this.form.locaLabel === data.item.item) {
        this.form.currLocaFav = this.locaLabels[toggleIdx].fav;
      }
    },
    selectLoca(data) {
      console.log(data);
      // 목록에 있는 데이터를 장소정보로 쓸려고할때
      this.form.locaLabel = data.item.item;
      this.form.currLocaFav = data.item.fav;
      this.locationId = data.item.idx;
      // 핀도 고정시키기
      let marker = { lng: data.item.lng, lat: data.item.lat };
      this.markers = [{ position: marker }];
      this.getActualAddress(marker);
    },
    toggleCurrLocaFavBtn() {
      // 현재 장소 즐겨찾기 등록 여부
      this.form.currLocaFav = !this.form.currLocaFav;
    },
    selectFile: function (data) {
      for (let i = 0; i < data.length; i++) {
        if (data[i].type.includes("video")) {
          // 나는 비디오
          this.form.files.push({
            fileId : 0,
            type: "video",
            content: data[i],
            previewImage: URL.createObjectURL(data[i]),
          });
        } else {
          this.form.files.push({
            fileId : 0,
            type: "img",
            content: data[i],
            previewImage: URL.createObjectURL(data[i]),
          });
        }
      }
    },
    deleteFile(i,deletefile) {
      if(this.feedId != -1 && deletefile.fileId != 0){ // 수정일때, 그리고 새로올린 사진이 아닐때
        console.log(deletefile)
        this.deleteFiles.push({
          fileId : deletefile.fileId,
          fileUrl :deletefile.fileUrl,
        })
      }
      this.form.files.splice(i, 1);

    },

    //데이터 쏘기
    submitFeed() {
      this.nowLoading = true;
      const token = localStorage.getItem("jwt");
      let data = new FormData();
      data.append("feedTitle", this.form.title);
      data.append("feedEventDate", this.form.date);
      
      let flag = 0
      for(let i=0;i<this.locaLabels.length;i++){ // 기존리스트 있는 경우
        if(this.form.locaLabel===this.locaLabels[i].item && this.markers[0].position.lat===this.locaLabels[i].lat && this.markers[0].position.lng===this.locaLabels[i].lng){
          data.append("feedLocationId", this.locationId)
          flag =1
          break
        }
      }
      if(flag === 0){
        let feedLocationStr = {};
            if (this.form.localLabel === 0 || this.markers.length === 0) {
              feedLocationStr["locationName"] = null; // front에서 그냥 넘겨주기때문에 back에서 목록에 원래 있는건지 확인하고 값 넣기
              feedLocationStr["lat"] = null;
              feedLocationStr["lng"] = null;
              feedLocationStr["favorite"] = false; // 장소 즐겨찾기 여부
            } else {
              feedLocationStr["locationName"] = this.form.locaLabel;
              feedLocationStr["lat"] = this.markers[0].position.lat; // 위도
              feedLocationStr["lng"] = this.markers[0].position.lng; // 경도
              feedLocationStr["favorite"] = this.form.currLocaFav; // 장소 즐겨찾기 여부
            }
            data.append("feedLocationStr", JSON.stringify(feedLocationStr));
      }
      // 참석인이 있는 경우
      let attendeeList = [];
      if (this.form.attendPeople.length > 0) {
        for (let i = 0; i < this.form.attendPeople.length; i++) {
          attendeeList.push({ userSeq: this.form.attendPeople[i].userSeq });
        }
      }
      data.append("feedAttendees", JSON.stringify(attendeeList));
      data.append("feedContent", this.form.content);
      data.append("feedHashtags", JSON.stringify(this.form.hashtag));
      for (let i = 0; i < this.form.files.length; i++) {
        data.append("file", this.form.files[i].content);
        }
      for (let [key, value] of data) {
        console.log(key);
        console.log(value);
      }
      data.append("groupId", this.nowGroup.groupId);

      ////////////// 새로운 피드 작성 /////////////////////
      if (this.feedId == -1) {
        
        console.log('새로운 피드작성')
        axios({
          method: "POST",
          url: `${process.env.VUE_APP_MCS_URL}/feed`,
          data: data,
          headers: {
            Authorization: token,
            "Content-Type": "multipart/form-data",
          },
        })
          .then((res) => {
            console.log("피드작성 성공");
            this.nowLoading = false;
            const noticeInfo = {
                targetUserSeq: -1,
                noticeType: "feedCreate",
                noticeContentId: res.data.feedId,
              };
              this.send(noticeInfo);
          })
          .then(() => {
            this.$router.push({
              name: "Main",
              params: { groupId: this.nowGroup.groupId },
            }); // 이전 페이지로 보내기
          })
          .catch((err) => {
            this.nowLoading = false;
            console.log(err);
          });
      }
      //////////////////////////// 피드 업데이트 /////////////////////////
      else{
        console.log('기존 피드 업데이트')
        if(flag===0){
          data.append("feedLocationId", 0)
        }
        data.append("feedId", this.feedId)
        data.append("fileDeleteStr", JSON.stringify(this.deleteFiles))
        axios({
          method: "POST",
          url: `${process.env.VUE_APP_MCS_URL}/feed/update`,
          data: data,
          headers: {
            Authorization: token,
            "Content-Type": "multipart/form-data",
          },
        })
        .then(()=>{
          console.log('피드수정 성공')
          this.$router.push({name:"Detail", params:{feedId:this.feedId}})
        })
        .catch((error)=>{
          console.log('피드수정 실패')
          console.log(error)
        })
      }
    },
  },
  created() {
    console.log(typeof this.feedId)
    this.getFeedInfo();
    if (this.feedId != -1) {
      console.log('여기들어옴?')
      this.FeedUpdateInfo();
    }
  },
  computed: {
    formIsValid() {
      // if(!!this.form.files.length){
      //   this.$store.dispatch('snackbar/')
      // }
      return this.form.title && this.form.content && this.form.files.length && this.markers.length && this.form.locaLabel;
    },
    setActualAddress() {
      // 텍스트 줄바꿈을 위해
      if (this.actualAddress) {
        return this.actualAddress.replace("\n", "<br />");
      } else {
        return null;
      }
    },
    google: gmapApi,
    ...mapState("account", ["nowGroup"]),
    
  },
};
</script>

<style scoped>
.preview-img {
  display: block;
  margin: 0px auto;
  /* height: 70%; */
  width: 50%;
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
  padding-bottom: 5%;
}
</style>
