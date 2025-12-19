import router from '@/router'
import {getToken} from '@/util/cookieUtil'
import axios from 'axios'

let request = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    timeout: 30000,
    headers: {
        'content-type': 'application/json;charset=utf-8'
    }
})

// request interceptor
request.interceptors.request.use(
    config => {
        Object.assign(config.headers || {});
        config.headers['Authorization'] = getToken() || '';
        return config;
    },
    error => {
        return Promise.reject(error);
    },
)

// response interceptor
request.interceptors.response.use(
    response => {
        if (response.status === 401) {
            router.push('/login');
        }
        return response;
    },
    error => {
        return Promise.reject(error);
    })

export {
    request
}
