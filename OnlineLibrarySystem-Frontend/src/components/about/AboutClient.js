import { AXIOS } from '../../router/http.js'

export default {
  name: "AboutClient",
  data() {
    return {
      client: '',
      isEmployee: false
    }
  },

  async mounted() {

    this.verifyEmployee()
    if (localStorage.getItem("userType") == "HeadLibrarian" || localStorage.getItem("userType") == "Librarian") {
      await AXIOS.get('/accounts/clients/' + localStorage.getItem('targetId'))
        .then(response => {
          console.log(response.data)
          this.verifyEmployee()
          this.client = response.data
        })
        .catch(e => {
          this.errorClient = e
        }) }
    else {
      await AXIOS.get('/accounts/clients/' + localStorage.getItem('userId'))
        .then(response => {
          console.log(response.data)
          this.client = response.data
        })
        .catch(e => {
          this.errorClient = e
        })
    }
  },
  methods: {
    
    goToEdit() {
      console.log("go to edit clicked !")
      window.location.href = '/#/editClient';      
    },

    setClientTarget() {
      AXIOS.get('/accounts/clients/' + this.chosenClient)
      .then(response => {
        if (typeof response.data === 'string' || response.data instanceof String) {
          this.returnStatus = response.data
        }
        else{
          console.log('set targetid and go to client info')
          // console.log(response.data)
          localStorage.setItem("targetId", this.chosenClient)
          localStorage.setItem("targetType", "Client")
          window.location.href = '/#/aboutClient'
        }
      })
    },
    setLibrarianTarget() {
      AXIOS.get('/accounts/employees/' + this.form.clientId)
      .then(response => {
        if (typeof response.data === 'string' || response.data instanceof String) {
          this.returnStatus = response.data
        }
        else {
          console.log('set targetid and go to Librarian info')
        // console.log(response.data)
        localStorage.setItem("targetId", this.form.clientId)
        localStorage.setItem("targetType", "Librarian")
        window.location.href = '/#/aboutLib'
        }
      })
      .catch(e => {
        this.errorClient = e //WHEN DO THEY USE ERRORPCLIENT IN THE TEMPLATE
        // console.log(e)
      });
    },

    verifyEmployee() {
      this.isEmployee = localStorage.getItem("userType") == "Librarian" || localStorage.getItem("userType") == "HeadLibrarian" 
    }

  }
}