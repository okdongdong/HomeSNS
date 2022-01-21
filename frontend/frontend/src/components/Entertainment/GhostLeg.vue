<template>
  <v-app>
    <template>
      <v-stepper v-model="step">
        <v-stepper-header>
          <v-stepper-step :complete="step > 1" step="1"> </v-stepper-step>

          <v-divider></v-divider>

          <v-stepper-step :complete="step > 2" step="2"> </v-stepper-step>

          <v-divider></v-divider>
          <v-stepper-step :complete="step > 3" step="3"> </v-stepper-step>

          <v-divider></v-divider>

          <v-stepper-step step="4"> </v-stepper-step>
        </v-stepper-header>

        <v-stepper-items>
          <!-- 페이지 1 : 플레이어 수 입력 -->
          <v-stepper-content step="1">
            <h3>플레이어 수를 입력하세요</h3>
            <v-text-field solo v-model="playerNum" />

            <v-btn color="primary" @click="step = 2"> Continue </v-btn>

            <v-btn text> Cancel </v-btn>
          </v-stepper-content>

          <!-- 페이지 2 : 사다리 기초 생성 및 플레이어명, 결과값 입력 -->
          <v-stepper-content step="2">
            <h3>플레이어명과 결과값을 입력하세요</h3>
            <div class="container">
              <!-- 플레이어명 -->
              <div class="my-2 d-flex justify-content-between">
                <div v-for="i in calPlayerNum" :key="i">
                  <input
                    type="text"
                    class="my-input"
                    v-model="playerNames[i]"
                  />
                </div>
              </div>

              <!-- 사다리 -->
              <div class="my-2 d-flex justify-content-between">
                <div
                  :class="{
                    'ladder-base': i > 1,
                    'start-or-end-ladder-base': i === 1,
                  }"
                  v-for="i in calPlayerNum"
                  :key="i"
                >
                  <div v-for="j in 10" :key="j" class="box">
                    <!-- {{ i }} {{ j }} -->
                  </div>
                </div>
                <div class="start-or-end-ladder-base"></div>
              </div>

              <!-- 결과값 -->
              <div class="my-2 d-flex justify-content-between">
                <div v-for="i in calPlayerNum" :key="i">
                  <input
                    type="text"
                    class="my-input"
                    v-model="resultNames[i]"
                  />
                </div>
              </div>
            </div>

            <v-btn color="primary" @click="(step = 3), randomPick()">
              Continue
            </v-btn>

            <v-btn text @click="step = 1"> Cancel </v-btn>
          </v-stepper-content>

          <!-- 페이지 3 : 결과 출력 -->
          <v-stepper-content step="3">
            <h3>사다리 보기</h3>
            <div class="container">
              <!-- 플레이어명 -->
              <div class="my-2 d-flex justify-content-between">
                <div
                  v-for="i in calPlayerNum"
                  :key="i"
                  class="container justify-content-between"
                  style="padding:0"
                >
                  <div
                    v-if="playerNames[i] != null"
                    class="my-text text-center"
                    @click.stop="draw(i)"
                  >
                    {{ playerNames[i] }}
                  </div>
                </div>
              </div>

              <!-- 사다리 -->

              <canvas id="my-canvas"></canvas>
              <div class="my-2 d-flex justify-content-between">
                <div
                  :class="{
                    'ladder-base': i > 1,
                    'start-or-end-ladder-base': i === 1,
                  }"
                  v-for="i in calPlayerNum"
                  :key="i"
                >
                  <div
                    v-for="j in 10"
                    :key="j"
                    class="box"
                    :class="{ edge: isEdge[i][j] == 2 }"
                  ></div>
                </div>
                <div class="start-or-end-ladder-base"></div>
              </div>
              <!-- 사다리타기 진행용 캔버스 -->

              <!-- 결과값 -->
              <div class="my-2 d-flex justify-content-between">
                <div
                  v-for="i in calPlayerNum"
                  :key="i"
                  class="container justify-content-between"
                >
                  <div
                    v-if="resultNames[i] != null"
                    class="my-text text-center"
                  >
                    {{ resultNames[i] }}
                  </div>
                </div>
              </div>
            </div>
            <v-btn color="primary" @click="(step = 4), calResult()">
              Continue
            </v-btn>

            <v-btn text @click="step = 2"> Cancel </v-btn>
          </v-stepper-content>

          <!-- 모든결과보기 -->
          <v-stepper-content step="4">
            <div v-for="i in playerNum" :key="i">
              {{ playerNames[i] }} => {{ resultNames[result[i - 1]] }}
            </div>

            {{ result }}
            <v-btn text @click="step = 3"> Cancel </v-btn>
          </v-stepper-content>
        </v-stepper-items>
      </v-stepper>
    </template>
  </v-app>
