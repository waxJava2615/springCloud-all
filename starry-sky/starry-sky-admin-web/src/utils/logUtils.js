import Vue from 'vue';

var logUtils = new Vue({
  methods: {
    printLog:function(...msg) {
      console.log(...msg);
    }
  }
})
export default logUtils;
