import { createRouter, createWebHistory } from 'vue-router'

const routes =[
    {
        path:'/',
        name:'Manager',
        redirect:'/login'
    },
    {
        path:'/login',
        name:'login',
        title: '登录',
        component: () => import('../components/login.vue')
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router