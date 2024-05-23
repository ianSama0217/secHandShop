<script setup>
const props = defineProps({
  cartId: Number,
  name: String,
  imgSrc: String,
  quantity: Number,
  price: Number,
  totalPrice: Number,
  turnPageHandler: Function,
  deleteHandler: Function,
});
</script>

<template>
  <div class="cartCard">
    <input type="checkbox" name="" id="" />

    <div class="img-container">
      <img :src="imgSrc" />
    </div>

    <span class="f-base">{{ name }}</span>
    <span class="f-base">{{ price }}</span>

    <div class="box">
      <div class="count-btn">
        <button type="button" class="add" @click="reduceBuyQuantity()">
          <i class="fa-solid fa-minus"></i>
        </button>
        <input type="number" min="1" :value="quantity" />
        <button type="button" class="reduce" @click="addBuyQuantity()">
          <i class="fa-solid fa-plus"></i>
        </button>
      </div>
    </div>

    <span class="f-base">{{ totalPrice }}</span>

    <div class="btn-bar">
      <button @click="turnPageHandler" type="button" class="btn get-product">
        查看商品
      </button>
      <button @click="deleteHandler" type="button" class="btn delete">
        刪除
      </button>
    </div>
  </div>
</template>

<style scoped lang="scss">
.cartCard {
  border: 1px solid #777;
  box-shadow: 0 8px 6px -6px #777;
  border-radius: 8px;
  height: 25vh;
  width: 75vw;
  margin-top: 1rem;
  padding: 0.2rem 1rem;
  display: grid;
  grid-template-columns: 10% 20% 15% 15% 15% 15% 10%;
  align-items: center;

  .img-container {
    width: 10vw;
    height: 10vw;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow-y: hidden;

    img {
      width: 100%;
    }
  }

  .count-btn {
    display: flex;
    align-items: center;
    input[type="number"] {
      width: 3vw;
      height: 2vw;
      font-size: 1.2rem;
    }

    .add,
    .reduce {
      text-decoration: none;
      border: none;
      width: 2vw;
      height: 2vw;

      &:hover {
        cursor: pointer;
      }
    }
  }

  .btn-bar {
    height: 50%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    .get-product {
      font-size: 1rem;
      font-weight: bold;
      color: white;
      background-color: #5bbcff;
      padding: 0.2rem 0.5rem;
    }

    .delete {
      font-size: 1rem;
      font-weight: bold;
      color: white;
      background-color: #ff3d3d;
      padding: 0.2rem 0.5rem;
    }
  }
}
</style>
