import { AXIOS } from '../../router/http.js'

export default {
  name: 'EditLib',
  data() {
    return {
      
      library: '',
      librarian: '',
      typeOfEmployee: 'Librarian',
      isHL: false
  
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


    if (this.isHL) {
        await AXIOS.get("/accounts/employees/" + localStorage.getItem('targetId'))
        .then(response => {
          console.log(response.data)
          this.librarian = response.data
        })
        .catch(e => {
          this.errorClient = e 
          // console.log(e)
        });
    }

    //getting the Lib and setting it to the data 'librarian'
    else if (!this.isHL) {
        await AXIOS.get("/accounts/employees/" + localStorage.getItem('userId'))
        .then(response => {
          console.log(response.data)
          this.librarian = response.data
        })
        .catch(e => {
          this.errorClient = e
          // console.log(e)
        });
    }
  },
  methods: {

    editLib(newLibName, newLibPassword, newLibAddress, newLibPhonenumber, newLibEmail, typeOfEmployee) {
        console.log("edit Lib button pressed by Lib")

        // create client assuming only one library exists, this is for client creates own client acc
        if (this.isHL) {
            AXIOS.put("/accounts/employees/" + localStorage.getItem('targetId') + "?name=" + newLibName + "&address=" + newLibAddress + "&libraryName=" + this.library.name + "&password=" + newLibPassword + "&phoneNumber=" + newLibPhonenumber + "&email=" + newLibEmail + "&typeOfEmployeeString=" + typeOfEmployee )
            .then(response => {
            console.log(response.data)
            console.log('Lib edited by HL')
            window.location.href = '/#/aboutLib'
            })
            .catch(e => {
            this.errorClient = e 
            console.log(e)
            })
        }

        else if (!this.isHL) {
            AXIOS.put("/accounts/employees/" + localStorage.getItem('userId') + "?name=" + newLibName + "&address=" + newLibAddress + "&libraryName=" + this.library.name + "&password=" + newLibPassword + "&phoneNumber=" + newLibPhonenumber + "&email=" + newLibEmail + "&typeOfEmployeeString=" + typeOfEmployee )
            .then(response => {
            console.log(response.data)
            console.log('Lib edited by Lib')
            window.location.href = '/#/aboutLib'
            })
            .catch(e => {
            this.errorClient = e //WHEN DO THEY USE ERRORPCLIENT IN THE TEMPLATE
            console.log(e)
            })
        }
      
    },

    returnPrevious() {
      window.history.back()
    },

    verifyHeadLibrarian() {
      this.isHL = localStorage.getItem('userType') == 'HeadLibrarian'
    }

  }
}