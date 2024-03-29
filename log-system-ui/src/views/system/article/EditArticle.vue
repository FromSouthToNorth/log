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
    <div id="vditor"></div>
    <el-dialog :visible.sync="visibleArticleEdit" width="40%">
      <div slot="title">发布文章</div>
      <el-form :model="articleParams" :rules="rules" ref="queryForm" :inline="true" label-width="100px">
        <el-form-item label="文章标题" prop="articleTitle">
          <el-input
            v-model="articleParams.articleTitle"
            placeholder="请输入文章标题"
            clearable
            size="small"
            style="width: 240px;"
          />
        </el-form-item>
        <el-form-item label="文章描述" prop="remark">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="articleParams.remark"
            maxlength="160"
          />
        </el-form-item>
        <el-form-item label="上传文章封面">
          <el-upload
            drag
            multiple
            :headers="header"
            :before-upload="beforeUpload"
            :on-success="onSuccess"
            name="articlefile"
            :action="action">
            <i class="el-icon-upload" v-show="articleParams.articleCover === '' && !articleParams.articleCover"></i>
            <div class="el-upload__text" v-show="articleParams.articleCover === '' && !articleParams.articleCover">将文件拖到此处，或<em>点击上传</em></div>
            <img
              v-show="articleParams.articleCover !== '' && articleParams.articleCover"
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
        <el-form-item label="标签" prop="tagIds">
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
import {adminArticleUploadImg, adminGetArticleInfo, editArticle} from "@/api/system/article";
import * as imageConversion from "image-conversion";
import {getToken} from '@/utils/auth'
import errorCode from "@/utils/errorCode";
import Vditor from "vditor";

export default {
  name: "Article",
  dicts: ['sys_article_status', 'sys_article_type'],
  data() {
    return {
      articleParams: {
        articleId: undefined,
        remark: undefined,
        articleTitle: undefined,
        typeId: undefined,
        tagIds: [],
        type: '1',
        status: '1',
        isTop: 0,
        articleContent: undefined,
        articleCover: ''
      },
      action: process.env.VUE_APP_BASE_API + '/system/article/image',
      types: [],
      tags: [],
      visibleArticleEdit: false,
      header: {
        Authorization: 'Bearer ' + getToken()
      },
      rules: {
        articleTitle: [
          { required: true, message: '请输入文章标题', trigger: 'blur' }
        ],
        remark: [
          { required: true, message: '请输入文章描述', trigger: 'blur' }
        ],
        typeId: [
          { required: true, message: '请选择文章类型', trigger: 'change' }
        ],
        tagIds: [
          { required: true, message: '请选择文章标签', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    const articleId = this.$route.params && this.$route.params.articleId
    this.getArticle(articleId)
  },
  methods: {
    initContentEditor(article) {
      this.$nextTick(() => {
        this.contentEditor = new Vditor('vditor', {
          height: 740,
          preview: {
            hljs: {
              enable: true,
              lineNumber: true,
              style: 'xcode'
            }
          },
          cache: {
            enable: false
          },
          outline: {
            enable: true
          },
          input: (value) => {
            this.articleParams.articleContent = value
          },
          after: () => {
            this.contentEditor.setValue(article)
          },
          upload: {
            url: this.action,           // 文件上传路径
            fieldName: 'articlefile',   // 文件上传字段名
            headers: this.header,       // 文件上传携带的请求头
            format(files, result) {
              let res = JSON.parse(result)
              let { msg, code, imgUrl } = res
              return JSON.stringify({
                msg,
                code,
                data: {
                  errFiles: [],
                  succMap: {[imgUrl]: imgUrl}
                }
              })
            }
          }
        })
      })
    },
    /** 获取文章 */
    getArticle(articleId) {
      adminGetArticleInfo(articleId).then(result => {
        this.tags = result.tags
        this.types = result.types
        let article = result.data
        this.articleParams.articleId = article.articleId
        this.articleParams.remark = article.remark
        this.articleParams.tagIds = article.tagIds
        this.articleParams.articleTitle = article.articleTitle
        this.articleParams.typeId = article.typeId
        this.articleParams.type = article.type
        this.articleParams.status = article.status
        this.articleParams.isTop = article.isTop
        this.articleParams.articleContent = article.articleContent
        this.articleParams.articleCover = article.articleCover
        this.initContentEditor(this.articleParams.articleContent)
      })
    },
    /** 提交文章 */
    submitArticle() {
      if (!this.articleParams.articleContent && this.articleParams.articleContent === '') {
        this.$message.error("文章内容不能为空!")
        return
      }
      this.$refs['queryForm'].validate(valid => {
        if (valid) {
          editArticle(this.articleParams).then(result => {
            this.$modal.msgSuccess("编辑成功")
            this.visibleArticleEdit = false
            this.$router.push({
              path: '/system/article'
            })
          })
        }
      })
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
      // return new Promise(resolve => {
      //   if (file.size / 1024 < 200) {
      //     resolve(file);
      //   }
      //   imageConversion.compressAccurately(file, 200)
      //     .then(res => {
      //       resolve(res)
      //     })
      // })
    },
    /** 上传文章封面返回结果 */
    onSuccess(result) {
      let {code, imgUrl, msg} = result;
      code = code || 200;
      msg = msg || errorCode[code] || errorCode['default']
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
          formData.append("articlefile", new window.File([result], file.name, {type: file.type}))
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
