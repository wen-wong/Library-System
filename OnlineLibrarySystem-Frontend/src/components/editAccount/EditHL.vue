<template>
    <b-container fluid>
        <h2>Edit Account</h2>

        <b-row>
          <b-col class="lib-header" cols="3">Name</b-col>
          <b-col cols="auto">
            <b-form-input v-model="headLibrarian.name"></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header" cols="3">Password</b-col>
          <b-col cols="auto">
            <b-form-input v-model="headLibrarian.password"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Address</b-col>
          <b-col cols="auto">
            <b-form-input v-model="headLibrarian.address"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Phone number</b-col>
          <b-col cols="auto">
            <b-form-input v-model="headLibrarian.phoneNumber" type='tel'></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Email</b-col>
          <b-col cols="auto">
            <b-form-input v-model="headLibrarian.email" type='email'></b-form-input>
          </b-col>
        </b-row>

        <div>
          <b-button variant="outline-success" v-on:click="editHLbyHL(headLibrarian.name, headLibrarian.password, headLibrarian.address, headLibrarian.phoneNumber, headLibrarian.email, typeOfEmployee)">Confirm</b-button>
          <b-button variant="outline-danger" v-on:click="returnPrevious()">Cancel</b-button>
        </div>
    </b-container>
</template>

<script src='./EditHL.js'>
</script>