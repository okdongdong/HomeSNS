<template>
  <div id="app">
    알림보낼사람:
    <v-text-field v-model="noticeInfo.targetUserSeq"></v-text-field>
    알림타입:
    <v-text-field v-model="noticeInfo.noticeType" type="text"></v-text-field>
    알림내용id:
    <v-text-field
      v-model="noticeInfo.noticeContentId"
      type="text"
      @keyup="send(noticeInfo)"
    ></v-text-field>
    <div v-for="(item, idx) in recvList" :key="idx">
      <h3>타겟유저이름: {{ item.targetUserSeq }}</h3>
      <h3>알람타입: {{ item.noticeType }}</h3>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "App",
  data: () => ({
    noticeInfo: { targetUserSeq: -1, noticeType: "test", noticeContentId: 1 },
    targetUserSeq: null,
    noticeType: null,
    noticeContentId: null,
    recvList: [],
  }),
  methods: {
    ...mapActions("notice", ["send"]),
  },
  computed: {
    ...mapState("account", ["nowGroup", "userSeq"]),
  },
};
</script>
