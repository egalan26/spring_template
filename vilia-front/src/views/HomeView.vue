<template>
  <div class="tavern-container">
    <header class="tavern-header">
      <div class="header-content">
        <h1 class="tavern-title">⚔️ SALÓN DE BATALLA ⚔️</h1>
        <p class="tavern-subtitle">Sesiones de Juego Disponibles</p>
      </div>
      <button @click="fetchUsers" :disabled="loading" class="btn-refresh">
        <span v-if="loading" class="spinner" />
        <span v-else>🔄 Actualizar</span>
      </button>
    </header>

    <div v-if="error" class="error-banner">
      ⚠️ {{ error }}
    </div>

    <div v-if="loading && !gameSessions.length" class="loading-state">
      <div class="pulse-card" v-for="n in 3" :key="n" />
    </div>

    <div v-else class="sessions-container">
      <div v-if="gameSessions.length === 0" class="empty-state">
        <p class="empty-text">No hay sesiones disponibles en el momento...</p>
        <p class="empty-subtitle">Vuelve más tarde, aventurero</p>
      </div>
      <div v-else class="sessions-grid">
        <div v-for="(gameSession, index) in gameSessions" :key="index" class="session-card">
          <div class="card-header">
            <span class="session-number">#{{ index + 1 }}</span>
            <span class="session-badge">ACTIVA</span>
          </div>
          <div class="card-body">
            <p class="session-id">ID Sesión: {{ gameSession }}</p>
            <div class="card-footer">
              <button class="btn-join">⚔️ Unirse</button>
            </div>
          </div>
        </div>
      </div>
    </div>
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
* {
  box-sizing: border-box;
}

:root {
  --tavern-dark: #1a1a1a;
  --tavern-bg: #2B1810;
  --tavern-accent: #8B6914;
  --tavern-light: #D4A574;
  --tavern-wine: #4A0E0E;
  --tavern-gold: #FFD700;
  --tavern-border: #5C3D2E;
}

.tavern-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #1a1a1a 0%, #2B1810 50%, #1a1a1a 100%);
  padding: 2rem 1rem;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.tavern-header {
  max-width: 1000px;
  margin: 0 auto 3rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2rem;
  background: linear-gradient(135deg, rgba(43, 24, 16, 0.8) 0%, rgba(139, 105, 20, 0.1) 100%);
  border: 2px solid var(--tavern-accent);
  border-radius: 12px;
  box-shadow: 0 8px 32px rgba(212, 165, 116, 0.15),
              inset 0 1px 0 rgba(255, 215, 0, 0.1);
  position: relative;
  overflow: hidden;
}

.tavern-header::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 1px;
  background: linear-gradient(90deg, transparent, var(--tavern-gold), transparent);
}

.header-content {
  flex: 1;
}

.tavern-title {
  font-size: 2.5rem;
  font-weight: 900;
  margin: 0;
  color: var(--tavern-gold);
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.8),
               0 0 20px rgba(212, 165, 116, 0.3);
  letter-spacing: 2px;
}

.tavern-subtitle {
  font-size: 1rem;
  color: var(--tavern-light);
  margin: 0.5rem 0 0 0;
  opacity: 0.8;
  font-style: italic;
}

