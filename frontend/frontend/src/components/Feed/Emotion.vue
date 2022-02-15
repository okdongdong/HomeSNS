<template>
  <v-expand-x-transition >
    <v-sheet
      color="white"
      elevation="14"
      height="50"
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
        {emoji : require('@/assets/emotions/heart_on.png'), status:'good'},
        {emoji : require('@/assets/emotions/sad.png'), status:'sad'},
        {emoji : require('@/assets/emotions/check.png'), status:'check'},
        {emoji : require('@/assets/emotions/fun.png'), status:'fun'},
        {emoji : require('@/assets/emotions/amaze.png'), status:'amaze'}
      ],
    };
  },
  watch:{
    selectEmotion: function(val){
      // console.log(val);
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
