<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" lebel-width="68px">
          <el-form-item label="标签名称" prop="tagName">
            <el-input
              v-model="queryParams.tagName"
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
            <el-button type="primary" icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:tag:add']"
            >新增标签
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['system:tag:edit']"
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
              @click="handleDelete"
              v-hasPermi="['system:tag:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="" :columns="columns"></right-toolbar>
        </el-row>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="tagList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center"></el-table-column>
      <el-table-column label="标签名称" align="center" prop="tagName" key="tagName" v-if="columns[0].visible" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:user:edit']"
          >编辑
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:user:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
      :total="total" />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="标签昵称" prop="tagName">
              <el-input v-model="form.tagName" placeholder="请输入标签昵称" maxlength="30"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addArticleTag, adminListArticleTag, getArticleTagInfo, updateArticleTag, delArticleTag} from "@/api/system/tag";

export default {
  name: "index",
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tagName: undefined,
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
      ],
      tagList: [],
      // 选中数组
      ids: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        tagName: [
          {required: true, message: "用户名称不能为空", trigger: "blur"}
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true
      adminListArticleTag(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.tagList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      console.log(selection);
      this.ids = selection.map(item => item.tagId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tagId) {
            updateArticleTag(this.form).then(result => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          }
          else {
            addArticleTag(this.form).then(result => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
    },
    reset() {
      this.form = {
        tagId: undefined,
        tagName: undefined
      }
      this.resetForm("form")
    },
    /** 编辑取消按钮 */
    cancel() {
      this.open = false
      this.reset()
    },
    /** 添加按钮 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加标签'
    },
    /** 编辑按钮 */
    handleUpdate(row) {
      this.reset()
      const tagId = row.tagId || this.ids;
      getArticleTagInfo(tagId).then(result => {
        this.title = '编辑标签'
        this.open = true
        this.form = result.data
      })
    },
    /** 删除按钮 */
    handleDelete(row){
      const tagIds = row.tagId || this.ids
      this.$modal.confirm('是否确认删除标签编号为"' + tagIds + '"的数据项？').then(function() {
        return delArticleTag(tagIds)
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
}
</script>

<style scoped>

</style>
