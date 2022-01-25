<template>
    <div class="right-content-h cus-relative">
        <div>
            <el-table
                    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
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
                <el-table-column label="排序" prop="order"/>
                <el-table-column
                        align="right" label="操作">
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
        </div>


        <div class="cus-page-el">
            <pagination @sizeChange='handleSizeChange'
                        :pageSize='pageSize'
                        @currentChange='handleCurrentChange'
                        :currentPage='currentPage' :total='total'/>
        </div>
    </div>
</template>

<script>


    import commonConstants from "../../utils/constants/CommonConstants";
    import selectOptionConstants from "../../utils/constants/SelectOptionConstants";

    export default {
        name: "permission",
        data() {
            return {
                pageSize: 200,
                currentPage: 1,
                total: 10000,
                tableData: [],
                multipleSelection: [],
                roleOperation: {},// 拥有什么操作
            };
        },
        mounted() {
            this.loadDataList();
        },
        methods: {
            /**
             * 数据访问
             */
            loadDataList() {

            },
        },
        computed:{

        }
    };
</script>

<style lang="less" scoped>

    .parent-permission-div {
    }

    .el-tab-pane {
        .el-scrollbar__wrap {
            overflow-x: hidden;
        }
    }

    .cus-page-el {
        text-align: center;
        margin: 12px auto;
    }

</style>