<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import { userApi } from "@/api";

const router = useRouter();
const { login } = userApi;

const isDisplay = ref(true);
const inputType = ref("password");

const email = ref("");
const pwd = ref("");

const loginHandler = async () => {
  let loginReq = {
    email: email.value,
    password: pwd.value,
  };

  const loginRes = await login(loginReq);

  switch (loginRes.rtnMsg) {
    case "PARAM_ERROR":
      Swal.fire({
        title: "請確認所有欄位是否輸入",
        icon: "warning",
        confirmButtonText: "確認",
      });
      break;
    case "ACCOUNT_NOT_FOUND":
      Swal.fire({
        title: "該信箱未註冊",
        icon: "warning",
        confirmButtonText: "確認",
      });
      break;
    case "PASSWORD_ERROR":
      Swal.fire({
        title: "請確認密碼是否正確",
        icon: "warning",
        confirmButtonText: "確認",
      });
      break;
    case "LOGIN_ERROR":
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      break;
    case "SUCCESSFUL":
      localStorage.setItem("user", JSON.stringify(loginRes.user));
      //setUser(loginRes.user);

      Swal.fire({
        title: "登入成功",
        text: "點擊確認以跳轉至首頁",
        icon: "success",
        confirmButtonText: "確認",
      }).then(() => {
        router.push({ name: "home" });
      });
      break;
  }
};

const showPwd = () => {
  isDisplay.value = !isDisplay.value;
  isDisplay.value ? (inputType.value = "password") : (inputType.value = "text");
};

const turnToRegister = () => {
  router.push({ name: "register" });
};
</script>

<template>
  <section class="main bg-container">
    <img
      class="bg-img"
      src="../../../public/bgImg/loginBgImg.jpg"
      alt="login-background-image"
    />
    <div class="login-area">
      <span class="title">會員登入</span>
      <div class="inputbar">
        <i class="fa-regular fa-envelope input-icon"></i>
        <input v-model.trim="email" type="text" placeholder="請輸入信箱" />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-unlock-keyhole input-icon"></i>
        <input
          v-model.trim="pwd"
          :type="inputType"
          class="input-pwd"
          placeholder="請輸入密碼"
        />
        <i v-if="isDisplay" @click="showPwd()" class="fa-regular fa-eye"></i>
        <i
          v-if="!isDisplay"
          @click="showPwd()"
          class="fa-regular fa-eye-slash"
        ></i>
      </div>
      <button @click="loginHandler()" type="button" class="login btn">
        登入
      </button>
      <button @click="turnToRegister()" type="button" class="register btn">
        註冊
      </button>
      <span class="forgot-pwd">忘記密碼</span>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  position: relative;

  .login-area {
    background-color: #ffffff;
    width: 30vw;
    height: 60vh;
    box-shadow: 0 8px 6px -6px #777;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    position: absolute;
    top: 50%;
    right: 10%;
    transform: translateY(-50%);

    .inputbar {
      input[type="text"],
      .input-pwd {
        width: 100%;
        height: 100%;
        font-size: 1rem;
        padding-left: 30px;
        border-radius: 8px;
      }

      .input-pwd {
        padding-right: 30px;
      }

      .fa-eye,
      .fa-eye-slash {
        position: absolute;
        top: 50%;
        right: 8px;
        transform: translateY(-50%);

        &:hover {
          cursor: pointer;
        }
      }
    }

    .login,
    .register {
      width: 80%;
      height: 10%;
      font-size: 1rem;
      color: white;
      background-color: #5bbcff;
    }

    .forgot-pwd {
      margin-right: 1rem;
      margin-bottom: 0.3rem;
      align-self: flex-end;
      color: red;
      font-size: 0.8rem;

      &:hover {
        cursor: pointer;
        border-bottom: 0.5px solid red;
      }
    }
  }
}
</style>
