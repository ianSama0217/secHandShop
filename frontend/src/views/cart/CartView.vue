<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { cartApi } from "@/api";
import Swal from "sweetalert2";
import cartCard from "../../components/cart/cartCard.vue";

const router = useRouter();
const { getCart, deleteCart } = cartApi;
const getCartRes = ref({});
const cartList = ref([]);

const deleteCartHandler = (cartId) => {
  Swal.fire({
    color: "red",
    title: "確定要移除商品",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "確認",
    cancelButtonText: "取消",
    reverseButtons: true,
  }).then((res) => {
    if (res.isConfirmed) {
      deleteCart(cartId);
      Swal.fire({
        title: "商品已移除購物車",
        icon: "success",
        confirmButtonText: "確認",
      }).then(() => {
        location.reload();
      });
    }
  });
};

const getCartHandler = async (id) => {
  getCartRes.value = await getCart(id);

  if (getCartRes.value.rtnMsg == "ACCOUNT_NOT_FOUND") {
    Swal.fire({
      title: "發生異常",
      icon: "error",
      confirmButtonText: "確認",
    }).then(() => {
      router.push({ name: "home" });
    });
  }

  cartList.value = getCartRes.value.cartList;
};

const turnToProduct = (productId) => {
  router.push({ name: "getProduct", params: { id: productId } });
};

onMounted(() => {
  const userId = useRoute().params.id;
  getCartHandler(userId);
});
</script>

<template>
  <section class="main">
    <span class="hint-font" v-if="cartList.length == 0">目前尚未加入商品</span>
    <div class="title-bar" v-if="cartList.length != 0">
      <span></span>
      <span class="f-base bold">圖片</span>
      <span class="f-base bold">名稱</span>
      <span class="f-base bold">單價</span>
      <span class="f-base bold">數量</span>
      <span class="f-base bold">總金額</span>
      <span class="f-base bold">操作</span>
    </div>
    <div v-for="item in cartList">
      <cartCard
        :imgSrc="'data:' + item.mimeType + ';base64,' + item.photo"
        :name="item.name"
        :quantity="item.quantity"
        :price="item.price"
        :totalPrice="item.totalPrice"
        :turnPageHandler="() => turnToProduct(item.productId)"
        :deleteHandler="() => deleteCartHandler(item.cartId)"
      ></cartCard>
    </div>
    <div class="checkout-bar" v-if="cartList.length != 0">
      <span class="f-base bold"
        >總金額(不含運費)：<i class="fa-solid fa-dollar-sign"></i>1234{{}}</span
      >

      <button type="button" class="base-btn checkout">結帳</button>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  padding: 1rem 0;
  display: flex;
  flex-direction: column;
  align-items: center;

  .title-bar {
    width: 75vw;
    border: 1px solid #777;
    border-radius: 8px;
    display: grid;
    grid-template-columns: 10% 20% 15% 15% 15% 15% 10%;
    align-items: center;
    padding: 0.2rem 1rem;
  }

  .checkout-bar {
    width: 75vw;
    margin-top: 1.5rem;
    display: flex;
    justify-content: space-around;
    align-items: center;

    .checkout {
      background-color: #ff8e3d;
    }
  }
}
</style>
