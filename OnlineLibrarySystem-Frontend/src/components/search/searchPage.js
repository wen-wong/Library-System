import { AXIOS } from '../../router/http.js'

export default {
    name: 'item',
    data() {
        return{

            value:"",
            librarian: "",
            books: [],
            movies: [],
            albums: [],
            newspapers: []
            
        }
    },

    async mounted() {
        this.librarian = localStorage.getItem("userType") == "HeadLibrarian" || localStorage.getItem("userType") == "Librarian"
    },

    methods: {
        getAllBooksByTitle(value){
            AXIOS.get('/book/title?title='+value)
            .then(response => {
                this.books = response.data
                console.log(value)
            })
        },

        getAllMoviesByTitle(value){
            AXIOS.get('/movie/title?title='+value)
            .then(response => {
                this.movies = response.data
                console.log(value)
            })
        },

        getAllMusicAlbumsByTitle(value){
            AXIOS.get('/musicalbum/title?title='+value)
            .then(response => {
                this.albums = response.data
                console.log(value)
            })
        },

        getAllNewspapersByTitle(value){
            AXIOS.get('/newspaper/title?title='+value)
            .then(response => {
                this.newspapers = response.data
                console.log(value)
            })
        },

        selectBook(book) {
            console.log(book.id)
            localStorage.setItem("itemId", book.id)
            localStorage.setItem("itemType", "Book")
            window.location.href = '/#/viewBook'
        },

        selectMovie(movie) {
            console.log(movie.id)
            localStorage.setItem("itemId", movie.id)
            localStorage.setItem("itemType", "Movie")
            window.location.href = '/#/viewMovie'
        },

        selectAlbum(album) {
            console.log(album.id)
            localStorage.setItem("itemId", album.id)
            localStorage.setItem("itemType", "Album")
            window.location.href = '/#/viewAlbum'
        },

        selectNewspaper(newspaper) {
            console.log(newspaper.id)
            localStorage.setItem("itemId", newspaper.id)
            localStorage.setItem("itemType", "Newspaper")
            window.location.href = '/#/viewNewspaper'
        },

        

        editBook(book) {
            
            localStorage.setItem("itemId", book.id)
            localStorage.setItem("itemType", "Book")
            window.location.href = '/#/editBook'
        },

        editMovie(movie){
            
            AXIOS.get('/libraryitem/'+movie.id)
            .then(response => {

                this.movie= response.data
                localStorage.setItem("itemId", movie.id)
                localStorage.setItem("itemType", "Movie")
                window.location.href = '/#/EditMovie'
       
            console.log("edit movie")
        })
            
        },

        editAlbum(album){
            
            AXIOS.get('/libraryitem/'+album.id)
            .then(response => {

                this.album= response.data
                localStorage.setItem("itemId", album.id)
                localStorage.setItem("itemType", "Album")
                window.location.href = '/#/EditMusicAlbum'
       
            console.log("edit album")
        })},

        editNewspaper(newspaper){
            
            AXIOS.get('/libraryitem/'+newspaper.id)
            .then(response => {

                this.newspaper= response.data
                localStorage.setItem("itemId", newspaper.id)
                localStorage.setItem("itemType", "Newspaper")
                window.location.href = '/#/EditNewspaper'
       
            console.log("edit newspaper")
        })
            
        },

        removeBook(book){
            console.log(book.id)
            AXIOS.delete('/'+book.id+'/remove')
            console.log('item was removed')
        },

        removeMovie(movie){
            AXIOS.delete('/'+movie.id+'/remove')
            console.log('item was removed')
        },

        removeAlbum(album){
            AXIOS.delete('/'+album.id+'/remove')
            console.log('item was removed')
        },

        removeNewspaper(newspaper){
            AXIOS.delete('/'+newspaper.id+'/remove')
            console.log('item was removed')
        }
    }
}
