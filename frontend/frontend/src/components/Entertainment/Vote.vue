<template>
  <div>
    <!-- 투표내용보여주기 -->
    <!-- 투표하기 -->
    <div v-for="(voteItem, idx) in info.voteItems" :key="idx">
      <div>
        <!-- 투표비율표시 -->
        <div class="container d-flex">
          <v-progress-linear
            class="vote-bar"
            height="30"
            :color="myVoteIdx === idx ? 'rgb(98,101,232)' : '#ABABAB'"
            :value="votePercent(voteItem.count)"
            @click.stop="nowVote ? myVote(idx) : ''"
            ><div class="d-flex justify-space-between container">
              <div>
                {{ voteItem.voteItemName }}
              </div>

              <div class="d-flex align-space-center">
                <div class="material-icons">person</div>
                <div class="ml-1">
                  {{ voteItem.count }}
                </div>
              </div>
            </div>
          </v-progress-linear>
          <v-expand-x-transition>
            <v-card
              v-if="nowVote"
              class="align-center d-flex ml-3"
              flat
              style="background-color: rgba(0, 0, 0, 0)"
            >
              <v-icon
                @click.stop="myVote(idx)"
                :color="myVoteIdx === idx ? 'rgb(98,101,232)' : '#ABABAB'"
                >{{
                  myVoteIdx === idx
                    ? "radio_button_checked"
                    : "radio_button_unchecked"
                }}</v-icon
              >
            </v-card>
          </v-expand-x-transition>
        </div>
      </div>
    </div>
    <v-row>
      <v-col v-if="info.updateYn == 'y'" class="justify-center d-flex">
        <v-btn v-if="!nowVote" @click.stop="nowVote = !nowVote">투표하기</v-btn>
        <v-btn v-else @click.stop="(nowVote = !nowVote), updateVote()"
          >투표완료</v-btn
        >
      </v-col>
      <v-col v-else class="text-center">
        <h4 class="py-3">마감된 투표입니다.</h4>
      </v-col>
      <v-col
        class="justify-center d-flex"
        v-if="!nowVote && info.updateYn == 'y' && userSeq == info.authorId"
      >
        <v-dialog v-model="dialog" scrollable max-width="400px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn v-bind="attrs" v-on="on" @click.stop="dialog = true"
              >투표종료</v-btn
            >
          </template>
          <v-card class="rounded-xl pa-3">
            <div class="justify-center d-flex align-center">
              <v-card-title>정말 종료하시겠습니까?</v-card-title>
            </div>
            <div style="height: 1.5rem"></div>
            <v-card-text class="d-flex">
              <v-btn
                class="mx-auto pa-3 transition-swing d-flex align-center"
                @click="endVote(info.contentId), (dialog = false)"
                color="red"
                width="100"
                dark
              >
                <h3>종료</h3>
              </v-btn>
              <v-btn
                width="100"
                class="mx-auto pa-3 transition-swing d-flex align-center"
                @click="dialog = false"
              >
                <h3>취소</h3>
              </v-btn>
            </v-card-text>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
export default {
  name: "Vote",
  data: () => ({
    dialog: false,
    nowVote: false,
    preVoteItemId: null,
  }),
  props: {
    info: Object,
  },
  methods: {
    ...mapActions("notice", ["send"]),
    endVote(gameId) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/game/vote/quit`,
        headers: { Authorization: token },
        data: { gameId: gameId },
      })
        .then((res) => {
          console.log(res);
          const noticeInfo = {
            targetUserSeq: -1,
            noticeType: "voteEnd",
            noticeContentId: this.info.contentId,
          };
          this.send(noticeInfo);
          this.info.updateYn = "n";
        })
        .catch((err) => {
          console.log(err);
        });
    },
    voteVote(voteItemId) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "post",
        url: `${process.env.VUE_APP_MCS_URL}/game/vote/voter`,
        headers: { Authorization: token },
        data: { voteItemId: voteItemId },
      })
        .then((res) => {
          console.log(res);

          this.preVoteItemId = null;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    voteCancel(voteItemId) {
      const token = localStorage.getItem("jwt");
      axios({
        method: "delete",
        url: `${process.env.VUE_APP_MCS_URL}/game/vote/voter`,
        headers: { Authorization: token },
        data: { voteItemId: voteItemId },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateVote() {
      if (this.preVoteItemId) {
        this.voteCancel(this.preVoteItemId);
      }
      if (this.info.myVoteItem) {
        this.voteVote(this.info.myVoteItem);
      }
    },
    myVote(idx) {
      if (this.myVoteIdx === null) {
        this.info.voteItems[idx].count += 1;
        this.preVoteItemId = null;
        this.info.myVoteItem = this.info.voteItems[idx].voteItemId;
      } else if (this.myVoteIdx === idx) {
        this.info.voteItems[this.myVoteIdx].count -= 1;
        this.preVoteItemId = this.info.myVoteItem;
        this.info.myVoteItem = null;
      } else {
        this.info.voteItems[this.myVoteIdx].count -= 1;
        this.info.voteItems[idx].count += 1;
        this.preVoteItemId = this.info.myVoteItem;
        this.info.myVoteItem = this.info.voteItems[idx].voteItemId;
      }
    },
    votePercent(count) {
      let voteSum = 0;
      console.log(voteSum);
      for (let i = 0; i < this.info.voteItems.length; i++) {
        voteSum += this.info.voteItems[i].count;
      }
      return (100 * count) / (voteSum + 0.00001); // zero division 방지
    },
  },
  computed: {
    ...mapState("account", ["userSeq"]),
    myVoteIdx() {
      if (!this.info.myVoteItem) {
        return null;
      }

      for (let i = 0; i < this.info.voteItems.length; i++) {
        if (this.info.voteItems[i].voteItemId == this.info.myVoteItem) {
          return i;
        }
      }
      return null;
    },
  },
};
</script>

<style scoped>
.vote-bar {
  border-radius: 20px;
  transition-duration: 500ms;
}
</style>
