import Vue from 'vue'
import Router from 'vue-router'
// View Library Component
import ViewLibrary from '@/components/library/ViewLibrary.vue'

import Cart from '@/components/cart/Cart.vue'
import LibrarianAdd from '@/components/librarianAdd/librarianAdd.vue'

import Events from '@/components/event/Events.vue'
import eventdetails from '@/components/event/eventdetails.vue'

// Create Library Items Components
import CreateBook from '@/components/createLibraryItems/CreateBook.vue'
import CreateMovie from '@/components/createLibraryItems/CreateMovie.vue'
import CreateMusicAlbum from '@/components/createLibraryItems/CreateMusicAlbum.vue'
import CreateNewspaper from '@/components/createLibraryItems/CreateNewspaper.vue'

// Edit Library Items Components
import EditBook from '@/components/editLibraryItems/EditBook.vue'
import EditMovie from '@/components/editLibraryItems/EditMovie.vue'
import EditMusicAlbum from '@/components/editLibraryItems/EditMusicAlbum.vue'
import EditNewspaper from '@/components/editLibraryItems/EditNewspaper.vue'

// Search Component
import Search from '@/components/search/Search.vue'

// View Library Items Components
import viewNewspaper from '@/components/viewLibraryItems/viewNewspaper.vue'
import viewBook from '@/components/viewLibraryItems/viewBook.vue'
import viewMovie from '@/components/viewLibraryItems/viewMovie.vue'
import viewAlbum from '@/components/viewLibraryItems/viewAlbum.vue'

// Login Component
import Login from '@/components/LogIn.vue'

// WorkSlot Component
import WorkSlots from '@/components/workslots/WorkSlots.vue'

// Manage Accounts Components
import AccountsHL from '@/components/manageAccounts/AccountsHL.vue'
import AccountsLib from '@/components/manageAccounts/AccountsLib.vue'

// Client related Components
import CreateClient from '@/components/createAccount/CreateClient.vue'
import AboutClient from '@/components/about/AboutClient.vue'
import EditClient from '@/components/editAccount/EditClient.vue'

// Head Librarian related Components
import AboutHL from '@/components/about/AboutHL.vue'
import EditHL from '@/components/editAccount/EditHL.vue'

// Librarian related Components
import CreateLib from '@/components/createAccount/CreateLib.vue'
import EditLib from '@/components/editAccount/EditLib.vue'
import AboutLib from '@/components/about/AboutLib.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/about',
      name: 'Library',
      component: ViewLibrary
    },
    {
      path: '/workslots',
      name: 'WorkSlots',
      component: WorkSlots
    },
    {
      path: '/cart',
      name: 'Cart',
      component: Cart
    },
    {
      path: '/librarianadd',
      name: 'LibrarianAdd',
      component: LibrarianAdd
    },
    {
      path: '/events',
      name: 'Events',
      component: Events
    },
    {
      path: '/eventdetails',
      name: 'eventdetails',
      component: eventdetails

    },
    {
      path: '/CreateBook',
      name: 'CreateBook',
      component: CreateBook
    },

    {
      path: '/CreateMovie',
      name: 'CreateMovie',
      component: CreateMovie
    },

    {
      path: '/CreateMusicAlbum',
      name: 'CreateMusicAlbum',
      component: CreateMusicAlbum
    },

    {
      path: '/CreateNewspaper',
      name: 'CreateNewspaper',
      component: CreateNewspaper
    },

    {
      path: '/EditBook',
      name: 'EditBook',
      component: EditBook
    },

    {
      path: '/EditMovie',
      name: 'EditMovie',
      component: EditMovie
    },

    {
      path: '/EditMusicAlbum',
      name: 'EditMusicAlbum',
      component: EditMusicAlbum
    },

    {
      path: '/EditNewspaper',
      name: 'EditNewspaper',
      component: EditNewspaper
    },

    {
      path: '/Search',
      name: 'Search',
      component: Search
    },

    {
      path: '/viewBook',
      name: 'viewBook',
      component: viewBook
    },

    {
      path: '/viewMovie',
      name: 'viewMovie',
      component: viewMovie
    },

    {
      path: '/viewAlbum',
      name: 'viewAlbum',
      component: viewAlbum
    },
    {
      path: '/viewNewspaper',
      name: 'viewNewspaper',
      component: viewNewspaper
    },
    {
      path: '/accountsHL',
      name: 'AccountsHL',
      component: AccountsHL
    },
    {
      path: '/accountsLib',
      name: 'AccountsLib',
      component: AccountsLib
    },
    {
      path: '/createClient',
      name: 'CreateClient',
      component: CreateClient
    },
    {
      path: '/aboutClient',
      name: 'AboutClient',
      component: AboutClient
    },
    {
      path: '/editClient',
      name: 'EditClient',
      component: EditClient
    },
    {
      path: '/aboutHL',
      name: 'AboutHL',
      component: AboutHL
    },
    {
      path: '/editHL',
      name: 'EditHL',
      component: EditHL
    },
    {
      path: '/createLib',
      name: 'CreateLib',
      component: CreateLib
    },
    {
      path: '/editLib',
      name: 'EditLib',
      component: EditLib
    },
    {
      path: '/aboutLib',
      name: 'AboutLib',
      component: AboutLib
    }

  ]
})
 