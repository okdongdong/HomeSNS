<template>
  <v-app>
    <!-- 투표제목 -->
    <h1>투표만들기</h1>
    <hr />
    <div>
      <h3>투표제목</h3>
      <v-text-field
        solo
        maxlength="20"
        :rules="rules.voteTitleRule"
        v-model="voteInfo.voteTitle"
        placeholder="투표제목을 입력하세요."
      />
    </div>

    <!-- 투표내용 -->
    <h3>투표항목</h3>
    <div v-for="(voteItem, idx) in voteInfo.voteItems" :key="idx">
      <div>
        {{ voteItem.voteItemName }}
        {{ voteItem.voteCnt }}
        {{ idx }}
      </div>
      <v-btn @click.stop="voteDelItem(idx)">-</v-btn>
    </div>
    <div>
      <v-text-field
        solo
        maxlength="30"
        :rules="rules.voteItemRule"
        v-model="tempVoteItem"
        placeholder="항목추가하기"
      />
      <v-btn @click.stop="voteAddItem()">+</v-btn>
    </div>
    <v-btn @click.stop="voteCreate(voteInfo)">투표생성</v-btn>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
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
    tempVoteItem: null,
    voteInfo: {
      voteTitle: null,
      voteItems: [
        { voteItemName: "aa", voteCnt: 1 },
        { voteItemName: "abb", voteCnt: 3 },
      ],
    },
  }),
  methods: {
    ...mapActions(["voteCreate"]),
    voteAddItem() {
      if (this.tempVoteItem) {
        this.voteInfo.voteItems.push({
          voteItemName: this.tempVoteItem,
          voteCnt: 0,
        });
        this.tempVoteItem = null;
      }
    },
    voteDelItem(idx) {
      this.voteInfo.voteItems.splice(idx, 1);
    },
    voteCreate() {
      // 임시용 경로변경
      this.$router.push({ name: "EntFeedList" });

      const data = this.voteInfo;
      const token = localStorage.getItem("jwt");
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/vote`,
        headers: { Authorization: `JWT ${token}` },
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
};
</script>

<style></style>
