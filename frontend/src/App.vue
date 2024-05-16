<script setup>
import { RouterView } from "vue-router";
import navbar from "./components/navbar.vue";
import linkBtn from "./components/linkBtn.vue";

const user = JSON.parse(localStorage.getItem("user"));

const turnToTop = () => {
  window.scroll({ top: 0, behavior: "smooth" });
};
</script>

<template>
  <navbar></navbar>
  <RouterView />
  <div v-if="user" class="link-btn-area">
    <linkBtn link="/" icon="fa-solid fa-cart-shopping icon-btn"></linkBtn>
    <linkBtn link="/" icon="fa-regular fa-bell icon-btn"></linkBtn>
    <linkBtn link="/" icon="fa-regular fa-comment-dots icon-btn"></linkBtn>
    <linkBtn
      @click="turnToTop()"
      icon="fa-solid fa-angle-up icon-btn"
    ></linkBtn>
  </div>
</template>

<style scoped lang="scss">
.link-btn-area {
  position: fixed;
  right: 2%;
  bottom: 10%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  width: 5vw;
  height: 30vh;
}
</style>
