<template>
    <div>
      <h1>Home Page</h1>
      <template v-if="userToken">
        <p>Welcome to the home page!</p>
      </template>
      <template v-else>
        <p>Please login to access this page.</p>
      </template>
    </div>
  </template>
  
  <script>
  export default {
    computed: {
      userToken() {
        return this.$store.state.userToken;
      },
    },
  };
  </script>
  