<template>
  <div class="left-menu">
      <template v-for="(navMenu,i) in navMenus">
        <el-menu-item v-if="!navMenu.listChildren"
                      :key="navMenu.id" :data="navMenu" :index="'/' + navMenu.onlyKey"
                      @click="handleRoute(navMenu.name,navMenu.url,navMenu.onlyKey)">
          <i :class="navMenu.icon"></i>
          <span slot="title">{{navMenu.name}}</span>
        </el-menu-item>

        <el-submenu v-if="navMenu.listChildren" @click.native="goto(navMenu.name)"
                    :key="navMenu.id" :data="navMenu" :index="navMenu.name">
          <template slot="title">
            <i :class="navMenu.icon"></i>
            <span>{{navMenu.name}}</span>
          </template>
          <leftMenu :navMenus="navMenu.listChildren"></leftMenu>
        </el-submenu>
      </template>
  </div>
</template>

<script>

  export default {
    name: "leftMenu",
    props: ['navMenus'],
    data() {
      return {}
    },
    methods: {
      goto(menu) {
        // 通过菜单URL跳转至指定路由
        this.$logUtils.printLog("goto: " + menu);
        // if (menu) {
        //   this.$router.push(menu)
        // }
      },
      handleRoute(name,path,onlyKey){
        this.$logUtils.printLog("handleRoute: " +name + "\t path: " + path +"\t onlyKey: " + onlyKey);
        var params = {};
        params.path = "/" + onlyKey;
        params.title = name;
        params.name = onlyKey;
        this.$store.commit("setTabsValue",params);
        this.$router.push(params.path);
      },
    }
  }
</script>

<style scoped>
</style>
