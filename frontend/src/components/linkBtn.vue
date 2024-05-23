<script setup>
import { RouterLink } from "vue-router";

const props = defineProps({
  icon: String,
});
</script>

<template>
  <div class="link-btn">
    <RouterLink class="link-circle">
      <i :class="icon"></i>
    </RouterLink>
  </div>
</template>

<style scoped lang="scss">
.link-btn {
  .link-circle {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 3vw;
    height: 3vw;
    text-decoration: none;
    border-radius: 50%;
    border: 1px solid #777;
    padding: 1rem;
  }
}
</style>
