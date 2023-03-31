import { AXIOS } from '../../router/http.js'
import Cart from '@/components/cart/Cart.vue'

export default {
  name: "LibrarianAdd",
  components: {
    Cart
  },
  data() {
    return {
      form: {
        clientId: ''
      },
      actions: [{ text: 'Select One', value: null }, 'Get Cart', 'Get Client Profile' ],
      show: true,
      loading: false,
      showCart: false,
      chosenClient: '',
      returnStatus: '',

      isHL: false,
    }
  },
  mounted() {
    if (localStorage.getItem('userType') == 'HeadLibrarian') {
      this.isHL = true
      this.actions.push ("Get Librarian Profile")
    }
    
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.loading = true
      this.chosenClient = this.form.clientId
      if(this.form.action == "Get Cart") {
        this.showCart = false;
        this.loading = true;
        this.showCart = true
        this.returnStatus = "showing cart of client " + this.chosenClient
        this.loading = false
      }
      else if(this.form.action == "Get Client Profile") {
        this.loading = true
        this.setClientTarget()
        this.returnStatus = "showing profile of client " + this.chosenClient
        this.loading = false
      }
      else if(this.form.action == "Get Librarian Profile") {
        this.loading = true
        if (this.chosenClient != localStorage.getItem('userId')) {
          this.setLibrarianTarget()
        }
        this.returnStatus = "showing profile of librarian " + this.chosenClient
        this.loading = false
      }
    },
    setClientTarget() {
      AXIOS.get('/accounts/clients/' + this.chosenClient)
      .then(response => {
        if (typeof response.data === 'string' || response.data instanceof String) {
          this.returnStatus = response.data
        }
        else{
          console.log('set targetid and go to client info')
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
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.clientId = ''
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}