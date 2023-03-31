<template>

  <div>
    <h2>Accounts</h2>
    <div>
      <b-button id="CreateClient" variant="primary" v-on:click="goToCreateClientByLib()">Create Client</b-button>
    </div>
  </div>
</template>

<script src='./AccountsLib.js'>
</script>

<style scoped>

  button.Sign-up {
    color: blueviolet;
  }

    #button {
    color: blueviolet;
    background-color: brown;
  }

</style>