<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="app-container">
    <header class="app-header">
      <div class="header-content">
        <div class="logo-section">
          <h1 class="app-title">Vilia</h1>
        </div>
        <nav class="app-nav">
          <RouterLink to="/" class="nav-link">Home</RouterLink>
          <RouterLink to="/about" class="nav-link">About</RouterLink>
        </nav>
      </div>
    </header>

    <main class="main-content">
      <RouterView />
    </main>

    <footer class="app-footer">
      <p>&copy; 2026 Vilia Games. All rights reserved.</p>
    </footer>
  </div>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
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

.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background: linear-gradient(135deg, #1a1a1a 0%, #2B1810 50%, #1a1a1a 100%);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.app-header {
  background: linear-gradient(180deg, rgba(43, 24, 16, 0.98) 0%, rgba(26, 26, 26, 0.95) 100%);
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.4), inset 0 1px 0 rgba(255, 215, 0, 0.1);
  padding: 1.5rem 0;
  position: sticky;
  top: 0;
  z-index: 100;
  border-bottom: 2px solid var(--tavern-border);
}

.header-content {
  width: 95%;
  margin: 0 auto;
  padding: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logo-section {
  flex-shrink: 0;
}

.app-title {
  font-size: 2.2rem;
  font-weight: 700;
  color: var(--tavern-gold);
  letter-spacing: 2px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5), 0 0 10px rgba(255, 215, 0, 0.3);
  font-family: Georgia, serif;
}

.app-nav {
  display: flex;
  gap: 3rem;
  align-items: center;
}

.nav-link {
  font-size: 1.1rem;
  font-weight: 600;
  color: var(--tavern-light);
  text-decoration: none;
  transition: all 0.3s ease;
  position: relative;
  padding: 0.5rem 0;
  text-transform: uppercase;
  letter-spacing: 1px;
}

.nav-link::before {
  content: '';
  position: absolute;
  width: 0;
  height: 3px;
  bottom: -5px;
  left: 0;
  background: var(--tavern-gold);
  box-shadow: 0 0 5px rgba(255, 215, 0, 0.5);
  transition: width 0.3s ease;
}

.nav-link:hover::before {
  width: 100%;
}

.nav-link:hover {
  color: var(--tavern-gold);
  text-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
}

.main-content {
  flex: 1;
  width: 95%;
  margin: 0 auto;
  padding: 2rem 0;
}

.app-footer {
  background: rgba(26, 26, 26, 0.8);
  color: var(--tavern-light);
  text-align: center;
  padding: 2rem;
  margin-top: auto;
  font-size: 0.95rem;
  border-top: 2px solid var(--tavern-border);
  width: 95%;
  margin-left: auto;
  margin-right: auto;
}

@media (max-width: 1024px) {
  .header-content {
    padding: 0;
  }

  .main-content {
    padding: 2rem 0;
  }

  .app-footer {
    padding: 1.5rem 2rem;
  }

  .app-nav {
    gap: 2rem;
  }

  .nav-link {
    font-size: 1rem;
  }

  .app-title {
    font-size: 1.8rem;
  }
}
</style>
