<script setup>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import Swal from "sweetalert2";
import { userApi } from "@/api";

const userId = JSON.parse(localStorage.getItem("user")).userId;
const router = useRouter();
const { getUser, updateUser } = userApi;

const name = ref(null);
const phone = ref(null);
const email = ref(null);
const imageSrc = ref(null);
let mime;
let base64;

const getUserHandler = async () => {
  const getUserRes = await getUser(userId);
  if (getUserRes.rtnMsg == "ACCOUNT_NOT_FOUND") {
    Swal.fire({
      title: "發生異常",
      icon: "error",
      confirmButtonText: "確認",
    }).then(() => {
      turnToHome();
    });
  }

  name.value = getUserRes.user.name;
  phone.value = getUserRes.user.phone;
  email.value = getUserRes.user.email;
  if (getUserRes.user.mimeType && getUserRes.user.photo) {
    imageSrc.value =
      "data:" + getUserRes.user.mimeType + ";base64," + getUserRes.user.photo;
  } else {
    imageSrc.value = null;
  }
};

const FileUploadHandler = (event) => {
  const file = event.target.files[0];

  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      imageSrc.value = e.target.result;
    };
    reader.readAsDataURL(file);
  }
};

const updateUserHandler = async () => {
  if (imageSrc.value) {
    mime = imageSrc.value.split(";")[0].split(":")[1];
    base64 = imageSrc.value.split(",")[1];
  }

  let updateUserReq = {
    userId: userId,
    name: name.value,
    phone: phone.value,
    mimeType: mime,
    photo: base64,
  };

  const updateUserRes = await updateUser(updateUserReq);

  switch (updateUserRes) {
    case "PARAM_ERROR":
      Swal.fire({
        title: "請確認姓名、電話是否輸入",
        icon: "warning",
        confirmButtonText: "確認",
      });
      break;
    case "UPDATE_ACCOUNT_ERROR":
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      break;
    case "SUCCESSFUL":
      Swal.fire({
        title: "更新商品成功",
        text: "點擊確認以返回首頁",
        icon: "success",
        confirmButtonText: "確認",
      }).then(() => {
        turnToHome();
      });
      break;
  }
};

const turnToHome = () => {
  router.push({ name: "home" });
};

onMounted(() => {
  getUserHandler();
});
</script>

<template>
  <section class="main bg-container">
    <img
      class="bg-img"
      src="../../../public/bgImg/updateBgImg.jpg"
      alt="login-background-image"
    />
    <div class="img-info">
      <div class="img-container">
        <img :src="imageSrc" alt="Preview Image" v-if="imageSrc" />
        <img src="../../../public/iconImg/defalt-img.png" v-if="!imageSrc" />
      </div>

      <div class="custom-file-upload">
        <label for="file-upload">
          <i class="fa-solid fa-image icon-link"></i> 選擇圖片
        </label>
        <input
          id="file-upload"
          accept="image/*"
          type="file"
          @change="FileUploadHandler"
        />
      </div>
    </div>
    <div class="update-area">
      <span class="title">帳號設定</span>
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
        <input :value="email" type="text" disabled />
      </div>
      <button @click="updateUserHandler()" type="button" class="update btn">
        儲存變更
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

  .img-info {
    position: absolute;
    top: 50%;
    left: 10%;
    transform: translateY(-50%);
    display: flex;
    flex-direction: column;
    align-items: center;

    .img-container {
      background-color: white;
      border-radius: 50%;
      width: 30vw;
      height: 30vw;
      margin-right: 1rem;
      display: flex;
      justify-content: center;
      align-items: center;
      overflow-y: hidden;

      img {
        width: 100%;
      }
    }
  }

  .update-area {
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
      input[type="text"] {
        width: 100%;
        height: 100%;
        font-size: 1rem;
        padding-left: 30px;
        border-radius: 8px;
      }
    }

    .update,
    .back-home {
      width: 80%;
      height: 10%;
      font-size: 1rem;
      color: white;
      background-color: #5bbcff;
    }
  }
}
</style>
