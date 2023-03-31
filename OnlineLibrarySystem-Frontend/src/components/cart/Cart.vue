<template >
    <div v-if="clientExists">
      <h2>My Cart</h2>
        <b-form @submit="onSubmit" @reset="onReset">
          <b-row>
            <b-col class="lib-header">
              <b-form-group
                id="input-group-1"
                label="Library Item Id"
                label-for="input-1"
              >
              </b-form-group>
            </b-col>
          </b-row>
          <b-row>
            <b-col>
              <b-form-input
                  id="input-1"
                  v-model="form.libraryItemId"
                  type="number"
                  placeholder="Library item id"
                  required
                ></b-form-input>
            </b-col>
            <b-col>
              <b-button type="submit">Add To Cart</b-button>
            </b-col>
          </b-row>
        </b-form>
        <p>{{returnStatus}}</p>
        <b-row>
          <b-col>
            <b-table ref="table" small :fields="fields" :items="inUseSlots" >
        <template #cell(Index)="data">
          {{ data.index + 1}}
        </template>
        <template #cell(Title)="data">
          {{ data.item.libraryItem.title }}
        </template>
        <template #cell(Status)="data">
          {{ data.item.status }}
        </template>
        <template #cell(Action)="data">
          <b-button variant="outline-primary" v-if="data.item.status == 'InCart'" v-on:click="reserve(data.index)"> Reserve </b-button>
          <b-button variant="outline-primary" v-if="data.item.status == 'Booked'" v-on:click="renew(data.index)"> Renew </b-button>
          <b-button variant="outline-primary" v-if="data.item.status == 'Reserved' && view == 'librarian'" v-on:click="book(data.index)"> Book </b-button>
        </template>
        <template #cell(StartDate)="data">
          {{ data.item.startDate }}
        </template>x`
        <template #cell(EndDate)="data">
          {{ data.item.endDate }}
        </template>
        <template #cell()="data">
          <b-button variant="outline-primary" v-if="data.item.status != 'Booked'" v-on:click="remove(data.index)"> Delete </b-button>
          <b-button variant="outline-primary" v-if="data.item.status == 'Booked' && view == 'librarian' " v-on:click="returnItem(data.item.libraryItem.id)"> Return </b-button>
        </template>
      </b-table>
          </b-col>
        </b-row>
    </div>
</template>
<script src="./cart.js"></script>

