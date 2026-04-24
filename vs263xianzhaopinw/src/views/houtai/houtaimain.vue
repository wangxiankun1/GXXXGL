<template>
  <div class="admin-dashboard">
    <!-- 顶部导航栏 -->
    <header class="admin-header">
      <div class="admin-left">
        <img :src="logoUrl" class="logo" alt="Logo" />
        <span class="admin-title">
          {{ jcpeizhiName }} ·
          <span v-if="role === 'user'">个人中心</span>
          <span v-else>后台管理</span>
        </span>
        <span class="admin-phone">联系电话：400-8888-8888</span>
      </div>
      <div class="admin-user">
        当前用户：{{ userName }}
        <button @click="goToFront">返回前台</button>
        <button @click="logout">退出</button>
      </div>
    </header>

    <!-- 主体区域 -->
    <div class="admin-main">
      <!-- 左侧导航 -->
      <nav class="admin-sidebar">
        <!-- 系统固定菜单 -->
        <div class="menu-group">
          <div class="menu-title" @click="toggleGroup('base')">
            基础信息
          </div>
          <div class="menu-sub" v-show="openGroups.includes('base')" v-if="role=='admin'">
            <div class="menu-item" :class="{ active: isPathActive('/adminmima') }" @click="goTo('/adminmima')">
              修改密码
            </div>
            <div class="menu-item" :class="{ active: isPathActive('/rizhi') }" @click="goTo('/rizhi')">
              登录日志
            </div>
          </div>
          <div class="menu-sub" v-show="openGroups.includes('base')" v-if="role=='admin1'">
            <div class="menu-item" :class="{ active: isPathActive('/admin') }" @click="goTo('/admin')">
              个人信息
            </div>
            <div class="menu-item" :class="{ active: isPathActive('/adminmima') }" @click="goTo('/adminmima')">
              修改密码
            </div>
          </div>
          <div class="menu-sub" v-show="openGroups.includes('base')" v-if="role=='user'">
            <div class="menu-item" :class="{ active: isPathActive('/user') }" @click="goTo('/user')">
              个人信息
            </div>
            <div class="menu-item" :class="{ active: isPathActive('/usermima') }" @click="goTo('/usermima')">
              修改密码
            </div>
          </div>
          <div class="menu-sub" v-show="openGroups.includes('base')" v-if="role=='yonghu'">
            <div class="menu-item" :class="{ active: isPathActive('/yonghu') }" @click="goTo('/yonghu')">
              个人信息
            </div>
            <div class="menu-item" :class="{ active: isPathActive('/yonghumima') }" @click="goTo('/yonghumima')">
              修改密码
            </div>
          </div>
          <div class="menu-divider"></div>
        </div>

        <!-- 动态菜单 -->
        <div
          v-for="(group, index) in jcbiaotis"
          :key="index"
          class="menu-group"
        >
          <div class="menu-title" @click="toggleGroup(index)">
            {{ group.jcbiaotiName }}
          </div>
          <div class="menu-sub" v-show="openGroups.includes(index)">
            <div
              v-for="(item, subIndex) in jcdaohangslist[index]"
              :key="subIndex"
              :class="['menu-item', { active: isItemActive(item) }]"
              @click="goTo('/' + item.jcdaohangNeirong)"
            >
              {{ item.jcdaohangName }}
            </div>
          </div>
          <div class="menu-divider" v-if="index < jcbiaotis.length - 1"></div>
        </div>
      </nav>

      <!-- 右侧内容 -->
      <section class="admin-content">
        <router-view />
      </section>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
const logoUrl = require('@/assets/logo.jpg')  // 保持和前台一致

const router = useRouter()
const route = useRoute()

const jcpeizhiName = ref('')
const userName = ref('')
const role = ref('')
const jcbiaotis = ref([])
const jcdaohangslist = ref([])
const openGroups = ref([])

// 判断静态菜单是否高亮
function isPathActive(path) {
  return route.path === path
}

// 判断动态菜单是否高亮
function isItemActive(item) {
  return route.path === '/' + item.jcdaohangNeirong || route.name === item.jcdaohangNeirong
}

function toggleGroup(index) {
  if (openGroups.value.includes(index)) {
    openGroups.value = openGroups.value.filter(i => i !== index)
  } else {
    openGroups.value.push(index)
  }
}

function goTo(path) {
  router.push(path)
}

function goToFront() {
  const role = sessionStorage.getItem('role')
  if (role === "user") {
    router.push("/");
  } else {
    window.open("/", "_blank");
  }
}

function logout() {
  if (confirm('确认退出吗？')) {
    sessionStorage.removeItem('currentUser')
    sessionStorage.removeItem('jcpeizhi')
    sessionStorage.removeItem('jcbiaotis')
    sessionStorage.removeItem('jcdaohangslist')
    sessionStorage.removeItem('role')
    router.push('/login')
  }
}

