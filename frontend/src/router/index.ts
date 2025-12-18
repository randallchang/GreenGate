import {getToken} from '@/util/cookieUtil'
import {createRouter,
        createWebHistory,
        type RouteRecordRaw} from 'vue-router'

export const constantRoutes: RouteRecordRaw[] = [
    {
        path: '/login',
        meta: {title: 'Login'},
        component: () => import('@/views/login/Index.vue')
    },
    {
        path: '/',
        redirect: '/index'
    },
    {
        path: '/index',
        name: 'index',
        component: () => import('@/views/Index.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('@/views/error/NotFound.vue')
    }
]

    const router = createRouter({
        history: createWebHistory(),
        routes: constantRoutes
    })

router.beforeEach((to, from, next) => {
    const hasToken = getToken()
    console.log('beforeEach:', to.path, 'token:', hasToken, 'type:', typeof hasToken)
    if (to.path === '/login') {
        next()
        return
    }
    if (!hasToken) {
        next('/login')
        return
    }
    next()
})

export default router
