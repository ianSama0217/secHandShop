import axios from "axios";

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
      throw e;
    });
};

const login = (req) => {
  return axios({
    method: "post",
    url: `http://localhost:8080/user/login`,
    data: req,
  })
    .then((res) => {
      //console.log(res.data);
      return res.data;
    })
    .catch((e) => {
      throw e;
    });
};

const logout = () => {
  sessionStorage.removeItem("user");
};

export default { register, login, logout };
