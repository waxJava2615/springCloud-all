<template>
    <div class="right-content-h">

        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="角色基础信息" name="baseRole">
                <div class="parent-permission-div">
                    角色基础信息
                </div>
            </el-tab-pane>
            <el-tab-pane label="可操作功能" name="accessOperation">
                <el-scrollbar style="height: 760px;">
                    <permission-tree-com/>
                </el-scrollbar>
            </el-tab-pane>
            <el-tab-pane label="可访问数据" name="accessData">
                <el-scrollbar style="height: 760px;">
                    <permission-tree-com/>
                </el-scrollbar>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>

    import permissionTreeCom from "../../../components/permissionTree";

    export default {
        name: "permissionFrom",
        components: {permissionTreeCom},
        data() {
            return {
                activeName: 'baseRole',
                cs: ''
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },


            /**
             * 角色基本信息
             */

            /**
             *  操作权限
             */
            loadOperation(){},
            /**
             * 数据访问
             */
            loadDataPermission(){},
        }
    };
</script>

<style lang="less" scoped>

    .parent-permission-div {
    }

    .el-tab-pane {
        .el-scrollbar__wrap{
            overflow-x: hidden;
        }
    }

</style>