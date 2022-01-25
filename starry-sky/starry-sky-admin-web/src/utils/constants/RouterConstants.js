const routerConstants = {

  contextPath: ()=> {return "/admin"},

  pathWelCome: () => {return "/"},

  // 部门 人事部
  pathDeptPersonnel: ()=> {return "/personnel"},

  // 部门 技术部
  pathDeptTechnical: ()=> {return "/technical"},

  /**
   * 权限
   */
  permission: ()=>{
    return "/permission";
  },


  /**
   * 权限
   */
  permissionType: ()=>{
    return "/permission/type";
  },

  permissionOperation: ()=>{
    return "/permission/type";
  },

  /**
   * 权限配置
   * @returns {string}
   */
  permissionSetting: ()=>{
    return "/permission/setting";
  },
  /**
   * 角色管理
   * @returns {string}
   */
  permissionRole:()=>{
    return "/permission/role";
  },


  pathEmploy: ()=> {return "/employ"},

  /**
   * 菜单管理
   */
  pathMenuManager: ()=> {return "/menu"},

  /**
   * 菜单创建修改/表单
   */
  pathMenuFrom: ()=> {return "/menu/from"}

};

export default routerConstants
