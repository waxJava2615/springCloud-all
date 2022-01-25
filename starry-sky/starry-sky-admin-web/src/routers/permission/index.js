import routerConstants from "@/utils/constants/RouterConstants";


export default {
  path: routerConstants.permission(),
  component: ()=> import("@/views/permission"),
  children: [
  ]
}
