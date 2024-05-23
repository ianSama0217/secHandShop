import axios from "axios";
import Swal from "sweetalert2";

//新增購物車
const createCart = (req) => {
  return axios({
    method: "post",
    url: `http://localhost:8080/cart/create`,
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

//刪除購物車
const deleteCart = (id) => {
  axios
    .delete(`http://localhost:8080/cart/delete/${id}`)
    .then((res) => {
      //console.log(res.data.rtnMsg);
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

//更新購物車(數量、總金額)
const updateCart = (req) => {
  return axios({
    method: "patch",
    url: `http://localhost:8080/cart/update`,
    data: req,
  })
    .then((res) => {
      console.log(res.data);
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

//取得該使用者的購物車
const getCart = (id) => {
  return axios
    .get(`http://localhost:8080/cart/${id}`)
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

export default {
  createCart,
  deleteCart,
  updateCart,
  getCart,
};
