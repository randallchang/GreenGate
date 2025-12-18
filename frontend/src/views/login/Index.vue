<template>
  <div class="login-container">
    <el-form ref="loginFormRef" :model="loginForm" class="login-form" auto-complete="on" label-position="left">
      <div class="form-box">
        <div class="form-row">
          <div class="input-box">
            <el-form-item label="username">
              <el-input v-model="loginForm.username" placeholder="Username" name="username" type="text" tabindex="2" />
            </el-form-item>
          </div>
        </div>
        <div class="form-row">
          <div class="input-box">
            <el-form-item label="password">
              <el-input v-model="loginForm.password" placeholder="password" name="password" type="password"
                tabindex="1" />
            </el-form-item>
          </div>
        </div>
      </div>
      <el-button :loading="loading" class="login-btn" type="primary" @click.prevent="handleLogin">login</el-button>
    </el-form>

  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Login } from '@/api/login'
import { setToken } from '@/util/cookieUtil'
import router from '@/router'

const loginForm = ref({
  username: '',
  password: '',
})

const loading = ref(false)

function handleLogin() {
  if (!loginForm.value.username) {
    ElMessage.warning('username can\'t be empty')
    return
  }
  if (!loginForm.value.password) {
    ElMessage.warning('password can\'t be empty')
    return
  }
  loading.value = true
  Login(loginForm.value)
    .then(res => {
      if (res.status === 200) {
        const token = res.data.token || ''
        setToken(token)
        router.push('/')
      }
    })
    .finally(() => {
      loading.value = false
    })
}
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
}

.login-form {
  width: 400px;
  padding: 30px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.form-row {
  margin-bottom: 20px;
}

.login-btn {
  width: 100%;
  margin-top: 10px;
}
</style>