.btn-refresh {
  padding: 0.8rem 1.5rem;
  cursor: pointer;
  background: linear-gradient(135deg, var(--tavern-accent), #A0791A);
  color: var(--tavern-gold);
  border: 1px solid var(--tavern-gold);
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 0.6rem;
  transition: all 0.3s ease;
  box-shadow: 0 4px 15px rgba(212, 165, 116, 0.2);
  white-space: nowrap;
}

.btn-refresh:hover:not(:disabled) {
  background: linear-gradient(135deg, var(--tavern-gold), var(--tavern-accent));
  color: var(--tavern-dark);
  box-shadow: 0 6px 20px rgba(255, 215, 0, 0.3);
  transform: translateY(-2px);
}

.btn-refresh:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.spinner {
  width: 16px;
  height: 16px;
  border: 2px solid rgba(255, 215, 0, 0.3);
  border-top-color: var(--tavern-gold);
  border-radius: 50%;
  animation: spin 0.6s linear infinite;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.error-banner {
  max-width: 1000px;
  margin: 0 auto 2rem;
  background: linear-gradient(135deg, rgba(74, 14, 14, 0.9), rgba(180, 50, 50, 0.2));
  color: #FFB6C6;
  padding: 1.2rem 1.5rem;
  border-radius: 8px;
  border-left: 4px solid var(--tavern-wine);
  box-shadow: 0 4px 12px rgba(212, 165, 116, 0.1);
}

.loading-state {
  max-width: 1000px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
}

.pulse-card {
  height: 200px;
  background: linear-gradient(135deg, rgba(43, 24, 16, 0.8), rgba(139, 105, 20, 0.1));
  border: 1px solid var(--tavern-border);
  border-radius: 8px;
  animation: pulse 1.4s ease-in-out infinite;
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.4; }
}

.sessions-container {
  max-width: 1000px;
  margin: 0 auto;
}

.empty-state {
  text-align: center;
  padding: 4rem 2rem;
  background: linear-gradient(135deg, rgba(43, 24, 16, 0.5), rgba(74, 14, 14, 0.3));
  border: 2px dashed var(--tavern-border);
  border-radius: 12px;
  color: var(--tavern-light);
}

.empty-text {
  font-size: 1.5rem;
  margin: 0;
  color: var(--tavern-light);
}

.empty-subtitle {
  font-size: 1rem;
  margin: 0.5rem 0 0 0;
  color: var(--tavern-accent);
  opacity: 0.7;
}

.sessions-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1.5rem;
}

.session-card {
  background: linear-gradient(135deg, rgba(43, 24, 16, 0.8), rgba(75, 45, 46, 0.4));
  border: 1px solid var(--tavern-accent);
  border-radius: 8px;
  overflow: hidden;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5);
  position: relative;
}

.session-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 2px;
  background: linear-gradient(90deg, transparent, var(--tavern-gold), transparent);
}

.session-card:hover {
  border-color: var(--tavern-gold);
  box-shadow: 0 8px 24px rgba(212, 165, 116, 0.2),
              inset 0 0 20px rgba(139, 105, 20, 0.1);
  transform: translateY(-4px);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1.2rem;
  background: linear-gradient(90deg, rgba(139, 105, 20, 0.3), transparent);
  border-bottom: 1px solid var(--tavern-border);
}

.session-number {
  font-size: 1.8rem;
  font-weight: 900;
  color: var(--tavern-gold);
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.8);
}

.session-badge {
  background: linear-gradient(135deg, var(--tavern-wine), #6B0E0E);
  color: var(--tavern-gold);
  padding: 0.4rem 0.8rem;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  letter-spacing: 1px;
  box-shadow: 0 2px 8px rgba(212, 165, 116, 0.2);
}

.card-body {
  padding: 1.5rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.session-id {
  margin: 0;
  color: var(--tavern-light);
  font-size: 0.95rem;
  word-break: break-all;
  font-family: 'Monaco', 'Courier New', monospace;
  background: rgba(0, 0, 0, 0.3);
  padding: 0.8rem;
  border-radius: 4px;
  border-left: 2px solid var(--tavern-accent);
}

.card-footer {
  padding-top: 1rem;
  border-top: 1px solid var(--tavern-border);
}

.btn-join {
  width: 100%;
  padding: 0.8rem 1rem;
  background: linear-gradient(135deg, var(--tavern-wine), #6B0E0E);
  color: var(--tavern-gold);
  border: 1px solid var(--tavern-gold);
  border-radius: 6px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(212, 165, 116, 0.15);
}

.btn-join:hover {
  background: linear-gradient(135deg, var(--tavern-gold), var(--tavern-accent));
  color: var(--tavern-dark);
  box-shadow: 0 6px 16px rgba(255, 215, 0, 0.3);
  transform: translateY(-2px);
}

.btn-join:active {
  transform: translateY(0);
  box-shadow: 0 2px 8px rgba(212, 165, 116, 0.2);
}

@media (max-width: 768px) {
  .tavern-header {
    flex-direction: column;
    gap: 1.5rem;
    text-align: center;
  }

  .tavern-title {
    font-size: 1.8rem;
  }

  .sessions-grid {
    grid-template-columns: 1fr;
  }

  .btn-refresh {
    width: 100%;
    justify-content: center;
  }
}
</style>
