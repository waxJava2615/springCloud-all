<template>
  <div>
    我是员工管理
    <el-input placeholder="请输入内容" v-model = "testInput" @input="change($event)">
      <template slot="prepend">Http://</template>
    </el-input>

    <el-scrollbar style="height: 100%">
      <div style="height: 100px; width: 300px;">
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
        <p>我是测试</p>
      </div>
    </el-scrollbar>

  </div>
</template>

<script>
  export default {
    name: "employ",
    data(){
      return{
        "testInput":""
      }
    },
    methods:{
      change: function(event){
        console.log("run employ methods change:")
        this.$forceUpdate();
      }
    }

  }
</script>

<style scoped>

</style>
