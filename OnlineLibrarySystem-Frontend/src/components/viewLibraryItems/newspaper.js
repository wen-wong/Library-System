import { AXIOS } from '../../router/http.js'

export default {
  name: "Newspaper",
  data() {
    return {
      item: ''
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

  }
}