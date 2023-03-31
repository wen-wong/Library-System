<template>
    <div>
    <b-container fluid>
        <h2>Search</h2>
        <b-row class="lib-header">
            <b-col>Enter a title to find an item</b-col>
        </b-row>
        <b-row class="lib-header">
            <b-col>
                <input v-model="value" type="text" class="form-control">
            </b-col>
        </b-row>
        <b-row class="lib-header">
            <b-col>
                <b-button v-on:click="getAllBooksByTitle(value); getAllMoviesByTitle(value); getAllMusicAlbumsByTitle(value); getAllNewspapersByTitle(value)" variant="outline-primary">Search</b-button>
            </b-col>
        </b-row>
        <b-row>
            <b-table striped hover :books="books"></b-table>
            <table cellpadding="10">
                <tr
                    v-for="book in books"
                    :key="book.id"
                    style="cursor: pointer"
                    class="parent">
                    <span v-on:click="selectBook(book)">
                    <td>
                        Book
                    </td>

                    <td>
                    Id: {{ book.id }}
                    </td>

                    <td> Title: {{book.title}}</td>

                    <td >
                    Author: {{book.author}}
                    </td>

                    <td >
                    Page Number: {{book.pageNumber}}
                    </td>
                </span>
            <b-button v-if="librarian" class="child" v-on:click="removeBook(book)" variant="outline-danger">Remove</b-button>
            <b-button v-if="librarian" class="child" v-on:click="editBook(book)" variant="outline-warning">Edit</b-button>
          </tr>
        </table>
        <b-table striped hover :movies="movies"></b-table>
            <table cellpadding="10">
            <tr
                v-for="movie in movies"
                :key="movie.id"
                style="cursor: pointer"
                class="parent">
            <span v-on:click="selectMovie(movie)">

                <td>
                    Movie
                </td>

                <td>
                    Id: {{ movie.id }}
                </td>

                <td> Title: {{movie.title}}</td>

                <td >
                Director: {{movie.director}}
                </td>

                <td >
                Movie Length: {{movie.movieLength}}
                </td>
            </span>

            <b-button v-if="librarian" class="child" v-on:click="removeMovie(movie)" variant="outline-danger">Remove</b-button>
            <b-button v-if="librarian" class="child" v-on:click="editMovie(movie)" variant="outline-warning">Edit</b-button>
            </tr>
            </table>

        <b-table striped hover :albums="albums"></b-table>
            <table cellpadding="10">
                <tr
                    v-for="album in albums"
                    :key="album.id"
                    style="cursor: pointer"
                    class="parent">
                    <span v-on:click="selectAlbum(album)">

                    <td>
                         Music Album
                    </td>

                    <td>
                    Id: {{ album.id }}
                    </td>

                    <td >
                    Artist: {{album.artist}}
                    
                    </td>

                    <td>
                        Genre: {{album.genre}}
                     </td>

                    <td> Title: {{album.title}}</td>
                    </span>

                    <b-button v-if="librarian" class="child" v-on:click="removeAlbum(album)" variant="outline-danger">Remove</b-button>
                    <b-button v-if="librarian" class="child" v-on:click="editAlbum(album)" variant="outline-warning">Edit</b-button>
                </tr>
                </table>

                 <b-table striped hover :newspapers="newspapers"></b-table>
                    <table cellpadding="10">
                <tr
                    v-for="newspaper in newspapers"
                    :key="newspaper.id"
                    style="cursor: pointer"
                    class="parent"
                    >
                    <span v-on:click="selectNewspaper(newspaper)">

                     <td>
                        Newspaper
                    </td>

                    <td>
                        Id: {{ newspaper.id }}
                    </td>

                    <td >
                    Publisher: {{newspaper.publisher}}
                    </td>

                    <td> Title: {{newspaper.title}}</td>
                    </span>

                    <b-button v-if="librarian" class="child" v-on:click="removeNewspaper(newspaper)" variant="outline-danger">Remove</b-button>
                    <b-button v-if="librarian" class="child" v-on:click="editNewspaper(newspaper)" variant="outline-warning">Edit</b-button>
                </tr>
            </table>
        </b-row>
        </b-container>
    </div>
</template>

<script src="./searchPage.js">
</script>