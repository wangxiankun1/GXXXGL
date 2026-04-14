import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
// 引入中文包
import zhCn from 'element-plus/lib/locale/lang/zh-cn'

import 'element-plus/lib/theme-chalk/index.css'
import 'dayjs/locale/zh-cn'
import './assets/first/theme/index.css'
import './assets/first/css/global.css'
import './assets/first/css/style.css'

const app = createApp(App)

app.use(store)
app.use(router)
// 全局注册 ElementPlus 并应用中文语言包
app.use(ElementPlus, {
    locale: zhCn,
})

app.mount('#app')