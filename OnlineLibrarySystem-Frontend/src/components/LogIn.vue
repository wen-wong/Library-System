<template>
  <b-container id="login-page" fluid>
    <h2>Login</h2>
    <b-row align-v="start">
      <b-col class="lib-header" cols="2">Id number</b-col>
      <b-col cols="auto">
        <b-form-input type="text" v-model="form.loginIdNum" placeholder="Enter id number" required></b-form-input>
      </b-col>
    </b-row>
    <b-row align-v="start">
      <b-col class="lib-header" cols="2">Password</b-col>
      <b-col cols="auto">
        <b-form-input type="text" v-model="form.loginPass" placeholder="Enter password" required></b-form-input>
      </b-col>
    </b-row>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <label class="input-group-text">Account Type</label>
      </div>
      <select class="custom-select" v-model="form.selectedLoginType">
        <option selected>Choose...</option>
          <option value="Client">Client</option>
          <option value="Lib">Librarian</option>
          <option value="HL">Head Librarian</option>
      </select>
    </div>
    <b-row align-v="start">
      <b-col>
        <b-button variant="primary" v-on:click="login(form.loginIdNum, form.loginPass, form.selectedLoginType)">Login</b-button>
      </b-col>
    </b-row>
    <b-row align-v="start">
      <b-button variant="link" v-on:click="goToCreate()">Don't have an account? Sign up</b-button>
    </b-row>
  </b-container>
</template>

<script src='./login.js'>
</script>