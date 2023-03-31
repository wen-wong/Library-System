import { AXIOS } from '../../router/http.js'

export default {
  name: "AboutHL",
  data() {
    return {
      headLibrarian: ''
    }
  },

  async mounted() {

    localStorage.setItem("targetId", "")
    localStorage.setItem("targetType", "")

    await AXIOS.get("/accounts/employees/" + localStorage.getItem('userId'))
      .then(response => {
        console.log(response.data)
        this.headLibrarian = response.data
      })
      .catch(e => {
        this.errorClient = e
      });
  },
  methods: {

    goToEdit() {
      console.log("go to edit clicked !")
      window.location.href = '/#/editHL';      
    }

  }
}