onMounted(() => {
  const jcpeizhiStr = sessionStorage.getItem('jcpeizhi')
  const userStr = sessionStorage.getItem('currentUser')
  const roleStr = sessionStorage.getItem('role')
  const jcbiaotiStr = sessionStorage.getItem('jcbiaotis')
  const jcdaohangsStr = sessionStorage.getItem('jcdaohangslist')

  const jcpeizhi = JSON.parse(jcpeizhiStr || '{}')
  const user = JSON.parse(userStr || '{}')

  jcpeizhiName.value = jcpeizhi.jcpeizhiName || ''
  role.value = roleStr || ''

  if (role.value === 'admin') {
    userName.value = (user.adminName || '') + '【系统管理员】'
  } else if (role.value === 'admin1') {
    userName.value = (user.adminName || '') + '【管理员】'
  } else if (role.value === 'user') {
    userName.value = (user.userName || '') + `【${jcpeizhi.userBieming || '用户'}】`
  } else if (role.value === 'yonghu') {
    userName.value = (user.yonghuName || '') + `【${jcpeizhi.yonghuBieming || '用户'}】`
  }

  jcbiaotis.value = JSON.parse(jcbiaotiStr || '[]')
  jcdaohangslist.value = JSON.parse(jcdaohangsStr || '[]')

  const currentPath = route.path
  jcdaohangslist.value.forEach((group, index) => {
    const found = group.some(item => currentPath.startsWith('/' + item.jcdaohangNeirong))
    if (found && !openGroups.value.includes(index)) {
      openGroups.value.push(index)
    }
  })

  if (currentPath === '/houtaimain') {
    const firstPath = jcdaohangslist.value[0]?.[0]?.jcdaohangNeirong
    if (firstPath) {
      router.replace('/' + firstPath)
    }
  }
})

watch(() => route.path, () => {})
</script>

<style scoped>
.admin-header {
  height: 62px;
  background: linear-gradient(120deg, #258ee7 0%, #22c88d 100%);
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  box-shadow: 0 3px 16px #22c88d33;
  z-index: 9;
}
.admin-left {
  display: flex;
  align-items: center;
  gap: 18px;
}
.logo {
  width: 44px;
  height: 44px;
  border-radius: 50%;
  background: #e9f4fe;
  border: 2px solid #22c88d;
  box-shadow: 0 0 12px #22c88d44;
}
.admin-title {
  font-size: 20px;
  font-weight: bold;
  letter-spacing: 1.1px;
  color: #fff;
  margin-right: 26px;
  text-shadow: 0 2px 10px #0cb17232;
}
.admin-phone {
  color: #14b897;
  font-weight: bold;
  font-size: 15px;
  margin-left: 6px;
  white-space: nowrap;
  letter-spacing: 1px;
  background: linear-gradient(90deg,#63f0fa 20%, #1be7a2 100%);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  text-fill-color: transparent;
}
.admin-user {
  display: flex;
  align-items: center;
}
.admin-user button {
  margin-left: 14px;
  padding: 7px 20px;
  border: none;
  background: linear-gradient(90deg, #1be7a2 0%, #258ee7 100%);
  color: #fff;
  border-radius: 22px;
  cursor: pointer;
  font-weight: 500;
  font-size: 15px;
  box-shadow: 0 1.5px 10px #0cb1721a;
  transition: background .22s, box-shadow 0.22s;
}
.admin-user button:hover {
  background: linear-gradient(100deg, #258ee7 0%, #1be7a2 100%);
  color: #fff;
  box-shadow: 0 4px 20px #0cb17222;
}

/* 主体部分 */
.admin-dashboard {
  font-family: "Segoe UI", "PingFang SC", sans-serif;
  height: 100vh;
  display: flex;
  flex-direction: column;
  background: #f4f6fa;
}
.admin-main {
  display: flex;
  flex: 1;
  background: #f6fcf8;
  height: calc(100vh - 62px);
}
.admin-sidebar {
  width: 244px;
  min-width: 220px;
  max-width: 260px;
  background: #f3fdf7;
  color: #128a5a;
  padding: 18px 0 18px 0;
  border-right: 1.5px solid #c2ecda;
  box-shadow: 2px 0 18px #20c77b13;
  overflow-y: auto;
  overflow-x: hidden;
}

.menu-title {
  font-weight: bold;
  font-size: 15px;
  margin-bottom: 6px;
  cursor: pointer;
  padding: 10px 24px 10px 32px;
  border-radius: 9px;
  transition: background 0.28s;
  color: #158a5c;
}
.menu-title:hover {
  background: #e0f9f0;
}
.menu-item {
  padding: 8px 42px 8px 48px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.22s, color 0.18s;
  color: #157154;
  font-size: 15px;
}
.menu-item:hover {
  background: #e2f9f0;
  color: #22b786;
}
.menu-item.active {
  background: linear-gradient(90deg, #258ee7 0%, #18c277 90%);
  color: #fff;
  font-weight: bold;
  box-shadow: 0 2px 10px #22c88d35;
}

.menu-divider {
  height: 1px;
  background-color: #d6efe3;
  margin: 12px 0 10px 22px;
  width: 80%;
}

.admin-content {
  flex: 1;
  padding: 32px 32px 22px 32px;
  background: #ffffff;
  border-left: 1.5px solid #d2f2e5;
  overflow-y: auto;
  max-height: calc(100vh - 62px);
  border-radius: 0 0 0 18px;
  box-shadow: -3px 1px 20px #20c77b09;
}

/* 滚动条美化 */
.admin-sidebar::-webkit-scrollbar {
  width: 6px;
  background: #f7fdf9;
}
.admin-sidebar::-webkit-scrollbar-thumb {
  background: #d6f7ea;
  border-radius: 8px;
}

/* 响应式 */
@media (max-width: 900px) {
  .admin-sidebar {
    width: 56vw;
    min-width: 160px;
    max-width: 80vw;
    font-size: 14px;
    padding-left: 3px;
  }
  .admin-content {
    padding: 10px 2vw;
  }
  .admin-header {
    padding: 0 7vw;
  }
  .logo {
    width: 36px;
    height: 36px;
  }
  .admin-title {
    font-size: 17px;
    margin-right: 10px;
  }
}

@media (max-width: 600px) {
  .admin-header {
    padding: 0 5vw;
    height: 48px;
  }
  .admin-title {
    font-size: 15px;
    margin-right: 7px;
  }
  .admin-content {
    padding: 8px 2vw 0 2vw;
    border-radius: 0;
  }
  .logo {
    width: 30px;
    height: 30px;
  }
}
</style>
