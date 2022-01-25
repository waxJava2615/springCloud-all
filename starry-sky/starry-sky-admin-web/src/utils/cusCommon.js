import Vue from 'vue';
import logUtils from "./logUtils";
import stores from "../stores";
import router from "../routers";
var cusCommon = new Vue({
    methods: {
        /**
         *
         * @param currentFullPath  当前全路径
         * @param routeFullPath    当前路由路径
         * @param tabValues        tabs列表
         */
       removeTabs:function (currentFullPath,routeFullPath,tabValues) {
           logUtils.printLog("cusCommon methods removeTabs run param:\t",currentFullPath,tabValues);
            if (currentFullPath === '/') return;
           // let currentFullPath = this.$route.fullPath;
           if (typeof tabValues =="undefined" || tabValues.length <= 0) {
               return;
           }
           let nextTab = {};
           tabValues.forEach((item, index) => {
             if (item.path === currentFullPath) {
               nextTab = tabValues[index - 1] || tabValues[index + 1]
             }
           });
           logUtils.printLog("cusCommon methods removeTabs nextTab:\t",nextTab);
           stores.commit('removeTabsValue', currentFullPath);
           logUtils.printLog("cusCommon methods removeTabs currentFullPath :\t",currentFullPath);
           logUtils.printLog("cusCommon methods removeTabs currentFullPath === routeFullPath?:\t",currentFullPath === routeFullPath);
           currentFullPath === routeFullPath && router.push(nextTab.path)
       }
    }
});
export default cusCommon;