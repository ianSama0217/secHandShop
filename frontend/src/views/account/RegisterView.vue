<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const isDisplay = ref(true);
const inputType = ref("password");
const isDisplay2 = ref(true);
const inputType2 = ref("password");

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
        <input type="text" placeholder="請輸入使用者名稱" />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-mobile-screen-button input-icon"></i>
        <input type="text" placeholder="請輸入手機號碼" />
      </div>
      <div class="inputbar">
        <i class="fa-regular fa-envelope input-icon"></i>
        <input type="text" placeholder="請輸入信箱" />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-unlock-keyhole input-icon"></i>
        <input :type="inputType" class="input-pwd" placeholder="請輸入密碼" />
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
      <button type="button" class="register btn">註冊</button>
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
