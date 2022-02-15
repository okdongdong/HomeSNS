import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Account/Login.vue";
import FindId from "../views/Account/FindId.vue";
import FindPassword from "../views/Account/FindPassword.vue";
import MyPage from "../views/Account/MyPage.vue";
import SignUp from "../views/Account/SignUp.vue";
import Background from "../views/Account/Background.vue";

import Location from "../views/Collection/Location.vue";
//test
import Calendar from "../views/Collection/Calendar.vue";
import Timeline from "../views/Collection/Timeline.vue";

import EntFeedList from "../views/Entertainment/EntFeedList.vue";
import EntFeedDetail from "../views/Entertainment/EntFeedDetail.vue";
import MiniGame from "../views/Entertainment/MiniGameList.vue";
import VoteList from "../views/Entertainment/VoteList.vue";
import VoteCreate from "../views/Entertainment/VoteCreate.vue";
import GhostLegCreate from "../views/Entertainment/GhostLegCreate.vue";

import FeedCreate from "../views/Feed/FeedCreate.vue";
import Detail from "../views/Feed/Detail.vue";
import Main from "../views/Feed/Main.vue";
// import Update from '../views/Feed/Update.vue'

import GroupCreate from "../views/Group/GroupCreate.vue";
import GroupEnter from "../views/Group/GroupEnter.vue";
// import Management from '../views/Group/Management.vue'
import GroupProfile from "../views/Group/GroupProfile.vue";
import Select from "../views/Group/Select.vue";

import UserPage from "../views/Profile/UserPage.vue";
import UserPageFeed from "../views/Profile/UserPageFeed.vue";
import UserPageScrap from "../views/Profile/UserPageScrap.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    component: Background,
    children: [
      { path: "", name: "Login", component: Login },
      {
        path: "/findid",
        name: "FindId",
        component: FindId,
      },
      {
        path: "/findpassword",
        name: "FindPassword",
        component: FindPassword,
      },
      {
        path: "/signup",
        name: "SignUp",
        component: SignUp,
      },
      {
        path: "/groupcreate",
        name: "GroupCreate",
        component: GroupCreate,
      },
      {
        path: "/groupenter",
        name: "GroupEnter",
        component: GroupEnter,
      },
      {
        path: "/select",
        name: "Select",
        component: Select,
      },
    ],
  },

  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },

  {
    path: "/location",
    name: "Location",
    component: Location,
  },
  {
    path: "/calendar",
    name: "Calendar",
    component: Calendar,
  },
  {
    path: "/timeline",
    name: "Timeline",
    component: Timeline,
  },
  {
    path: "/EntFeed",
    name: "EntFeedList",
    component: EntFeedList,
  },
  {
    path: "/EntFeed/:contentType/:contentId",
    name: "EntFeedDetail",
    component: EntFeedDetail,
    props: true,
  },
  {
    path: "/minigame",
    name: "MiniGame",
    component: MiniGame,
  },
  {
    path: "/votelist",
    name: "VoteList",
    component: VoteList,
  },
  {
    path: "/votecreate",
    name: "VoteCreate",
    component: VoteCreate,
  },
  {
    path: "/ghostlegcreate",
    name: "GhostLegCreate",
    component: GhostLegCreate,
  },
  {
    path: "/feedcreate/:feedId",
    name: "FeedCreate",
    component: FeedCreate,
    props: true,
  },
  {
    path: "/detail/:feedId",
    name: "Detail",
    component: Detail,
    props: true,
  },
  {
    path: "/main/:groupId",
    name: "Main",
    component: Main,
    props: true,
  },
  // {
  //   path: '/update',
  //   name: 'Update',
  //   component: Update
  // },

  // {
  //   path: '/management',
  //   name: 'Management',
  //   component: Management
  // },
  {
    path: "/groupprofile",
    name: "GroupProfile",
    component: GroupProfile,
  },

  {
    path: "/userpage",
    component: UserPage,
    props: true,
    children: [
      {
        path: "/:userSeq",
        name: "UserPage",
        component: UserPageFeed,
        props: true,
      },
      {
        path: "/:userSeq/scrap",
        name: "UserPageScrap",
        component: UserPageScrap,
        props: true,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  // console.log(to);
  // console.log(from);
  const token = localStorage.getItem("jwt");
  if (token && (to.name == "Login" || to.name == "SignUp")) {
    next({ name: "Select" });
  } else if (!token && to.name != "Login") {
    if (
      to.name == "SignUp" ||
      to.name == "FindId" ||
      to.name == "FindPassword"
    ) {
      next();
    } else {
      next({ name: "Login" });
    }
  } else {
    next();
  }
});

export default router;
