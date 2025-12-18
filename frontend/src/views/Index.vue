<template>
  <div class="user-manage">
    <div class="page-header">
      <h1 class="page-title">User Management</h1>
      <p class="page-subtitle">View and manage system users</p>
    </div>
    <div class="page-body">
      <el-card class="table-card" shadow="hover">
        <template #header>
          <div class="card-header">
            <span class="header-title">
              <el-icon>
                <User />
              </el-icon>
              User List
            </span>
            <el-button type="primary" :icon="Refresh" circle @click="getUserList" :loading="tableLoading" />
          </div>
        </template>
        <el-table v-loading="tableLoading" :data="userList" stripe size="large" empty-text="No data" style="width: 100%"
          :header-cell-style="{ background: '#f5f7fa', color: '#606266', fontWeight: '600' }"
          :row-style="{ height: '60px' }">
          <el-table-column type="index" label="#" width="60" align="center" />
          <el-table-column prop="id" label="ID" width="100" align="center">
            <template #default="{ row }">
              <el-tag type="info" effect="plain">{{ row.id }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="username" label="Username" align="center">
            <template #default="{ row }">
              <div class="user-info">
                <el-avatar :size="32" :icon="UserFilled" />
                <span class="username">{{ row.username }}</span>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { User, UserFilled, Refresh } from '@element-plus/icons-vue'
import { UserList } from '@/api/user'

const tableLoading = ref(false)
const userList = ref([])

function getUserList() {
  tableLoading.value = true
  UserList()
    .then(res => {
      if (res.status === 200) {
        userList.value = res.data
      }
    })
    .finally(() => {
      tableLoading.value = false
    })
}

onMounted(() => {
  getUserList()
})

</script>

<style scoped>
.user-manage {
  padding: 24px;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #e4e8eb 100%);
}

.page-header {
  margin-bottom: 24px;
}

.page-title {
  font-size: 28px;
  font-weight: 600;
  color: #303133;
  margin: 0 0 8px 0;
}

.page-subtitle {
  font-size: 14px;
  color: #909399;
  margin: 0;
}

.table-card {
  border-radius: 12px;
  border: none;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-title {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.user-info {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 12px;
}

.username {
  font-weight: 500;
  color: #303133;
}

:deep(.el-table) {
  border-radius: 8px;
  overflow: hidden;
}

:deep(.el-table__row) {
  transition: background-color 0.2s;
}

:deep(.el-table__row:hover) {
  background-color: #ecf5ff !important;
}
</style>
