<template>
  <div class="app-container">
    <el-row>
      <!-- 文章数据-->
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" lebel-width="68px">
          <el-form-item label="文章标题" prop="articleTitle">
            <el-input
              v-model="queryParams.articleTitle"
              placeholder="请输入文章标题"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="getList()"
            />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="文章状态"
              clearable
              size="small"
              style="width: 240px">
              <el-option
                v-for="dict in dict.type.sys_article_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="类型" prop="articleStatus">
            <el-select
              v-model="queryParams.type"
              placeholder="文章类型"
              clearable
              size="small"
              style="width: 240px">
              <el-option
                v-for="dict in dict.type.sys_article_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="发布日期">
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
            <router-link to="/system/article-add/index">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                v-hasPermi="['system:article:add']"
              >新增文章
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
              v-hasPermi="['system:article:edit']"
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
              v-hasPermi="['system:article:remove']"
            >删除
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="" :columns="columns"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="articleList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center"></el-table-column>
          <el-table-column label="封面" align="center" prop="articleCover" v-if="columns[0].visible">
            <template slot-scope="scope">
              <el-image
                :style="{ width: '80px', height: '50px' }"
                :src="scope.row.articleCover"/>
            </template>
          </el-table-column>
          <el-table-column label="标题" align="center" key="articleTitle" prop="articleTitle" v-if="columns[1].visible"/>
          <el-table-column label="分类" align="center" prop="articleType" v-if="columns[2].visible">
            <template slot-scope="scope">
              <el-tag type="success">{{ scope.row.articleType.typeName }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="类型" align="center" prop="type" key="type" v-if="columns[3].visible">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_article_type" :value="scope.row.type"/>
            </template>
          </el-table-column>
          <el-table-column label="状态" align="center" prop="status" key="status" v-if="columns[4].visible">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_article_status" :value="scope.row.status"/>
            </template>
          </el-table-column>
          <el-table-column label="是否置顶" align="center" prop="isTop" v-if="columns[5].visible">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.isTop"
                active-value="1"
                inactive-value="0"
                @change="handleStatusChange(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="发布时间" align="center" prop="createTime" v-if="columns[6].visible">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
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
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {adminListArticle, changArticleTop, delArticle} from "@/api/system/article";

export default {
  name: "ArticleList",
  dicts: ['sys_article_status', 'sys_article_type'],
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        articleTitle: undefined,
        status: undefined,
        type: undefined,
        tags: []
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
      articleList: [],
      // 列表信息
      columns: [
        {key: 0, label: `封面`, visible: true},
        {key: 1, label: `标题`, visible: true},
        {key: 2, label: `分类`, visible: true},
        {key: 3, label: `类型`, visible: true},
        {key: 4, label: `状态`, visible: true},
        {key: 4, label: `是否置顶`, visible: true},
        {key: 5, label: `发布时间`, visible: true}
      ]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询文章列表 */
    getList() {
      this.loading = true;
      adminListArticle(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.articleList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.articleId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 修改文章操作 */
    handleUpdate(row) {
      const articleId = row.articleId || this.ids
      this.$router.push({
        path: '/system/article-edit/index/' + articleId
      })
    },
    // 修改文章置顶
    handleStatusChange(row) {
      let text = row.isTop === "0" ? "取消" : "开启";
      this.$modal.confirm('确认要' + text + '""' + row.articleTitle + "置顶吗？").then(() => {
        return changArticleTop(row.articleId, row.isTop)
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
    // 删除文章
    handleDelete(row) {
      const articleIds = row.articleId || this.ids;
      this.$modal.confirm('是否要删除文章编号为"' + articleIds +'"的数据？').then(() => {
        return delArticle(articleIds);
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>

<style scoped>

</style>
