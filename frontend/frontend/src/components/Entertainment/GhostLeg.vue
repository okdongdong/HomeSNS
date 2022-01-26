<template>
  <v-stepper v-model="step" flat style="background-color: rgba(0, 0, 0, 0)">
    <v-stepper-items>
      <!-- 페이지 1 : 플레이어 수 입력 -->

      <!-- 페이지 3 : 결과 출력 -->
      <v-stepper-content step="1">
        <div class="container">
          <!-- 플레이어명 -->
          <div class="my-2 d-flex justify-content-between">
            <div v-for="i in calPlayerNum" :key="i">
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
              <canvas id="my-canvas"></canvas>
            </div>

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
                  :class="{ edge: info.isEdge[i][j] == 2 }"
                ></div>
              </div>
              <div class="start-or-end-ladder-base"></div>
            </div>
          </div>

          <!-- 결과값 -->
          <div class="my-2 d-flex justify-content-between">
            <div v-for="i in calPlayerNum" :key="i">
              <div v-if="info.resultNames[i] != null" class="text-center">
                {{ info.resultNames[i] }}
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
          if (this.info.isEdge[myPos][j] === 1) {
            myPos += 1;
          } else if (this.info.isEdge[myPos][j] === 2) {
            myPos -= 1;
          }
        }
        this.result.push(myPos);
      }
    },
    drawAll(i) {
      const canvas = document.getElementById("my-canvas");
      if (this.nowColorIdx >= 10) {
        this.nowColorIdx = 0;
      }

      const ctx = canvas.getContext("2d");
      this.nowPosX = (300 * i - 150) / this.info.playerNum - 1;
      this.nowPosY = 0;
      let addPosX = 300 / this.info.playerNum;

      ctx.strokeStyle = this.colors[this.nowColorIdx++];
      ctx.lineWidth = 4;
      ctx.beginPath();
      ctx.moveTo(this.nowPosX, this.nowPosY);
      let j = 0;
      while (j < 10) {
        if (this.info.isEdge[i][j] === 1) {
          this.nowPosX += addPosX;
          i++;
          ctx.lineTo(this.nowPosX, this.nowPosY);
        } else if (this.info.isEdge[i][j] === 2) {
          this.nowPosX -= addPosX;
          i--;
          ctx.lineTo(this.nowPosX, this.nowPosY);
        }
        j++;
        this.nowPosY += 12;
        ctx.lineTo(this.nowPosX, this.nowPosY);
      }
      ctx.stroke();
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
        this.nowPosX = (300 * i - 150) / this.info.playerNum - 2;
        this.nowPosY = 0;
      }
      var addPosX = 300 / this.info.playerNum;
      var canvas = document.getElementById("my-canvas");
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

      if (dx === addPosX) {
        if (this.info.isEdge[i][j] === 1) {
          i++;
        } else if (this.info.isEdge[i][j] === 2) {
          i--;
        }
      }
      if (dy === 12) {
        j++;
        dx = 0;
        dy = 0;
      }

      if (this.info.isEdge[i][j] === 1 && dx < addPosX) {
        this.nowPosX++;
        dx++;
      } else if (this.info.isEdge[i][j] === 2 && dx < addPosX) {
        this.nowPosX--;
        dx++;
      } else {
        this.nowPosY++;
        dy++;
      }
      // 재귀로 동작하는 부분
      ctx.beginPath();
      ctx.arc(this.nowPosX, this.nowPosY, 2, 0, Math.PI * 2);
      ctx.fill();
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
#my-canvas {
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
