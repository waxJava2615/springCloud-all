import axios from 'axios'    //引入axios
import qs from 'qs'    //引入qs，用来序列化post类型的数据，否则后端无法接收到数据
import store from '../stores'
import logUtils from '../utils/logUtils'

// 设置post请求头
axios.defaults.headers.accept = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.withCredentials = false;//在跨域请求时，不会携带用户凭证；返回的 response 里也会忽略 cookie

//创建axios实例，请求超时时间为300秒，因为项目中有多个域名，所以对应的也要创建多个axios实例
const instanceA = axios.create({
  timeout: 300000,
});
const instanceB = axios.create({
  timeout: 300000,
});

// logUtils.printLog("request get process.env.NODE_ENV:\t");
// logUtils.printLog(process.env.NODE_ENV);
// //如果项目为单一域名，这里可以不用进行配置，当项目接口有多个域名时，要对axios实例基础路径进行配置，否则在项目生产环境中无法进行区别调用
// if (process.env.NODE_ENV == 'production' || process.env.NODE_ENV == 'development') {
//   instanceA.defaults.baseURL = process.env.VUE_APP_BASE_API_A; // process.env.BASE_API_A
//   instanceB.defaults.baseURL = process.env.VUE_APP_BASE_API_B; // process.env.BASE_API_B
// }
// logUtils.printLog("instanceA.defaults.baseURL:\t" + instanceA.defaults.baseURL);

//请求和响应拦截可以根据实际项目需求进行编写
// 请求发起前拦截
instanceA.interceptors.request.use((config) => {
  //这里可以对接口请求头进行操作 如：config.headers['X-Token'] = token
  if (store.getters['getToken'] && store.getters['getToken'].length > 0){
    config.headers['Authorization'] = 'Bearer ' + store.getters['getToken'];
  }
  logUtils.printLog(store);
  logUtils.printLog("请求拦截", config);
  return config;
}, (error) => {
  // Do something with request error
  return Promise.reject(error)
})
// instanceB.interceptors.request.use((config) => {
//   logUtils.printLog("请求拦截", config);
//   return config;
// }, (error) => {
//   // Do something with request error
//   return Promise.reject(error)
// })

// 响应拦截（请求返回后拦截）
instanceA.interceptors.response.use(response => {
  logUtils.printLog("响应拦截", response);
  return response;
}, error => {
  logUtils.printLog('catch', error)
  return Promise.reject(error)
})
// instanceB.interceptors.response.use(response => {
//   logUtils.printLog("响应拦截", response);
//   return response;
// }, error => {
//   logUtils.printLog('catch', error)
//   return Promise.reject(error)
// })

//按照请求类型对axios进行封装
const api = {
  get(url, data) {
    logUtils.printLog('post', url);
    return instanceA.get(url, {params: data})
  },
  post(url, data) {
    logUtils.printLog('post', url);
    return instanceA.post(url, qs.stringify(data))
    // return axios.get(url, qs.stringify(data),{timeout: 300000,})
  },
  // doGet(url, data) {
  //   return instanceB.get(url, {params: data})
  // },
  // doPost(url, data) {
  //   return instanceB.post(url, qs.stringify(data))
  // }
}
export {api}
