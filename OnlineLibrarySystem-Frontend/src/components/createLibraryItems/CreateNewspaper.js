import { AXIOS } from '../../router/http.js'

export default {
    name: 'newspaper',
    data() {
        
        return{
            library:'',
            publisher:'',
            title:'',
            description:'',
            releaseDate:'',
            isArchive:false

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
        createNewspaper(publisher, title, description, releaseDate, isArchive){
            console.log("create newspaper button pressed")
            AXIOS.post('/newspaper/library/library?publisher='+publisher+'&title='+title+'&description='+description+'&releasedate='+releaseDate+'&isArchive='+isArchive)
            .then(response => {
                console.log(response.data)
                localStorage.setItem('itemId', response.data.id)
                localStorage.setItem('itemType', 'newspaper')
                window.location.href = '/#/Search'
                
            })
            .catch(error => { 
                console.log(error)
                this.errorItem = 'Missing input'
            })
        }
    }
}