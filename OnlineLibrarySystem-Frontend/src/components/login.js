import { AXIOS } from '../router/http.js'

  export default {
    data() {
      return {
        form: {
          loginIdNum: '',
          loginPass: '',
          selectedLoginType: ''
        }
      }
    },

    async mounted() {

      this.newUser()

      await AXIOS.get('/library/library')
        .then(response => {
            console.log('succesfully got the library')
            localStorage.setItem('libraryName', response.data.name)
        })
        .catch(error => {
            this.errorLibrary = error
        });
    },


    methods: {

      newUser() {
        localStorage.setItem("eventId", "")
        localStorage.setItem("isEventCreator", "")
        localStorage.setItem("itemId", "")
        localStorage.setItem("itemType", "")
        localStorage.setItem("length", "")
        localStorage.setItem("libraryName", "")
        localStorage.setItem("notEventCreator", "")
        localStorage.setItem("targetId", "")
        localStorage.setItem("targetType", "")
        localStorage.setItem("userId", "")
        localStorage.setItem("userType", "")
      },

      goToCreate() {
        console.log("go to create clicked !")
        localStorage.setItem("userType", "Client")
        window.location.href = '/#/createClient'
      },

      login(loginIdNum, loginPass, selectedLoginType) {
        console.log(loginIdNum)
        console.log(loginPass)
        console.log(selectedLoginType)

        if (selectedLoginType == 'Client') {
          
          console.log('recognized slected login as client, directed to correct method')
          
          AXIOS.post("/accounts/clients/login/" + "?id=" + loginIdNum + "&password=" + loginPass)
            .then(response => {
              console.log(response.data)
              localStorage.setItem('userId', response.data.id)
              localStorage.setItem('userType', 'Client')
              localStorage.setItem('targetId', response.data.id)
              localStorage.setItem('targetType', 'Client')
              window.location.href = '/#/aboutClient'
              window.location.reload()
            })
            .catch(e => {
              this.errorClient = e
              console.log(e)
            })    
        }

        else if (selectedLoginType == 'Lib') {
          
          console.log('recognized slected login as librarian, directed to correct method')
          
          AXIOS.post("/accounts/employees/login/" + "?id=" + loginIdNum + "&password=" + loginPass)
            .then(response => {
              console.log(response.data)
              localStorage.setItem('userId', response.data.id)
              localStorage.setItem('userType', 'Librarian')
              window.location.href = '/#/aboutLib'
              window.location.reload()
            })
            .catch(e => {
              this.errorClient = e
              console.log(e)
            }) 

        }

        else if (selectedLoginType == 'HL') {

          console.log('recognized slected login as head Librarian, directed to correct method')
          
          AXIOS.post("/accounts/employees/login/" + "?id=" + loginIdNum + "&password=" + loginPass)
            .then(response => {
              console.log(response.data)
              // this.client = response.data,
              localStorage.setItem('userId', response.data.id)
              localStorage.setItem('userType', 'HeadLibrarian')
              window.location.href = '/#/aboutHL'
              window.location.reload()
            })
            .catch(e => {
              this.errorClient = e
              console.log(e)
            })
        }
      }
    }
  }