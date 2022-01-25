
const selectOptionConstants = {

  menuOption:function () {
    return [
      {label: "左侧", value: 0}, {label: "顶侧", value: 1}
    ];
  },
  menuOptionLabel:function(v){
    let menuOption = this.menuOption();
    let result = "未知";
    menuOption.forEach(function(item,index){
      if (item.value == v){
        result = item.label;
        return;
      }
    })
    return result;
  },

  hideOption:function () {
    return [
      {label: "显示", value: 0}, {label: "隐藏", value: 1}
    ];
  }


};


export default selectOptionConstants
