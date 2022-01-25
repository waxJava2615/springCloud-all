import routerConstants from "@/utils/constants/RouterConstants";


export default {
  path: routerConstants.pathWelCome(),
  component: ()=> import("@/views/welCome"),
  children: [
  ]
}
