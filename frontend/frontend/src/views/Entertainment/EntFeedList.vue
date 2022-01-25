<template>
  <div class="container">
    <div class="d-flex justify-space-between align-center">
      <h1>ENTERTAINMENT</h1>
      <EntCreatePopup :dialog="dialog" />
    </div>
    <div v-for="content in contentData" :key="content">
      <EntFeedContainer :content="content" />
    </div>
  </div>
</template>

<script>
import EntFeedContainer from "../../components/Entertainment/EntFeedContainer.vue";
import EntCreatePopup from "../../components/Entertainment/EntCreatePopup.vue";
import axios from "axios";

export default {
  name: "EntFeedList",
  data: () => ({
    dialog: false,
    contentData: [
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
    getContent();
  },
  beforeUpdate() {
    getContent();
  },
  methods: {
    getContent() {
      // 임시용 경로변경
      this.$router.push({ name: "EntFeedList" });

      const data = {
        playerNum: 0,
        isEdge: [],
        playerNames: [],
        resultNames: [],
      };
      data.playerNum = this.playerNum;
      for (let i = 1; i <= this.playerNum; i++) {
        data.isEdge.push(this.isEdge[i]);
        data.playerNames.push(this.playerNames[i]);
        data.resultNames.push(this.resultNames[i]);
      }
      axios({
        method: "get",
        url: `${process.env.VUE_APP_MCS_URL}/ghostleg`,
        data: data,
      })
        .then((res) => {
          this.contentData = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
