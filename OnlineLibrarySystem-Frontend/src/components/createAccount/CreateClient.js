import { AXIOS } from '../../router/http.js'

export default {
  name: 'CreateClient',
  data() {
    return {
      
      library: '',
      clientName: '',
      clientPassword: '',
      clientAddress: '',
      clientPhonenumber: '',
      clientEmail: '',
      clientIsResident: false,
      isEmployee: false,
      isHL: false
  
    }
  },

  async mounted() {
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

    createClientAccount(clientName, clientPassword, clientAddress, clientPhonenumber, clientEmail, clientIsResident) {
      console.log("create client button pressed")

      // create client assuming only one library exists, this is for client creates own client acc
      AXIOS.post('/accounts/clients/' + "?name=" + clientName + "&address=" + clientAddress + "&libraryName=" + this.library.name + "&password=" + clientPassword + "&phoneNumber=" + clientPhonenumber + "&email=" + clientEmail + "&isResidentString=" + clientIsResident )
        .then(response => {
          console.log(response.data)
          // this.client = response.data,

          if (localStorage.getItem('userType') == 'Librarian' || localStorage.getItem('userType') == 'HeadLibrarian') {
              console.log('the logged in user is an employee and created a client account')
              localStorage.setItem('targetId', response.data.id)
              localStorage.setItem('targetType', 'Client')
              window.location.href = '/#/aboutClient'
              window.location.reload()
          }

          else if (localStorage.getItem('userType') == 'Client') {
            console.log('client created a new account for themselves')
            localStorage.setItem('userId', response.data.id)
            localStorage.setItem('userType', 'Client')
            window.location.href = '/#/aboutClient'
            window.location.reload()
          }
          
        })
        .catch(e => {
          this.errorClient = e //WHEN DO THEY USE ERRORPCLIENT IN THE TEMPLATE
          console.log(e)
        })
    },

    returnPrevious() {
      window.history.back()
    },
    
    verifyEmployee() {
      this.isEmployee = localStorage.getItem('userType') == 'Librarian'
      this.isHL = localStorage.getItem('userType') == 'HeadLibrarian'
    }
  }
}