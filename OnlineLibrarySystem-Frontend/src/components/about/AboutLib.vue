<template>
    <div>
        <b-container fluid>

            <h2 v-if="isHL" >About Librarian: {{ librarian.id }}</h2>
            <h2 v-if="!isHL" >About You</h2>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Name</b-col>
                <b-col class="lib-text" cols="auto">{{ librarian.name }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Password</b-col>
                <b-col class="lib-text" cols="auto">{{ librarian.password }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Address</b-col>
                <b-col class="lib-text" cols="auto">{{ librarian.address }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Phone Number</b-col>
                <b-col class="lib-text" cols="auto">{{ librarian.phoneNumber }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Email</b-col>
                <b-col class="lib-text" cols="auto">{{ librarian.email }}</b-col>
            </b-row>
            
            <b-button v-on:click="goToEdit(librarian)">Edit Account</b-button>
        </b-container>
    </div>
</template>


<script src="./AboutLib.js">
</script>