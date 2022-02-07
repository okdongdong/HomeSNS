<template>
  <div>
    <v-stepper v-model="step">
      <v-stepper-items>
        <!-- 페이지 1 : 플레이어 수 입력 -->
        <v-stepper-content step="1">
          <h3>플레이어 수를 입력하세요</h3>
          <v-text-field solo v-model="playerNum" />
          <div class="justify-end d-flex">
            <v-btn icon color="primary" @click.stop="step = 2">
              <v-icon> chevron_right </v-icon>
            </v-btn>
          </div>
        </v-stepper-content>

        <!-- 페이지 2 : 사다리 기초 생성 및 플레이어명, 결과값 입력 -->
        <v-stepper-content step="2">
          <h3>플레이어명과 결과값을 입력하세요</h3>
          <div class="container">
            <!-- 플레이어명 -->
            <div class="my-2 d-flex justify-content-between">
              <div v-for="i in calPlayerNum" :key="i">
                <input type="text" class="my-input" v-model="playerNames[i]" />
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
                <input type="text" class="my-input" v-model="resultNames[i]" />
              </div>
            </div>
          </div>
          <div class="justify-space-between d-flex">
            <v-btn icon text @click.stop="step = 1">
              <v-icon> chevron_left </v-icon>
            </v-btn>

            <v-btn icon color="primary" @click.stop="(step = 3), randomPick()">
              <v-icon> chevron_right </v-icon>
            </v-btn>
          </div>
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
              >
                <div
                  v-if="playerNames[i] != null"
                  class="text-center"
                  @click.stop.stop="
                    drawActive
                      ? (draw(i), (nowActiveIdx = i))
                      : drawAll(nowActiveIdx)
                  "
                >
                  {{ playerNames[i] }}
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
                    :class="{ edge: isEdge[i][j] == 2 }"
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
                <div v-if="resultNames[i] != null" class="text-center">
                  {{ resultNames[i] }}
                </div>
              </div>
            </div>
          </div>
          <div class="justify-space-between d-flex">
            <v-btn icon text @click.stop="step = 2">
              <v-icon> chevron_left </v-icon>
            </v-btn>
            <v-btn icon color="primary" @click.stop="(step = 4), calResult()">
              <v-icon> chevron_right </v-icon>
            </v-btn>
          </div>
        </v-stepper-content>

        <!-- 모든결과보기 -->
        <v-stepper-content step="4">
          <div v-for="i in playerNum" :key="i">
            {{ playerNames[i] }} => {{ resultNames[result[i - 1]] }}
          </div>

          <div class="justify-space-between d-flex">
            <v-btn icon text @click.stop="step = 3">
              <v-icon> chevron_left </v-icon>
            </v-btn>
            <v-btn icon text @click.stop="ghostLegCreate()">
              <v-icon> send </v-icon>
            </v-btn>
          </div>
        </v-stepper-content>
      </v-stepper-items>
    </v-stepper>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

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
      0,
      "강동옥",
      "박상준",
      "김태현",
      "최이삭",
      "임창현",
      "강동옥2",
      "박상준2",
      "김태현2",
      "최이삭2",
      "임창현2",
    ],
    resultNames: [
      0,
      "결과1",
      "결과2",
      "결과3",
      "결과4",
      "결과5",
      "결과6",
      "결과7",
      "결과8",
      "결과9",
      "결과10",
    ],
    nowColorIdx: 0,
    nowPosX: null,
    nowPosY: null,
    drawActive: true,
    drawDone: false,
    nowActiveIdx: null,
  }),
  methods: {
    ghostLegCreate() {
      // 임시용 경로변경
      this.$router.push({ name: "EntFeedList" });

      const data = {
        playerNum: 0,
        isEdge: [],
        playerNames: [],
        resultNames: [],
      };
      data.playerNum = this.playerNum;
      this.isEdge.forEach((arr) => {
        data.isEdge.push(arr.join());
      });
      data.isEdge = data.isEdge.join("+");
      data.playerNames = this.playerNames.join();

      console.log(data)

      const token = localStorage.getItem("jwt");
      axios({
        method: "POST",
        url: `${process.env.VUE_APP_MCS_URL}/ghostleg`,
        headers: { Authorization: token },
        data: data,
      })
        .then((res) => {
          this.$router.push({ name: "EntFeedList" });
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    randomPick() {
      this.isEdge = [
        // isEdge값 초기화
        [], // 0번 인덱스는 안씀
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
    drawAll(i) {
      const canvas = document.getElementById("my-canvas");
      if (this.nowColorIdx >= 10) {
        this.nowColorIdx = 0;
      }

      const ctx = canvas.getContext("2d");
      this.nowPosX = (300 * i - 150) / this.playerNum - 1;
      this.nowPosY = 0;
      let addPosX = 300 / this.playerNum;

      ctx.strokeStyle = this.colors[this.nowColorIdx++];
      ctx.lineWidth = 4;
      ctx.beginPath();
      ctx.moveTo(this.nowPosX, this.nowPosY);
      let j = 0;
      while (j < 10) {
        if (this.isEdge[i][j] === 1) {
          this.nowPosX += addPosX;
          i++;
          ctx.lineTo(this.nowPosX, this.nowPosY);
        } else if (this.isEdge[i][j] === 2) {
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
        this.nowPosX = (300 * i - 150) / this.playerNum - 2;
        this.nowPosY = 0;
      }
      var addPosX = 300 / this.playerNum;
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
        if (this.isEdge[i][j] === 1) {
          i++;
        } else if (this.isEdge[i][j] === 2) {
          i--;
        }
      }
      if (dy === 12) {
        j++;
        dx = 0;
        dy = 0;
      }

      if (this.isEdge[i][j] === 1 && dx < addPosX) {
        this.nowPosX++;
        dx++;
      } else if (this.isEdge[i][j] === 2 && dx < addPosX) {
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
      return [1] * this.playerNum;
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
