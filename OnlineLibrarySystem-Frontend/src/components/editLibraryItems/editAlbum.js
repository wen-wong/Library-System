import { AXIOS } from '../../router/http.js'

export default {
  name: "EditAlbum",
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

    editAlbum(id, newArtist, newGenre, newTitle, newDescription, newReleaseDate, isArchive){
        AXIOS.put('/musicalbum/'+id+'/?libraryname=library&artist='+newArtist+'&genre='+newGenre+'&title='+newTitle+'&description='+newDescription+'&releasedate='+newReleaseDate+'&isArchive='+isArchive)
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
