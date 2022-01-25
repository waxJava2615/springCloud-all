<template>
    <div class="cus-menu-manager right-content-h cus-relative">
        <div class="cus-menu-manager-from">
            <el-form :inline="true" :model="fromParam" class="demo-form-inline">
                <el-form-item label="ID">
                    <el-input v-model="fromParam.id" placeholder="ID"></el-input>
                </el-form-item>
                <el-form-item label="父ID">
                    <el-input v-model="fromParam.parentId" placeholder="父ID"></el-input>
                </el-form-item>
                <el-form-item label="名称">
                    <el-input v-model="fromParam.name" placeholder="名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="onSubmitSelect">查询</el-button>
                </el-form-item>
                <div class="cus-menu-manager-from-btn-create">
                    <el-button v-if="roleOperation.hasOwnProperty('push')" type="primary"
                               :icon="roleOperation.push.icon"
                               title="新建菜单"
                               @click="handleEdit">新建菜单
                    </el-button>
                    <el-button v-if="roleOperation.hasOwnProperty('export')" type=""
                               :icon="roleOperation.export.icon"
                               title="导出"
                               @click="handleEdit">导出
                    </el-button>
                </div>
            </el-form>
        </div>
        <el-table :data="tableDataComputed.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
                  stripe
                  tooltip-effect="dark"
                  ref="multipleTable"
                  @selection-change="handleSelRow"
                  height="85%" border>
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column label="ID" prop="id"/>
            <el-table-column label="名称" prop="name"/>
            <el-table-column label="父ID" prop="parentId"/>
            <el-table-column label="位置" prop="option"/>
            <el-table-column label="排序" prop="order"/>
            <el-table-column label="图片" prop="icon"/>
            <el-table-column label="地址" prop="url"/>
            <el-table-column
                    align="right">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="根据名称筛选" clearable/>
                </template>
                <template slot-scope="scope">

                    <el-button
                            size="mini"
                            type="success"
                            v-if="scope.row.listChildren"
                            icon="el-icon-search"
                            title="查看子菜单"
                            @click="selectChildren(scope.$index, scope.row)">
                    </el-button>

                    <el-button
                            v-if="roleOperation.hasOwnProperty('edit')"
                            size="mini"
                            type="primary"
                            :icon="roleOperation.edit.icon"
                            title="修改"
                            class="cus-edit-bg"
                            @click="handleEdit(scope.$index, scope.row)">
                    </el-button>
                    <el-button
                            v-if="roleOperation.hasOwnProperty('remove')"
                            size="mini"
                            type="danger"
                            :icon="roleOperation.remove.icon"
                            title="删除"
                            @click="handleDelete(scope.$index, scope.row)">
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="cus-page-el">
            <!--      <el-pagination background @current-change="handleCurrentChange"-->
            <!--                     next-text="下一页" prev-text="上一页"-->
            <!--        :page-size="size"-->
            <!--        :current-page="currentPage"-->
            <!--        layout="prev, pager, next"-->
            <!--        :total="total">-->
            <!--      </el-pagination>-->
            <pagination @sizeChange='handleSizeChange'
                        :pageSize='pageSize'
                        @currentChange='handleCurrentChange'
                        :currentPage='currentPage' :total='total'/>
        </div>
    </div>
</template>

