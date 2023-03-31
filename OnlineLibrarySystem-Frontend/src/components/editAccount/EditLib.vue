<template>
    <b-container fluid>
        <h2 v-if="isHL" >Edit Librarian: {{ librarian.id }}</h2>
        <h2 v-if="!isHL" >Edit Account</h2>

        <b-row>
          <b-col class="lib-header" cols="3">Name</b-col>
          <b-col cols="auto">
            <b-form-input v-model="librarian.name"></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header" cols="3">Password</b-col>
          <b-col cols="auto">
            <b-form-input v-model="librarian.password"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Address</b-col>
          <b-col cols="auto">
            <b-form-input v-model="librarian.address"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Phone number</b-col>
          <b-col cols="auto">
            <b-form-input v-model="librarian.phoneNumber" type='tel'></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Email</b-col>
          <b-col cols="auto">
            <b-form-input v-model="librarian.email" type='email'></b-form-input>
          </b-col>
        </b-row>

        <div>
          <b-button variant="outline-success" v-on:click="editLib(librarian.name, librarian.password, librarian.address, librarian.phoneNumber, librarian.email, typeOfEmployee)">Confirm</b-button>
          <b-button variant="outline-danger" v-on:click="returnPrevious()">Cancel</b-button>
        </div>
    </b-container>
</template>

<script src='./EditLib.js'>
</script>