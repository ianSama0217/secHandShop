<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
const props = defineProps({
  productId: Number,
  name: String,
  photo: String,
  price: Number,
});

const turnToProduct = (id) => {
  router.push(`/product/${id}`);
};
</script>

<template>
  <section class="productCard">
    <div class="overlay" :title="name" @click="turnToProduct(productId)"></div>
    <img class="photo" :src="photo" :alt="name" />
    <div class="infobar">
      <i class="fa-solid fa-dollar-sign"
        ><span class="bold f-base">{{ price }}</span></i
      >
      <i class="fa-solid fa-cart-shopping f-base" title="加入購物車"></i>
    </div>
  </section>
</template>

<style scoped lang="scss">
.productCard {
  position: relative;
  box-shadow: 0 8px 6px -6px #777;
  width: 15vw;
  height: 35vh;
  border-radius: 8px;
  overflow: hidden;
  margin: 1rem 0.5rem;

  &:hover {
    cursor: pointer;
    .overlay {
      opacity: 1;
    }
  }

  .overlay {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    border-radius: 8px;
    background-color: rgba(255, 255, 255, 0.5);
    backdrop-filter: blur(2px);
    transition: background-color 0.3s ease;
    opacity: 0;
  }

  .photo {
    width: 100%;
    height: 100%;
  }

  .infobar {
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.3rem 0.5rem;
    background-color: rgba(255, 255, 255, 0.5);
    backdrop-filter: blur(2px);
    opacity: 1;
    border-radius: 8px;

    .fa-cart-shopping {
      z-index: 10;
      transition: all 0.3s ease;
      &:hover {
        scale: 1.15;
        color: #777;
      }
    }
  }
}
</style>
