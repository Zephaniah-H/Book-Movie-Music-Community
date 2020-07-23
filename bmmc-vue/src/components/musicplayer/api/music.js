import axios from 'axios'
//获取歌词
export const getWords=(id)=>{
    return axios.get(`http://localhost:3000/lyric?id=${id}`);
}
//获取歌曲详情
export const getMusicInfo=(id)=>{
    return axios.get(`http://localhost:3000/song/detail?ids=${id}`);
}
//获取歌曲url
export const getMusicUrl=(id)=>{
    return axios.get(`http://localhost:3000/song/url?id=${id}`);   
}
//获取热门歌曲
export const getHotMusic=(id)=>{
    return axios.get(`http://localhost:3000/top/list?idx=${id}`);
}
//获取搜索建议
export const getSearchSuggest=(key)=>{
    return axios.get(`http://localhost:3000/search/suggest?keywords=${key}`)
}
//获取歌单 ( 网友精选碟 )
export const getSongList=()=>{
    return axios.get(`http://localhost:3000/top/playlist`);
}
//获取歌单歌曲详情
export const getListSong=(id)=>{
    return axios.get(`http://localhost:3000/playlist/detail?id=${id}`);
}
//获取歌手单曲详情
export const getSingerSong=(id)=>{
    return axios.get(`http://localhost:3000/artists?id=${id}`);
}
//获取单曲评论
export const getSongComment=(id)=>{
    return axios.get(`http://localhost:3000/comment/music?id=${id}`);
}
