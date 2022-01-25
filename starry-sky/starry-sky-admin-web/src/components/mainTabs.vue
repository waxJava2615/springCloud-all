<template>
  <el-tabs type="card"
           :value="$route.fullPath"
           @tab-click="tabClick"
           closable
           class="my-tabs"
           @edit="handleTabsEdit">

    <el-tab-pane :key="item.path"
                 v-for="(item) in tabsValue"
                 :label="item.title"
                 :name="item.path">
    </el-tab-pane>

  </el-tabs>
</template>

<script>
  import cusCommon from "../utils/cusCommon";

  export default {
    name: 'mainTabs',
    data () {
      return {
      }
    },
    created () {
    },
    watch: {

    },
    mounted () {
      this.$logUtils.printLog("main tabs mounted run:");
      // 监听刷新，将数据保存到sessionStorage里
      // window.addEventListener('beforeunload', e =>{
      //   this.$logUtils.printLog("main tabs mounted window.addEventListener run:");beforeRouteLeave
      //   this.setStorage();
      // })
    },
    destroyed () {
      this.$logUtils.printLog("main tabs destroyed run:");
      // window.removeEventListener('beforeunload', e => this.setStorage())
    },
    computed: {
      // ...mapState({
      //   tabsValue: state => {
      //     console.log("main tabs computed run:\t");
      //     console.log(state);
      //     return state;
      //   }
      // })
      tabsValue(){
        return this.$store.getters.getTabsValue;
      }

    },
    methods: {
      // setStorage () {
      //   console.log('set Storage==============>');
      // },
      handleTabsEdit (targetPath, action) {
        this.$logUtils.printLog("mainTabs method handleTabsEdit run:\t");
        this.$logUtils.printLog("mainTabs method handleTabsEdit param targetPath val:\t" + targetPath);
        // 删除选项卡
        if (action === 'remove') {
          // let nextTab = {}
          // // 找到下一个路由
          // this.tabsValue.forEach((item, index) => {
          //   if (item.path === targetPath) {
          //     nextTab = this.tabsValue[index - 1] || this.tabsValue[index + 1]
          //   }
          // })
          // this.$logUtils.printLog("mainTabs method handleTabsEdit commit remove tabs:\t" + targetPath);
          // this.$logUtils.printLog("mainTabs method handleTabsEdit this route path val:\t" + this.$route.path);
          this.$logUtils.printLog("mainTabs method handleTabsEdit this route fullPath val:\t" + this.$route.fullPath);
          // this.$store.commit('removeTabsValue', targetPath);
          // // 如果删除的是当前页，则进行跳转
          // this.$logUtils.printLog("mainTabs method handleTabsEdit (targetPath === this.$route.path?):\t" + targetPath
          //   === this.$route.fullPath);
          // targetPath === this.$route.fullPath && this.$router.push(nextTab.path)

          cusCommon.removeTabs(targetPath,this.$route.fullPath,this.tabsValue);
        }
      },
      tabClick ({ name }) {
        this.$logUtils.printLog("mainTabs method tabClick run:\t" + name);
        // this.$logUtils.printLog("mainTabs method tabClick this.$route.path val:\t" + this.$route.path);
        if (name === this.$route.path) return;
        this.$router.push(name)
      }
    }
  }
</script>

<style scoped>
  .el-tabs{
    width: 100%;
    /*height: 30px;*/
  }


  /*.el-tabs__nav{*/
  /*  line-height: 40px;*/
  /*}*/

  .el-tabs__item{

  }

</style>
