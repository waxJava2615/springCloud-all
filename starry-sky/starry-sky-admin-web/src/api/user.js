import {api} from '@/utils/request'
import log from '@/utils/logUtils'
import RouterConstants from "@/utils/constants/RouterConstants";

export function doLogin(data) {
  log.printLog("doLogin data val :\t" + JSON.stringify(data));
  return api.post(RouterConstants.contextPath() + '/login', data);
}


export function loadPage(data) {
  log.printLog("loadPage data val :\t" + JSON.stringify(data));
  return api.post(RouterConstants.contextPath() + '/loadPage', data);
}
