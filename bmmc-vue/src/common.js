import axios from 'axios'

export  function getUrlKey(name){ //转化地址栏上面的参数
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null
}

export default{
	addZero(num){
		if(num < 10){
			return `0${num}`
		}else
			return num
	},
	getCurrentTime(){
		console.log(new Date().getMonth())
		let time = new Date().getFullYear() + '-' + this.addZero(new Date().getMonth()+1) + '-' + this.addZero(new Date().getDate()) + ' ' 
		           + this.addZero(new Date().getHours()) + ':' + this.addZero(new Date().getMinutes()) + ':' + this.addZero(new Date().getSeconds())
		return time
	},
	getRandom(){
		let a = new Date().getFullYear() + this.addZero(new Date().getMonth()+1) + this.addZero(new Date().getDate()) 
		           + this.addZero(new Date().getHours()) + this.addZero(new Date().getMinutes()) + this.addZero(new Date().getSeconds())
		let b = parseInt(Math.random()*100000)
		let c = `${a}${b}`
		return c
	},
	requestData(api,params,type){
		if(type == 'get'){
			return axios.get(`http://localhost:8888/api${api}`,params)
		}else{
			return axios.post(`http://localhost:8888/api${api}`,params)
		}
	},
	requestMusicData(api,id){
		// if(id != '')
		return axios.get(`http://localhost:3000${api}?idx=${id}`)
		// else
			// return axios.get(`http://localhost:3000${api}`)
	},
}