<script>

    import menuApi from "@/api/menu"
    import routerConstants from "@/utils/constants/RouterConstants";
    import pagination from "../../components/pagination";
    import selectOptionConstants from "../../utils/constants/SelectOptionConstants";

    export default {
        name: "menuList",
        components: {pagination},
        data() {
            return {
                fromParam: {
                    id: '',
                    parentId: '',
                    name: '',
                },
                search: '',
                pageSize: 200,
                currentPage: 1,
                total: 10000,
                tableData: [],
                multipleSelection: [],
                roleOperation: {},// 拥有什么操作
            }
        },
        created() {
            this.$logUtils.printLog("menu vue create")
        },
        mounted() {
            this.loadTableData();
        },
        computed:{
            tableDataComputed:function () {
                this.$logUtils.printLog("menuManage index computed method tableDataComputed run :");
                return this.tableData.map((val) => {
                    val.option = selectOptionConstants.menuOptionLabel(val.option);
                    return val;
                });
            }
        },
        methods: {
            selectChildren: function (index, row) {
                var that = this;
                that.$logUtils.printLog("menuManage index method selectChildren run :")
                that.fromParam.parentId = row.id;
                that.loadTableData();
            },
            onSubmitSelect: function () {
                var that = this;
                this.$logUtils.printLog("menuManage index method onSubmitSelect run :");
                this.$logUtils.printLog("this data fromParam :\t" + JSON.stringify(that.fromParam));
                that.loadTableData();
            },
            handleSelRow: function (row) {
                this.$logUtils.printLog("menuManage index method handleSelRow run :");
                this.multipleSelection = row;
            },
            handleEdit: function (index, row) {
                this.$logUtils.printLog("menuManage index method handleEdit run :");
                let _title = "创建菜单";
                let _path = routerConstants.pathMenuFrom();
                if (row) {
                    _path = _path + "?id=" + row.id;
                    _title = "修改菜单";
                } else {
                    _path = _path + "?id=-" + new Date().getTime();
                }
                var params = {};
                params.path = _path;
                params.title = _title;
                params.name = "menuFrom";
                this.$store.commit("setTabsValue", params);
                // this.$router.push(params.path);
                // this.$router.push(routerConstants.pathMenuFrom() + "?id="+row.id);
                // this.$router.push({name:params.name,params:{id:row.id}});
                this.$router.push(_path);
            },
            handleDelete: function (index, row) {
                this.$logUtils.printLog("menuManage index method handleDelete params :" + index + "\t" + row.id);
                var that = this;
                that.$cusConfirm('是否删除菜单 ' + row.name + '?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }).then(function () {
                    // 发送删除请求
                    let data  = {};
                    data.id = row.id;
                    menuApi.remove(data).then((res)=>{
                        if (res.data.code === 0){
                            that.$cusMessage.success({message:res.data.msg,center:false,showClose:true});
                            that.tableData.splice(index, 1);
                        }else {
                            that.$cusMessage.error({message:res.data.msg,center:false,showClose:true});
                        }
                    }).catch((err)=>{
                        that.$logUtils.printLog("menuManage index method handleDelete error :" + JSON.stringify(err));
                        that.$cusMessage.error({message:JSON.stringify(err),center:false,showClose:true});
                    })
                }).catch(function () {

                });
            },
            handlerBatchDelete: function (rows) {
                this.$logUtils.printLog("menuManage index method handlerBatchDelete run :");
            },
            // 监听 pagesize改变的事件
            handleSizeChange(newSize) {
                this.$logUtils.printLog("menuManage index method newSize val:\t" + newSize);
                this.pageSize = newSize;
                this.loadTableData()
            },
            // 监听 页码值 改变事件 第几页
            handleCurrentChange(newPageNo) {
                this.$logUtils.printLog("menuManage index method newPageNo val:\t" + newPageNo);
                this.currentPage = newPageNo;
                this.loadTableData()
            },
            //  加载后台数据
            loadTableData: function (pageNo = 1, pageSize = 10) {
                var that = this;
                that.$logUtils.printLog("menuManage index method loadTableData run:");
                var data = {};
                data.pageNo = pageNo;
                data.pageSize = pageSize;
                if (that.fromParam.name.length > 0) {
                    data.name = that.fromParam.name;
                }
                if (that.fromParam.parentId > 0) {
                    data.parentId = that.fromParam.parentId;
                }
                if (that.fromParam.id > 0) {
                    data.id = that.fromParam.id;
                }
                menuApi.list(data).then((res) => {
                    that.$logUtils.printLog("menuManage index method loadTableData load list data:");
                    that.$logUtils.printLog(res);
                    if (res.data.code === 0) {
                        that.$logUtils.printLog(typeof res.data.data.item != "undefined" && res.data.data.item.length > 0);
                        if (typeof res.data.data.list != "undefined" && res.data.data.list.length > 0) {
                            that.tableData = res.data.data.list;
                        }
                        if (typeof res.data.data.pageNo != "undefined") {
                            that.pageNo = res.data.data.pageNo;
                        }
                        if (typeof res.data.data.pageSize != "undefined") {
                            that.pageSize = res.data.data.pageSize;
                        }
                        if (typeof res.data.data.total != "undefined") {
                            that.total = res.data.data.total;
                        }
                        if (typeof res.data.data.roleOperation != "undefined" && Object.keys(res.data.data.roleOperation).length > 0) {
                            that.roleOperation = res.data.data.roleOperation;
                        }
                        that.$logUtils.printLog("menuManager index method loadTableData sync set data:");
                        that.$logUtils.printLog(that.tableData);
                        that.$logUtils.printLog(that.roleOperation);
                        that.$logUtils.printLog("menuManager index method loadTableData sync set data end ========>");
                    }
                }).catch((err) => {
                    that.$logUtils.printLog("menuManage index method loadTableData load list data error:");
                    that.$logUtils.printLog(err);
                })
            },
        }
    }
</script>

<style scoped>
    .cus-menu-manager {
    }

    .cus-menu-manager-from {
        width: 100%;
    }

    .cus-menu-manager-from-btn-create {
        float: right;
        margin-right: 1%;
    }

    .cus-page-el {
        text-align: center;
        margin: 12px auto;
    }

    .cus-edit-bg {
        background: lightgreen;
        border-color: lightgreen;
        color: #FFF;
    }

    /*>>>.el-pager li{*/
    /*  width: 36px;*/
    /*  height: 36px;*/
    /*  line-height: 36px;*/
    /*}*/
    /*>>>.el-pagination.is-background .el-pager li:not(.disabled).active{*/
    /*  background-color: #0C9F9A!important;*/
    /*  width: 36px;*/
    /*  height: 36px;*/
    /*  line-height: 36px;*/
    /*}*/
    /*!* .el-pager *!*/
    /*>>>.el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{*/
    /*  height: 36px;*/
    /*  min-width: 36px;*/
    /*}*/
    /*>>>.el-pagination.is-background .btn-prev, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li{*/
    /*  height: 36px;*/
    /*  min-width: 36px;*/
    /*}*/
</style>
