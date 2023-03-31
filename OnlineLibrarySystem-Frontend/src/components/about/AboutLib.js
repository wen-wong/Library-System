import { AXIOS } from '../../router/http.js'

export default {
  name: "AboutHL",
  data() {
    return {
      librarian: '',
      isHL: false
    }
  },
  async mounted() {

    this.verifyHeadLibrarian()

    //getting the Lib and setting it to the data 'Librarian'
    if (this.isHL) {
      await AXIOS.get("/accounts/employees/" + localStorage.getItem('targetId'))
          .then(response => {
          console.log(response.data)
          this.librarian = response.data
      })
      .catch(e => {
          this.errorClient = e
      });
    }

    else if (!this.isHL) {
      localStorage.setItem("targetId", "")
      localStorage.setItem("targetType", "")

      await AXIOS.get("/accounts/employees/" + localStorage.getItem('userId'))
          .then(response => {
          console.log(response.data)
          this.librarian = response.data
      })
      .catch(e => {
          this.errorClient = e
      });
  }
  },
  methods: {
    
    goToEdit(lib) {
      console.log("go to edit clicked !")
      if (this.isHL) {
        localStorage.setItem("targetId", lib.id)
      }
      window.location.href = '/#/editLib';      
    },

    goToAccountsLib() {
        console.log("go to accountsLib pressed by Lib")
        window.location.href = '/#/accountsLib'
    },

    verifyHeadLibrarian() {
      if (localStorage.getItem('userType') == 'HeadLibrarian') {
        console.log('HeadLibrarian is logged in and wants to create a Client acc')
        this.isHL = true
      }
    }

  }
}