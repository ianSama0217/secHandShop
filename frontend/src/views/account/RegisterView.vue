<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import api from "../../api/index.js";

const router = useRouter();
const { register } = api;

const isDisplay = ref(true);
const inputType = ref("password");
const isDisplay2 = ref(true);
const inputType2 = ref("password");

const name = ref("");
const phone = ref("");
const email = ref("");
const pwd = ref("");
const pwd2 = ref("");

const registerHandler = async () => {
  if (pwd.value != pwd2.value) {
    Swal.fire({
      title: "請確認密碼輸入是否一致",
      icon: "warning",
      confirmButtonText: "確認",
    });
  } else {
    let registerReq = {
      name: name.value,
      phone: phone.value,
      email: email.value,
      password: pwd.value,
    };

    const registerRes = await register(registerReq);
    switch (registerRes) {
      case "PARAM_ERROR":
        Swal.fire({
          title: "請確認所有欄位是否輸入",
          icon: "warning",
          confirmButtonText: "確認",
        });
        break;
      case "EMAIL_IS_EXISTED":
        Swal.fire({
          title: "該信箱已註冊",
          icon: "warning",
          confirmButtonText: "確認",
        });
        break;
      case "PHONE_FORMAT_ERROR":
        Swal.fire({
          title: "請確認手機號碼格式是否正確",
          icon: "warning",
          confirmButtonText: "確認",
        });
        break;
      case "EMAIL_FORMAT_ERROR":
        Swal.fire({
          title: "請確認信箱格式是否正確",
          icon: "warning",
          confirmButtonText: "確認",
        });
        break;
      case "PASSWORD_FORMAT_ERROR":
        Swal.fire({
          title: "請確認密碼格式是否正確\n",
          text: "需含英數字\r至少8個字元",
          icon: "warning",
          confirmButtonText: "確認",
        });
        break;
      case "REGISTER_ERROR":
        Swal.fire({
          title: "系統異常\r請稍後再試",
          icon: "error",
          confirmButtonText: "確認",
        });
        break;
      case "SUCCESSFUL":
        Swal.fire({
          title: "註冊成功",
          text: "點擊確認以跳轉至登入畫面",
          icon: "success",
          confirmButtonText: "確認",
        }).then(() => {
          router.push({ name: "login" });
        });
        break;
    }
  }
};

const showPwd = () => {
  isDisplay.value = !isDisplay.value;
  isDisplay.value ? (inputType.value = "password") : (inputType.value = "text");
};

const showPwd2 = () => {
  isDisplay2.value = !isDisplay2.value;
  isDisplay2.value
    ? (inputType2.value = "password")
    : (inputType2.value = "text");
};

const turnToHome = () => {
  router.push({ name: "home" });
};
</script>

<template>
  <section class="main bg-container">
    <img
      class="bg-img"
      src="../../../public/bgImg/registerBgImg.jpg"
      alt="login-background-image"
    />
    <div class="login-area">
      <span class="title">帳號申請</span>
      <div class="inputbar">
        <i class="fa-regular fa-user input-icon"></i>
        <input v-model.trim="name" type="text" placeholder="請輸入使用者名稱" />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-mobile-screen-button input-icon"></i>
        <input v-model.trim="phone" type="text" placeholder="請輸入手機號碼" />
      </div>
      <div class="inputbar">
        <i class="fa-regular fa-envelope input-icon"></i>
        <input v-model.trim="email" type="text" placeholder="請輸入信箱" />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-unlock-keyhole input-icon"></i>
        <input
          :type="inputType"
          v-model.trim="pwd"
          class="input-pwd"
          placeholder="請輸入密碼(需含英數字至少8字元)"
        />
        <i v-if="isDisplay" @click="showPwd()" class="fa-regular fa-eye"></i>
        <i
          v-if="!isDisplay"
          @click="showPwd()"
          class="fa-regular fa-eye-slash"
        ></i>
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-unlock-keyhole input-icon"></i>
        <input
          v-model.trim="pwd2"
          :type="inputType2"
          class="input-pwd"
          placeholder="請再次輸入密碼"
        />
        <i v-if="isDisplay2" @click="showPwd2()" class="fa-regular fa-eye"></i>
        <i
          v-if="!isDisplay2"
          @click="showPwd2()"
          class="fa-regular fa-eye-slash"
        ></i>
      </div>
      <button @click="registerHandler()" type="button" class="register btn">
        註冊
      </button>
      <button @click="turnToHome()" type="button" class="back-home btn">
        返回首頁
      </button>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  position: relative;

  .login-area {
    background-color: #ffffff;
    width: 30vw;
    height: 70vh;
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

    .register,
    .back-home {
      width: 80%;
      height: 8%;
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
