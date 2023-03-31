import { AXIOS } from '../../router/http.js'

export default {
  name: 'EditHeadLib',
  data() {
    return {
      
      library: '',
      headLibrarian: '',
      typeOfEmployee: 'HeadLibrarian',
      isEmployee: false
    }
  },

  async mounted() {

    this.verifyEmployee()

    // Initializing the library from backend
    await AXIOS.get('/library/library')
    .then(response => {
        this.library = response.data
    })
    .catch(error => {
        this.errorLibrary = error
    });


    //getting the HL and setting it to the data 'headLibrarian'
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

    editHLbyHL(newHLName, newHLPassword, newHLAddress, newHLPhonenumber, newHLEmail, typeOfEmployee) {
      console.log("edit client button pressed")

      // create client assuming only one library exists, this is for client creates own client acc
      AXIOS.put("/accounts/employees/" + localStorage.getItem('userId') + "?name=" + newHLName + "&address=" + newHLAddress + "&libraryName=" + this.library.name + "&password=" + newHLPassword + "&phoneNumber=" + newHLPhonenumber + "&email=" + newHLEmail + "&typeOfEmployeeString=" + typeOfEmployee )
        .then(response => {
          console.log(response.data)
          localStorage.setItem('userId', response.data.id) //should already be set too
          window.location.href = '/#/aboutHL'
        })
        .catch(e => {
          this.errorClient = e
          console.log(e)
        })
    },

    returnPrevious() {
      window.history.back()
    },

    verifyEmployee() {
      this.isEmployee = localStorage.getItem('userType') == 'Librarian' || localStorage.getItem('userType') == 'HeadLibrarian'
    }

  }
}