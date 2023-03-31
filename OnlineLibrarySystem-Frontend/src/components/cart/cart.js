import { AXIOS } from '../../router/http.js'
import { BIcon, BIconTrash} from 'bootstrap-vue'
/**
 * @Author: Simon Wang
 * */

export default {
  props: ["view","clientId"],
  name: "Cart",
  components: {
    BIcon,
    BIconTrash
  },
  data() {
    return {
      form: {
        libraryItemId: ''
      },
      fields: [
        // A virtual column that doesn't exist in items
        'Index',
        // A regular column
        'Title',
        // Status, found in inUseSlot object
        'Status',
        // An action based on status
        'Action',
        // Start Date, found in inUseSlot object
        'StartDate',
        // End Date, found in inUseSlot object
        'EndDate',
        // delete button
        'Delete'
      ],
      inUseSlots: [],
      givenClientId: this.clientId,
      returnStatus: '',
      clientExists: false
    };
  },
  async mounted() {
    // // create librar
    /**
     *Before displaying cart, check if client exists, if not, show message
     * */
    console.log(this.givenClientId)
    this.checkClientId()
    await AXIOS.get('/client/cartInUseSlot/'+ this.givenClientId)
      .then(r => {
        if(typeof r.data === 'string' || r.data instanceof String) {
          this.clientExists = false
        }
        else {
          // JSON responses are automatically parsed.
          this.clientExists = true
          this.inUseSlots = r.data
        }
      })
  },
  methods: {
    remove(libraryItemIndex) {
      this.checkClientId()
      let libraryItemId = this.inUseSlots[libraryItemIndex].libraryItem.id
      AXIOS.post('/remove/?clientid=' + this.givenClientId + "&itemid=" + libraryItemId);
      this.inUseSlots.splice(libraryItemIndex,1)
    },
    reserve(libraryItemIndex) {
      this.checkClientId()
      let libraryItemId = this.inUseSlots[libraryItemIndex].libraryItem.id
        AXIOS.post('/client/cart/reserve/?clientid=' + this.givenClientId + "&itemid=" + libraryItemId + "&currentDate=" + new Date().toISOString().substring(0, 10)).then(r => {
          this.inUseSlots.splice(libraryItemIndex, 1, r.data)
        });
    },
    renew(libraryItemIndex) {
      this.checkClientId()
      let libraryItemId = this.inUseSlots[libraryItemIndex].libraryItem.id
      AXIOS.post('/renew/?clientid=' + this.givenClientId + "&itemid=" + libraryItemId + "&currentDate=" + new Date().toISOString().substring(0, 10)).then(r => {
        console.log(r.data)
        this.inUseSlots.splice(libraryItemIndex, 1, r.data)
      });
    },
    /**
     *
     * @param event
     * add libaryItem to client cart
     */
    onSubmit(event) {
      this.checkClientId()
      event.preventDefault()
      if(this.inUseSlots.length == 10) {
        this.returnStatus = "cannot haven more than 10 items in cart"
        return
      }
      AXIOS.post("/add/?clientid=" + this.givenClientId + "&itemid=" + this.form.libraryItemId +"&currentDate=" + "&currentdate="+new Date().toISOString().substring(0, 10)).then(r => {
        if (typeof r.data === 'string' || r.data instanceof String) {
          this.returnStatus = r.data
        } else {
          this.returnStatus = "Add Succesful"
          this.inUseSlots.push(r.data)
        }
      })
    },
    /**
     * limited to librarians
     * @param libraryItemIndex
     */
    book(libraryItemIndex) {
      this.checkClientId()
      let libraryItemId = this.inUseSlots[libraryItemIndex].libraryItem.id
      AXIOS.post('/client/cart/checkout/?clientid=' + this.givenClientId + "&itemid=" + libraryItemId + "&currentDate=" + new Date().toISOString().substring(0, 10)).then(r => {
        console.log(r.data)
        this.inUseSlots.splice(libraryItemIndex, 1, r.data)
      });
    },
    /**
     *
     * @param libraryItemId
     * limited to librarians
     */
    returnItem(libraryItemId) {
      let indexToDelete = this.inUseSlots.findIndex((x) => x.libraryItem.id == libraryItemId)
      if(indexToDelete == -1) {
        this.returnStatus = "item not found"
        return
      }
      else {
        AXIOS.post("/client/cart/return/?itemid=" + libraryItemId + "&currentDate="+new Date().toISOString().substring(0, 10)).then(r => {
          if (typeof r.data === 'string' || r.data instanceof String) {
            this.returnStatus = r.data
          } else {
            this.returnStatus = "return succesful"
            this.inUseSlots.pop(indexToDelete)
          }
        })
      }
    },
    /**
     * if accessed from manageAccounts, a clientId will be given, otherwise it is taken from localStorage
     */
    checkClientId() {
      if(!this.givenClientId) {
        this.givenClientId = localStorage.getItem("userId")
      }
    },
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.libraryItemId=''
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }
  }
}
