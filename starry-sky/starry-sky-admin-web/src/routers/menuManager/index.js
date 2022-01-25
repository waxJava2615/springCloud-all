import routerConstants from "@/utils/constants/RouterConstants";

export default {
  path: routerConstants.pathMenuManager(),
  component: ()=> import("@/views/menuManager"),
  name: "menu",
}
