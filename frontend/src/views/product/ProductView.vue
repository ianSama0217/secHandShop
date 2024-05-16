<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import Swal from "sweetalert2";
import { productApi } from "@/api";

const router = useRouter();
const { getProduct } = productApi;
const getProductRes = ref({});
const product = ref({});

const buyQuantity = ref(1);

const getProductHandler = async (id) => {
  getProductRes.value = await getProduct(id);

  if (getProductRes.value.rtnMsg == "PRODUCT_NOT_FOUND") {
    Swal.fire({
      title: "發生異常",
      icon: "error",
      confirmButtonText: "確認",
    }).then(() => {
      router.push({ name: "home" });
    });
  }

  product.value = getProductRes.value.product;
};

const addBuyQuantity = () => {
  if (buyQuantity.value == product.value.inventory) {
    return;
  }
  buyQuantity.value++;
};

const reduceBuyQuantity = () => {
  if (buyQuantity.value == 1) {
    return;
  }
  buyQuantity.value--;
};

const turnToBack = () => {
  window.history.back();
};

onMounted(() => {
  const productId = useRoute().params.id;
  getProductHandler(productId);
});
</script>

<template>
  <section class="main">
    <div class="img-container">
      <img
        :src="'data:image/png;base64,' + product.photo"
        :alt="product.name"
        :title="product.name"
      />
    </div>
    <div class="product-info">
      <span class="title">{{ product.name }}</span>

      <div class="box">
        <span class="title-base">商品價格</span>
        <span class="bold price">NT$ {{ product.price }}</span
        ><span class="title-base">元</span>
      </div>

      <div class="box">
        <span class="f-base">3.5</span>
        <div class="box2">
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star"></i>
          <i class="fa-solid fa-star-half-stroke"></i>
          <i class="fa-regular fa-star"></i>
        </div>
        <div class="box">
          <span class="f-base">3{{}}</span>
          <span class="title-base">評價</span>
        </div>
      </div>

      <div class="box">
        <span class="title-base">刊登數量</span>
        <span class="f-base bold">{{ product.inventory }}</span>
      </div>

      <div class="box">
        <span class="title-base">銷售總數</span>
        <span class="f-base bold">{{ product.sellCount }}</span>
      </div>

      <div class="box">
        <span class="title-base">付款方式</span>
        <div class="box2">
          <img
            class="pay-img"
            src="../../../public/iconImg/7-11-logo.jpg"
            alt=""
          />
          <img
            class="pay-img"
            src="../../../public/iconImg/familymart-logo.png"
            alt=""
          />
          <img
            class="pay-img"
            src="../../../public/iconImg/ibon-logo.png"
            alt=""
          />
          <img
            class="pay-img"
            src="../../../public/iconImg/post-logo.jpg"
            alt=""
          />
        </div>
      </div>

      <div class="box">
        <span class="title-base">取貨方式</span>
        <div class="box2">
          <span class="pickup">郵局寄出90元</span>
          <span class="pickup">宅配/快遞90元</span>
          <span class="pickup">7-11取貨付款70元</span>
          <span class="pickup">7-11取貨70元</span>
          <span class="pickup">全家取貨付款70元</span>
          <span class="pickup">全家取貨70元</span>
        </div>
      </div>

      <div class="box">
        <span class="title-base">購買數量</span>
        <div class="count-btn">
          <button type="button" class="add" @click="reduceBuyQuantity()">
            <i class="fa-solid fa-minus"></i>
          </button>
          <input type="number" min="1" v-model="buyQuantity" />
          <button type="button" class="reduce" @click="addBuyQuantity()">
            <i class="fa-solid fa-plus"></i>
          </button>
        </div>
      </div>

      <div class="btn-bar">
        <button @click="turnToBack()" type="button" class="base-btn back">
          返回前頁
        </button>
        <button type="button" class="base-btn buy">立即購買</button>
        <button type="button" class="base-btn add-cart">加入購物車</button>
        <button type="button" class="base-btn send-msg">
          <i class="fa-regular fa-comment-dots icon-link"></i>詢問商品
        </button>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
.main {
  height: 90vh;
  padding: 1rem 10rem 1rem 5rem;
  display: flex;

  .img-container {
    align-self: center;
    width: 30vw;
    height: 30vw;
    margin-right: 1rem;
    display: flex;
    align-items: center;

    img {
      width: 100%;
    }
  }

  .product-info {
    width: 45vw;
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    .box {
      display: flex;
      align-items: center;

      .box2 {
        display: flex;
        flex-wrap: wrap;
        max-width: 38vw;
        margin-right: 0.5rem;

        .pay-img {
          width: auto;
          height: 2rem;
        }

        .pickup {
          font-size: 1.05rem;
          margin: 0.2rem 0.5rem;
          padding: 0.2rem 0.5rem;
          border: 1px solid #777;
          border-radius: 8px;
        }
      }

      .price {
        margin-right: 0.5rem;
        font-size: 1.5rem;
        color: #ff3d3d;
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
    }

    .btn-bar {
      display: flex;
      justify-content: space-between;
      width: 50vw;

      .back {
        background-color: #a1a1a1;
      }

      .buy {
        background-color: #ff3d3d;
      }

      .add-cart {
        background-color: #ff8e3d;
      }

      .send-msg {
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #5bbcff;
      }
    }
  }
}
</style>
