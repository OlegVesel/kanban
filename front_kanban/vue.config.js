const { defineConfig } = require('@vue/cli-service')
let baseUrl = {
  target: 'http://localhost:8080',
  changeOrigin: true
};
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  devServer:{
    port: 9000,
    proxy: {
      '/api/*': baseUrl
    }
  },
})
