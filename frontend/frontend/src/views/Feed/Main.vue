<template>
  <v-app>
    <v-card>
      <v-card-title>2021.12.21</v-card-title>
      <v-card-subtitle>내용 미쳤어</v-card-subtitle>
      <v-container>
        <v-row>
          <v-col
            v-for="n in 9"
            :key="n"
            class="d-flex child-flex"
            cols="4"
          >
            <v-img
              :src="`https://picsum.photos/200/300`"
              :lazy-src="`https://picsum.photos/200/300`"
              aspect-ratio="1"
              class="grey lighten-2"
            >
              <template v-slot:placeholder>
                <v-row
                  class="fill-height ma-0"
                  align="center"
                  justify="center"
                >
                  <v-progress-circular
                    indeterminate
                    color="grey lighten-5"
                  ></v-progress-circular>
                </v-row>
              </template>
            </v-img>
          </v-col>
        </v-row>
      </v-container>
      <v-divider></v-divider>
    </v-card>
  </v-app>
</template>

<script>
  export default {
    name: "Main",
      data: () => ({
        
    }),
  };
</script>

<style scoped>

</style>