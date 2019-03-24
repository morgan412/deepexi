import axios from 'axios';

// axios.defaults.baseURL='http://127.0.0.1:8081'
axios.defaults.baseURL='http://106.13.11.180:8081'
let base = '/product/api/v1';

export const getComponentCategorySelect = () => { return axios.get(`${base}/components/category/select`); };

export const addComponentCategory = params => { return axios.post(`${base}/components/category`, params); };

export const getComponentPage = params => { return axios.get(`${base}/components`, {params : params}); };

export const getComponent = id => { return axios.get(`${base}/components/` + id); };

export const addComponent = params => { return axios.post(`${base}/components`, params); };

export const editComponent = (id, params) => { return axios.put(`${base}/components/` + id, params); };

export const updateComponentDr = (dr, params) => { return axios.post(`${base}/components/` + dr, params); };

export const batchRemoveComponent = params => {return axios.post(`${base}/components/batchdelete`, params); };

export const removeComponent = id => { return axios.delete(`${base}/components/` + id); };