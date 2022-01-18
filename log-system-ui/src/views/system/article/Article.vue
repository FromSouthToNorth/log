<template>
  <div class="app-container">
    <el-col :span="24" :xs="24">
      <el-form :model="articleParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="文章标题" prop="articleTitle">
          <el-input
            v-model="articleParams.articleTitle"
            placeholder="请输入文章标题"
            clearable
            size="small"
            style="width: 240px;"
          />
        </el-form-item>
        <el-form-item label="分类" prop="typeId">
          <el-select
            v-model="articleParams.typeId"
            placeholder="文章分类"
            clearable
            size="small"
            style="width: 240px;"
          ></el-select>
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-select
            v-model="articleParams.tags"
            placeholder="文章标签"
            clearable
            size="small"
            style="width: 240px;"
            multiple
          >
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select
            v-model="articleParams.status"
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
        <el-form-item label="类型" prop="type">
          <el-select
            v-model="articleParams.type"
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
        <el-form-item label="是否置顶" prop="isTop">
          <el-switch
            v-model="articleParams.isTop"
            active-value="1"
            inactive-value="0"
          ></el-switch>
        </el-form-item>
        <mavon-editor
          ref="md"
          v-model="articleParams.articleContent"
          :style="{ height: 'calc(100vh - 260px)' }"
        />
      </el-form>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "Article",
  dicts: ['sys_article_status', 'sys_article_type'],
  data() {
    return {
      articleParams: {
        articleTitle: undefined,
        typeId: undefined,
        tags: [],
        type: undefined,
        isTop: 0,
        articleContent: undefined
      }
    }
  }
}
</script>

<style scoped>

</style>
