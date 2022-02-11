module.exports = {
  transpileDependencies: ["vuetify"],
  pwa: {
    themeColor: "rgb(98,101,232)",
    msTileColor: "#FF0000",
  },
  devServer: {
    // 5500 포트에서 vue 개발 서버를 실행
    port: 5500,
    // 네이버 맵 api 호출을 위한 proxy 설정
    proxy:"https://naveropenapi.apigw.ntruss.com",
  },
};
// 6265E8
