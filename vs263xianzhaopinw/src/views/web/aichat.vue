<template>
  <div class="chatgpt-page">
    <div class="chatgpt-container">
      <!-- 标题区 -->
      <header class="chatgpt-header">
        <div class="title">AI智能助手</div>
        <div class="actions">
          <button class="action-btn" @click="newSession" :disabled="loading">新对话</button>
          <button class="action-btn" @click="clearHistory" :disabled="loading || messages.length === 0">清空历史</button>
        </div>
      </header>

      <!-- 历史消息 -->
      <section class="chatgpt-history" ref="historyRef">
        <div v-for="(m, idx) in messages" :key="idx" :class="['msg', m.role]">
          <div class="avatar" :class="m.role">{{ m.role === 'user' ? '我' : 'AI' }}</div>
          <div class="bubble">
            <div class="content">{{ m.content }}</div>
          </div>
        </div>

        <div v-if="loading" class="msg assistant">
          <div class="avatar assistant">AI</div>
          <div class="bubble"><span class="typing">正在思考中…</span></div>
        </div>
      </section>

      <!-- 输入区 -->
      <footer class="chatgpt-input">
        <textarea
          v-model="userInput"
          placeholder="请输入你的问题…（Enter 发送，Shift+Enter 换行）"
          rows="3"
          @keydown.enter.prevent="onEnter"
          class="input"
        ></textarea>
        <div class="toolbar">
          <div class="hint">上下文将自动带给后端</div>
          <button class="send-btn" @click="askXunfei" :disabled="loading || !userInput.trim()">
            {{ loading ? '处理中…' : '发送' }}
          </button>
        </div>
      </footer>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, onMounted, nextTick } from 'vue'
import request, { base } from '../../../utils/http' // 按你的项目路径调整

const messages = ref([{ role: 'assistant', content: '你好，我是AI智能问答助手。有什么想聊的？' }])
const userInput = ref('')
const loading = ref(false)
const historyRef = ref(null)
const STORAGE_KEY = 'spark_chat_history'

function scrollToBottom () {
  nextTick(() => {
    const el = historyRef.value
    if (el) el.scrollTop = el.scrollHeight
  })
}
function loadHistory () {
  try {
    const cache = localStorage.getItem(STORAGE_KEY)
    if (cache) {
      const parsed = JSON.parse(cache)
      if (Array.isArray(parsed)) messages.value = parsed
    }
  } catch (_) {}
}
function saveHistory () {
  try { localStorage.setItem(STORAGE_KEY, JSON.stringify(messages.value)) } catch (_) {}
}
onMounted(() => { loadHistory(); scrollToBottom() })
watch(messages, () => { saveHistory(); scrollToBottom() }, { deep: true })

function onEnter (e) {
  if (e.shiftKey) { userInput.value += '\n'; return }
  askXunfei()
}
function newSession () {
  if (loading.value) return
  messages.value = [{ role: 'assistant', content: '你好呀，我是你的专属ai小助手，你可以问我关于求职的任何问题？' }]
  userInput.value = ''
}
function clearHistory () {
  if (loading.value) return
  messages.value = []
  userInput.value = ''
  try { localStorage.removeItem(STORAGE_KEY) } catch (_) {}
}

// 兼容多种返回结构取文本
function extractAnswer (body) {
  if (!body) return ''
  if (typeof body === 'string') return body
  if (body.answer) return body.answer
  const p = body.payload && body.payload.choices && body.payload.choices[0]
  if (p && p.text) return p.text
  const d = body.data && body.data.choices && body.data.choices[0]
  if (d && d.message && d.message.content) return d.message.content
  const c = body.choices && body.choices[0]
  if (c && c.message && c.message.content) return c.message.content
  if (c && typeof c.text === 'string') return c.text
  if (body.message && body.message.content) return body.message.content
  return ''
}

async function askXunfei () {
  const text = (userInput.value || '').trim()
  if (!text || loading.value) return

  messages.value.push({ role: 'user', content: text })
  userInput.value = ''
  scrollToBottom()

  loading.value = true
  try {
    const url = base + '/api/spark/ask'
    const chatMessages = messages.value.map(m => ({ role: m.role, content: m.content }))
    const fallbackPrompt = messages.value.map(m => (m.role === 'user' ? '用户：' + m.content : '助手：' + m.content)).join('\n\n')

    // 关键：把前端超时调大（30s）
    const res = await request.post(
      url,
      { prompt: fallbackPrompt, messages: chatMessages },
      { timeout: 70000 }
    )

    const body = (res && res.data) ? res.data : res
    console.log('spark resp body =', body)

    if (typeof body.code !== 'undefined' && body.code !== 200) {
      const detail = body.msg || JSON.stringify(body, null, 2)
      messages.value.push({ role: 'assistant', content: detail })
      return
    }

    let aiText = extractAnswer(body)
    if (!aiText) aiText = JSON.stringify(body, null, 2)
    messages.value.push({ role: 'assistant', content: aiText })
  } catch (e) {
    const data = e && e.response && e.response.data
    const msg = (data && (data.msg || data.upstreamBody)) || e.message || '接口调用失败，请稍后重试'
    console.error('Spark error:', e)
    messages.value.push({ role: 'assistant', content: msg })
  } finally {
    loading.value = false
    scrollToBottom()
  }
}

