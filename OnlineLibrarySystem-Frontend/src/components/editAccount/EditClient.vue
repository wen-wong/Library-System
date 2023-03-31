<template>
    <b-container fluid>
        <h2>Edit Account</h2>

        <b-row>
          <b-col class="lib-header" cols="3">Name</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.name"></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header" cols="3">Password</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.password"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Address</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.address"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Phone number</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.phoneNumber" type='tel'></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Email</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.email" type='email'></b-form-input>
          </b-col>
        </b-row>


        <b-row v-if=isEmployee>
          <b-col class="lib-header" cols="3">Number of Flags</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.numOfFlags"></b-form-input>
          </b-col>
        </b-row>

        <b-row v-if=isEmployee>
          <b-col class="lib-header" cols="3">IsResident</b-col>
          <b-col cols="auto">
            <b-form-input v-model="client.isResident"></b-form-input>
          </b-col>
        </b-row>

        <div>
          <b-button variant="outline-success" v-if=!isEmployee v-on:click="editClientByClient(client.name, client.password, client.address, client.phoneNumber, client.email, client.numOfFlags, client.isResident)">Confirm</b-button>
          <b-button variant="outline-success" v-if=isEmployee v-on:click="editClientByEmployee(client.name, client.password, client.address, client.phoneNumber, client.email, client.numOfFlags, client.isResident)">Confirm</b-button>
          <b-button variant="outline-warning" v-on:click="returnPrevious()">Cancel</b-button>
        </div>
    </b-container>
</template>

<script src='./EditClient.js'>
</script>