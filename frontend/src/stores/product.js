import { ref } from "vue";
import { defineStore } from "pinia";

export const useProductStore = defineStore("product", () => {
  const typeOption = ref([
    {
      key: 1,
      value: "食品",
    },
    {
      key: 2,
      value: "玩具",
    },
    {
      key: 3,
      value: "書籍",
    },
    {
      key: 4,
      value: "藝術創作",
    },
    {
      key: 999,
      value: "其他",
    },
  ]);

  const stateOption = ref([
    {
      key: 0,
      value: "未公開",
    },
    {
      key: 1,
      value: "一般販售",
    },
    // {
    //   key: 2,
    //   value: "已售完",
    // },
  ]);

  return { typeOption, stateOption };
});