defineExpose({ askXunfei, newSession, clearHistory })
</script>

<style scoped>
/* 页面整体，仅右侧问答区域 */
.chatgpt-page {
  display: flex;
  justify-content: center;
  background: #0f172a;
  min-height: 100vh;
  padding: 24px 12px;
  box-sizing: border-box;
}

/* 容器 */
.chatgpt-container {
  width: 100%;
  max-width: 920px;
  background: #0b1220;
  border: 1px solid #1f2a44;
  border-radius: 14px;
  box-shadow: 0 6px 28px rgba(0,0,0,0.25);
  display: flex;
  flex-direction: column;
  overflow: hidden;
}

/* 头部 */
.chatgpt-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 14px 16px;
  border-bottom: 1px solid #1f2a44;
  background: #0e1628;
}
.chatgpt-header .title {
  color: #e2e8f0;
  font-weight: 700;
  font-size: 16px;
}
.actions { display: flex; gap: 8px; }
.action-btn {
  background: #1e293b;
  color: #cbd5e1;
  border: 1px solid #334155;
  border-radius: 8px;
  padding: 6px 10px;
  font-size: 12px;
  cursor: pointer;
}
.action-btn:disabled { opacity: .6; cursor: not-allowed; }
.action-btn:hover:not(:disabled) { background: #0f172a; }

/* 历史消息滚动区 */
.chatgpt-history {
  flex: 1;
  overflow-y: auto;
  padding: 16px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

/* 单条消息 */
.msg { display: flex; gap: 10px; align-items: flex-start; max-width: 100%; }
.avatar {
  width: 36px; height: 36px; border-radius: 50%;
  flex: 0 0 36px; display: grid; place-items: center;
  font-size: 14px; font-weight: 700; color: #e2e8f0;
}
.avatar.user { background: #155e75; }
.avatar.assistant { background: #334155; }

/* 气泡与内容：防止超出、自动换行 */
.bubble {
  background: #0f172a;
  border: 1px solid #1f2a44;
  color: #e2e8f0;
  padding: 12px 14px;
  border-radius: 12px;
  width: fit-content;
  max-width: calc(100% - 46px);
  overflow: hidden;
  word-break: break-word;
  overflow-wrap: anywhere;
}
.msg.user .bubble { background: #0b2533; border-color: #184b63; }

.content {
  margin: 0;
  font-family: ui-monospace, SFMono-Regular, Menlo, Consolas, monospace;
  font-size: 14px;
  line-height: 1.65;
  white-space: pre-wrap;  /* 保留换行但允许折行 */
}

/* “正在输入”效果 */
.typing { display: inline-block; position: relative; }
.typing::after { content: '…'; animation: blink 1.2s steps(1,end) infinite; }
@keyframes blink { 0%, 50% { opacity: .2; } 100% { opacity: 1; } }

/* 底部输入区 */
.chatgpt-input { border-top: 1px solid #1f2a44; background: #0e1628; padding: 12px; }
.input {
  width: 100%;
  background: #0f172a;
  border: 1px solid #1f2a44;
  color: #e2e8f0;
  border-radius: 10px;
  padding: 10px 12px;
  resize: vertical;
  min-height: 68px;
  font-size: 14px;
  line-height: 1.6;
}
.input::placeholder { color: #8da2b8; }

.toolbar {
  margin-top: 8px;
  display: flex;
  align-items: center;
  gap: 12px;
  justify-content: space-between;
}
.hint { color: #93a7bd; font-size: 12px; }
.send-btn {
  background: #1565c0; color: #fff; border: none;
  border-radius: 10px; padding: 10px 16px; font-size: 14px; cursor: pointer;
}
.send-btn:disabled { background: #4a6ea8; cursor: not-allowed; }

/* 小屏优化 */
@media (max-width: 640px) {
  .chatgpt-container { border-radius: 0; }
  .chatgpt-page { padding: 0; }
}
</style>
