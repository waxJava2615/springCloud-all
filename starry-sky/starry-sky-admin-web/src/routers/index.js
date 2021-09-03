import Vue from 'vue'
import Router from 'vue-router'
import loginRoute from './login'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    loginRoute
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // }
  ]
})
