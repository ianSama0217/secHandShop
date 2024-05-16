<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import Swal from "sweetalert2";
import sidebar from "@/components/product/sidebar.vue";
import storeProductCard from "@/components/product/storeProductCard.vue";
import { productApi } from "@/api";

const router = useRouter();
const { getStore } = productApi;
const getStoreRes = ref({});
const productList = ref([]);

const getStoreHandler = async (id) => {
  getStoreRes.value = await getStore(id);

  if (getStoreRes.value.rtnMsg == "STORE_NOT_FOUND") {
    Swal.fire({
      title: "發生異常",
      icon: "error",
      confirmButtonText: "確認",
    }).then(() => {
      router.push({ name: "home" });
    });
  }

  productList.value = getStoreRes.value.productList;
};

const turnToCreateProduct = () => {
  router.push({ name: "createProduct" });
};

onMounted(() => {
  const userId = useRoute().params.id;
  getStoreHandler(userId);
});
</script>

<template>
  <section class="main">
    <sidebar></sidebar>
    <div class="product-area">
      <div class="card-area">
        <span class="hint-font" v-if="productList.length == 0"
          >目前尚未加入商品</span
        >
        <div v-for="item in productList">
          <storeProductCard
            :productId="item.productId"
            :name="item.name"
            :photo="'data:' + item.mimeType + ';base64,' + item.photo"
            :inventory="item.inventory"
            :price="item.price"
          ></storeProductCard>
        </div>
      </div>
      <button @click="turnToCreateProduct()" type="button" class="base-btn add">
        新增商品
      </button>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  display: flex;
  margin-top: 0.5rem;

  .product-area {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-bottom: 10vh;

    .card-area {
      width: 70vw;
      display: flex;
      flex-wrap: wrap;
      align-items: center;
    }

    .add {
      position: absolute;
      bottom: 10px;
      left: 50%;
      transform: translateX(-50%);
      background-color: #ff3d3d;
    }
  }
}
</style>
