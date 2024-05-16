import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/account/LoginView.vue"),
    },
    {
      path: "/register",
      name: "register",
      component: () => import("../views/account/RegisterView.vue"),
    },
    {
      path: "/account/update",
      name: "updateAccount",
      component: () => import("../views/account/UpdateView.vue"),
    },
    {
      path: `/product/:id`,
      name: "getProduct",
      component: () => import("../views/product/ProductView.vue"),
    },
    {
      path: `/store/:id`,
      name: "getStore",
      component: () => import("../views/product/StoreView.vue"),
    },
    {
      path: `/product/create`,
      name: "createProduct",
      component: () => import("../views/product/CreateView.vue"),
    },
    {
      path: `/product/update/:id`,
      name: "updateProduct",
      component: () => import("../views/product/UpdateView.vue"),
    },
  ],
});

export default router;
