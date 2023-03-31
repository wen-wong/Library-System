<template>
    <div>
        <b-container fluid>
            <h2>About</h2>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Address</b-col>
                <b-col class="lib-text" cols="auto">
                    <b-form-input v-if="isHeadLibrarian" type="text" v-model="library.address"></b-form-input>
                    <span v-else>{{ library.address }}</span>
                </b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Phone Number</b-col>
                <b-col class="lib-text" cols="auto">
                    <b-form-input v-if="isHeadLibrarian" type="tel" v-model="library.phoneNumber"></b-form-input>
                    <span v-else>{{ library.phoneNumber }}</span>
                </b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Email</b-col>
                <b-col class="lib-text" cols="auto">
                    <b-form-input v-if="isHeadLibrarian" type="email" v-model="library.email"></b-form-input>
                    <span v-else>{{ library.email }}</span>
                </b-col>
            </b-row>
            <b-row v-if="isHeadLibrarian" align-v="start">
                <b-col class="lib-header" md="3" offset-md="3">
                    <b-button v-on:click="editLibrary(library)">Apply Changes</b-button>
                </b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Business Hours</b-col>
                <b-col>
                    <b-table ref="table" small :fields="hoursFields" :items="businessHours" head-variant="light">
                        <template #cell(DayOfWeek)="data">
                            {{ businessHours[data.index].dayOfWeek }}
                        </template>
                        <template #cell(StartTime)="data">
                            <b-form-timepicker v-if="isHeadLibrarian" v-model="businessHours[data.index].startTime"></b-form-timepicker>
                            <span v-else>{{ businessHours[data.index].startTime }}</span>
                        </template>
                        <template #cell(EndTime)="data">
                            <b-form-timepicker v-if="isHeadLibrarian" v-model="businessHours[data.index].endTime"></b-form-timepicker>
                            <span v-else>{{ businessHours[data.index].endTime }}</span>
                        </template>
                        <template #cell(Edit)="data" v-if="isHeadLibrarian">
                            <b-button v-on:click="editBusinessHours(businessHours[data.index].dayOfWeek, businessHours[data.index].startTime, businessHours[data.index].endTime)">Apply</b-button>
                        </template>
                    </b-table>
                </b-col>
            </b-row>
            <b-row align-v="start">
                <b-col class="lib-header" cols="3">Holidays</b-col>
                <b-col>
                    <b-table sticky-header no-border-collapse ref="table" small :fields="holidayFields" :items="holidaySlots" cols="auto" head-variant="light">
                        <template #cell(StartDate)="data">
                            <b-form-input v-if="isHeadLibrarian" type="date" v-model="holidaySlots[data.index].startDate"></b-form-input>
                            <span v-else>{{ holidaySlots[data.index].startDate }}</span>
                        </template>
                        <template #cell(EndDate)="data">
                            <b-form-input v-if="isHeadLibrarian" type="date" v-model="holidaySlots[data.index].endDate"></b-form-input>
                            <span v-else>{{ holidaySlots[data.index].endDate }}</span>
                        </template>
                        <template #cell(StartTime)="data">
                            <b-form-timepicker v-if="isHeadLibrarian" v-model="holidaySlots[data.index].startTime"></b-form-timepicker>
                            <span v-else>{{ holidaySlots[data.index].startTime }}</span>
                        </template>
                        <template #cell(EndTime)="data">
                            <b-form-timepicker v-if="isHeadLibrarian" v-model="holidaySlots[data.index].endTime"></b-form-timepicker>
                            <span v-else>{{ holidaySlots[data.index].endTime }}</span>
                        </template>
                        <template #cell(Edit)="data" v-if="isHeadLibrarian">
                            <b-button v-on:click="editHolidaySlot(holidaySlots[data.index].id, 
                                                                    holidaySlots[data.index].startDate, 
                                                                    holidaySlots[data.index].endDate,
                                                                    holidaySlots[data.index].startTime,
                                                                    holidaySlots[data.index].endTime)">Apply</b-button>
                        </template>
                        <template #cell(Delete)="data" v-if="isHeadLibrarian">
                            <b-button v-on:click="deleteHolidaySlot(data.index)" variant="light">Delete</b-button>
                        </template>
                    </b-table>
                </b-col>
            </b-row>
            <b-row v-if="isHeadLibrarian">
                <b-col offset-md="3">
                    <b-form inline>
                    <label class="sr-only" for="inline-form-datepicker">Start Date</label>
                    <b-form-input
                        type="date"
                        id="inline-form-datepicker"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="form.startDate">
                    </b-form-input>

                    <label class="sr-only" for="inline-form-datepicker">End Date</label>
                    <b-form-input
                        type="date"
                        id="inline-form-input-username"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="form.endDate">
                    </b-form-input>

                    <label class="sr-only" for="inline-form-timepicker">End Date</label>
                    <b-form-timepicker 
                        id="inline-form-input-username"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="form.startTime">
                    </b-form-timepicker>

                    <label class="sr-only" for="inline-form-timepicker">End Date</label>
                    <b-form-timepicker 
                        id="inline-form-input-username"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="form.endTime">
                    </b-form-timepicker>
                    
                    <b-button v-on:click="onSubmit" variant="primary">Save</b-button>
                </b-form>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>
<script src="./viewlibrary.js">
</script>
