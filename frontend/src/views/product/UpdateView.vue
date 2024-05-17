<script setup>
import { onMounted, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { storeToRefs } from "pinia";
import { useProductStore } from "@/stores/product.js";
import Swal from "sweetalert2";
import { productApi } from "@/api";

const router = useRouter();
const { getProduct, updateProduct, deleteProduct } = productApi;
const userId = JSON.parse(localStorage.getItem("user")).userId;
const productId = useRoute().params.id;
const getProductRes = ref({});
const product = ref({});
const productStore = useProductStore();
const { typeOption, stateOption } = storeToRefs(productStore);

const name = ref(null);
const type = ref(null);
const price = ref(null);
const state = ref(null);
const inventory = ref(null);
const imageSrc = ref(null);
let mime;
let base64;

const getProductHandler = async (id) => {
  getProductRes.value = await getProduct(id);

  if (getProductRes.value.rtnMsg == "PRODUCT_NOT_FOUND") {
    Swal.fire({
      title: "發生異常",
      icon: "error",
      confirmButtonText: "確認",
    }).then(() => {
      router.push({ name: "getStore", params: { id: userId } });
    });
  }

  product.value = getProductRes.value.product;
  name.value = product.value.name;
  type.value = product.value.type;
  price.value = product.value.price;
  state.value = product.value.state;
  inventory.value = product.value.inventory;
  imageSrc.value =
    "data:" + product.value.mimeType + ";base64," + product.value.photo;
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

const updateProductHandler = async () => {
  if (imageSrc.value) {
    mime = imageSrc.value.split(";")[0].split(":")[1];
    base64 = imageSrc.value.split(",")[1];
  }

  let updateProductReq = {
    productId: productId,
    name: name.value,
    type: type.value,
    price: price.value,
    inventory: inventory.value,
    state: state.value,
    mimeType: mime,
    photo: base64,
  };

  const updateProductRes = await updateProduct(updateProductReq);

  switch (updateProductRes) {
    case "PARAM_ERROR":
      Swal.fire({
        title: "請確認所有欄位是否輸入",
        icon: "warning",
        confirmButtonText: "確認",
      });
      break;
    case "UPDATE_PRODUCT_ERROR":
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      break;
    case "SUCCESSFUL":
      Swal.fire({
        title: "更新商品成功",
        text: "點擊確認以跳轉至商店",
        icon: "success",
        confirmButtonText: "確認",
      }).then(() => {
        router.push({ name: "getStore", params: { id: userId } });
      });
      break;
  }
};

const deleteProductHandler = () => {
  Swal.fire({
    color: "red",
    title: "確定要刪除商品",
    text: "刪除後商品無法復原",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "確認",
    cancelButtonText: "取消",
    reverseButtons: true,
  }).then((res) => {
    if (res.isConfirmed) {
      deleteProduct(productId);
      Swal.fire({
        title: "商品刪除成功",
        icon: "success",
        confirmButtonText: "確認",
      }).then(() => {
        router.push({ name: "getStore", params: { id: userId } });
      });
    }
  });
};

const turnToBack = () => {
  window.history.back();
};

onMounted(() => {
  getProductHandler(productId);
});
</script>

<template>
  <section class="main">
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
    <div class="product-info">
      <span class="title">新增商品</span>
      <div class="inputbar">
        <i class="fa-solid fa-box input-icon"></i>
        <input v-model.trim="name" type="text" placeholder="請輸入商品名稱" />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-tag input-icon"></i>
        <select v-model="type">
          <option value="null" disabled>請選擇商品類型</option>
          <option v-for="item in typeOption" :value="item.key">
            {{ item.value }}
          </option>
        </select>
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-dollar-sign input-icon"></i>
        <input
          v-model.trim="price"
          type="number"
          placeholder="請輸入商品價格"
        />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-warehouse input-icon"></i>
        <input
          v-model.trim="inventory"
          type="number"
          placeholder="請輸入商品庫存數量"
        />
      </div>
      <div class="inputbar">
        <i class="fa-solid fa-paperclip input-icon"></i>
        <select v-model="state">
          <option value="null" disabled>請選擇商品狀態</option>
          <option v-for="item in stateOption" :value="item.key">
            {{ item.value }}
          </option>
        </select>
      </div>
      <div class="btn-bar">
        <button @click="turnToBack()" type="button" class="base-btn cancel">
          返回前頁
        </button>
        <button
          @click="deleteProductHandler()"
          type="button"
          class="base-btn delete"
        >
          刪除商品
        </button>
        <button
          @click="updateProductHandler()"
          type="button"
          class="base-btn update"
        >
          儲存變更
        </button>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  height: 90vh;
  padding: 3rem 10rem 1rem 5rem;
  display: flex;
  justify-content: space-around;

  .img-info {
    display: flex;
    flex-direction: column;
    align-items: center;

    .img-container {
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

  .product-info {
    width: 30vw;
    height: 70vh;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;

    .inputbar {
      input[type="text"],
      input[type="number"],
      select {
        width: 100%;
        height: 100%;
        font-size: 1rem;
        padding-left: 30px;
        border-radius: 8px;
      }
    }

    .btn-bar {
      width: 40vw;
      display: flex;
      justify-content: space-between;

      .cancel {
        background-color: #a1a1a1;
      }

      .delete {
        background-color: #ff3d3d;
      }

      .update {
        background-color: #2eca5d;
      }
    }
  }
}
</style>
