<template>
  <div>
    <b-container fluid>
        <h2>Edit Book</h2>
        <b-row>
          <b-col class="lib-header">Enter the following details</b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Id</b-col>

          <b-col cols="auto">
            {{item.id}}
          </b-col>

        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Author</b-col>
          <b-col cols="auto">
            <b-form-input type="text" v-model="item.author"></b-form-input>
          </b-col>
        </b-row>
        
        <b-row>
          <b-col class="lib-header" cols="3">Page Number</b-col>
          <b-col cols="auto">
            <b-form-input type="text" v-model="item.pageNumber"></b-form-input>
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
            <b-form-input type='date' v-model="item.releaseDate"></b-form-input>
          </b-col>
        </b-row>

        <b-row>
          <b-col class="lib-header" cols="3">Archive</b-col>
          <b-col>
            <b-button @click="isArchive = !isArchive" v-model="isArchive" variant="warning">Archived</b-button>
          </b-col>
        </b-row>

        <div>
          <b-button v-on:click="editBook(item.id, item.author, item.pageNumber, item.title, item.description, item.releaseDate, isArchive)" variant="outline-success">Update Item</b-button>
          <b-button v-on:click="returnPrevious()" variant="outline-warning">Cancel</b-button>
        </div>
    </b-container>
  </div>
</template>

<script src="./editBook.js">
</script>