import { AXIOS } from '../../router/http.js'

// var isEmployee = false
var isHL = false

if (localStorage.getItem('userType') == 'HeadLibrarian') {
  isHL = true
}

export default {
  name: 'AccountsHL',
  data() {
    return {
      
      library: '',
      isHL 
    }
  },

  async mounted() {

    await AXIOS.get('/library/library')
    .then(response => {
        this.library = response.data
    })
    .catch(error => {
        this.errorLibrary = error
    });

  },
  methods: {

    searchTargetClient(id) {
      AXIOS.post('/accounts/clients/' + "?name=targetClient" + "&address=targetAddress" + "&libraryName=" + this.library.name + "&password=targetPass" + "&phoneNumber=targetPhoneNumber" + "&email=targetEmail" + "&isResidentString=False" )
        .then(response => {
          localStorage.setItem('targetId', response.data.id)
          localStorage.setItem('targetType', 'Client')
          console.log('hardcoded the target client to see about info on, this has to be changed depending on simons implementation')
        })
        .catch(error => {
          this.errorLibrary = error
        });
      console.log('HL clicked on search target client, should take them to the about page of said target client')
      window.location.href = '/#/aboutClient'; 

    },

    goToCreateClientByHL: (id) => {
        console.log("go to create Client clicked by HL!")
        window.location.href = '/#/createClient';      
    },

    goToCreateLibByHL: (id) => {
        console.log("go to create Lib clicked by HL!")
        window.location.href = '/#/createLib'; //NEED TO CREATE THIS PAGE
    }
  }
}