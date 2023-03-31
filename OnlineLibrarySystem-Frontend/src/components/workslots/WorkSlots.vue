<template>
    <div>
        <b-container fluid>
             <h2>Work Slots</h2>
            <b-row align-v="start">
                <b-col>
                    <b-table sticky-header no-border-collapse ref="table" small :fields="workSlotFields" :items="workSlots" cols="auto" head-variant="light">
                        <template #cell(StartDate)="data">
                            <b-form-input v-if="isHeadLibrarian" type="date" v-model="workSlots[data.index].startDate"></b-form-input>
                            <span v-else>{{ workSlots[data.index].startDate }}</span>
                        </template>
                        <template #cell(EndDate)="data">
                            <b-form-input v-if="isHeadLibrarian" type="date" v-model="workSlots[data.index].endDate"></b-form-input>
                            <span v-else>{{ workSlots[data.index].endDate }}</span>
                        </template>
                        <template #cell(StartTime)="data">
                            <b-form-timepicker v-if="isHeadLibrarian" v-model="workSlots[data.index].startTime"></b-form-timepicker>
                            <span v-else>{{ workSlots[data.index].startTime }}</span>
                        </template>
                        <template #cell(EndTime)="data">
                            <b-form-timepicker v-if="isHeadLibrarian" v-model="workSlots[data.index].endTime"></b-form-timepicker>
                            <span v-else>{{ workSlots[data.index].endTime }}</span>
                        </template>
                        <template #cell(EmployeeId)="data">
                            <b-form-input v-if="isHeadLibrarian" v-model="workSlots[data.index].id"></b-form-input>
                            <span v-else>{{ workSlots[data.index].employee.id }}</span>
                        </template>

                        
                        <template #cell(Delete)="data" v-if="isHeadLibrarian">
                            <b-button v-on:click="deleteWorkSlot(data.index)" variant="light">Delete</b-button>
                        </template>
                        <template #cell(DeleteAccount)="data" v-if="isHeadLibrarian">
                            <b-button v-on:click="deleteAccount(data.index)" variant="light">Fire</b-button>
                        </template>
                    </b-table>
                </b-col>
            </b-row>
            <b-row v-if="isHeadLibrarian">
                <b-col offset-md="0">
                    
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

                    <label class="sr-only" for="inline-form-timepicker">Start Time</label>
                    <b-form-timepicker 
                        id="inline-form-input-username"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="form.startTime">
                    </b-form-timepicker>

                    <label class="sr-only" for="inline-form-timepicker">End Time</label>
                    <b-form-timepicker 
                        id="inline-form-input-username"
                        class="mb-2 mr-sm-2 mb-sm-0"
                        v-model="form.endTime">
                    </b-form-timepicker>

                    <label class="sr-only" for="inline-form-input">Employee Id</label>
                    <b-input-group class="mb-2 mr-sm-2 mb-sm-0">
                    <b-form-input id="inline-form-input" placeholder="id"
                    v-model="form.id"
                    ></b-form-input>
                    </b-input-group>
                    
                    <b-button v-on:click="onSubmit" variant="primary">Save</b-button>
                </b-form>
               
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>
<script src="./WorkSlot.js">
</script>
