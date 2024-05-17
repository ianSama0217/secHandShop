<script setup></script>

<template>
  <ul class="sidebar">
    <li class="link">
      <i class="fa-solid fa-box-archive icon-link"></i>商品管理
    </li>
    <li class="link"><i class="fa-regular fa-file icon-link"></i>銷售訂單</li>
    <li class="link">
      <i class="fa-regular fa-file-lines icon-link"></i>出貨訂單
    </li>
    <li class="link">
      <i class="fa-solid fa-file-circle-check icon-link"></i>完成訂單
    </li>
    <li class="link">
      <i class="fa-regular fa-file-excel icon-link"></i>取消訂單
    </li>
    <li class="link"><i class="fa-solid fa-list icon-link"></i>銷售報表</li>
  </ul>
</template>

<style scoped lang="scss">
.sidebar {
  list-style-type: none;
  margin: 1rem;
  width: 15vw;
  height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  border: 1px solid gray;
  border-radius: 8px;
  box-shadow: 0 8px 6px -6px #777;
  font-size: 1.2rem;

  .link {
    padding: 0.5rem 0.5rem;

    &:hover {
      cursor: pointer;
      background-color: #d4d4d4;
    }
  }
}
</style>
