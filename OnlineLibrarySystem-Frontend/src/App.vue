<template>
  <div>
    <img src="./assets/Books.png" alt="Books Image">
    <head-librarian-nav-bar v-if='userType == "HeadLibrarian"' />
    <librarian-nav-bar v-if='userType == "Librarian"' />
    <client-nav-bar v-if='userType == "Client"' />
    <div id="content-back" class="container-fluid">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import ClientNavBar from './components/navbar/ClientNavBar.vue'
import LibrarianNavBar from './components/navbar/LibrarianNavBar.vue'
import HeadLibrarianNavBar from './components/navbar/HeadLibrarianNavBar.vue'
export default {
  name: 'app',
  components: {
    ClientNavBar,
    LibrarianNavBar,
    HeadLibrarianNavBar
  },
  data() {
    return {
      userType: '',
      render: true
    }
  },
  async mounted() {
    this.userType = localStorage.getItem("userType")
  }
}
</script>
<style>
img {
  position: absolute;
  top: -5rem;
  left: 0rem;
  z-index: -1;
}
body {
  background: #F2E5D0;
  padding: 1.5rem 4rem 1.5rem 3rem;
  font-family: Montserrat;
  font-style: normal;
  font-size: 1.5rem;
  line-height: 1.8rem;
}
#content-back {
  font-family: Montserrat;
  font-style: normal;
  font-size: 1.5rem;
  line-height: 1.8rem;
  background: #F9F3EE;
  border-radius: 1rem;
  padding: 1.5rem 1rem 1.5rem 1rem;
}
.lib-header {
  font-weight: bold;
  padding-bottom: 1.5rem;
}
h2 {
  font-family: Libre Caslon Text;
  font-style: normal;
  font-weight: bold;
  font-size: 4.5rem;
  line-height: 5.5rem;
  padding-bottom: 1.5rem;
}
</style>