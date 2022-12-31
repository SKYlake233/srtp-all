import { createRouter, createWebHistory } from 'vue-router'
import layout from "@/layout/layout.vue";
import layout_user from "@/layout/Layout_user.vue";
import layout_comm from "@/layout/Layout_comm.vue";

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
        path:'/register',
        name:'register',
        title: '注册',
        component: () => import('../components/register.vue')
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
    },
    {
        path: '/user',
        name: 'Layout_user',
        component: layout_user,
        redirect:"/user",
        children:[
            {
                path: '/user',
                name: 'user',
                component: ()=>import("@/view/user.vue"),

            },
        ]
    },
    {
        path: '/comm',
        name: 'Layout_comm',
        component: layout_comm,
        redirect:"/comm/item",
        children:[
            {
                path: '/comm/item',
                name: 'comm_item',
                component: ()=>import("@/view/comm_item.vue"),
            },
            {
                path: '/comm/person',
                name: 'comm_user',
                component: ()=>import("@/view/person.vue"),
            },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router