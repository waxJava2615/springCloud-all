<template>
  <el-container >
    <el-header >
      扯淡管理系统
    </el-header>
    <el-container>
      <el-aside width="200px" class="el-aside">
        <el-menu mode="vertical" @open="handleOpen" @close="handleClose" :unique-opened="true"
                 :default-active="$route.path" :router="true" @select="handleSelect">
          <leftMenu :navMenus="leftData.listMenu"></leftMenu>
        </el-menu>
      </el-aside>
      <el-main class="">

        <div class="def-clz">
          <mainTabs />
        </div>
        <keep-alive>
          <router-view :key="$route.fullPath" />
        </keep-alive>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import LabelWrap from "element-ui/packages/form/src/label-wrap";
  import {loadPage} from "@/api/user";
  import commonConstants from "@/utils/constants/CommonConstants";
  import leftMenu from "@/components/leftMenu";
  import mainTabs from "@/components/mainTabs";

  export default {
    name: "welCome",
    components: {LabelWrap, leftMenu,mainTabs},
    data() {
      return {
        leftData: {
          listMenu: []
        },
        topData: {
          listMenu: [],
        },
        userInfo: {},
      }
    },
    mounted() {
      this.loadPage();
    },
    created() {
      this.$logUtils.printLog("welCome created run sessionStorage");
      sessionStorage.getItem(commonConstants.getSessionStoresKey()) &&
      this.$store.replaceState(JSON.parse(sessionStorage.getItem(commonConstants.getSessionStoresKey())));
    },


    watch: {

    },

    computed: {
    },

    methods: {
      handleOpen(key, keyPath) {
        this.$logUtils.printLog(key, keyPath);
      },
      handleClose(key, keyPath) {
        this.$logUtils.printLog(key, keyPath);
      },
      handleSelect(index,indexPath){
        this.$logUtils.printLog("handleSelect : ");
        this.$logUtils.printLog(index, indexPath);
      },
      loadPage() {
        this.$logUtils.printLog("loadPage start");
        this.$logUtils.printLog(this.$store.getters.getToken);
        let data = {};
        loadPage(data).then((res) => {
          this.$logUtils.printLog("response data:");
          this.$logUtils.printLog(res);
          if (res.status === 200) {
            this.$logUtils.printLog("response status code 200");
            if (res.data.code === 0) {
              if (typeof res.data.data.leftMenu != "undefined" && res.data.data.leftMenu.length > 0) {
                this.$logUtils.printLog("=====left========");
                this.$logUtils.printLog(this.$data);
                this.$data.leftData.listMenu = res.data.data.leftMenu;
              }
              if (typeof res.data.data.topMenu != "undefined" && res.data.data.topMenu.length > 0) {
                this.$logUtils.printLog("=====top==data======");
                this.$data.topData.listMenu = res.data.data.topMenu;
              }
              if (typeof res.data.data.userInfo != "undefined") {
                this.$logUtils.printLog("=====userInfo==data======");
                this.$data.userInfo = res.data.data.userInfo;
              }
            } else {
              this.$alert(res.data.msg);
            }
          }
        }).catch((error) => {
          this.$logUtils.printLog("error msg :");
          this.$logUtils.printLog(error);
          this.$alert(commonConstants.getNetworkErrorMsg());
        });
      },
    }
  }
</script>


<style scoped>


  .el-container {
    height: 100%;
  }

  .el-header, .el-footer {
    background-color: #b3c0d1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #d3dce6;
    color: #333;
    text-align: left;
    /*line-height: 200px;*/
    height: 100%;

  }

  .el-main {
    /*background-color: #e9eef3;*/
    color: #333;
    /*text-align: center;*/
    padding: 0.5%;
    height: 100%;
    width: 100%;
    overflow: hidden;
  }

  .def-clz{
    line-height: 46px;
  }


  .el-menu {
    background-color: #d3dce6;
  }


  .el-breadcrumb{
    line-height: 50px;
    height: 50px;
    font-size: 15px;
  }



  .el-tabs{
    line-height: 46px;
  }

</style>
