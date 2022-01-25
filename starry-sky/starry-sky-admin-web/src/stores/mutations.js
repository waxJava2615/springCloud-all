import log from "@/utils/logUtils"
import commonConstants from "@/utils/constants/CommonConstants";

export default {
  setToken: (state, _token) => {
    state.token = _token;
    storeLocalStore(state);
  },
  setTabsValue:(state,_params) => {
    // 判断是否已经存在
    const isExist = state.tabsValue.some(item => item.path === _params.path);
    log.printLog("isExist:\t" + isExist + "\t _params : \t" + JSON.stringify(_params));
    // 如果不存在添加
    if (!isExist) {
      state.tabsValue.push(_params);
      storeLocalStore(state);
    }
  },
  removeTabsValue: (state,targetPath)=>{
    state.tabsValue = state.tabsValue.filter(item => item.path !== targetPath)
    storeLocalStore(state);
  },
}

function storeLocalStore(state) {
  log.printLog("storeLocalStore val :\t" + JSON.stringify(state));
  window.sessionStorage.setItem(commonConstants.getSessionStoresKey(), JSON.stringify(state));
}
