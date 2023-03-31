<template>
  <b-container fluid>
    <h2 v-if=isHeadLibrarian>Create Librarian Account</h2>
    <h2 v-if=!isHeadLibrarian>Create Account</h2>

    <b-row>
      <b-col class="lib-header" cols="3">Name</b-col>
      <b-col cols="auto">
        <b-form-input v-model="libName" placeholder="Enter librarian's name" required></b-form-input>
      </b-col>
    </b-row>
        
    <b-row>
      <b-col class="lib-header" cols="3">Password</b-col>
      <b-col cols="auto">
        <b-form-input v-model="libPassword" placeholder="Set librarian's password" required></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="lib-header" cols="3">Address</b-col>
      <b-col cols="auto">
        <b-form-input v-model="libAddress" placeholder="Enter librarian's address" required></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="lib-header" cols="3">Phone number</b-col>
      <b-col cols="auto">
        <b-form-input v-model="libPhonenumber" type='tel' placeholder="Enter librarian's phonenumber" required></b-form-input>
      </b-col>
    </b-row>

    <b-row>
      <b-col class="lib-header" cols="3">Email</b-col>
      <b-col cols="auto">
        <b-form-input v-model="libEmail" type='email' placeholder="Enter librarian's email" required></b-form-input>
      </b-col>
    </b-row>

    <div>
      <b-button variant="outline-success" v-on:click="createLibAccount(libName, libPassword, libAddress, libPhonenumber, libEmail, typeOfEmployee)">Confirm</b-button>
      <b-button variant="outline-danger" v-on:click="returnPrevious()">Cancel</b-button>
    </div>
  </b-container>
</template>

<script src='./CreateLib.js'>
</script>