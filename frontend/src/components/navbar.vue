<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import { userApi } from "@/api";

const router = useRouter();
const { logout } = userApi;
const user = JSON.parse(localStorage.getItem("user"));

const isDisplay = ref(false);

const logoutHandler = () => {
  logout();
  Swal.fire({
    title: "登出成功",
    text: "點擊確認以跳轉至首頁",
    icon: "success",
    confirmButtonText: "確認",
  }).then(() => {
    turnToHome();
  });
};

const showDropList = () => {
  isDisplay.value = true;
};

const hideDropList = () => {
  isDisplay.value = false;
};

const turnToHome = () => {
  router.push({ name: "home" });
};

const turnToLogin = () => {
  router.push({ name: "login" });
};

const turnToStore = () => {
  router.push({ name: "getStore", params: { id: user.userId } });
};

const turnToUpdateAccount = () => {
  router.push({ name: "updateAccount" });
};
</script>

<template>
  <section class="navbar">
    <div class="logo" @click="turnToHome()">
      <i class="fa-brands fa-shopify"></i>
      <span>二手拍賣網</span>
    </div>
    <!-- 尚未登入顯示畫面 -->
    <div class="login" v-if="!user" @click="turnToLogin()">
      <div class="border">
        <i class="fa-regular fa-circle-user icon"></i>
        <span>會員登入</span>
      </div>
    </div>
    <!-- 登入後顯示畫面 -->
    <div
      class="user-bar"
      v-if="user"
      @mousemove="showDropList()"
      @mouseleave="hideDropList()"
    >
      <div class="border">
        <i class="fa-regular fa-circle-user icon"></i>
        <span>{{ user.name }}</span>
      </div>
      <!-- 下拉選單 -->
      <div class="drop-list" v-if="isDisplay">
        <div class="link" @click="turnToStore()">
          <i class="fa-solid fa-store icon-link"></i>
          <span class="f-base">我的賣場</span>
        </div>
        <div class="link" @click="turnToUpdateAccount()">
          <i class="fa-solid fa-gear icon-link"></i>
          <span class="f-base">帳號設定</span>
        </div>
        <div class="link" @click="logoutHandler()">
          <i class="fa-solid fa-arrow-right-from-bracket icon-link"></i>
          <span class="f-base">登出</span>
        </div>
      </div>
      <!-- 下拉選單 -->
    </div>
  </section>
</template>

<style scoped lang="scss">
.navbar {
  min-height: 10vh;
  box-shadow: 0 8px 6px -6px #777;
  display: flex;
  justify-content: space-between;
  padding: 0 1rem;

  .logo {
    display: flex;
    align-items: center;

    &:hover {
      cursor: pointer;
    }

    .fa-shopify {
      color: #5bbcff;
      font-size: 3rem;
      margin-right: 0.5rem;
    }

    span {
      color: #5bbcff;
      font-size: 1.8rem;
    }
  }

  .login,
  .user-bar {
    display: flex;
    align-items: center;

    .border {
      min-width: 10vw;
      display: flex;
      align-items: center;
      padding: 0.2rem 0.5rem;

      &:hover {
        cursor: pointer;
        border-radius: 8px;
      }
    }

    span {
      font-size: 1.15rem;
    }
  }

  .login {
    .border {
      &:hover {
        outline: 2px solid #777;
      }
    }
  }

  .user-bar {
    position: relative;
    .border {
      &:hover {
        background-color: #d4d4d4;
      }
    }

    .drop-list {
      position: absolute;
      top: 80%;
      right: 5%;
      width: 125%;
      border: 1px solid gray;
      border-radius: 8px;
      background-color: #ffffff;
      box-shadow: 0 8px 6px -6px #777;
      display: none;
      z-index: 10;

      .link {
        padding: 0.5rem 0.5rem;

        &:hover {
          cursor: pointer;
          background-color: #d4d4d4;
        }
      }
    }
  }

  .user-bar:hover .drop-list {
    display: block;
  }
}
</style>
