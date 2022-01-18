import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Account/Login.vue'
import FindId from '../views/Account/FindId.vue'
import FindPassword from '../views/Account/FindPassword.vue'
// import MyPage from '../views/Account/MyPage.vue'
import SignUp from '../views/Account/SignUp.vue'

// import Location from '../views/Collection/Location.vue'
// import Schedule from '../views/Collection/Schedule.vue'
// import Timeline from '../views/Collection/Timeline.vue'

// import MiniGame from '../views/Entertainment/MiniGame.vue'
// import Vote from '../views/Entertainment/Vote.vue'

// import FeedCreate from '../views/Feed/FeedCreate.vue'
// import Detail from '../views/Feed/Detail.vue'
// import Main from '../views/Feed/Main.vue'
// import Update from '../views/Feed/Update.vue'

import GroupCreate from '../views/Group/GroupCreate.vue'
// import Management from '../views/Group/Management.vue'
// import Member from '../views/Group/Member.vue'
import Select from '../views/Group/Select.vue'

// import UserPage from '../views/Profile/UserPage.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/findid',
    name: 'FindId',
    component: FindId
  },
  {
    path: '/findpassword',
    name: 'FindPassword',
    component: FindPassword
  },
  // {
  //   path: '/mypage',
  //   name: 'MyPage',
  //   component: MyPage
  // },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  // {
  //   path: '/location',
  //   name: 'Location',
  //   component: Location
  // },
  // {
  //   path: '/schedule',
  //   name: 'Schedule',
  //   component: Schedule
  // },
  // {
  //   path: '/timeline',
  //   name: 'Timeline',
  //   component: Timeline
  // },
  // {
  //   path: '/minigame',
  //   name: 'MiniGame',
  //   component: MiniGame
  // },
  // {
  //   path: '/vote',
  //   name: 'Vote',
  //   component: Vote
  // },
  // {
  //   path: '/feedcreate',
  //   name: 'FeedCreate',
  //   component: FeedCreate
  // },
  // {
  //   path: '/detail',
  //   name: 'Detail',
  //   component: Detail
  // },
  // {
  //   path: '/main',
  //   name: 'Main',
  //   component: Main
  // },
  // {
  //   path: '/update',
  //   name: 'Update',
  //   component: Update
  // },
  {
    path: '/groupcreate',
    name: 'GroupCreate',
    component: GroupCreate
  },
  // {
  //   path: '/management',
  //   name: 'Management',
  //   component: Management
  // },
  // {
  //   path: '/member',
  //   name: 'Member',
  //   component: Member
  // },
  {
    path: '/select',
    name: 'Select',
    component: Select
  },
  // {
  //   path: '/userpage',
  //   name: 'UserPage',
  //   component: UserPage
  // },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
