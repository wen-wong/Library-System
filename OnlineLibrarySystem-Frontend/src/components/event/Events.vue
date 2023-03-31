<template>
  <div>
    <b-container fluid>
    <h2>View Availabilities</h2>
    <table>
      <tr>
        <td>
          <b-calendar v-model="selectedDate" locale="en" @input="searchByDate(selectedDate)"></b-calendar>
        </td>
        <b-button @click="viewAll()">View All</b-button>
        <b-button @click="myEvents()">My Events</b-button>
        <table cellpadding="10">
          <tr
            v-for="event in events"
            :key="event.startDate"
            style="cursor: pointer"
            @click="selectEvent(event)">
            <td>
              <span style="font-weight: bold">Start: </span
              >{{ event.startDate }} at {{ event.startTime.substring(0,5)}}
            </td>
            <td>
              <span style="font-weight: bold">End:&nbsp; </span
              >{{ event.endDate }} at {{ event.endTime.substring(0,5) }}
            </td>
            <td><span style="font-weight: bold;">Description: </span
              >{{ event.description }}</td>
            <td>{{ event.id }}</td>
            <!-- <td>{{ event.client.id }}</td> -->
          </tr>
        </table>
      </tr>
    </table>
    <br />
      <h2>Create Event</h2>
      <table>
        <tr>
          <td>
            <label for="sdate" class="lib-header">Start Date:</label>
            <input type="date" v-model="newStartDate" id="sdate" name="sdate" />
          </td>
          <td>
            <label for="stime" class="lib-header">Start Time:</label>
            <input type="time" v-model="newStartTime" id="stime" name="stime" />
          </td>
        </tr>
        <tr>
          <td>
            <label for="edate" class="lib-header">End Date:&nbsp;</label>
            <input type="date" v-model="newEndDate" id="edate" name="edate" />
          </td>
          <td>
            <label for="etime" class="lib-header">End Time:&nbsp;</label>
            <input type="time" v-model="newEndTime" id="etime" name="etime" />
          </td>
        </tr>
        <label for="description" class="lib-header">Event description:</label
        ><br />
        <textarea
          id="textarea"
          v-model="newDescription"
          rows="5"
          cols="15"
          placeholder="Enter text..."
        ></textarea>
      </table>

    <br />
    <b-button
      id="createButton"
      v-bind:disabled="!newStartDate"
      @click="
        createEvent(
          newStartDate,
          newEndDate,
          newStartTime,
          newEndTime,
          newDescription
        )
      "
    >
      Create
    </b-button>

    <p>
      <span v-if="errorEvent" style="color: red">{{ errorEvent }}</span>
    </p>
    </b-container>
  </div>
</template>
<script src="./createevents.js"></script>

<style>
#td[rowspan] {
  vertical-align: top;
  text-align: left;
}

</style>
