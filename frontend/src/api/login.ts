import {request} from '@/util/request'

export const Login = (data = {}) => {
    return request({
        url: '/login',
        method: 'post',
        data
    })
}