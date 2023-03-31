import { AXIOS } from '../../router/http.js'

export default {
  name: "editBook",
  data() {
    return {
      item: '',
      isArchive: false
    }
  },

  async mounted() {
    await AXIOS.get('/libraryitem/' + localStorage.getItem('itemId'))
      .then(response => {
        console.log(response.data)
        this.item = response.data
      })
      .catch(e => {
        this.errorClient = e 
      });
  },
  methods: {

    editBook(id, newAuthor, newPageNum, newTitle, newDescription, newReleaseDate, isArchive){
        AXIOS.put('/book/'+id+'/?libraryname=library&author='+newAuthor+'&pageNumber='+newPageNum+'&title='+newTitle+'&description='+newDescription+'&releasedate='+newReleaseDate+'&isArchive='+isArchive)
        .then(response => {
            console.log(response.data)
           
            this.item=response.data
            window.location.href = '/#/Search'
            })
    },
    
    returnPrevious() {
      window.history.back()
    }

  }
}

