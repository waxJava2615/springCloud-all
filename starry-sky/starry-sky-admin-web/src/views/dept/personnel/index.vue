<template>
  <div class="personnel-div">
    在下人事部门的
  </div>
</template>

<script>
  export default {
    name: "personnel",
    created() {
      this.$logUtils.printLog("personnel vue create")
    }

  }
</script>

<style scoped>
    .personnel-div{
      background: aqua;
      border: #4e555b solid 1px;
      width: 100%;
      height: 100%;
    }
</style>
