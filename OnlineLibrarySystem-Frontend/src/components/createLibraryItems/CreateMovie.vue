<template>
    <b-container fluid>
        <h2>Add Movie</h2>
        <b-row>
          <b-col class="lib-header">Enter the following details</b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-director">Director:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-director" v-model="director" placeholder="Enter the director" required></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header">
            <label for="input-movieLength">Movie Length:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-movieLength" v-model="movieLength" placeholder="Enter the length of the movie in minutes" required></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-title">Title:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-title" v-model="title" placeholder="Enter the title" required></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-description"> Description:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-description" v-model="description" placeholder="Enter the description" required></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-releaseData">Release Date:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-releaseDate" v-model="releaseDate" type='date' placeholder="yyyy-mm-dd" required></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-isArchive"> Archive:</label>
          </b-col>
          <b-col sm="10">
         <b-button @click="isArchive = !isArchive" v-model="isArchive" variant="warning">Archived</b-button>
        </b-col>
        </b-row>

        <div>
          <b-button v-on:click="createMovie(director, movieLength, title, description, releaseDate, isArchive)" variant="outline-success">Add Library Item</b-button>
          <b-button variant="outline-danger">Cancel</b-button>
        </div>
    </b-container>
</template>

<script src="./CreateMovie.js">
</script>
