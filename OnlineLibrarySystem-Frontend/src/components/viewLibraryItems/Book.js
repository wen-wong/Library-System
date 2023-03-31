import { AXIOS } from '../../router/http.js'

export default {
  name: "Book",
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
    
    addItemToCart(itemId){
        console.log(" Add item button pressed")
            AXIOS.post('/add/?clientid='+localStorage.getItem('userId')+'&itemid='+itemId+'&currentdate='+ new Date().toISOString().substring(0, 10))
            .then(response => {
                console.log(response.data)
                window.history.back()
            })
    }

  }
}