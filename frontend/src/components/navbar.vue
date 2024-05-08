<script setup></script>

<template>
  <section class="navbar">
    <div class="logo">
      <i class="fa-brands fa-shopify"></i>
      <span>二手拍賣網</span>
    </div>
    <div class="login">
      <div class="border">
        <i class="fa-regular fa-circle-user icon"></i>
        <span>會員登入</span>
      </div>
    </div>
  </section>
</template>

<style scoped lang="scss">
.navbar {
  min-height: 10vh;
  box-shadow: 0 8px 6px -6px #777;
  display: flex;
  justify-content: space-between;
  padding: 0 1rem;
  margin-bottom: 0.5rem;

  .logo {
    display: flex;
    align-items: center;
    .fa-shopify {
      color: #5bbcff;
      font-size: 3rem;
      margin-right: 0.5rem;
    }
    span {
      color: #5bbcff;
      font-size: 1.8rem;
    }
  }

  .login {
    display: flex;
    align-items: center;

    .border {
      display: flex;
      align-items: center;
      padding: 0.2rem 0.5rem;
      transition: all 0.3s ease;

      &:hover {
        cursor: pointer;
        border: 2px solid #777;
        border-radius: 8px;
      }
    }

    span {
      font-size: 1.15rem;
    }
  }
}
</style>
