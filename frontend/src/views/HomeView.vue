<script setup>
import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";
import { useProductStore } from "@/stores/product.js";
import productCard from "@/components/product/productCard.vue";
import { productApi } from "@/api";

const productStore = useProductStore();
const { typeOption } = storeToRefs(productStore);
const { search } = productApi;
let userId;
const productList = ref([]);

const name = ref(null);
const type = ref(null);
const lowPrice = ref(null);
const highPrice = ref(null);
const sort = ref(null);

const searchHandler = async (name, type, lowPrice, highPrice, sort) => {
  userId = localStorage.getItem("user")
    ? JSON.parse(localStorage.getItem("user")).userId
    : null;

  productList.value = await search(
    userId,
    name,
    type,
    lowPrice,
    highPrice,
    sort
  );
};

const clearHandler = () => {
  name.value = null;
  type.value = null;
  lowPrice.value = null;
  highPrice.value = null;
  sort.value = null;
};

onMounted(() => {
  searchHandler(null, null, null, null, null);
});
</script>

<template>
  <section class="main">
    <section class="searchSidebar">
      <div class="type-area">
        <span class="bold search-title">尋找商品</span>
        <select v-model="type">
          <option value="null" disabled>請選擇商品類型</option>
          <option :value="null">全部商品</option>
          <option v-for="item in typeOption" :value="item.key">
            {{ item.value }}
          </option>
        </select>
      </div>

      <div class="price-area">
        <span class="bold search-title">價格</span>
        <div class="price">
          <span>從</span>
          <input v-model="lowPrice" type="number" />
          <span>~</span>
          <input v-model="highPrice" type="number" />
        </div>
        <div class="sort-area">
          <div class="sort">
            <input
              v-model="sort"
              type="radio"
              name="sort"
              value="price_asc"
              id="price_asc"
            />
            <label for="price_asc">由低到高</label>
          </div>
          <div class="sort">
            <input
              v-model="sort"
              type="radio"
              name="sort"
              value="price_desc"
              id="price_desc"
            />
            <label for="price_desc">由高到低</label>
          </div>
        </div>
      </div>
      <div class="btn-bar">
        <button @click="clearHandler()" type="button" class="base-btn clear">
          清除
        </button>
        <button
          @click="searchHandler(name, type, lowPrice, highPrice, sort)"
          type="button"
          class="base-btn search"
        >
          搜尋
        </button>
      </div>
    </section>
    <div class="product-area">
      <div class="searchbar">
        <input
          @keyup.enter="searchHandler(name, type, lowPrice, highPrice, sort)"
          v-model="name"
          type="search"
          placeholder="查詢商品"
        />
        <i
          @click="searchHandler(name, type, lowPrice, highPrice, sort)"
          class="fa-solid fa-magnifying-glass"
        ></i>
      </div>
      <div class="card-area">
        <span class="hint-font" v-if="productList?.length == 0"
          >找不到相關商品 請再次確認</span
        >
        <div v-for="item in productList">
          <productCard
            :productId="item.productId"
            :name="item.name"
            :photo="'data:' + item.mimeType + ';base64,' + item.photo"
            :price="item.price"
          ></productCard>
        </div>
      </div>
    </div>
  </section>

  <div v-if="!userId">
    <img src="../../public/iconImg/add-img.png" alt="" class="advertise" />
  </div>
</template>

<style scoped lang="scss">
.main {
  display: flex;
  margin-top: 0.5rem;

  .searchSidebar {
    list-style-type: none;
    margin: 1rem;
    padding: 0 0.5rem;
    width: 15vw;
    min-height: 80vh;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    border: 1px solid gray;
    border-radius: 8px;
    box-shadow: 0 8px 6px -6px #777;
    font-size: 1.15rem;

    .search-title {
      align-self: center;
      margin-bottom: 0.5rem;
    }

    .type-area,
    .price-area {
      display: flex;
      flex-direction: column;

      select {
        width: 100%;
        height: 100%;
        font-size: 1rem;
        border-radius: 8px;
        padding: 0.2rem;
      }

      .price {
        display: flex;

        span {
          margin: 0 0.2rem;
        }

        input[type="number"] {
          width: 35%;
        }
      }

      .sort-area {
        display: flex;
        flex-direction: column;
        .sort {
          margin-top: 0.3rem;
        }
      }
    }

    .btn-bar {
      display: flex;
      justify-content: space-around;
      align-items: center;

      .clear {
        width: 35%;
        background-color: #b4b4b4;
      }

      .search {
        width: 35%;
        background-color: #ff8e3d;
      }
    }
  }

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

.advertise {
  position: fixed;
  right: 2%;
  top: 12%;
  width: 10vw;
  height: auto;
}
</style>
