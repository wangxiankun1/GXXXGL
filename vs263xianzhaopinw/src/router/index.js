import { createRouter, createWebHistory } from 'vue-router'
import store from '../store'
const routes = [ 
  //首页
  {
    path: '/',
    redirect:'/webindex'
  },
  //登陆
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/shouye/login')
  },
  //admin注册
  {
    path: '/adminzhuce',
    name: 'adminzhuce',
    component: () => import('../views/admin/adminzhuce'),
  },
  //管理员找回密码admin不可以
  {
    path: '/adminzhaohui',
    name: 'adminzhaohui',
    component: () => import('../views/admin/adminzhaohui'),
  },
  //user注册
  {
    path: '/userzhuce',
    name: 'userzhuce',
    component: () => import('../views/admin/userzhuce'),
  },
  //user找回密码
  {
    path: '/userzhaohui',
    name: 'userzhaohui',
    component: () => import('../views/admin/userzhaohui'),
  },
  //yonghu注册
  {
    path: '/yonghuzhuce',
    name: 'yonghuzhuce',
    component: () => import('../views/admin/yonghuzhuce'),
  },
  //yonghu找回密码
  {
    path: '/yonghuzhaohui',
    name: 'yonghuzhaohui',
    component: () => import('../views/admin/yonghuzhaohui'),
  },
  //AI问答
  {
    path: '/aichat',
    name: 'aichat',
    component: () => import('../views/web/aichat'),
  },
  //登陆后台后显示
  {
    path: '/houtaimain',
    name: 'houtaimain',
    component: () => import('../views/houtai/houtaimain'),
    //登陆成功后成定向至
    redirect: "/neiye",
    children: [
      {
        path: '/neiye',
        name: 'neiye',
        component: () => import('../views/shouye/neiye'),
        meta:{requireAuth:true}
      },
      {
        path: '/adminmima',
        name: 'adminmima',
        component: () => import('../views/admin/adminmima'),
        meta:{requireAuth:true}
      },
      {
        path: '/rizhi',
        name: 'rizhi',
        component: () => import('../views/admin/rizhi'),
        meta:{requireAuth:true}
      },
      {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/admin/admin'),
        meta:{requireAuth:true}
      },
    ]

  },
  //前台网站路由
  {
    path: '/webhome',
    name: 'webhome',
    component: () => import('../views/web/webhome'),
    redirect: "/webindex",
    children: [
      {
        path: '/webindex',
        name: 'webindex',
        component: () => import('../views/web/webindex'),
      },
      {
        path: '/shujuList',
        name: 'shujuList',
        component: () => import('../views/web/shuju/shujuList'),
      },
      {
        path: '/shujuTuijian',
        name: 'shujuTuijian',
        component: () => import('../views/web/shuju/shujuTuijian'),
      },
      {
        path: '/shujuShow',
        name: 'shujuShow',
        component: () => import('../views/web/shuju/shujuShow'),
      },
      {
        path: '/gonggaoList',
        name: 'gonggaoList',
        component: () => import('../views/web/gonggao/gonggaoList'),
      },
      {
        path: '/gonggaoShow',
        name: 'gonggaoShow',
        component: () => import('../views/web/gonggao/gonggaoShow'),
      },
      {
        path: '/sjxiaoxiShow',
        name: 'sjxiaoxiShow',
        component: () => import('../views/web/sjxiaoxi/sjxiaoxiShow'),
      },
      {
        path: '/sjduochuList',
        name: 'sjduochuList',
        component: () => import('../views/web/sjduochu/sjduochuList'),
      },
      {
        path: '/sjduochuShow',
        name: 'sjduochuShow',
        component: () => import('../views/web/sjduochu/sjduochuShow'),
      },
      {
        path: '/yonghuList',
        name: 'yonghuList',
        component: () => import('../views/web/yonghu/yonghuList'),
      },
      {
        path: '/yonghuShow',
        name: 'yonghuShow',
        component: () => import('../views/web/yonghu/yonghuShow'),
      },
    ]

  }

]



