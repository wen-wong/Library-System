<template>
    <b-container fluid>
        <h2>Add Book</h2>
        <b-row>
          <b-col class="lib-header">Enter the following details</b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header">
            <label for="input-author">Author:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-author" v-model="author" placeholder="Enter the author" required></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header">
            <label for="input-page number">Page Number:</label>
          </b-col>
          <b-col sm="10">
            <b-form-input id="input-page number" v-model="pageNumber" placeholder="Enter the amount of pages" required></b-form-input>
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
            <label for="input-isArchive" > Archive:</label>
          </b-col>
          <b-col sm="10">

          <b-button @click="isArchive = !isArchive" v-model="isArchive" variant="warning">Archived</b-button>
         
        </b-col>
        </b-row>

        <div>
          <b-button v-on:click="createBook(author, pageNumber, title, description, releaseDate, isArchive)" variant="outline-success">Add Library Item</b-button>
          <b-button variant="outline-danger">Cancel</b-button>
        </div>
    </b-container>
</template>

<script src="./CreateBook.js">
</script>
