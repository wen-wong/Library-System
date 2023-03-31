<template>
    <div>
        <b-container fluid>
            <h2>{{ item.title }}</h2>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">ID</b-col>
                <b-col class="lib-text" cols="auto">{{ item.id }}</b-col>
            </b-row> 
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Publisher</b-col>
                <b-col class="lib-text" cols="auto">{{ item.publisher }}</b-col>
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
                <b-col class="lib-text" cols="auto" v-if="item.isArchive">Yes</b-col>
                <b-col class="lib-text" cols="auto" v-if="!item.isArchive">No</b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script src="./newspaper.js">
</script>