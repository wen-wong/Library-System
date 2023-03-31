import { AXIOS } from '../../router/http.js'

export default {
  name: 'EditClient',
  data() {
    return {
      
      library: '',
      client: '',
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


    //getting the client and setting it to the data 'client'
    this.isEmployee ? await AXIOS.get('/accounts/clients/' + localStorage.getItem('targetId'))
    .then(response => {
      console.log(response.data)
      this.client = response.data
    })
    .catch(e => {
      this.errorClient = e
    }) :
    await AXIOS.get('/accounts/clients/' + localStorage.getItem('userId'))
      .then(response => {
        console.log(response.data)
        this.client = response.data
      })
      .catch(e => {
        this.errorClient = e
      })
  },
  methods: {

    editClientByClient(newClientName, newClientPassword, newClientAddress, newClientPhonenumber, newClientEmail, newNumOfFLags, newClientIsResident) {
      console.log("edit client button pressed by client")

      // create client assuming only one library exists, this is for client creates own client acc
      AXIOS.put('/accounts/clients/' + localStorage.getItem('userId') + "?name=" + newClientName + "&address=" + newClientAddress + "&libraryName=" + this.library.name + "&password=" + newClientPassword + "&phoneNumber=" + newClientPhonenumber + "&email=" + newClientEmail + "&numOfFlagsString=" + newNumOfFLags + "&isResidentString=" + newClientIsResident )
        .then(response => {
          console.log(response.data)
          window.location.href = '/#/aboutClient'
        })
        .catch(e => {
          this.errorClient = e
          console.log(e)
        })
    },

    editClientByEmployee(newClientName, newClientPassword, newClientAddress, newClientPhonenumber, newClientEmail, newNumOfFLags, newClientIsResident) {
      console.log("edit client button pressed by employee")

      // create client assuming only one library exists, this is for client creates own client acc
      AXIOS.put('/accounts/clients/' + localStorage.getItem('targetId') + "?name=" + newClientName + "&address=" + newClientAddress + "&libraryName=" + this.library.name + "&password=" + newClientPassword + "&phoneNumber=" + newClientPhonenumber + "&email=" + newClientEmail + "&numOfFlagsString=" + newNumOfFLags + "&isResidentString=" + newClientIsResident )
        .then(response => {
          console.log(response.data)
          window.location.href = '/#/aboutClient'
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