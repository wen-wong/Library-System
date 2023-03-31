import { AXIOS } from '../../router/http.js'


var isHeadLibrarian = true


export default {
    name: 'ViewLibrary',
    data() {
        return {
            library: '',
            hoursFields: [],
            businessHours: [],
            holidayFields: [],
            holidaySlots: [],
            form: {
                startDate: '',
                endDate: '',
                startTime: '',
                endTime: ''
            },
            isHeadLibrarian : false
        }
    },
    
    async mounted() {
        // Set if the user is a Head Librarian
        this.verifyHeadLibrarian()
        this.isHeadLibrarian ? this.hoursFields = ['DayOfWeek', 'StartTime', 'EndTime', 'Edit'] : this.hoursFields = ['DayOfWeek', 'StartTime', 'EndTime']
        this.isHeadLibrarian ? this.holidayFields = ['StartDate', 'EndDate', 'StartTime', 'EndTime', 'Edit', 'Delete'] : this.holidayFields = ['StartDate', 'EndDate', 'StartTime', 'EndTime']

        // Initializing the library from backend
        await AXIOS.get('/library/' + 'library')
            .then(response => {
                this.library = response.data
            })
            .catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg) 
            });
        
        // Initializing all business hours from backend
        await AXIOS.get('/allBusinesshours/library/' + 'library')
            .then(response => {
                this.businessHours = response.data
                this.businessHours.sort((a,b) => a.dayOfWeek.toLowerCase() - b.dayOfWeek.toLowerCase())
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            });
        // Initializing all holiday slots from backend
        await AXIOS.get('/allHolidayslots/library/' + 'library')
            .then(response => {
                this.holidaySlots = response.data
            })
            .catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            });
    },
    methods: {
        // Edit the attributes of Library
        editLibrary(editLibrary) {
            AXIOS.put('/editlibrary/' + this.library.name + "?address=" + editLibrary.address + "&phonenumber=" + editLibrary.phoneNumber + "&email=" + editLibrary.email
            ).then(response => {
                this.library = response.data
                console.log(response.data)
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
        },
        // Edit the attributes of a specific Business Hours
        editBusinessHours(editDayOfWeek, editStartTime, editEndTime) {
            AXIOS.put('/businesshours/' + editDayOfWeek + '?starttime=' + editStartTime + '&endtime=' + editEndTime
            ).then(response => {
                this.businessHours.sort((a,b) => a.dayOfWeek.toLowerCase() - b.dayOfWeek.toLowerCase())
                console.log(response.data)
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
        },
        // Edit the attributes of a specific Holiday Slot
        editHolidaySlot(slotId, editSlotStartDate, editSlotEndDate, editSlotStartTime, editSlotEndTime) {
            AXIOS.put('/editholidayslot/' + slotId + '?startdate=' + 
                        editSlotStartDate + '&enddate=' + editSlotEndDate + 
                        '&starttime=' + editSlotStartTime + '&endtime=' + editSlotEndTime
            ).then(response => {
                console.log(response.data)
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
            this.updateHolidaySlots += 1;
        },
        // Delete a specific Holiday Slot
        deleteHolidaySlot(holidayIndex) {
            let holidaySlotId = this.holidaySlots[holidayIndex].id
            AXIOS.delete('/deleteholidayslot/' + holidaySlotId
            ).then(response => {
                this.holidaySlots.splice(holidayIndex, 1)
                console.log(response.data)
            }).catch(error => {
                var errorMsg = error.response.data.message
                console.log(errorMsg)
            })
        },
        // Create a new Holiday Slot 
        onSubmit(event) {
            event.preventDefault()
            // Create HolidaySlot
            AXIOS.post('/holidayslot/library/' + this.library.name + '?startdate=' + this.form.startDate + '&enddate=' + this.form.endDate + '&starttime=' + this.form.startTime + '&endtime=' + this.form.endTime
            ).then((response) => {
                // Update the List of HolidaySlots
                this.holidaySlots.push(response.data)
                console.log(response.data)
                // Reset the form
                this.form.startDate = ''
                this.form.endDate = ''
                this.form.startTime = ''
                this.form.endTime = ''
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