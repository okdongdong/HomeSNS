<template>
  <div class="pa-3">
    <div class="pa-5 content-box">
      <!-- 투표제목 -->
      <h1>투표만들기</h1>
      <div>
        <h3></h3>
        <v-text-field
          background-color="white"
          label="투표제목"
          outlined
          maxlength="20"
          :rules="rules.voteTitleRule"
          v-model="voteInfo.voteTitle"
          placeholder="투표제목을 입력하세요."
        />
      </div>

      <!-- 투표내용 -->
      <h3>투표항목</h3>
      <div
        v-for="(voteItem, idx) in voteInfo.voteItems"
        :key="idx"
        class="d-flex justify-space-between align-center my-2"
      >
        <v-text-field
          background-color="white"
          v-model="voteItem.voteItemName"
          :rules="rules.voteTitleRule"
          hide-details
          outlined
          clearable
          rounded
          dense
        ></v-text-field>
        <v-btn @click.stop="voteDelItem(idx)" icon
          ><v-icon color="red" size="32">remove_circle</v-icon></v-btn
        >
      </div>
      <div>
        <v-btn
          @click.stop="voteAddItem()"
          height="40"
          outlined
          rounded
          color="grey"
          class="my-2"
          block
        >
          <v-icon size="32">add_circle_outline</v-icon>
        </v-btn>
      </div>
      <div>
        <v-btn
          @click.stop="voteCreate(voteInfo)"
          class="my-5"
          large
          rounded
          color="rgb(98,101,232)"
          block
          ><span class="mr-3" style="color: #fff; font-size: 1.3rem"
            >투표생성</span
          ><v-icon color="white">how_to_vote</v-icon></v-btn
        >
      </div>
    </div>
    <div>
      <v-btn
        @click.stop="voteCreate(voteInfo)"
        class="my-5"
        large
        rounded
        color="rgb(98,101,232)"
        block
        text
        ><span class="mr-3" style="font-size: 1rem">뒤로가기</span></v-btn
      >
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";
export default {
  name: "VoteCreate",
  data: () => ({
    rules: {
      voteItemRule: [
        (v) => !!v || " 투표항목을 입력해주세요.",
        (v) =>
          !(v && v.length >= 30) || "투표항목은 30자 이상 입력할 수 없습니다.",
      ],
      voteTitleRule: [
        (v) => !!v || " 투표제목을 입력해주세요.",
        (v) =>
          !(v && v.length >= 20) || "투표제목은 20자 이상 입력할 수 없습니다.",
      ],
    },
    voteInfo: {
      voteTitle: null,
      voteItems: [
        { voteItemName: "항목1", voteCnt: 0 },
        { voteItemName: "항목2", voteCnt: 0 },
        { voteItemName: "항목3", voteCnt: 0 },
      ],
    },
  }),
  methods: {
    ...mapActions(["voteCreate"]),
    voteAddItem() {
      this.voteInfo.voteItems.push({
        voteItemName: `항목${this.voteInfo.voteItems.length + 1}`,
        voteCnt: 0,
      });
    },
    voteDelItem(idx) {
      this.voteInfo.voteItems.splice(idx, 1);
    },
    voteCreate() {
      const data = {
        groupId: this.nowGroup.groupId,
        gameTitle: this.voteInfo.voteTitle,
        voteItemDtoList: this.voteInfo.voteItems,
      };
      const token = localStorage.getItem("jwt");
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/game/vote`,
        headers: { Authorization: token },
        data: data,
      })
        .then((res) => {
          this.$router.push({ name: "EntFeedList" });
          console.log(res);
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

<style scoped>
.content-box {
  border: solid 2px black;
  border-radius: 25px;
  background-color: rgb(245, 245, 245);
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.329);
}
</style>
