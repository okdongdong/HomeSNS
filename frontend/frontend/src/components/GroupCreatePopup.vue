<template>
  <v-dialog v-model="dialog" scrollable max-width="400px">
    <template v-slot:activator="{ on, attrs }">
      <v-img
        v-bind="attrs"
        v-on="on"
        icon
        aspect-ratio="1"
        src="@/assets/group_add.png"
        alt="그룹 사진입니다."
        class="group-img"
      />
    </template>
    <v-card class="rounded-xl">
      <div class="justify-end d-flex align-center">
        <v-btn text @click="dialog = false">
          <v-icon>close</v-icon>
        </v-btn>
      </div>
      <v-card-text>
        <v-container>
          <v-hover>
            <template v-slot:default="{ hover }">
              <v-card
                ripple
                max-width="380px"
                class="mx-auto pa-3 transition-swing rounded-xl d-flex align-center"
                :class="`elevation-${hover ? 20 : 6}`"
                @click="$router.push({ name: 'GroupCreate' })"
              >
                <v-icon large class="mx-3">create</v-icon>
                <h2>새 그룹생성</h2>
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
                @click="$router.push({ name: 'GroupEnter' })"
              >
                <v-icon large class="mx-3">login</v-icon>
                <h2>다른 그룹입장</h2>
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
  name: "GroupCreatePopup",
  data: () => ({
    dialog: false,
  }),
};
</script>

<style scoped>
.group-img {
  object-fit: cover;
  border: 4px solid white;
  border-radius: 20%;
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.329);
}
</style>
