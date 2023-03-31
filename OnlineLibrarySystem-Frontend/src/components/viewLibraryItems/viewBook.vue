<template>
    <div>
        <b-container fluid>
            <h2>{{ item.title }}</h2>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">ID</b-col>
                <b-col class="lib-text" cols="auto">{{ item.id}}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Author</b-col>
                <b-col class="lib-text" cols="auto">{{ item.author}}</b-col>
            </b-row>
                <b-row align-v="start">
                <b-col class="lib-header" cols="3">Number of Pages</b-col>
                <b-col  class="lib-text" cols="auto">{{ item.pageNumber}}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Description</b-col>
                <b-col class="lib-text" cols="auto">{{ item.description }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3"> Release Date</b-col>
                <b-col class="lib-text" cols="auto">{{ item.releaseDate }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Archived</b-col>
                <b-col class="lib-text" cols="auto">{{ item.isArchive }}</b-col>
            </b-row>
            <b-row align-v="start">
                <b-col>
                    <b-button v-on:click="addItemToCart(item.id)">Add To Cart</b-button>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script src="./Book.js">
</script>