import { ref, onMounted } from "vue";
import { defineStore } from "pinia";

export const useAccountStore = defineStore("account", () => {
  const userInfo = ref(JSON.parse(localStorage.getItem("user")));

  const setUser = (res) => {
    userInfo.value = res;
    console.log(userInfo.value);
  };

  onMounted(() => {
    console.log("store user:" + JSON.stringify(userInfo.value));
  });

  return { userInfo, setUser };
});
