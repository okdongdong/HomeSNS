<template>
  <div class="container">
    <div v-for="content in contentData" :key="content">
      <EntFeedContainer :content="content" />
    </div>
  </div>
</template>

<script>
import EntFeedContainer from "../../components/Entertainment/EntFeedContainer.vue";

export default {
  name: "EntFeedList",
  data: () => ({
    contentData: [
      {
        type: "ghost",
        info: {
          title: "이것은 제목입니다",
          author: "작성자",
          authorPicUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiM_3DwEqCAeTk61eqxYHzcNibjWl3_2fPfQ&usqp=CAU",
          createdAt: "2020.02.02",
        },
      },
      {
        type: "vote",
        info: {
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
  },
  methods: {},
};
</script>

<style></style>
