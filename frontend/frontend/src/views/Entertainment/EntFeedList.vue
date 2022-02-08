<template>
  <div class="container">
    <div class="d-flex justify-space-between align-center">
      <h1>ENTERTAINMENT</h1>
      <EntCreatePopup :dialog="dialog" />
    </div>
    <div v-for="(content, idx) in contentDataList" :key="idx">
      <EntFeedContainer :content="content" />
    </div>
            <infinite-loading @infinite="getContent"></infinite-loading>

  </div>
</template>

<script>
import EntFeedContainer from "../../components/Entertainment/EntFeedContainer.vue";
import EntCreatePopup from "../../components/Entertainment/EntCreatePopup.vue";
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "EntFeedList",
  data: () => ({
    dialog: false,
    start: 0,
    contentDataList: [
      {
        type: "ghost",
        info: {
          title: "이것은 제목입니다",
          author: "작성자",
          authorPicUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiM_3DwEqCAeTk61eqxYHzcNibjWl3_2fPfQ&usqp=CAU",
          createdAt: "2020.02.02",
          playerNum: 10,
          isEdge: [
            [],
            [0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0],
            [0, 1, 1, 0, 2, 0, 2, 0, 2, 0, 0],
            [0, 2, 2, 1, 0, 0, 1, 0, 0, 1, 0],
            [0, 0, 0, 2, 0, 1, 2, 1, 0, 2, 0],
            [0, 1, 0, 0, 0, 2, 1, 2, 1, 1, 0],
            [0, 2, 1, 1, 0, 1, 2, 1, 2, 2, 0],
            [0, 0, 2, 2, 1, 2, 1, 2, 1, 0, 0],
            [0, 0, 1, 0, 2, 1, 2, 1, 2, 1, 0],
            [0, 0, 2, 1, 0, 2, 0, 2, 1, 2, 0],
            [0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
          ],
          playerNames: [
            0,
            "강동옥",
            "박상준",
            "김태현",
            "최이삭",
            "임창현",
            "강동옥2",
            "박상준2",
            "김태현2",
            "최이삭2",
            "임창현2",
          ],
          resultNames: [
            0,
            "결과1",
            "결과2",
            "결과3",
            "결과4",
            "결과5",
            "결과6",
            "결과7",
            "결과8",
            "결과9",
            "결과10",
          ],
        },
      },
      {
        type: "vote",
        info: {
          voteId: 1,
          title: "12312312312",
          author: "작성자",
          authorId: 2,
          authorPicUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVxb9s4l1TQIf3ZGk-aajm_mSgC8oqx4jmCQ&usqp=CAU",
          createdAt: "2020.02.02",

          nowVote: false,
          voteItems: [
            {
              voteItemName: "ㅁㅁㅁㄴㄴㅇㅇ",
              voteCnt: 3,
            },
            {
              voteItemName: "xogus짱짱맨",
              voteCnt: 0,
            },
            {
              voteItemName: "쭈꾸미 비빔밥",
              voteCnt: 6,
            },
          ],
          myVoteItem: null,
        },
      },
    ],
  }),
  components: {
    EntFeedContainer,
    EntCreatePopup,
  },
  created() {
    this.getContent();
  },
  beforeUpdate() {},
  methods: {
    getContent($state) {
      const token = localStorage.getItem("jwt");

      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/entfeedlist}`,
        headers: { Authorization: token },
        params: {
          groupId: this.nowGroup.groupId,
          start: this.start
        }
      })
        .then((res) => {
          if(res.data.length) {
          res.data.forEach((contentData) => {
            if (contentData.type == "ghost") {
              const tempEdge = [];
              contentData.isEdge.split("+").forEach((arr) => {
                tempEdge.push(arr.aplit(","));
              });
              const tempPlayerNames = contentData.playerNames.split(",");
              const tempResultNamse = contentData.resultNames.split(",");
              this.contentDataList.push({
                type: "ghost",
                title: contentData.title,
                author: contentData.author,
                authorPicUrl: contentData.authorPicUrl,
                createdAt: contentData.createdAt,
                playerNum: contentData.playerNum,
                isEdge: tempEdge,
                playerNames: tempPlayerNames,
                resultNames: tempResultNamse,
              });
            } else {
              this.contentDataList.push({
                ...contentData,
              });
            }
            this.start+=20
            $state.loaded();
          });} else {
            $state.complete();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  computed: {
    ...mapState("account", ["nowGroup"]),
  },
};
</script>

<style></style>
