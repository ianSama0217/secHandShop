<script setup>
import sidebar from "@/components/sidebar.vue";
import productCard from "@/components/product/productCard.vue";
</script>

<template>
  <section class="main">
    <sidebar></sidebar>
    <div class="product-area">
      <div class="searchbar">
        <input type="search" />
        <i class="fa-solid fa-magnifying-glass"></i>
      </div>
      <div class="card-area">
        <div v-for="item in 20">
          <productCard></productCard>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  display: flex;

  .product-area {
    display: flex;
    flex-direction: column;
    align-items: center;

    .searchbar {
      position: relative;
      border: 0px solid green;
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
