<template>

  <div>
    <h2>Accounts</h2>
    <div>
      <b-button id="CreateClient" variant="primary" v-on:click="goToCreateClientByHL()">Create Client</b-button>
      <b-button id="Create Librarian" variant="primary" v-if=isHL v-on:click="goToCreateLibByHL()">Create Employee</b-button>
    </div>

      <b-button id="Search" variant="primary" v-on:click='SearchTargetClient(2)'>SearchTargetClient</b-button> -->
  </div>
</template>

<script src='./AccountsHL.js'>
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