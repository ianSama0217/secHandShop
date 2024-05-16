import axios from "axios";
import Swal from "sweetalert2";

//取得user資料
const getUser = (id) => {
  return axios
    .get(`http://localhost:8080/user/${id}`)
    .then((res) => {
      //console.log(res.data);
      return res.data;
    })
    .catch((e) => {
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      throw e;
    });
};

//註冊
const register = (req) => {
  return axios({
    method: "post",
    url: `http://localhost:8080/user/register`,
    data: req,
  })
    .then((res) => {
      //console.log(res.data.rtnMsg);
      return res.data.rtnMsg;
    })
    .catch((e) => {
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      throw e;
    });
};

//登入
const login = (req) => {
  return axios({
    method: "post",
    url: `http://localhost:8080/user/login`,
    data: req,
  })
    .then((res) => {
      return res.data;
    })
    .catch((e) => {
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      throw e;
    });
};

//登出
const logout = () => {
  localStorage.removeItem("user");
};

//更新帳戶資料
const updateUser = (req) => {
  return axios({
    method: "patch",
    url: `http://localhost:8080/user/update`,
    data: req,
  })
    .then((res) => {
      //console.log(res.data.rtnMsg);
      return res.data.rtnMsg;
    })
    .catch((e) => {
      Swal.fire({
        title: "系統異常\r請稍後再試",
        icon: "error",
        confirmButtonText: "確認",
      });
      throw e;
    });
};

export default { getUser, register, login, logout, updateUser };
