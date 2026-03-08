<template>
  <div class="container">
    <header>
      <h1>Users</h1>
      <button @click="fetchUsers" :disabled="loading" class="btn-refresh">
        <span v-if="loading" class="spinner" />
        <span v-else>↻ Refresh</span>
      </button>
    </header>

    <div v-if="error" class="error-banner">
      ⚠ {{ error }}
    </div>

    <div v-if="loading && !gameSessions.length" class="loading-state">
      <div class="pulse" v-for="n in 3" :key="n" />
    </div>

    <ul v-else class="user-list">
      <li v-for="gameSession in gameSessions"  class="user-card">
        <span class="user-id">#{{ gameSession }}</span>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:7000/api/v1'

const gameSessions = ref([])
const loading = ref(false)
const error = ref(null)

async function fetchUsers() {
  loading.value = true
  error.value = null

  try {
    const { data } = await axios.get(`${BASE_URL}/game-sessions`)
    console.log(data)
    gameSessions.value = data
  } catch (err) {
    error.value = err.response?.data?.message || err.message || 'Error al conectar con el backend'
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchUsers()
})
</script>

<style scoped>
.container { max-width: 600px; margin: 2rem auto; padding: 0 1rem; font-family: monospace; }
header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem; }
h1 { font-size: 1.5rem; margin: 0; }
.btn-refresh { padding: 0.4rem 1rem; cursor: pointer; background: #111; color: #fff; border: none; border-radius: 4px; display: flex; align-items: center; gap: 0.4rem; }
.btn-refresh:disabled { opacity: 0.5; cursor: not-allowed; }
.spinner { width: 14px; height: 14px; border: 2px solid #fff4; border-top-color: #fff; border-radius: 50%; animation: spin 0.6s linear infinite; }
@keyframes spin { to { transform: rotate(360deg); } }
.error-banner { background: #fee2e2; color: #991b1b; padding: 0.75rem 1rem; border-radius: 6px; margin-bottom: 1rem; }
.loading-state { display: flex; flex-direction: column; gap: 0.75rem; }
.pulse { height: 56px; background: #e5e7eb; border-radius: 8px; animation: pulse 1.4s ease-in-out infinite; }
@keyframes pulse { 0%, 100% { opacity: 1; } 50% { opacity: 0.4; } }
.user-list { list-style: none; padding: 0; margin: 0; display: flex; flex-direction: column; gap: 0.5rem; }
.user-card { display: flex; align-items: center; gap: 1rem; padding: 0.75rem 1rem; border: 1px solid #e5e7eb; border-radius: 8px; }
.user-id { color: #9ca3af; font-size: 0.8rem; min-width: 2rem; }
.user-card div { display: flex; flex-direction: column; gap: 0.1rem; }
small { color: #6b7280; font-size: 0.8rem; }
</style>
