import {api} from '@/utils/request'
import log from '@/utils/logUtils'
import routerConstants from "@/utils/constants/RouterConstants";


const commonApi = {
  loadMenuTree: function () {
    log.printLog("commonApi loadMenuTree run :\t");
    return api.get(routerConstants.contextPath() + '/menu/loadMenuTree.do', {});
  }
};


export default commonApi
