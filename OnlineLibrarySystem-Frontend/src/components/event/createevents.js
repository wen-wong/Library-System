import { AXIOS } from '../../router/http.js'

export default {
    name: 'event',
    data() {
        return {
            events: [],
            id:'',
            newEvent: '',
            newStartDate: '',
            newStartTime: '',
            newEndDate: '',
            newEndTime: '',
            newDescription: '',
            errorEvent: '',
            response: [],
            selectedDate: '',
        }
    },
    
    async mounted() {
        await AXIOS.get('/events').then(response => {
            this.events = response.data
        })
        .catch(e => {
            this.errorEvent = e
            console.log(e)
        })
    },

    methods: {
        createEvent(startDate, endDate, startTime, endTime, description) {
            AXIOS.post('/events/?client='+localStorage.getItem('userId')+'&startDate='+startDate+'&endDate='+endDate+'&startTime='+startTime+'&endTime='+endTime+'&description='+description)
            .then(response => {
                console.log(response.data.id)
                this.events.push(response.data)
                
            })
            .catch(error => { 
                console.log(error.data)
                this.errorEvent = 'Missing input or overlapping event'
                // this.errorEvent = error.data
            })
        },
        searchByDate(selectedDate) {
            AXIOS.get('/events/availabilities?date='+selectedDate)
            .then(response => {
                this.events = response.data
                console.log(selectedDate)
            })
        },
        viewAll() {
            AXIOS.get('/events').then(response => {
                this.events = response.data
            })
        },
        selectEvent(event) {
            AXIOS.get('/events/'+event.id).then(response => {
                this.event = response.data
                if (event.client.id == localStorage.getItem('userId')) {
                    localStorage.setItem('isEventCreator', 'true')
                    localStorage.setItem('notEventCreator', '')
                }
                else {
                    localStorage.setItem('isEventCreator', '')
                    localStorage.setItem('notEventCreator', 'true')
                }
                console.log("Cliend Id: "+event.client.id + ' Current client: '+localStorage.getItem('userId'))
                localStorage.setItem('eventId', event.id)
                window.location.href = '/#/eventdetails'
            })
        },
        searchEvent(id) {
            AXIOS.get('/events/'+id).then(response => {
                console.log(response.data.id)
            })
        },
        myEvents(){
            AXIOS.get('/events/client/'+localStorage.getItem('userId')).then(response => {
                this.events = response.data
            })
        }
    }
  
}