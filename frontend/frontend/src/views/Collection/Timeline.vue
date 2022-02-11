<template>
  <v-app class="container">
    <v-timeline dense align-top>
      <v-timeline-item v-for="feed in feeds" :key="feed.feedId">
        <template v-slot:icon>
          <v-avatar>
            <v-img :src="feed.feedAuthorProfileUrl"></v-img>
          </v-avatar>
        </template>
        <v-card>
          <v-col cols="12" class="d-flex">
            <v-img
              alt="피드사진"
              max-width="120px"
              :src=feed.feedFileUrls[0]
              @click="expand = !expand"
            ></v-img>
            <v-row style="width: 150px">
              <v-card-title>
                {{ feed.feedEventDate }}
              </v-card-title>
              <v-card-subtitle>
                참석자 or 해쉬태그 or 기타 등등
              </v-card-subtitle>
              <v-card-text>
                {{ feed.feedTitle }}
              </v-card-text>
            </v-row>
          </v-col>
        </v-card>
        <v-expand-transition>
          <div class="feeds-group" v-show="expand">
            <v-container>
              <v-row>
                <v-col class="d-flex" v-for="fileUrl in feed.feedFileUrls" :key="fileUrl">
                  <v-img
                    :src="fileUrl"
                    aspect-ratio="1"
                    width="120px"
                    height="120px"
                    class="grey lighten-2"
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
              </v-row>

              <v-card>
                <v-container>
                  <v-row>
                    <v-col
                      v-for="n in 9"
                      :key="n"
                      class="d-flex child-flex"
                      cols="4"
                    >
                      <v-img
                        :src="`https://picsum.photos/200/300`"
                        :lazy-src="`https://picsum.photos/200/300`"
                        aspect-ratio="1"
                        class="grey lighten-2"
                      >
                        <template v-slot:placeholder>
                          <v-row
                            class="fill-height ma-0"
                            align="center"
                            justify="center"
                          >
                            <v-progress-circular
                              indeterminate
                              color="grey lighten-5"
                            ></v-progress-circular>
                          </v-row>
                        </template>
                      </v-img>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card>
            </v-container>
          </div>
        </v-expand-transition>
      </v-timeline-item>
    </v-timeline>
  </v-app>
</template>

