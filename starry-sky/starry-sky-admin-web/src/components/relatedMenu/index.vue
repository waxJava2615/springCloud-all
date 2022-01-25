<template v-if="listRoot.length > 0">
  <div class="cus-select-relate">
    <div class="cus-select-relate-item">
      <el-select v-model="selectOption.childrenMenu" value-key="id" placeholder="请选择" @change="changeItem">
        <el-option value="">请选择</el-option>
          <el-option  v-for="(item, idx) in perkListRoot" :key="item.id" :value="item" :label="item.name">
          </el-option>
      </el-select>
    </div>
    <related-menu  v-if="listChildren.length > 0" :list-root="listChildren"
                   :select-option="selectOption.childrenMenu" @change="changeItem"></related-menu>
  </div>
</template>

<script>
  export default {
    name: "relatedMenu",
    props: {
      listRoot: {
        type: Array,
        default: ()=>[]
      },
      selectOption: {
        type:Object,
        default: ()=>{}
      }
    },
    data() {
      return {
        tmMenuId: '',
        listChildren: [],
      }
    },
    methods: {
      changeItem: function (val) {
        var that = this;
        that.$logUtils.printLog("relatedMenu methods changeItem run");
        that.$logUtils.printLog(val);
        // that.$emit('childrenMenu', val);
        that.listChildren = [];
        var isChild = true;
        that.listRoot.forEach((item, index) => {
          if (val.id == item.id && typeof item.listChildren != "undefined" && item.listChildren.length > 0) {
            that.$logUtils.printLog("relatedMenu methods changeItem start forEach");
            item.listChildren.forEach((t, k) =>{
              that.$logUtils.printLog("relatedMenu methods changeItem selectOption.parentMenu.id == t.id :\t" );
              that.$logUtils.printLog(that.selectOption);
              that.$logUtils.printLog(t.id);
              if (that.selectOption.parentMenu.id == t.id){
                isChild = true;
              }
            });
            if (isChild){
              that.listChildren = item.listChildren;
            }
          }
        });
        that.$logUtils.printLog("relatedMenu methods changeItem run end..." + val);
        that.$logUtils.printLog(val);
        var resultVal = {};
        if (val != null && val != "" && Object.keys(val).length > 0){
          resultVal = val;
        }else{
          resultVal = that.selectOption;
        }
        that.Bus.$emit('menuChildren', resultVal);
      },
    },
    computed:{
      perkListRoot:function () {
        var that = this;
        return that.listRoot.filter((data) => {
          that.$logUtils.printLog("run filter:");
          that.$logUtils.printLog(that.selectOption);
          that.$logUtils.printLog(data.id,data.name);
          that.$logUtils.printLog(that.selectOption.id,that.selectOption.name);
          return data.id != that.selectOption.id;
        });
      }
    }
  }
</script>

<style scoped>

  .cus-select-relate div{
    display: inline-block;
  }

  .cus-select-relate{
    display: inline-block;
  }

  .cus-select-relate-item{
    width:150px;
    margin-left: 5px;
  }
</style>
