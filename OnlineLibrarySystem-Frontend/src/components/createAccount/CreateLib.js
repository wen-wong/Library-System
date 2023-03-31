import { AXIOS } from '../../router/http.js'

export default {
  name: 'CreateLib',
  data() {
    return {
      library: '',
      libName: '',
      libPassword: '',
      libAddress: '',
      libPhonenumber: '',
      libEmail: '',
      typeOfEmployee: 'Librarian',
      isHeadLibrarian: false
    }
  },

  async mounted() {

    this.verifyHeadLibrarian()

    // Initializing the library from backend
    await AXIOS.get('/library/library')
    .then(response => {
        this.library = response.data
    })
    .catch(error => {
        this.errorLibrary = error
    });

  },
  methods: {
    createLibAccount(libName, libPassword, libAddress, libPhonenumber, libEmail, typeOfEmployee) {
      console.log("create librarian button pressed")

      // create client assuming only one library exists, this is for client creates own client acc
      AXIOS.post("/accounts/employees/" + "?name=" + libName + "&address=" + libAddress + "&libraryName=" + this.library.name + "&password=" + libPassword + "&phoneNumber=" + libPhonenumber + "&email=" + libEmail + "&typeOfEmployeeString=" + typeOfEmployee )
        .then(response => {
          console.log(response.data)
          if (this.isHeadLibrarian) {
              console.log('the only account with access (logged in) is HL and created a librarian account')
              localStorage.setItem('targetId', response.data.id)
              localStorage.setItem('targetType', 'Librarian')
              window.location.href = '/#/aboutLib'
              window.location.reload()
          }
        })
        .catch(e => {
          this.errorClient = e
          console.log(e)
        })
    },
    
    returnPrevious() {
      window.history.back()
    },

    verifyHeadLibrarian() {
      this.isHeadLibrarian = localStorage.getItem('userType') == 'HeadLibrarian'
    }
 
  }
}