</template>

<script>
export default {
  name: "GhostLeg",
  data: () => ({
    step: 1,
    playerNum: 5,
    edgeOn: false,
    result: [],
    isEdge: [
      [], // 0번 인덱스는 안씀
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], // 1번인덱스에는 값이 들어가면 안됨
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    ],
    playerNames: [
      "null0",
      "null1",
      "null2",
      "null3",
      "null4",
      "null5",
      "null6",
      "null7",
      "null8",
      "null9",
      null,
      null,
    ],
    resultNames: [
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
    ],
    nowColorIdx: 0,
    colors: [
      "#FF3333",
      "#FF9933",
      "#FFFF33",
      "#99FF33",
      "#006600",
      "#33FFFF",
      "#3333FF",
      "#9933FF",
      "#FF33FF",
      "#FF3399",
    ],
  }),
  methods: {
    randomPick() {
      this.isEdge = [
        // isEdge값 초기화
        [], // 0번 인덱스는 안씀
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], // 1번인덱스에는 값이 들어가면 안됨
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
      ];
      let temp;
      for (let i = 1; i < this.playerNum; i++) {
        temp = [];
        for (let j = 1; j < 10; j++) {
          if (this.isEdge[i][j] === 0) {
            temp.push(j);
          }
        }
        let edgeCnt = Math.floor(Math.random() * 3 + 2);
        for (let cnt = 0; cnt < edgeCnt; cnt++) {
          let j = temp.splice(Math.floor(Math.random() * temp.length), 1);
          this.isEdge[i][j] = 1;
          this.isEdge[i + 1][j] = 2;
        }
      }
    },
    calResult() {
      this.result = [];
      for (let i = 1; i <= this.playerNum; i++) {
        let myPos = i;
        for (let j = 0; j < 10; j++) {
          if (this.isEdge[myPos][j] === 1) {
            myPos += 1;
          } else if (this.isEdge[myPos][j] === 2) {
            myPos -= 1;
          }
        }
        this.result.push(myPos);
      }
    },
    draw(i) {
      const canvas = document.getElementById("my-canvas");
      if (this.nowColorIdx >= 10) {
        this.nowColorIdx = 0;
      }

      if (canvas.getContext) {
        const ctx = canvas.getContext("2d");
        let nowPosX = (300 * i - 150) / this.playerNum -1;
        let nowPosY = 0;
        let addPosX = 300 / this.playerNum;

        ctx.strokeStyle = this.colors[this.nowColorIdx++];
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.moveTo(nowPosX, nowPosY);
        let j = 0;
        while (j < 10) {
          if (this.isEdge[i][j] === 1) {
            nowPosX += addPosX;
            i++;
            ctx.lineTo(nowPosX, nowPosY);
          } else if (this.isEdge[i][j] === 2) {
            nowPosX -= addPosX;
            i--;
            ctx.lineTo(nowPosX, nowPosY);
          }
          j++;
          nowPosY += 12;
          ctx.lineTo(nowPosX, nowPosY);
        }
        ctx.stroke();
      }
    },
  },
  computed: {
    calPlayerNum() {
      return [1] * this.playerNum;
    },
  },
};
</script>

<style scoped>
#my-canvas {
  width: 84%;
  height: 300px;
  position: absolute;
}

.my-text {
  /* width: 100%; */
}
.my-input {
  width: 100%;
  border: 1px solid black;
}
.ladder-base {
  width: 100%;
}
.start-or-end-ladder-base {
  width: 50%;
}

.box {
  height: 1.5rem;
  border-right: 4px solid black;
}
.edge {
  border-bottom: 4px solid black;
}
</style>
