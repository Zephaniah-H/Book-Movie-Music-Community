import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      userid: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).userid,
      username: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).username,
      phone: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).phone,
      icon: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).icon,
      role: window.localStorage.getItem('user' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('user' || '[]')).role
    },
    book:{
      bookid: window.localStorage.getItem('book' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('book' || '[]')).bookid,
      bookname: window.localStorage.getItem('book' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('book' || '[]')).bookname,
    },
    movie:{
      movieid: window.localStorage.getItem('movie' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('movie' || '[]')).movieid,
      moviename: window.localStorage.getItem('movie' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('movie' || '[]')).moviename,
    },
    order:{
      order_id: window.localStorage.getItem('order' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('order' || '[]')).order_id,
    },
    seat:{
      seat1: window.localStorage.getItem('seat' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('seat' || '[]')).seat1,
      seat2: window.localStorage.getItem('seat' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('seat' || '[]')).seat2,
      seat3: window.localStorage.getItem('seat' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('seat' || '[]')).seat3,
      seat4: window.localStorage.getItem('seat' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('seat' || '[]')).seat4,
    },
    search:{
      searchthing: window.localStorage.getItem('search' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('search' || '[]')).searchthing,
    },
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user', JSON.stringify(user))
    },
    onebookinfo (state, book) {
      state.book = book
      window.localStorage.setItem('book', JSON.stringify(book))
    },
    onemovieinfo (state, movie) {
      state.movie = movie
      window.localStorage.setItem('movie', JSON.stringify(movie))
    },
    buy (state, order) {
      state.order = order
      window.localStorage.setItem('order', JSON.stringify(order))
    },
    selectSeat (state, seat) {
      state.seat = seat
      window.localStorage.setItem('seat', JSON.stringify(seat))
    },
    searchbook (state, search) {
      state.search = search
      window.localStorage.setItem('search', JSON.stringify(search))
    },
  }
})
