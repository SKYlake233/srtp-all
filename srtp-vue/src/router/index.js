import { createRouter, createWebHistory } from 'vue-router'
import layout from "@/layout/layout.vue";

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
    {
        path: '/market',
        name: 'Layout',
        component: layout,
        redirect:"/market",
        children:[
            {
                path: '/market',
                name: 'market',
                component: ()=>import("@/view/market.vue"),

            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router