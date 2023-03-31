<template>
  <div>
    <b-container fluid>
      <h2>Event ID: {{ event.id }}</h2>
      <b-row>
        <b-col>
          <b-button @click="prevPage()">Back</b-button>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <table cellpadding="10">
        <tr>
          <td><span style="font-weight: bold">Start Date: </span></td>
          <td v-if="notEventCreator">{{ startDate }}</td>
          <td v-if="isEventCreator">
            <input type="date" v-model="startDate" />
          </td>
        </tr>
        <tr>
          <td><span style="font-weight: bold">Start Time: </span></td>
          <td v-if="notEventCreator">{{ startTime.substring(0, 5) }}</td>
          <td v-if="isEventCreator">
            <input type="time" v-model="startTime" />
          </td>
        </tr>
        <tr>
          <td><span style="font-weight: bold">End Date: </span></td>
          <td v-if="notEventCreator">{{ endDate }}</td>
          <td v-if="isEventCreator"><input type="date" v-model="endDate" /></td>
        </tr>
        <tr>
          <td><span style="font-weight: bold">End Time: </span></td>
          <td v-if="notEventCreator">{{ endTime.substring(0, 5) }}</td>
          <td v-if="isEventCreator"><input type="time" v-model="endTime" /></td>
        </tr>
        <tr>
          <td><span style="font-weight: bold">Description: </span></td>
          <td v-if="notEventCreator">{{ description }}</td>
          <td v-if="isEventCreator">
            <textarea
              id="textarea"
              v-model="description"
              rows="5"
              cols="15"
              placeholder="Empty description"
            ></textarea>
          </td>
        </tr>
        <tr v-if="notEventCreator">
          <td><span style="font-weight: bold">Event creator: </span></td>
          <td>{{ event.client.name }}, ID: {{ event.client.id }}</td>
        </tr>
        <b-button
          v-if="isEventCreator"
          @click="edit(startDate, startTime, endDate, endTime, description)"
        >
          Edit
        </b-button>
        <b-button v-if="isEventCreator" @click="remove()">Delete</b-button>
      </table>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <p>
            <span v-if="errorEdit" style="color: red">{{ errorEdit }}</span>
            <span v-if="successEdit" style="color: green">{{ successEdit }}</span>
          </p>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
// src= "./editEvent.js"
import axios from "axios";
var config = require("../../../config");

var frontendUrl = "http://" + config.dev.host + ":" + config.dev.port;
var backendUrl =
  "http://" + config.dev.backendHost + ":" + config.dev.backendPort;

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { "Access-Control-Allow-Origin": frontendUrl },
});

export default {
  name: "eventDetails",
  data() {
    return {
      id: "",
      startDate: "",
      startTime: "",
      endDate: "",
      endTime: "",
      description: "",
      event: "",
      errorEdit: "",
      successEdit: "",
      isEventCreator: "",
      notEventCreator: "",
    };
  },

  async mounted() {
    await AXIOS.get("/events/" + localStorage.getItem("eventId")).then(
      (response) => {
        this.event = response.data;
        this.id = response.data.id;
        this.startDate = response.data.startDate;
        this.startTime = response.data.startTime;
        this.endDate = response.data.endDate;
        this.endTime = response.data.endTime;
        this.description = response.data.description;

        console.log(response.data.client.id);
      }
    );

    this.isEventCreator = localStorage.getItem("isEventCreator");
    this.notEventCreator = localStorage.getItem("notEventCreator");
  },
  methods: {
    remove() {
      AXIOS.get("events/remove/" + this.event.id)
        .then(() => {
          this.errorEdit = "";
          this.successEdit = "Event has been deleted";
        })
        .catch((error) => {
          this.successEdit = "";
          this.errorEdit = "Event does not exist";
        });
    },
    edit(startDate, startTime, endDate, endTime, description) {
      AXIOS.put(
        "/events/edit/" +
          localStorage.getItem("eventId") +
          "/?startDate=" +
          startDate +
          "&endDate=" +
          endDate +
          "&startTime=" +
          startTime.substring(0, 5) +
          "&endTime=" +
          endTime.substring(0, 5) +
          "&description=" +
          description
      )
        .then((response) => {
          this.errorEdit = "";
          this.event = response.data;
          this.startDate = response.data.startDate;
          this.startTime = response.data.startTime;
          this.endDate = response.data.endDate;
          this.endTime = response.data.endTime;
          this.description = response.data.description;
          this.successEdit = "Event has been edited";
        })
        .catch((error) => {
          console.log(error.data);
          this.successEdit = "";
          this.errorEdit = "Missing/invalid input or overlapping event";
        });
    },

    prevPage() {
      window.location.href = "/#/events";
    },
  },
};
</script>