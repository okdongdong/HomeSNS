<template>
  <v-dialog v-model="dialog" scrollable max-width="400px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" icon>
        <v-icon>add_circle_outline</v-icon>
      </v-btn>
    </template>
    <v-card class="rounded-xl">
      <div class="justify-space-between d-flex align-center">
        <v-card-title>새 글 생성</v-card-title>
        <v-btn color="" text @click="dialog = false">
          <v-icon>close</v-icon>
        </v-btn>
      </div>
      <v-divider></v-divider>
      <v-card-text>
        <v-container>
          <v-hover>
            <template v-slot:default="{ hover }">
              <v-card
                ripple
                max-width="380px"
                class="mx-auto pa-3 transition-swing rounded-xl d-flex align-center"
                :class="`elevation-${hover ? 20 : 6}`"
                @click="$router.push({ name: 'VoteCreate' })"
              >
                <v-icon large class="mx-3">how_to_vote</v-icon>
                <h2>투표</h2>
              </v-card>
            </template>
          </v-hover>
        </v-container>
        <v-container>
          <v-hover>
            <template v-slot:default="{ hover }">
              <v-card
                ripple
                max-width="380px"
                class="mx-auto pa-3 transition-swing rounded-xl d-flex align-center"
                :class="`elevation-${hover ? 20 : 5}`"
                @click="$router.push({ name: 'GhostLegCreate' })"
              >
                <v-icon large class="mx-3">sports_esports</v-icon>
                <h2>사다리타기</h2>
              </v-card>
            </template>
          </v-hover>
        </v-container>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "EntCreatePopup",
  props: {
    dialog: Boolean,
  },
};
</script>

<style></style>
