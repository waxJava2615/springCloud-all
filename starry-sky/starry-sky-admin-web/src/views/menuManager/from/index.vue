<template>
    <div class="div-from">
        <el-form label-width="100px">
            <el-form-item label="菜单名称:">
                <el-input v-model="menuFrom.name"></el-input>
            </el-form-item>
            <el-form-item label="图片:">
                <el-input v-model="menuFrom.icon"></el-input>
            </el-form-item>
            <el-form-item label="地址:">
                <el-input v-model="menuFrom.url"></el-input>
            </el-form-item>
            <el-form-item label="唯一标识:">
                <el-input v-model="menuFrom.onlyKey"></el-input>
            </el-form-item>
            <div class="cus-select-div">
                <el-form-item label="父ID:" class="cus-select-item">
                    <el-select v-model="menuFrom.parentMenu" value-key="id" placeholder="根目录"
                               @change="changeParent">
                        <el-option value="">根目录</el-option>
                        <el-option v-for="(item,i) in menuTree" :key="item.id" :value="item"
                                   :label="item.name" :data-cus-id="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <related-menu v-if="listChildren.length > 0" :list-root="listChildren"
                              :select-option="menuFrom.parentMenu"/>


            </div>
            <div class="cus-select-div">
                <el-form-item label="位置:" class="cus-select-item">
                    <el-select v-model="menuFrom.option" @change="changeOption">
                        <el-option v-for="item in selOption" :key="item.value" :value="item.value"
                                   :label="item.label"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="隐藏显示:" class="cus-select-item">
                    <el-select v-model="menuFrom.hide" @change="changeHide">
                        <el-option v-for="item in hideOption" :key="item.value" :value="item.value"
                                   :label="item.label"></el-option>
                    </el-select>
                </el-form-item>
            </div>

            <el-form-item label="排序:">
                <el-input v-model="menuFrom.order"></el-input>
            </el-form-item>
        </el-form>

        <div class="cus-tool">
            <el-button class="el-button-submit" v-if="id > 0" @click="submitEditMenu">保
                存</el-button>
            <el-button class="el-button-submit" v-if="id <= 0" @click="submitPushMenu">保
                存</el-button>
            <el-button class="el-button-quit" type="warning" @click="quitEditMenu">放 弃</el-button>
        </div>
    </div>
</template>

