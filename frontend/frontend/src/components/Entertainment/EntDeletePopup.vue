<template>
  <v-dialog v-model="dialog" scrollable max-width="400px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-bind="attrs" v-on="on" icon>
        <v-icon size="32">delete_forever</v-icon>
      </v-btn>
    </template>
    <v-card class="rounded-xl pa-3">
      <div class="justify-center d-flex align-center">
        <v-card-title>정말 삭제하시겠습니까?</v-card-title>
      </div>
      <div style="height: 1.5rem"></div>
      <v-card-text class="d-flex">
        <v-btn
          class="mx-auto pa-3 transition-swing d-flex align-center"
          @click="$emit('deleteEntFeed'), (dialog = false)"
          color="red"
          width="100"
          dark
        >
          <h3>삭제</h3>
        </v-btn>
        <v-btn
          width="100"
          class="mx-auto pa-3 transition-swing d-flex align-center"
          @click="dialog = false"
        >
          <h3>취소</h3>
        </v-btn>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "EntCreatePopup",
  data: () => ({
    dialog: false,
  }),
};
</script>

<style></style>
