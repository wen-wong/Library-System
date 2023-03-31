<template>
    <div>
        <b-container fluid>
            <h2>About You</h2>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">ID</b-col>
                <b-col class="lib-text" cols="auto">{{ headLibrarian.id }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Name</b-col>
                <b-col class="lib-text" cols="auto">{{ headLibrarian.name }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Password</b-col>
                <b-col class="lib-text" cols="auto">{{ headLibrarian.password }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Address</b-col>
                <b-col class="lib-text" cols="auto">{{ headLibrarian.address }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Phone Number</b-col>
                <b-col class="lib-text" cols="auto">{{ headLibrarian.phoneNumber }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Email</b-col>
                <b-col class="lib-text" cols="auto">{{ headLibrarian.email }}</b-col>
            </b-row>
            
            <b-button v-on:click="goToEdit()">Edit Account</b-button>
        </b-container>
    </div>
</template>


<script src="./AboutHL.js">
</script>