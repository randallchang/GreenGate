import {request} from '@/util/request'

export const UserList = () => {
    return request({
        url: '/user/list',
        method: 'get'
    })
}