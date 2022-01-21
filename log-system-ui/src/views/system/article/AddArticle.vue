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
      @imgAdd="uploadImg"
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
            name="articlefile"
            :headers="header"
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            action="/dev-api/system/article/image">
            <i class="el-icon-upload" v-show="articleParams.articleCover === ''"></i>
            <div class="el-upload__text" v-show="articleParams.articleCover === ''">将文件拖到此处，或<em>点击上传</em></div>
            <img
              v-show="articleParams.articleCover !== ''"
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
      </el-form>
      <div slot="footer">
        <el-button
          type="success"
          plain
          icon="el-icon-position"
          size="small"
          @click="submitArticle"
          v-hasPermi="['system:article:add']"
        >
          发表文章
        </el-button>
        <el-button
          plain
          icon="el-icon-close"
          type="warning"
          size="small"
        >
          取消发表
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {adminArticleUploadImg, adminGetArticleInfo} from "@/api/system/article";
import * as imageConversion from "image-conversion";
import { getToken } from '@/utils/auth'
import errorCode from "@/utils/errorCode";
export default {
  name: "Article",
  dicts: ['sys_article_status', 'sys_article_type'],
  data() {
    return {
      articleParams: {
        articleTitle: undefined,
        typeId: undefined,
        tagIds: [],
        type: '',
        status: '',
        isTop: 0,
        articleContent: undefined,
        articleCover: ''
      },
      types: [],
      tags: [],
      visibleArticleEdit: false,
      header: {
        Authorization: 'Bearer ' + getToken()
      }
    }
  },
  created() {
    this.getTypeAndTag()
  },
  methods: {
    /** 获取文章 */
    getTypeAndTag() {
    },
    /** 提交文章 */
    submitArticle() {
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
    beforeUpload(file) {
      return new Promise(resolve => {
        if (file.size / 1024 < 200) {
          resolve(file);
        }
        imageConversion.compressAccurately(file, 200)
          .then(res => {
            resolve(res)
          })
      })
    },
    /** 上传文章封面返回结果 */
    onSuccess(result) {
      let { code, imgUrl, msg } = result;
      code = code || 200;
      msg =  msg || errorCode[code] || errorCode['default']
      if (code === 200) {
        this.articleParams.articleCover = imgUrl
        this.$message.success(msg);
      }
      else {
        this.$message.error(msg);
      }
    },
    /** 文章内容图片上传 */
    uploadImg(pos, file) {
      var formData = new FormData();
      if (file.size / 1024 < 200) {
        formData.append("articlefile", file)
        adminArticleUploadImg(formData).then(result => {
          this.$refs.md.$img2Url(pos, result.imgUrl);
        })
      }
      else {
        imageConversion.compressAccurately(file, 200).then(result => {
          formData.append("articlefile", new window.File([result], file.name, { type: file.type }))
          adminArticleUploadImg(formData).then(result => {
            this.$refs.md.$img2Url(pos, result.imgUrl);
          })
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
