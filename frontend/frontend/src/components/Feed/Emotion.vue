<template>
  <v-expand-x-transition >
    <v-sheet
      color="white"
      elevation="14"
      height="45"
      rounded="xl"
      v-show="showEmotions"
      class="emoji-position"
      >
      <v-chip-group
        v-model="selectEmotion"
        class="emoji-position"
        active-class="primary--text"
        column
      >
        <v-chip
          v-for="(emotion, idx) in emotions"
          :key="idx"
          :value="emotion.status"
          @click="$emit('emotion-show-toggle')"
        >
        <v-avatar center>
          <v-img :src="emotion.emoji"></v-img>
        </v-avatar>
        </v-chip>
      </v-chip-group>
      </v-sheet>
  </v-expand-x-transition>
</template>

<script>
export default {
  name: 'Emotion',
  props:{
    showEmotions:Boolean,
  },
  data() {
    return {
      toggle_exclusive: undefined,
      selectEmotion:null,
      emotions: [
        {emoji : require('@/assets/emotions/heart_on.png'), status:'good', code:30001},
        {emoji : require('@/assets/emotions/sad.png'), status:'sad', code:30002},
        {emoji : require('@/assets/emotions/check.png'), status:'check', code:30003},
        {emoji : require('@/assets/emotions/fun.png'), status:'fun', code:30004},
        {emoji : require('@/assets/emotions/amaze.png'), status:'amaze', code:30005}
      ],
    };
  },
  watch:{
    selectEmotion: function(val){
      this.$emit('select-emotion',val)
    }
  }
};
</script>

<style scoped>
  .emoji-position{
    margin : 0px 10px;
  }
</style>
