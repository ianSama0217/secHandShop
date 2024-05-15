import axios from "axios";

const search = (userId, name, type, lowPrice, highPrice, sort) => {
  let url = `http://localhost:8080/product?`;
  if (userId) {
    url += `userId=${userId}`;
  }
  if (name) {
    url += `&name=${name}`;
  }
  if (type) {
    url += `&type=${type}`;
  }
  if (lowPrice) {
    url += `&lowPrice=${lowPrice}`;
  }
  if (highPrice) {
    url += `&highPrice=${highPrice}`;
  }
  if (sort) {
    url += `&sort=${sort}`;
  }
  return axios
    .get(url)
    .then((res) => {
      //console.log(res.data.productList);
      return res.data.productList;
    })
    .catch((e) => {
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
      //console.log(res.data.rtnMsg);
      return res.data.rtnMsg;
    })
    .catch((e) => {
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
