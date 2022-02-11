<template>
  <v-stepper v-model="step" flat>
    <v-stepper-items>
      <!-- 결과 출력 -->
      <v-stepper-content step="1" class="pa-0">
        <div
          class="d-flex justify-center mb-5"
          style="
            width: 100%;
            box-shadow: inset 0 0 7px #888;
            background-color: white;
          "
        >
          <div class="overflow-x-auto py-2">
            <div :style="'width:calc(90px * ' + info.playerNum + ');'">
              <!-- 플레이어명 -->
              <div class="my-2 d-flex justify-content-between">
                <div
                  v-for="i in calPlayerNum"
                  :key="i"
                  class="container justify-content-between"
                >
                  <div
                    v-if="info.playerNames[i] != null"
                    class="text-center"
                    @click.stop="
                      drawActive
                        ? (draw(i), (nowActiveIdx = i))
                        : drawAll(nowActiveIdx)
                    "
                  >
                    {{ info.playerNames[i] }}
                  </div>
                </div>
              </div>

              <!-- 사다리 -->
              <div style="position: relative">
                <div
                  class="container canvas-container"
                  style="padding: 0; position: absolute"
                >
                  <!-- 사다리타기 진행용 캔버스 -->
                  <!-- 플레이어 수에 맞춰서 캔버스 크기 조정 -->
                  <canvas
                    :id="'my-canvas-' + info.contentId"
                    class="my-canvas"
                    :width="300 * info.playerNum + 'px'"
                    :height="150 * info.playerNum + 'px'"
                  ></canvas>
                </div>

                <div class="my-2 d-flex justify-content-between">
                  <div
                    :class="{
                      'ladder-base': i > 1,
                      'start-or-end-ladder-base': i == 1,
                    }"
                    v-for="i in calPlayerNum"
                    :key="i"
                  >
                    <div
                      v-for="j in 10"
                      :key="j"
                      class="box"
                      :class="{ edge: info.isEdge[i][j] == 2 }"
                    ></div>
                  </div>
                  <div class="start-or-end-ladder-base"></div>
                </div>
              </div>

              <!-- 결과값 -->
              <div class="my-2 d-flex justify-content-between">
                <div
                  v-for="i in calPlayerNum"
                  :key="i"
                  class="container justify-content-between"
                >
                  <div v-if="info.resultNames[i] != null" class="text-center">
                    {{ info.resultNames[i] }}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="justify-end d-flex">
          <div
            @click.stop="(step = 2), calResult()"
            class="align-center d-flex"
          >
            <div>모든결과보기</div>
            <v-btn icon color="primary">
              <v-icon> chevron_right </v-icon>
            </v-btn>
          </div>
        </div>
      </v-stepper-content>

      <!-- 모든결과보기 -->
      <v-stepper-content step="2">
        <div v-for="i in info.playerNum" :key="i">
          {{ info.playerNames[i] }} => {{ info.resultNames[result[i - 1]] }}
        </div>

        <div class="justify-start d-flex">
          <div @click.stop="step = 1" class="align-center d-flex">
            <v-btn icon text color="primary">
              <v-icon> chevron_left </v-icon>
            </v-btn>
            <div>사다리보기</div>
          </div>
        </div>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "GhostLeg",
  data: () => ({
    step: 1,
    edgeOn: false,
    result: [],

    nowColorIdx: 0,
    nowPosX: null,
    nowPosY: null,
    drawActive: true,
    drawDone: false,
    nowActiveIdx: null,
  }),
  props: {
    info: Object,
  },
  methods: {
    calResult() {
      this.result = [];
      for (let i = 1; i <= this.info.playerNum; i++) {
        let myPos = i;
        for (let j = 0; j < 10; j++) {
          if (this.info.isEdge[myPos][j] == 1) {
            myPos += 1;
          } else if (this.info.isEdge[myPos][j] == 2) {
            myPos -= 1;
          }
        }
        this.result.push(myPos);
      }
    },
    drawAll(i) {
      const canvas = document.getElementById(
        `my-canvas-${this.info.contentId}`
      );
      if (this.nowColorIdx >= 10) {
        this.nowColorIdx = 0;
      }

      const ctx = canvas.getContext("2d");
      this.nowPosX = 300 * i - 150 - 20 / 3;
      this.nowPosY = -this.info.playerNum;
      let addPosX = 300;

      ctx.strokeStyle = this.colors[this.nowColorIdx++];
      ctx.lineWidth = 40 / 3; // 라인의 굵기

      let j = 0;
      while (j < 10) {
        if (this.info.isEdge[i][j] == 1) {
          ctx.beginPath();
          ctx.moveTo(this.nowPosX, this.nowPosY);
          this.nowPosX += addPosX;
          i++;
          ctx.lineWidth = 2 * this.info.playerNum; // 라인의 굵기
          ctx.lineTo(this.nowPosX, this.nowPosY);
          ctx.stroke();
        } else if (this.info.isEdge[i][j] == 2) {
          ctx.beginPath();
          ctx.moveTo(this.nowPosX, this.nowPosY);
          this.nowPosX -= addPosX;
          i--;
          ctx.lineWidth = 2 * this.info.playerNum; // 라인의 굵기
          ctx.lineTo(this.nowPosX, this.nowPosY);
          ctx.stroke();
        }
        j++;
        ctx.beginPath();
        ctx.moveTo(this.nowPosX, this.nowPosY - this.info.playerNum);
        this.nowPosY += 12 * this.info.playerNum;
        ctx.lineWidth = 15;
        ctx.lineTo(this.nowPosX, this.nowPosY + this.info.playerNum);
        ctx.stroke();
      }
      this.drawDone = true;
      this.nowPosX = null;
    },

    draw(i, j = 0, dx = 0, dy = 0) {
      if (this.drawDone) {
        this.drawActive = true;
        this.drawDone = false;
        return;
      }

      if (this.nowPosX === null) {
        // 최초로 변수 정의하는 부분
        this.drawActive = false;
        this.nowPosX = 300 * i - 150;
        this.nowPosY = 0;
      }
      var addPosX = 300;
      var canvas = document.getElementById(`my-canvas-${this.info.contentId}`);
      var ctx = canvas.getContext("2d");
      ctx.moveTo(this.nowPosX, this.nowPosY);
      ctx.fillStyle = this.colors[this.nowColorIdx];
      ctx.lineWidth = 3;

      if (j >= 10) {
        this.nowColorIdx++;
        this.nowPosX = null;
        this.drawActive = true;
        return;
      }

      if (this.nowColorIdx >= 10) {
        this.nowColorIdx = 0;
      }
      if (dx / addPosX >= 1) {
        if (this.info.isEdge[i][j] == 1) {
          i++;
        } else if (this.info.isEdge[i][j] == 2) {
          i--;
        }
      }
      if (dy === 12) {
        j++;
        dx = 0;
        dy = 0;
      }

      if (this.info.isEdge[i][j] == 1 && dx < addPosX) {
        dx += this.info.playerNum;
        this.nowPosX +=
          dx > addPosX
            ? this.info.playerNum - dx + addPosX
            : this.info.playerNum;
      } else if (this.info.isEdge[i][j] == 2 && dx < addPosX) {
        dx += this.info.playerNum;
        this.nowPosX -=
          dx > addPosX
            ? this.info.playerNum - dx + addPosX
            : this.info.playerNum;
      } else {
        this.nowPosY += this.info.playerNum;
        dy += 1;
      }
      // 재귀로 동작하는 부분
      ctx.beginPath();
      ctx.fillRect(
        this.nowPosX - 40 / 3,
        this.nowPosY - 2 * this.info.playerNum,
        40 / 3, //가로
        2 * this.info.playerNum //세로
      );
      ctx.closePath();
      setTimeout(() => {
        return this.draw(i, j, dx, dy);
      }, 10);
    },
  },
  computed: {
    ...mapState("minigame", ["colors"]),
    calPlayerNum() {
      return [1] * this.info.playerNum;
    },
  },
};
</script>

<style scoped>
.canvas-container {
  width: 100%;
  position: absolute;
}
.my-canvas {
  position: relative;
  width: inherit;
  height: 300px;
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
