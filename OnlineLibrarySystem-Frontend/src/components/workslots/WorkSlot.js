import { AXIOS } from '../../router/http.js'

export default {
    name: 'WorkSlots',
    data() {
        return {
            library: '',
            workSlotFields: [],
            workSlots: [],
            form: {
                employee: '',
                startDate: '',
                endDate: '',
                startTime: '',
                endTime: '',
                id: ''
            },
            isHeadLibrarian : true
        }
    },

    async mounted() {
        // Set if the user is a Head Librarian
        this.verifyHeadLibrarian()
        this.isHeadLibrarian ? this.workSlotFields = ['StartDate', 'EndDate', 'StartTime', 'EndTime', 'EmployeeId', 'Delete', 'DeleteAccount'] : this.workSlotFields = ['StartDate', 'EndDate', 'StartTime', 'EndTime', 'EmployeeId']

        // Initializing the library from backend
        await AXIOS.get('/library/' + 'library')
            .then(response => {
                this.library = response.data
            })
            .catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg) 
            });
        
        // Initializing all work slots from backend
        await AXIOS.get('/workSlots/')
            .then(response => {
                this.workSlots = response.data
            })
            .catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            });
    },
    methods: {
      
        //Delete a specific Work Slot
        deleteWorkSlot(workIndex) {
            let workSlotId = this.workSlots[workIndex].employee.id
            AXIOS.delete('/deleteworkslot/' + workSlotId
            ).then(response => {
                this.workSlots.splice(workIndex, 1)
                console.log(response.data)
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
        },
        //Delete an employee account
        deleteAccount(workIndex) {
            let workSlotId = this.workSlots[workIndex].employee.id
            AXIOS.delete('/accounts/employees/' + id //workSlotId
            ).then(response => {
                this.workSlots.splice(workIndex, 1)
                console.log(response.data)
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
        },
        // Create a new Work Slot 
        onSubmit(event) {
            event.preventDefault()
            // Create WorkSlot
            AXIOS.post('/workslots/employee/' + this.form.id +'?startdate=' + this.form.startDate + '&enddate=' + this.form.endDate + '&starttime=' + this.form.startTime + '&endtime=' + this.form.endTime + '&id=' + this.form.id 
            ).then((response) => {
                // Update the List of WorkSlots
                this.workSlots.push(response.data)
                console.log(response.data)
                // Reset the form
                this.form.startDate = ''
                this.form.endDate = ''
                this.form.startTime = ''
                this.form.endTime = ''
                this.form.id = ''
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
        },
        // Verify if the user is a Head Librarian
        verifyHeadLibrarian() {
            this.isHeadLibrarian = localStorage.getItem("userType") == "HeadLibrarian"
        }
        
 
    }
}