<script >
    import menuApi from "../../../api/menu";
    import selectOptionConstants from "../../../utils/constants/SelectOptionConstants";
    import commonApi from "../../../api/common";
    import relatedMenu from "../../../components/relatedMenu/index";
    import cusCommon from "../../../utils/cusCommon";

    export default {
        name: "menuFrom",
        components: {relatedMenu},
        created() {
            this.Bus.$on('menuChildren', async (res) => {
                await this.childrenMenu(res);
            });
            this.$logUtils.printLog("menu from create run:");
            this.$logUtils.printLog("get params :\t " + JSON.stringify(this.$route.query));
            let queryId = this.$route.query.id;
            if (typeof queryId != "undefined" && queryId > 0) {
                this.$data.id = this.$route.query.id;
            }
        },
        mounted() {
            this.$logUtils.printLog("menu from mounted run:");
            this.loadFromData();
        },
        data() {
            return {
                id: 0,
                menuFrom: {
                    name: '',
                    onlyKey: '',
                    url: '',
                    icon: '',
                    parentId: '',
                    parentMenu: {},
                    option: 0,
                    hide: 0,
                    order: ''
                },
                menuTree: [],
                listChildren: [],
                selOption: selectOptionConstants.menuOption(),
                hideOption: selectOptionConstants.hideOption(),
            }
        },
        beforeRouteEnter(to, from, next) {
            // 在渲染该组件的对应路由被 confirm 前调用
            // 不！能！获取组件实例 `this`
            // 因为当守卫执行前，组件实例还没被创建
            next();
        },
        beforeRouteUpdate(to, from, next) {
            // 在当前路由改变，但是该组件被复用时调用
            // 举例来说，对于一个带有动态参数的路径 /foo/:id，在 /foo/1 和 /foo/2 之间跳转的时候，
            // 由于会渲染同样的 Foo 组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
            // 可以访问组件实例 `this`
            this.$logUtils.printLog("menu from beforeRouteUpdate run:");
            next();
        },
        beforeRouteLeave(to, from, next) {
            // 导航离开该组件的对应路由时调用
            // 可以访问组件实例 `this`
            this.$logUtils.printLog("menu from beforeRouteLeave run:");
            // 销毁组件，避免通过vue-router再次进入时，仍是上次的history缓存的状态
            if (this.id > 0){
                this.$destroy(true);
            }
            next()
        },
        computed: {
            tabsValue() {
                return this.$store.getters.getTabsValue;
            }
        },
        methods: {
            loadFromData: function () {
                var that = this;
                that.$logUtils.printLog("menu from loadFromData run:");
                var data = {};
                data.id = that.id;
                menuApi.select(data).then(async (res) => {
                    if (res.data.code === 0) {
                        that.$logUtils.printLog("menu from loadFromData menuApi.select val:");
                        that.$logUtils.printLog(res);
                        if (typeof res.data.data != "undefined" && Object.keys(res.data.data).length > 0) {
                            that.menuFrom = res.data.data;
                            that.$logUtils.printLog("that.menuFrom val: ");
                            that.$logUtils.printLog(that.menuFrom);
                            // that.menuFrom.parentId =
                            //     {"id":"933299029689016320","listChildren":[{"id":"933404513439883264"}]};
                            await that.loadMenuTree();
                            // that.listChildren = that.menuFrom.parentId.listChildren;
                        }
                    } else {
                        that.$message.closeAll();
                        that.$message.error("加载数据失败...");
                    }
                    // that.$notify({
                    //   title: '成功',
                    //   // message: '这是一条成功的提示消息',
                    //   type: 'success',
                    //   offset: 100,
                    //   duration:3000
                    // });
                }).catch((err) => {
                    that.$logUtils.printLog("err :\t" + JSON.stringify(err));
                    that.$message.closeAll();
                    that.$message.error("加载数据失败...");
                });
            },
            loadMenuTree: function () {
                var that = this;
                that.$logUtils.printLog("menu from loadMenuTree run:");
                commonApi.loadMenuTree().then((res) => {
                    if (res.data.code === 0) {
                        if (typeof res.data.data != "undefined" && res.data.data.length > 0) {
                            that.menuTree = res.data.data;
                            that.$logUtils.printLog("====> menuTree.length:\t" + that.menuTree.length);
                            that.menuTree.forEach((item, index) => {
                                that.$logUtils.printLog("====> item:\t",item);
                                if ( typeof that.menuFrom.parentMenu != "undefined" && item.id ==
                                    that.menuFrom.parentMenu.id && typeof item.listChildren != "undefined"
                                    && item.listChildren.length > 0) {
                                    that.listChildren = item.listChildren;
                                }
                            });
                        }
                    } else {
                        that.$message.closeAll();
                        that.$message.error("加载级联菜单数据失败...");
                    }
                }).catch((err) => {
                    that.$logUtils.printLog("menu from loadMenuTree err :\t" + JSON.stringify(err));
                    that.$message.closeAll();
                    that.$message.error("加载级联菜单数据失败...");
                });
            },

            changeParent: function (val) {
                var that = this;
                that.$logUtils.printLog('menu from method changeParent run :');
                that.$logUtils.printLog(val);
                that.listChildren = [];
                that.menuTree.forEach((item, index) => {
                    if (val.id == item.id && typeof item.listChildren != "undefined" && item.listChildren.length > 0) {
                        that.listChildren = item.listChildren;
                    }
                });
                that.menuFrom.parentId = typeof val.id == "undefined"?'':val.id;
                that.$logUtils.printLog('menu from method changeParent end :' + JSON.stringify(that.listChildren));
            },

            childrenMenu: function (val) {
                var that = this;
                that.$logUtils.printLog('menu from method childrenMenu run :' + val);
                that.$logUtils.printLog(val.id,val.name);
                if (val != null && val != "" && Object.keys(val).length > 0){
                    that.menuFrom.parentId = val.id;
                }
            },

            changeOption: function (val) {
                console.log('menu from method changeOption run :' + val);
            },
            changeHide: function (val) {
                console.log('menu from method changeHide run :' + val);
            },
            submitEditMenu: function () {
                var that = this;
                var data = that.menuFrom;
                that.$logUtils.printLog('menuManager from method submitEditMenu data:', JSON.stringify(data));
                menuApi.edit(data).then((res)=>{
                    that.$logUtils.printLog("submitEditMenu success======>");
                    that.$logUtils.printLog(res);
                    if (res.data.code === 0){
                        that.$cusMessage.success({message:res.data.msg,center:false,showClose:true});
                        setTimeout(function () {
                            cusCommon.removeTabs(that.$route.fullPath,that.$route.fullPath,that.tabsValue);
                        },2000);
                    }else {
                        that.$cusMessage.error({message:res.data.msg,center:false,showClose:true});
                    }
                }).catch((err)=>{
                    that.$logUtils.printLog("submitEditMenu error ======>");
                });
            },
            submitPushMenu: function(){
                var that = this;
                var data = that.menuFrom;
                that.$logUtils.printLog("submitPushMenu run: ");
                that.$logUtils.printLog("submitPushMenu menuFrom val: " + JSON.stringify(data));
                menuApi.push(data).then((res)=>{
                    that.$logUtils.printLog("submitPushMenu success======>");
                    that.$logUtils.printLog(res);
                    if (res.data.code === 0){
                        that.$cusMessage.success({message:res.data.msg,center:false,showClose:true});
                        setTimeout(function () {
                            cusCommon.removeTabs(that.$route.fullPath,that.$route.fullPath,that.tabsValue);
                        },2000);
                    }else {
                        that.$cusMessage.error({message:res.data.msg,center:false,showClose:true});
                    }
                }).catch((err)=>{
                    that.$logUtils.printLog("submitPushMenu error ======>");
                });
            },
            quitEditMenu: function () {
                var that = this;
                that.$cusConfirm('是否放弃此次修改?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }).then(function () {
                    cusCommon.removeTabs(that.$route.fullPath,that.$route.fullPath,that.tabsValue);
                }).catch(function () {

                });

                // this.$logUtils.printLog("menuManager from method quitEditMenu this route fullPath val:\t" +
                //         this.$route.fullPath);
                // let currentFullPath = this.$route.fullPath;
                // let nextTab = {};
                // this.tabsValue.forEach((item, index) => {
                //   if (item.path === currentFullPath) {
                //     nextTab = this.tabsValue[index - 1] || this.tabsValue[index + 1]
                //   }
                // });
                // this.$store.commit('removeTabsValue', currentFullPath);
                // if (Object.keys(nextTab).length > 0){
                //   this.$router.push(nextTab.path);
                // }
            },
        },
        destroyed() {
            console.log('menu from method destroyed run :');
        }
    }

</script>

<style scoped>
    .cus-select-item {
        display: inline-block;
        width: 15%;
    }

    .cus-tool {
        width: 100%;
        line-height: 60px;
        background: #ffffff;
        display: flex;
        justify-content: center;
    }

    .cus-tool .el-button {
        width: 200px;
    }
</style>