<script>
export default {
  name: "Timeline",

  data: () => ({
    expand: false,
    feeds: [
      {
        feedId: 2,
        feedAuthor: "강동옥",
        feedAuthorProfileUrl:
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FUN4WZ%2Fbtq54qF4hex%2FRfXpBtSZxnG0bRauOiwdX0%2Fimg.jpg",
        feedTitle: "제목",
        feedEventDate: "2022-01-28",
        feedFileUrls: [
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FUN4WZ%2Fbtq54qF4hex%2FRfXpBtSZxnG0bRauOiwdX0%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnKYkx%2Fbtq52p9seNz%2FRhaNtgRAJAgJo2WOn5LPWk%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbQV7JG%2Fbtq54UfTzI8%2FRpLGN02Oi66U4zW5K8B6D0%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbMSU5h%2Fbtq53vnCCiP%2FH4QZpvm73h0IvzxeLkKHN1%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F59ILH%2Fbtq57pNc4Ok%2FfwIq5YOKvVAkcF0kJOtrpK%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FydCWB%2Fbtq52zjIQ29%2F9xNKnhdWSA5BIRuTKh5Tk1%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkCFmG%2Fbtq54U1ezaO%2FVK1ftWa9hZWZK4GOvr4YEK%2Fimg.jpg",
        ],
      },
      {
        feedId: 2,
        feedAuthor: "박상준",
        feedAuthorProfileUrl:
          "https://w.namu.la/s/1bbf9e43debd0f295834403383838b748663a29ac014ac0c92926b6d9cee57f9a2e5ab8bf76b0909dc410fca024495859f6dce298b74ce88240fe9f051a38dbbaadc0200f84f8d611ada391a1e8536bb790460a4e3cf4e6c3e3dd6d63d4e146b",
        feedTitle: "제목",
        feedEventDate: "2021-01-28",
        feedFileUrls: [
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxPjoc%2Fbtq54VeN4gR%2FAiDpmbRed0BYKlgjgPtZvk%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FYMovS%2Fbtq52A3ZiK0%2FF54AXyiPjy7DUBTQDSKMGK%2Fimg.jpg",
          "https://blog.kakaocdn.net/dn/qkP5A/btrgXhzsmTw/FwAeKHfD7kR1iDHJoUjpv0/img.gif",
        ],
      },
      {
        feedId: 2,
        feedAuthor: "강동옥3",
        feedAuthorProfileUrl:
          "https://w.namu.la/s/1bbf9e43debd0f295834403383838b748663a29ac014ac0c92926b6d9cee57f9a2e5ab8bf76b0909dc410fca024495859f6dce298b74ce88240fe9f051a38dbbaadc0200f84f8d611ada391a1e8536bb790460a4e3cf4e6c3e3dd6d63d4e146b",
        feedTitle: "제목",
        feedEventDate: "2019-01-28",
        feedFileUrls: [
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FUN4WZ%2Fbtq54qF4hex%2FRfXpBtSZxnG0bRauOiwdX0%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnKYkx%2Fbtq52p9seNz%2FRhaNtgRAJAgJo2WOn5LPWk%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbQV7JG%2Fbtq54UfTzI8%2FRpLGN02Oi66U4zW5K8B6D0%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbMSU5h%2Fbtq53vnCCiP%2FH4QZpvm73h0IvzxeLkKHN1%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F59ILH%2Fbtq57pNc4Ok%2FfwIq5YOKvVAkcF0kJOtrpK%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FydCWB%2Fbtq52zjIQ29%2F9xNKnhdWSA5BIRuTKh5Tk1%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkCFmG%2Fbtq54U1ezaO%2FVK1ftWa9hZWZK4GOvr4YEK%2Fimg.jpg",
        ],
      },
      {
        feedId: 2,
        feedAuthor: "강동옥2",
        feedAuthorProfileUrl:
          "https://w.namu.la/s/1bbf9e43debd0f295834403383838b748663a29ac014ac0c92926b6d9cee57f9a2e5ab8bf76b0909dc410fca024495859f6dce298b74ce88240fe9f051a38dbbaadc0200f84f8d611ada391a1e8536bb790460a4e3cf4e6c3e3dd6d63d4e146b",
        feedTitle: "제목",
        feedEventDate: "2022-11-28",
        feedFileUrls: [
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxPjoc%2Fbtq54VeN4gR%2FAiDpmbRed0BYKlgjgPtZvk%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FYMovS%2Fbtq52A3ZiK0%2FF54AXyiPjy7DUBTQDSKMGK%2Fimg.jpg",
          "https://blog.kakaocdn.net/dn/qkP5A/btrgXhzsmTw/FwAeKHfD7kR1iDHJoUjpv0/img.gif",
        ],
      },
      {
        feedId: 2,
        feedAuthor: "강동옥",
        feedAuthorProfileUrl:
          "https://w.namu.la/s/1bbf9e43debd0f295834403383838b748663a29ac014ac0c92926b6d9cee57f9a2e5ab8bf76b0909dc410fca024495859f6dce298b74ce88240fe9f051a38dbbaadc0200f84f8d611ada391a1e8536bb790460a4e3cf4e6c3e3dd6d63d4e146b",
        feedTitle: "제목11231232",
        feedEventDate: "2022-2-28",
        feedFileUrls: [
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxPjoc%2Fbtq54VeN4gR%2FAiDpmbRed0BYKlgjgPtZvk%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FYMovS%2Fbtq52A3ZiK0%2FF54AXyiPjy7DUBTQDSKMGK%2Fimg.jpg",
          "https://blog.kakaocdn.net/dn/qkP5A/btrgXhzsmTw/FwAeKHfD7kR1iDHJoUjpv0/img.gif",
        ],
      },
      {
        feedId: 2,
        feedAuthor: "강동옥",
        feedAuthorProfileUrl:
          "https://w.namu.la/s/1bbf9e43debd0f295834403383838b748663a29ac014ac0c92926b6d9cee57f9a2e5ab8bf76b0909dc410fca024495859f6dce298b74ce88240fe9f051a38dbbaadc0200f84f8d611ada391a1e8536bb790460a4e3cf4e6c3e3dd6d63d4e146b",
        feedTitle: "제목3333",
        feedEventDate: "2010-01-28",
        feedFileUrls: [
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FUN4WZ%2Fbtq54qF4hex%2FRfXpBtSZxnG0bRauOiwdX0%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnKYkx%2Fbtq52p9seNz%2FRhaNtgRAJAgJo2WOn5LPWk%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbQV7JG%2Fbtq54UfTzI8%2FRpLGN02Oi66U4zW5K8B6D0%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbMSU5h%2Fbtq53vnCCiP%2FH4QZpvm73h0IvzxeLkKHN1%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F59ILH%2Fbtq57pNc4Ok%2FfwIq5YOKvVAkcF0kJOtrpK%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FydCWB%2Fbtq52zjIQ29%2F9xNKnhdWSA5BIRuTKh5Tk1%2Fimg.jpg",
          "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FkCFmG%2Fbtq54U1ezaO%2FVK1ftWa9hZWZK4GOvr4YEK%2Fimg.jpg",
        ],
      },
    ],
  }),
};
</script>

<style scoped>
.feeds-group {
  background-color: white;
  margin: 10px 20px 20px 5px;
  box-shadow: 12px 12px 2px 1px rgb(173, 173, 255);
  /* 위 오른 아래 왼 */
  /* border-style: solid dotted dotted solid;
  border-style: dotted solid solid dotted; */
  border-color: rgb(240, 240, 240) rgb(192, 192, 192) rgb(192, 192, 192) rgb(240, 240, 240);
  border-width: 2px 6px 6px 2px;
  border-style: solid none none solid;
  /* border-style: dotted none none dotted; */
  /* border-style: solid; */

}
</style>