const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  let asyncRoutestMark = store.state.asyncRoutestMark;
  //console.log("跳转login："+asyncRoutestMark);
  let currentUser = JSON.parse(sessionStorage.getItem('currentUser'));
  if ((!asyncRoutestMark)&&(currentUser)) {
    let jcdaohangslist = JSON.parse(sessionStorage.getItem('jcdaohangslist'));
    let role = sessionStorage.getItem('role');
    //console.log(role);
    //console.log("加载动态路由开始");
    for (let i in jcdaohangslist) {
      for (let j in jcdaohangslist[i]) {
        router.addRoute('houtaimain', {
          path: `/${jcdaohangslist[i][j].jcdaohangNeirong}`,
          name: `${jcdaohangslist[i][j].jcdaohangNeirong}`,
          component: () => import(`../views/admin/${jcdaohangslist[i][j].jcdaohangNeirong}`),
          meta:{requireAuth:true}
        })
        //console.log(jcdaohangslist[i][j].jcdaohangNeirong);
      }
    }
    if(role=='user'){
     router.addRoute('houtaimain', {
       path: '/usermima',
       name: 'usermima',
       component: () => import('../views/admin/usermima'),
       meta:{requireAuth:true}
      })
      router.addRoute('houtaimain', {
       path: '/user',
       name: 'user',
       component: () => import('../views/admin/user'),
       meta:{requireAuth:true}
      })
    }
    if(role=='yonghu'){
     router.addRoute('houtaimain', {
       path: '/yonghumima',
       name: 'yonghumima',
       component: () => import('../views/admin/yonghumima'),
       meta:{requireAuth:true}
      })
      router.addRoute('houtaimain', {
       path: '/yonghu',
       name: 'yonghu',
       component: () => import('../views/admin/yonghu'),
       meta:{requireAuth:true}
      })
    }
    console.log("加载动态路由后");
    console.log(router.getRoutes());
    store.commit('setAsyncRoutestMark', true); // 添加路由后更改标识为true
    next({...to,replace: true})// 处理刷新新的路由地址和旧的路由重复
  }else{
    if (to.path == '/login') {
      sessionStorage.removeItem('currentUser');
      sessionStorage.removeItem('role');
      sessionStorage.removeItem('jcpeizhi');
      sessionStorage.removeItem('jcbiaotis');
      sessionStorage.removeItem('jcdaohangslist');
      store.commit('setAsyncRoutestMark', false); // 退出登陆将动态路由标志设为空
    }
   
    //判断该路由是否需要登录权限
    if (to.meta.requireAuth) {
      if(to.path != '/login'){
        if(!currentUser){
         next({ path: '/login' });
        }else{
         //如果没有登陆，在登陆过程中动态加载对应router
         //console.log("跳转houtaimain"+asyncRoutestMark);
         if (!asyncRoutestMark) {
           //获取动态导航数据
           let jcdaohangslist = JSON.parse(sessionStorage.getItem('jcdaohangslist'));
           let role = sessionStorage.getItem('role');
           //console.log(role);
           //console.log("加载动态路由开始");
           for (let i in jcdaohangslist) {
             for (let j in jcdaohangslist[i]) {
               router.addRoute('houtaimain', {
                path: `/${jcdaohangslist[i][j].jcdaohangNeirong}`,
                name: `${jcdaohangslist[i][j].jcdaohangNeirong}`,
                component: () => import(`../views/admin/${jcdaohangslist[i][j].jcdaohangNeirong}`),
                meta:{requireAuth:true}
               })
               //console.log(jcdaohangslist[i][j].jcdaohangNeirong);
             }
           }
           if(role=='user'){
            router.addRoute('houtaimain', {
              path: '/usermima',
              name: 'usermima',
              component: () => import('../views/admin/usermima'),
              meta:{requireAuth:true}
             })
             router.addRoute('houtaimain', {
              path: '/user',
              name: 'user',
              component: () => import('../views/admin/user'),
              meta:{requireAuth:true}
             })
           }
           if(role=='yonghu'){
            router.addRoute('houtaimain', {
              path: '/yonghumima',
              name: 'yonghumima',
              component: () => import('../views/admin/yonghumima'),
              meta:{requireAuth:true}
             })
             router.addRoute('houtaimain', {
              path: '/yonghu',
              name: 'yonghu',
              component: () => import('../views/admin/yonghu'),
              meta:{requireAuth:true}
             })
           }
           console.log("加载动态路由后");
           console.log(router.getRoutes());
           store.commit('setAsyncRoutestMark', true) // 添加路由后更改标识为true
           next({...to,replace: true})// 处理刷新新的路由地址和旧的路由重复
         } else {
           //console.log("步骤32");
           next();
         }
        }
     }else{
       //console.log("步骤4");
       next();
     } 
    } else {
      //console.log("步骤5");
      next();
    } 
  } 
})

export default router
