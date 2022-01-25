/*
  技术部路由
 */
import routerConstants from "@/utils/constants/RouterConstants";

export default {
  // path: "/welCome",
  path: routerConstants.pathDeptTechnical(),
  component: ()=> import("@/views/dept/technical"),
  name : "deptTechnical"
}
