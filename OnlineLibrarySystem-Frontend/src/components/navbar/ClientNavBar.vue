<template>
    <div class="lib-header" v-if="render">
        <b-navbar toggleable="lg" variant="transparent">
            <b-navbar-brand id="page-title" to="/about">Online Library</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item to="/about" exact exact-active-class="active">Library</b-nav-item>
                    <b-nav-item to="/Search" exact exact-active-class="active">Search</b-nav-item>
                    <b-nav-item v-on:click="navToMyAccount" exact exact-active-class="active">My Account</b-nav-item>
                    <b-nav-item to="/events" exact exact-active-class="active">Events</b-nav-item>
                    <b-nav-item to="/cart" exact exact-active-class="active">My Cart</b-nav-item>
                    <b-button v-if="isLogin" v-on:click="logout">Logout</b-button>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>
<script>
import NavBar from './navBar.js'

export default {
    data() {
        return {
            isLogin: '',
            render: true

        }
    },
    async mounted() {
        this.isLogin = localStorage.getItem("userId") != '' && localStorage.getItem("userType") != ''
    },
    methods: {
        navToMyAccount(event) {
                event.preventDefault()
                const navBarService = new NavBar()
                navBarService.viewAccount()
            },
        logout() {
            var userId = localStorage.getItem("userId")
            var userType = localStorage.getItem("userType")
            if (userId != '' && userType != '') {
                localStorage.setItem("userType", "")
                localStorage.setItem("userId", "")
                window.location.href = '/#/'
                window.location.reload()
            }
        }
    }
} 
</script>
<style>
#page-title {
    font-size: 1.5rem;
}
</style>

