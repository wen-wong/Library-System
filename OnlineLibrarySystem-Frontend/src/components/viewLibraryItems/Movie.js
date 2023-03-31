import { AXIOS } from '../../router/http.js'

export default {
  name: "Movie",
  data() {
    return {
      item: ''
    }
  },

  async mounted() {
    // await AXIOS.post('/accounts/clients/'.concat("?name=Jack&address=12 jacky str&libraryName=library&password=pass&phoneNumber=1234567891&email=jack@mail.ca&isResidentString=false")).then(res => localStorage.setItem('userId', res.data.id))
    // await AXIOS.post("/add/?clientid=" + localStorage.getItem('userId') + "&itemid=" + localStorage.getItem('itemId') + "&currentdate=2021-11-10").then(res => console.log(res.data))
 
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