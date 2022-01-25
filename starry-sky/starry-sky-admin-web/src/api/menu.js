import {api} from '@/utils/request'
import log from '@/utils/logUtils'
import RouterConstants from "@/utils/constants/RouterConstants";


const menuApi = {
    list: function (data) {
        log.printLog("menuApi list params data val :\t" + JSON.stringify(data));
        return api.get(RouterConstants.contextPath() + '/menu/list.do', data);
    },
    select: function (data) {
        log.printLog("menuApi select params data val :\t" + JSON.stringify(data));
        return api.get(RouterConstants.contextPath() + '/menu/select.do', data);
    },
    edit: function (data) {
        log.printLog("menuApi edit params data val :\t" + JSON.stringify(data));
        return api.post(RouterConstants.contextPath() + '/menu/edit.do', data);
    },
    push: function (data) {
        log.printLog("menuApi push params data val :\t" + JSON.stringify(data));
        return api.post(RouterConstants.contextPath() + '/menu/push.do', data);
    },
    remove: function (data) {
        log.printLog("menuApi remove params data val :\t" + JSON.stringify(data));
        return api.post(RouterConstants.contextPath() + '/menu/remove.do', data);
    }

};

export default menuApi

