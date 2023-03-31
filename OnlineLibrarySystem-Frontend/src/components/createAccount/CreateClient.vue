<template>
  <b-container fluid>
    <h2 v-if=isEmployee>Create Client Account</h2>
    <h2 v-if=!isEmployee>Create Account</h2>

    <b-row>
      <b-col class="lib-header" cols="3">Name</b-col>
      <b-col cols="auto">
        <b-form-input v-model="clientName" placeholder="Enter your name" required></b-form-input>
      </b-col>
    </b-row>
        
    <b-row>
      <b-col class="lib-header" cols="3">Password</b-col>
      <b-col cols="auto">
        <b-form-input v-model="clientPassword" placeholder="Set your password" required></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="lib-header" cols="3">Address</b-col>
      <b-col cols="auto">
        <b-form-input v-model="clientAddress" placeholder="Enter your address" required></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="lib-header" cols="3">Phone number</b-col>
      <b-col cols="auto">
        <b-form-input v-model="clientPhonenumber" type='tel' placeholder="Enter your phonenumber" required></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="lib-header" cols="3">Email</b-col>
      <b-col cols="auto">
        <b-form-input v-model="clientEmail" type='email' placeholder="example@mail.ca" required></b-form-input>
      </b-col>
    </b-row>

    <b-row v-if="isEmployee"> 
      <b-col class="lib-header" cols="3">IsResident</b-col>
      <b-col cols="auto">
        <b-form-input v-model="clientIsResident" placeholder="true or false" required></b-form-input>
      </b-col>
    </b-row>

    <div>
      <b-button variant="outline-success" v-on:click="createClientAccount(clientName, clientPassword, clientAddress, clientPhonenumber, clientEmail, clientIsResident )">Confirm</b-button>
      <b-button variant="outline-danger" v-on:click="returnPrevious()">Cancel</b-button>
    </div>
  </b-container>
</template>

<script src='./CreateClient.js'>
</script>