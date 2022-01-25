import routerConstants from "@/utils/constants/RouterConstants";

import deptPersonnel from '../dept/personnel'
import deptTechnical from '../dept/technical'


import menu from '../menuManager'
import menuFrom from '../menuManager/from'

import employ from '../employ'

import permission from '../permission';

export default {
  path: routerConstants.pathWelCome(),
  component: ()=> import("@/views/welCome"),
  children: [
    deptPersonnel,
    deptTechnical,
    menu,
    menuFrom,
    employ,
    permission

  ]
}
