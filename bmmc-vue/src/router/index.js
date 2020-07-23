import Vue from 'vue'
import Router from 'vue-router'
import Header from '@/components/index/Header'
import loginedHeader from '@/components/index/loginedHeader'
import Login from '@/components/index/Login'
import Content from '@/components/index/Content'
import Index from '@/components/index/Index'
import OneBook from '@/components/book/OneBook'
import ReadBookIndex from '@/components/book/ReadBookIndex'
import ReadHeader from '@/components/book/ReadHeader'
import ReadBook from '@/components/book/ReadBook'
import MusicIndex from '@/components/music/MusicIndex'
import player from '@/components/musicplayer/player'
import MusicHeader from '@/components/music/MusicHeader'
import a from '@/components/music/a'
import b from '@/components/music/b'
import MovieHeader from '@/components/movie/MovieHeader'
import MovieIndex from '@/components/movie/MovieIndex'
import OneMovie from '@/components/movie/OneMovie'
import BuyTicket from '@/components/movie/BuyTicket'
import Home from '@/components/back/home/Home'
import backLogin from '@/components/back/home/backLogin'
import HomeIndex from '@/components/back/page/HomeIndex'
import userManage from '@/components/back/page/userManage'
import bookManage from '@/components/back/page/bookManage'
import musicManage from '@/components/back/page/musicManage'
import movieManage from '@/components/back/page/movieManage'
import bookCommentMng from '@/components/back/page/bookCommentMng'
import musicCommentMng from '@/components/back/page/musicCommentMng'
import movieCommentMng from '@/components/back/page/movieCommentMng'
import planManage from '@/components/back/page/planManage'
import chapterManage from '@/components/back/page/chapterManage'
import orderManage from '@/components/back/page/orderManage'
import loginedIndex from '@/components/index/loginedIndex'
import Search from '@/components/index/Search'
import Order from '@/components/index/Order'
import BookShelf from '@/components/index/BookShelf'
import MovieSearch from '@/components/movie/MovieSearch'
import AccountMng from '@/components/index/AccountMng'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/header',
      name: 'Header',
      component: Header
    },
    {
      path: '/loginedheader',
      name: 'loginedHeader',
      component: loginedHeader
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/content',
      name: 'Content',
      component: Content
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/loginedindex',
      name: 'loginedIndex',
      component: loginedIndex
    },
    {
      path: '/bookshelf',
      name: 'BookShelf',
      component: BookShelf
    },
    {
      path: '/order',
      name: 'Order',
      component: Order
    },
    {
      path: '/search',
      name: 'Search',
      component: Search
    },
    {
      path: '/accountmng',
      name: 'AccountMng',
      component: AccountMng
    },
    {
      path: '/readheader',
      name: 'ReadHeader',
      component: ReadHeader
    },
    {
      path: '/onebook',
      name: 'OneBook',
      component: OneBook
    },
    {
      path: '/readbookindex',
      name: 'ReadBookIndex',
      component: ReadBookIndex
    },
    {
      path: '/readbook',
      name: 'ReadBook',
      component: ReadBook
    },
    {
      path: '/musicindex',
      name: 'MusicIndex',
      component: MusicIndex
    },
    {
      path: '/musicplayer',
      name: 'player',
      component: player
    },
    {
      path: '/musicheader',
      component: MusicHeader
    },
    {
      path: '/',
      component: a
    },
    {
      path: '/b',
      component: b
    },
    {
      path: '/movieheader',
      component: MovieHeader
    },
    {
      path: '/movieindex',
      component: MovieIndex
    },
    {
      path: '/moviesearch',
      component: MovieSearch
    },
    {
      path: '/onemovie',
      component: OneMovie
    },
    {
      path: '/buyticket',
      component: BuyTicket
    },
    {
      path: '/backlogin',
      component: backLogin
    },
    {
      path: '/home',
      component: Home,
      children: [
        {
          path: '/homeindex',
          component: HomeIndex
        },
        {
          path: '/usermanage',
          component: userManage
        },
        {
          path: '/bookmanage',
          component: bookManage
        },
        {
          path: '/musicmanage',
          component: musicManage
        },
        {
          path: '/moviemanage',
          component: movieManage
        },
        {
          path: '/bookcomment',
          component: bookCommentMng
        },
        {
          path: '/musiccomment',
          component: musicCommentMng
        },
        {
          path: '/moviecomment',
          component: movieCommentMng
        },
        {
          path: '/planmanage',
          component: planManage
        },
        {
          path: '/chaptermanage',
          component: chapterManage
        },
        {
          path: '/ordermanage',
          component: orderManage
        },
      ]
    },
  ]
})
