<template>
  <!-- 分页组件 -->
  <!-- size-change（每页条数）    pageSize 改变时会触发 -->
  <!-- current-change（当前页）  currentPage 改变时会触发 -->
  <!-- page-size    每页显示条目个数，支持 .sync 修饰符 -->
  <!-- page-sizes   每页显示个数选择器的选项设置 -->
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    background
    :current-page="currentPage"
    :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" :layout="layout" :total="total">
  </el-pagination>
</template>

<script>
  export default {
    name:"pagination",
    props: {
      currentPage: {
        type: [String, Number],
        default: 1
      },
      total: {
        type: [String, Number],
        default: 0
      },
      pageSizes: {
        type: Array,
        default: () => ([10, 15, 30, 50]),
      },
      pageSize: {
        type: [String, Number],
        default: 10
      },
      // prev表示上一页，next为下一页，pager表示页码列表,jumper表示跳页元素，total表示总条目数，size用于设置每页显示的页码数量
      // total, sizes, prev, pager, next, jumper
      layout: {
        type: String,
        default: 'total, sizes, prev, pager, next, jumper'
      }
    },
    data() {
      return {}
    },
    methods: {
      handleSizeChange(val) {
        // 子组件向父组件传值
        this.$emit('sizeChange', val)
      },
      handleCurrentChange(val) {
        this.$emit('currentChange', val)
      }
    }
  }
</script>
<style lang="less" scoped>
  .page {
    text-align: center;
    color: #409EFF;
  }
</style>
