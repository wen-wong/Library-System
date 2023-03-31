<template>
    <div>
        <b-container fluid>
            <h2 v-if="!isEmployee">About You</h2>
            <h2 v-if="isEmployee">About Client: {{ client.id }}</h2>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">ID</b-col>
                <b-col class="lib-text" cols="auto">{{ client.id }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Name</b-col>
                <b-col class="lib-text" cols="auto">{{ client.name }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Password</b-col>
                <b-col class="lib-text" cols="auto">{{ client.password }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Address</b-col>
                <b-col class="lib-text" cols="auto"> {{ client.address }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Phone Number</b-col>
                <b-col class="lib-text" cols="auto"> {{ client.phoneNumber }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Email</b-col>
                <b-col class="lib-text" cols="auto">{{ client.email }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Number of Flags</b-col>
                <b-col class="lib-text" cols="auto">{{ client.numOfFlags }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Resident</b-col>
                <b-col class="lib-text" cols="auto" v-if="client.isResident">Yes</b-col>
                <b-col class="lib-text" cols="auto" v-if="!client.isResident">No</b-col>
            </b-row>
            <b-button v-on:click="goToEdit()">Edit Account</b-button>
        </b-container>
    </div>
</template>


<script src="./AboutClient.js">
</script>


<style>
.client-header {
    font-weight: bold;
    padding-bottom: 1.5rem;
}
</style>