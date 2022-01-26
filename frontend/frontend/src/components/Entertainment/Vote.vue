<template>
  <div>
    <h3>투표항목</h3>

    <!-- 투표내용보여주기 -->
    <!-- 투표하기 -->
    <div v-for="(voteItem, idx) in info.voteItems" :key="idx">
      <div>
        <!-- 투표비율표시 -->
        <div class="container d-flex">
          <v-progress-linear
            class="vote-bar"
            height="30"
            :color="info.myVoteItem === idx ? 'rgb(98,101,232)' : '#ABABAB'"
            :value="votePercent(voteItem.voteCnt)"
            @click.stop="info.nowVote ? myVote(idx) : pass"
            ><div class="d-flex justify-space-between container">
              <div>
                {{ voteItem.voteItemName }}
              </div>

              <div class="d-flex align-space-center">
                <div class="material-icons">person</div>
                <div class="ml-1">
                  {{ voteItem.voteCnt }}
                </div>
              </div>
            </div>
          </v-progress-linear>
          <v-expand-x-transition>
            <v-card
              v-if="info.nowVote"
              class="align-center d-flex ml-3"
              flat
              color="#00000000"
            >
              <v-radio
                :value="idx"
                @click.stop="myVote(idx)"
                :checked="info.myVoteItem === idx"
              ></v-radio>
            </v-card>
          </v-expand-x-transition>
        </div>
      </div>
    </div>

    <div class="justify-center d-flex">
      <v-btn
        v-if="info.nowVote"
        @click.stop="(info.nowVote = !info.nowVote), updateVote()"
        >투표완료</v-btn
      >
      <v-btn v-else @click.stop="info.nowVote = !info.nowVote">투표하기</v-btn>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Vote",
  props: {
    info: Object,
  },
  methods: {
    updateVote() {
      const token = localStorage.getItem("jwt");
      const data = {
        voteId: this.info.voteId,
        myVoteItem: this.info.myVoteItem,
      };
      axios({
        method: "put",
        url: `${process.env.VUE_APP_MCS_URL}/ghostleg`,
        headers: { Authorization: `JWT ${token}` },
        data: data,
      });
    },
    myVote(idx) {
      if (this.info.myVoteItem === null) {
        this.info.voteItems[idx].voteCnt += 1;
        this.info.myVoteItem = idx;
      } else if (this.info.myVoteItem === idx) {
        this.info.voteItems[this.info.myVoteItem].voteCnt -= 1;
        this.info.myVoteItem = null;
      } else {
        this.info.voteItems[this.info.myVoteItem].voteCnt -= 1;
        this.info.voteItems[idx].voteCnt += 1;
        this.info.myVoteItem = idx;
      }
    },
    votePercent(voteCnt) {
      let voteSum = 0;
      console.log(voteSum);
      for (let i = 0; i < this.info.voteItems.length; i++) {
        voteSum += this.info.voteItems[i].voteCnt;
      }
      return (100 * voteCnt) / (voteSum + 0.00001); // zero division 방지
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
