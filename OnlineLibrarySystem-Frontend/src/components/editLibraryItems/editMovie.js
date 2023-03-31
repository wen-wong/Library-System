import { AXIOS } from '../../router/http.js'

export default {
  name: "editMovie",
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

    editMovie(id, newDirector, newLength, newTitle, newDescription, newReleaseDate, isArchive){
        AXIOS.put('/movie/'+id+'/?libraryname=library&director='+newDirector+'&movieLength='+newLength+'&title='+newTitle+'&description='+newDescription+'&releasedate='+newReleaseDate+'&isArchive='+isArchive)
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
