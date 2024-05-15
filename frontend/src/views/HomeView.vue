<script setup>
import { onMounted, ref } from "vue";
import searchSidebar from "@/components/searchSidebar.vue";
import productCard from "@/components/product/productCard.vue";
import { productApi } from "@/api";

const { search } = productApi;
let userId;
const productList = ref([]);

const searchHandler = async (name, type, lowPrice, highPrice, sort) => {
  if (sessionStorage.getItem("user")) {
    userId = JSON.parse(sessionStorage.getItem("user")).userId;
  } else {
    userId = null;
  }
  productList.value = await search(
    userId,
    name,
    type,
    lowPrice,
    highPrice,
    sort
  );
};

onMounted(() => {
  searchHandler(null, null, null, null, null);
});
</script>

<template>
  <section class="main">
    <searchSidebar></searchSidebar>
    <div class="product-area">
      <div class="searchbar">
        <input type="search" />
        <i class="fa-solid fa-magnifying-glass"></i>
      </div>
      <div class="card-area">
        <span class="hint-font" v-if="productList.length == 0"
          >找不到相關商品 請再次確認</span
        >
        <div v-for="item in productList">
          <productCard
            :productId="item.productId"
            :name="item.name"
            :photo="'data:image/png;base64,' + item.photo"
            :price="item.price"
          ></productCard>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  display: flex;
  margin-top: 0.5rem;

  .product-area {
    display: flex;
    flex-direction: column;
    align-items: center;

    .searchbar {
      position: relative;
      width: 40vw;
      height: 5vh;

      input[type="search"] {
        width: 100%;
        height: 100%;
        font-size: 1.2rem;
        padding-right: 30px;
      }

      .fa-magnifying-glass {
        position: absolute;
        top: 50%;
        right: 8px;
        transform: translateY(-50%);

        &:hover {
          cursor: pointer;
        }
      }
    }

    .card-area {
      width: 70vw;
      display: flex;
      flex-wrap: wrap;
      align-items: center;
    }
  }
}
</style>
