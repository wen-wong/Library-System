import { AXIOS } from '../../router/http.js'

export default {
    name: 'book',
    data() {
        
        return{
            library:'',
            author:'',
            pageNumber:'',
            title:'',
            description:'',
            releaseDate:'',
            isArchive: false

        }
    },

    async mounted() {

        await AXIOS.post('/library/'.concat("library?address=1 avenue Library&phonenumber=1231231234&email=library@mail.ca"))
    
        // Initializing the library from backend
        await AXIOS.get('/library/' + 'library')
        .then(response => {
            this.library = response.data
        })
        .catch(error => {
            this.errorLibrary = error
        });
    },

    methods: {
        createBook(author, pageNumber, title, description, releaseDate, isArchive){
            console.log("create book button pressed")
            AXIOS.post('/book/library/library?author='+author+'&pageNumber='+pageNumber+'&title='+title+'&description='+description+'&releasedate='+releaseDate+'&isArchive='+isArchive)
            .then(response => {
                console.log(response.data)
                localStorage.setItem('itemId', response.data.id)
                localStorage.setItem('itemType', 'book')
                window.location.href = '/#/Search'
                
            })
            .catch(error => { 
                console.log(error)
                this.errorItem = 'Missing input'
            })
        }
    }
}