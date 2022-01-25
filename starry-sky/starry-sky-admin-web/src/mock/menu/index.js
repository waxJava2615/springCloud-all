import routerConstants from "../../utils/constants/RouterConstants";

const Mock = require('mockjs');

// const Random = Mock.Random;

Mock.mock(process.env.BASE_API_A + routerConstants.contextPath() + "/menu/list.do",{
  code:0,
  msg:"成功",
  data:{
    'item|200':[
      {
        id:"@id",
        name:"@name",
        parentId: "@id",
        url: "@url",
        'option|0-1': 0,
        'icon|3-8':"@string"
      }
    ]
  }
});



