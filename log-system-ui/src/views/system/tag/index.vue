<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" lebel-width="68px">
          <el-form-item label="标签名称" prop="typeName">
            <el-input
              v-model="queryParams.typeName"
              placeholder="请输入标签名称"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native=""
            />
          </el-form-item>
          <el-form-item label="创建日期">
            <el-date-picker
              v-model="dateRange"
              size="small"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <router-link to="/system/article-add/index">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                v-hasPermi="['system:article:add']"
              >新增标签
              </el-button>
            </router-link>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="multiple"
              @click="handleUpdate"
              v-hasPermi="['system:type:edit']"
            >编辑
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click=""
              v-hasPermi="['system:type:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="" :columns="columns"></right-toolbar>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: undefined,
      },
      // 日期范围
      dateRange: [],
      showSearch: true,
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 非多个禁用
      multiple: true,
      // 非单个禁用
      single: true,
      // 总条数
      total: 0,
      // 列表信息
      columns: [
        {key: 0, label: `名称`, visible: true},
      ]
    }
  },
  methods: {
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
    },
    handleUpdate(row) {
    },
  }
}
</script>

<style scoped>

</style>
