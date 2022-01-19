<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-position"
          size="mini"
          @click="pushArticle"
          v-hasPermi="['system:article:add']"
        >
          发表文章
        </el-button>
      </el-col>
    </el-row>
    <mavon-editor
      ref="md"
      v-model="articleParams.articleContent"
      :style="{ height: 'calc(100vh - 160px)' }"
    />
    <el-dialog :visible.sync="visibleArticleEdit" width="40%">
      <div slot="title">发布文章</div>
      <el-form :model="articleParams" ref="queryForm" :inline="true" label-width="100px">
        <el-form-item label="文章标题" prop="articleTitle">
          <el-input
            v-model="articleParams.articleTitle"
            placeholder="请输入文章标题"
            clearable
            size="small"
            style="width: 240px;"
          />
        </el-form-item>
        <el-form-item label="上传文章封面">
          <el-upload
            drag
            multiple
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            action="">
            <i class="el-icon-upload" v-if="articleParams.articleCover === ''"></i>
            <div class="el-upload__text" v-if="articleParams.articleCover === ''">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <img
              v-else
              :src="articleParams.articleCover"
              width="360px"
              height="180px"
              alt="文章封面"
            />
          </el-upload>
        </el-form-item>
        <el-form-item label="分类" prop="typeId">
          <el-select
            v-model="articleParams.typeId"
            placeholder="文章分类"
            clearable
            size="small"
            style="width: 240px;"
          >
            <el-option
              v-for="type in types"
              :key="type.typeId"
              :label="type.typeName"
              :value="type.typeId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="标签" prop="tags">
          <el-select
            v-model="articleParams.tagIds"
            placeholder="文章标签"
            clearable
            size="small"
            style="width: 240px;"
            multiple
          >
            <el-option
              v-for="tag in tags"
              :key="tag.tagId"
              :label="tag.tagName"
              :value="tag.tagId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select
            v-model="articleParams.status + ''"
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
            v-model="articleParams.type + ''"
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
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {adminGetArticleInfo} from "@/api/system/article";

export default {
  name: "Article",
  dicts: ['sys_article_status', 'sys_article_type'],
  data() {
    return {
      articleParams: {
        articleTitle: undefined,
        typeId: undefined,
        tagIds: [],
        type: undefined,
        status: undefined,
        isTop: 0,
        articleContent: undefined,
        articleCover: undefined
      },
      types: [],
      tags: [],
      visibleArticleEdit: false
    }
  },
  created() {
    const articleId = this.$route.params && this.$route.params.articleId
    this.getArticle(articleId)
  },
  methods: {
    /** 获取文章 */
    getArticle(articleId) {
      if (articleId !== 0) {
        adminGetArticleInfo(articleId).then(result => {
          this.tags = result.tags
          this.types = result.types
          let article = result.data
          this.articleParams.tagIds = result.tagIds
          this.articleParams.articleTitle = article.articleTitle
          this.articleParams.typeId = article.typeId
          this.articleParams.type = article.type
          this.articleParams.status = article.status
          this.articleParams.isTop = article.isTop
          this.articleParams.articleContent = article.articleContent
          this.articleParams.articleCover = article.articleCover
        })
      }
    },
    /** 文章编辑界面发布按钮 */
    pushArticle() {
      if (this.articleParams.articleContent && this.articleParams.articleContent !== '') {
        this.visibleArticleEdit = true
      }
      else {
        this.$message.error(" 文章内容不能为空! ")
      }
    },
    /** 上传文章封面 */
    beforeUpload() {
      console.log('beforeUpload');
    },
    /** 上传文章封面返回结果 */
    onSuccess() {

    }
  }
}
</script>

<style scoped>

</style>
