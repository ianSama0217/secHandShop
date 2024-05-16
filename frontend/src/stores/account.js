import { ref, onMounted } from "vue";
import { defineStore } from "pinia";

export const useAccountStore = defineStore("account", () => {
  const userInfo = ref({});

  const setUser = (res) => {
    userInfo.value = res;
    console.log(userInfo.value);
  };

  // onMounted(async () => {
  //   await setUser();
  // });

  return { userInfo, setUser };
});
