/*
  人事路由
 */
import routerConstants from "@/utils/constants/RouterConstants";

export default {
  path: routerConstants.pathDeptPersonnel(),
  component: ()=> import("@/views/dept/personnel"),
  name : "deptPersonnel",
}
