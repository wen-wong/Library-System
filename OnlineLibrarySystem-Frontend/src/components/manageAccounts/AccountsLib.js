import { AXIOS } from '../../router/http.js'

export default {
  name: 'AccountsHL',
  data() {
    return {
      
      library: ''
    }
  },

  async mounted() {

    await AXIOS.get('/library/' + localStorage.getItem('libraryName'))
    .then(response => {
        this.library = response.data
    })
    .catch(error => {
        this.errorLibrary = error
    });

  },
  methods: {

    goToCreateClientByLib: (id) => {
        console.log("go to create Client clicked by Lib!")
        window.location.href = '/#/createClient';      
    },

  }
}