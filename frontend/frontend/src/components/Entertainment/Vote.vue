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

    <div class="justify-center d-flex">
      <v-btn v-if="nowVote" @click.stop="(nowVote = !nowVote), updateVote()"
        >투표완료</v-btn
      >
      <v-btn v-else @click.stop="nowVote = !nowVote">투표하기</v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Vote",
  data: () => ({
    nowVote: false,
    preVoteItemId: null,
  }),
  props: {
    info: Object,
  },
  methods: {
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
