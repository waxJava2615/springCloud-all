import Vue from 'vue'
import Router from 'vue-router'
import loginRoute from './login'
import welCome from './welCome'

Vue.use(Router);

const originalPush = Router.prototype.push;
Router.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
};

export default new Router({
  // mode: 'history',
  routes: [
    loginRoute,
    welCome
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // }
  ]
})

