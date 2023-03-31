<template>
  <div>
    <b-container fluid>
        <h2>Edit Music Album</h2>
        <b-row>
          <b-col class="lib-header">Enter the following details</b-col>
        </b-row>
        <b-row>
          <b-col class="lib-header" cols="3">Id</b-col>

           <b-col cols="auto">
            {{ item.id }}
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header" cols="3">Artist</b-col>
          <b-col cols="auto">
            <b-form-input type="text" v-model="item.artist"></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header" cols="3">Genre</b-col>
          <b-col cols="auto">
            <b-form-input type="text" v-model="item.genre"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Title</b-col>
          <b-col cols="auto">
            <b-form-input type="text" v-model="item.title"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Description</b-col>
          <b-col cols="auto">
            <b-form-input type="text" v-model="item.description"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Release Date</b-col>
          <b-col cols="auto">
            <b-form-input type="date" v-model="item.releaseDate"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Archive</b-col>
          <b-col cols="auto">
          <b-button @click="isArchive = !isArchive" v-model="isArchive" variant="warning">Archived</b-button>
        </b-col>
        </b-row>

        <b-row>
          <b-button v-on:click="editAlbum(item.id, item.artist, item.genre, item.title, item.description, item.releaseDate, isArchive)" variant="outline-success">Update Item</b-button>
          <b-button v-on:click="returnPrevious()" variant="outline-warning">Cancel</b-button>
        </b-row>
    </b-container>
  </div>
</template>

<script src="./editAlbum.js">
</script>
