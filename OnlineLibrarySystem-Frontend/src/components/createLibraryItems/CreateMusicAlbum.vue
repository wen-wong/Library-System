<template>
    <b-container fluid>
        <h2>Add Music Album</h2>
        <b-row>
          <b-col class="lib-header">Enter the following details</b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-artist">Artist:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-artist" v-model="artist" placeholder="Enter the artist"></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header">
            <label for="input-genre"> Genre:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-genre" v-model="genre" placeholder="Enter the genre"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-title">Title:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-title" v-model="title" placeholder="Enter the title"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-description"> Description:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-description" v-model="description" placeholder="Enter the description"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-releaseData">Release Date:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-releaseDate" v-model="releaseDate" type='date' placeholder="yyyy-mm-dd"></b-form-input>
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
          <b-button v-on:click="createMusicAlbum(artist, genre, title, description, releaseDate, isArchive)" variant="outline-success">Add Library Item</b-button>
          <b-button variant="outline-danger">Cancel</b-button>
        </div>
    </b-container>
</template>

<script src="./CreateMusicAlbum.js">
</script>
