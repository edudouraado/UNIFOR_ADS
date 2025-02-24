<template>
  <div id="app">
    <header>
      <h1>Vue Contact App</h1>
      <ContactListView />
    </header>
  </div>
</template>

<script>
import ContactListView from './components/ContactListView.vue';

export default {
  name: 'App',
  components: {
    ContactListView,
  },
};
</script>

<style>
#app {
  text-align: center;
}

header {
  background-color: #282c34;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: calc(10px + 2vmin);
  color: white;
}
</style>
