import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import home from '@/page/home'
import login from '@/page/login'
import component from '@/page/component/component'
import swagger from '@/page/swagger/swagger'

Vue.use(Router)

export default new Router({
  routes: [
//     {
//       path: '/',
//       name: 'HelloWorld',
//       component: HelloWorld
//     },
//     {
//       path: '/',
//       name: 'component',
//       component: component
//     },
    {
      path: '/',
      name: 'home',
      component: home,
      redirect: '/component',
      children: [
        {path: 'component', name: '组件', component: component, icon: 'el-icon-menu'},
        {path: 'swagger', name: 'swagger Api', component: swagger, icon: 'el-icon-tickets'},
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: login
    }
  ]
})
