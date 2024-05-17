import axios from "axios";
import Swal from "sweetalert2";

const search = (userId, name, type, lowPrice, highPrice, sort) => {
  let url = `http://localhost:8080/product?`;
  const params = [];
  if (userId) {
    params.push(`userId=${userId}`);
  }
  if (name) {
    params.push(`name=${name}`);
  }
  if (type) {
    params.push(`type=${type}`);
  }
  if (lowPrice) {
    params.push(`lowPrice=${lowPrice}`);
  }
  if (highPrice) {
    params.push(`highPrice=${highPrice}`);
  }
  if (sort) {
    params.push(`sort=${sort}`);
  }

  url += params.join("&");
  console.log("URL:" + url);

  return axios
    .get(url)
    .then((res) => {
      // console.log(res.data.productList);
      return res.data.productList;
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

//取得指定商品資訊
const getProduct = (id) => {
  return axios
    .get(`http://localhost:8080/product/${id}`)
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

//創建商品
const createProduct = (req) => {
  return axios({
    method: "post",
    url: `http://localhost:8080/product/create`,
    data: req,
  })
    .then((res) => {
      console.log(res.data.rtnMsg);
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

//刪除商品(將state更改為-1)
const deleteProduct = (id) => {
  return axios
    .patch(`http://localhost:8080/product/delete?id=${id}`)
    .then((res) => {
      console.log(res.data.rtnMsg);
    })
    .catch((e) => {
      throw e;
    });
};

//更新商品資料
const updateProduct = (req) => {
  return axios({
    method: "patch",
    url: `http://localhost:8080/product/update`,
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
      s;
      throw e;
    });
};

//取得使用者販售商品資訊
const getStore = (id) => {
  return axios
    .get(`http://localhost:8080/store/${id}`)
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
  search,
  getProduct,
  createProduct,
  deleteProduct,
  updateProduct,
  getStore,
};
