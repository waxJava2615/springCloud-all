import routerConstants from "@/utils/constants/RouterConstants";


export default {
  path: routerConstants.pathMenuFrom(),
  component: ()=> import("@/views/menuManager/from"),
  name: "menuFrom",
  props: {props: route => ({ id: route.query.id })}
}
