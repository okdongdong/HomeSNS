<template>
  <v-app>
    <!-- 투표제목 -->

    <hr />
    <div>
      <h3>투표제목:{{ voteInfo.voteTitle }}</h3>
    </div>

    <h3>투표항목</h3>
    <!-- 투표하기 -->
    <div v-if="nowVote">
      <div v-for="(voteItem, idx) in voteInfo.voteItems" :key="idx">
        <div>
          {{ voteItem.voteItemName }}
          {{ voteItem.voteCnt }}
          {{ idx }}
        </div>

        <v-btn v-if="voteInfo.myVoteItem===idx" @click.stop="myVote(idx)">투표취소하기</v-btn>
        <v-btn v-else @click.stop="myVote(idx)">여기 투표하기</v-btn>
      </div>
      <hr>
      <v-btn @click.stop="nowVote = !nowVote">투표완료</v-btn>
    </div>

    <!-- 투표내용보여주기 -->
    <div v-else>
      <div v-for="(voteItem, idx) in voteInfo.voteItems" :key="idx">
        <div>
          {{ voteItem.voteItemName }}
          {{ voteItem.voteCnt }}
          {{ idx }}
        </div>
      </div>
      <v-btn @click.stop="nowVote = !nowVote">투표하기</v-btn>
    </div>
  </v-app>
</template>

<script>
export default {
  name: "Vote",
  // props: {
  //   voteInfo: Object,
  //   // VoteInfo: {
  //   //   voteTitle : '12312312312'
  //   //   voteItems: [{
  //   //     voteItemName:'ㅁㅁㅁㄴㄴㅇㅇ"
  //   //     voteCnt:3
  //   //     }]
  //   //   myVoteItem :null
  //   // }
  // },
  data: () => ({
    nowVote: false,
    voteInfo: {
      voteTitle: "12312312312",
      voteItems: [
        {
          voteItemName: "ㅁㅁㅁㄴㄴㅇㅇ",
          voteCnt: 3,
        },
        {
          voteItemName: "xogus짱짱맨",
          voteCnt:0,
        },
        {
          voteItemName: "쭈꾸미 비빔밥",
          voteCnt: 6,
        },
      ],
      myVoteItem: null,
    },
  }),
  methods: {
    myVote(idx) {
      if (this.voteInfo.myVoteItem===null) {
        this.voteInfo.voteItems[idx].voteCnt += 1;
        this.voteInfo.myVoteItem = idx;
      } else if (this.voteInfo.myVoteItem === idx) {
        this.voteInfo.voteItems[this.voteInfo.myVoteItem].voteCnt -= 1;
        this.voteInfo.myVoteItem = null;
      } else {
        this.voteInfo.voteItems[this.voteInfo.myVoteItem].voteCnt -= 1;
        this.voteInfo.voteItems[idx].voteCnt += 1;
        this.voteInfo.myVoteItem = idx;
      }
    },
  },
};
</script>

<style></style>
