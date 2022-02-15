module.exports = {
  transpileDependencies: ["vuetify"],
  pwa: {
    themeColor: "rgb(98,101,232)",
    msTileColor: "#FF0000",
  }
  // ,
  // outputDir: '../src/main/resources/static/'       
  //   // index.html 파일이 생성될 위치를 지정
  //   , indexPath: "../static/index.html"
  //   , publicPath: '/' 
  //   , assetsDir: 'assets'


  ,devServer: {
    // 5500 포트에서 vue 개발 서버를 실행
    port: 5500,
    // 네이버 맵 api 호출을 위한 proxy 설정
    proxy: "https://naveropenapi.apigw.ntruss.com",
    proxy: {
      // 프록시 요청을 보낼 api의 시작 부분
      "/api": {
        // 프록시 요청을 보낼 서버의 주소
        target: "https://localhost:8080",
      },
    },

    
  },
};
// 6265E8
