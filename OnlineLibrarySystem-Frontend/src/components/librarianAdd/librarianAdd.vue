<template>
  <div>
      <h2>Manage Accounts</h2>
    <hr>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-row>
        <b-col>
          <b-form-group
            id="input-group-1"
            label-for="input-1"
            label="client id"
          >
            <b-form-input
              id="input-1"
              v-model="form.clientId"
              type="number"
              placeholder="Client id"
              required
            ></b-form-input>
          </b-form-group>
        </b-col>
      <b-col>
            <b-form-group id="input-group-3" label-for="input-3" label="Librarian action">
              <b-form-select
                id="input-3"
                v-model="form.action"
                :options="actions"
                required
              ></b-form-select>
            </b-form-group>
      </b-col>
        <b-col>
          <b-button type="submit">Search</b-button>
        </b-col>
      </b-row>
    </b-form>
    <p>{{returnStatus}}</p>
    <p v-if="loading">loading request...</p>
    <Cart :key="returnStatus" ref="cart" v-if="showCart" view="librarian" :clientId="form.clientId"></Cart>
  </div>
</template>

<script src="./librarianAddView.js"></script>