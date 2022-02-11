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
        class="emoji-position"
        active-class="primary--text"
        column
      >
        <v-chip
          v-for="(emotion, idx) in emotions"
          :key="idx"
        >
        {{ emotion.emoji }}
        </v-chip>
      </v-chip-group>
      <!-- <v-btn-toggle class="emoji-position" rounded v-model="toggle_exclusive" multiple >
          <v-btn icon>
            <v-icon>😊</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>😢</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>✅</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>🤣</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>👍</v-icon>
          </v-btn>
        </v-btn-toggle> -->
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
      emotions: [
        {emoji : '😊', status:'good'},
        {emoji : '😢', status:'sad'},
        {emoji : '✅', status:'check'},
        {emoji : '🤣', status:'fun'},
        {emoji : '👍', status:'amaze'}
      ],
    };
  },
};
</script>

<style scoped>
  .emoji-position{
    margin : 0px 10px;
  }
</style>
