import routerConstants from "../../utils/constants/RouterConstants";

export default {
  path: routerConstants.pathEmploy(),
  component: ()=> import("@/views/employ"),
  name: "employ"
}
