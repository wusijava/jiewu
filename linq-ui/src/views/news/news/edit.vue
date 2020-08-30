<template>
  <el-form ref="form" :model="form" :rules="rules" size="mini" label-width="100px" label-position="right">
    <el-form-item label="新闻类别" prop="newsTypeId">
      <el-select v-model="form.newsTypeId" placeholder="请输入新闻类别" clearable :style="{width: '80%'}">
        <el-option v-for="(item, index) in newsTypeOptions" :key="index" :label="item.newsTypeName"
                   :value="item.newsTypeId" :disabled="item.status === '1'"/>
      </el-select>
    </el-form-item>
    <el-form-item label="新闻标题" prop="newsTitle">
      <el-input v-model="form.newsTitle" placeholder="请输入新闻标题" :maxlength="30" show-word-limit clearable
                prefix-icon='el-icon-tickets' :style="{width: '80%'}"></el-input>
    </el-form-item>
    <el-form-item label="新闻封面" prop="newsImage">
      <el-upload class="news-image-uploader" :action="newsImageAction" :show-file-list="false"
                 :on-success="uploadSuccess" :headers="headers">
        <img v-if="imageUrl" :src="imageUrl" class="news-image" alt="">
        <i v-else class="el-icon-plus avatar-uploader-icon"/>
      </el-upload>
    </el-form-item>
    <el-form-item label="是否公开" prop="isPublic">
      <el-radio-group v-model="form.isPublic" size="medium">
        <el-radio v-for="(item, index) in isPublicOptions" :key="index" :label="item.value"
                  :disabled="item.disabled">{{ item.label }}
        </el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="显示顺序" prop="orderNum">
      <el-input-number v-model="form.orderNum" placeholder="显示顺序"></el-input-number>
    </el-form-item>
    <el-form-item label="新闻属性" prop="newsAttr">
      <el-radio-group v-model="form.newsAttr" size="medium">
        <el-radio-button v-for="(item, index) in newsAttrOptions" :key="index" :label="item.value"
                         :disabled="item.disabled">{{ item.label }}
        </el-radio-button>
      </el-radio-group>
    </el-form-item>
    <!--富文本编辑器-->
    <el-form-item label="新闻内容" prop="newsContent">
      <tinymce v-model="form.newsContent" height="200" width="80%"/>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="form.remark" type="textarea" placeholder="请输入备注"
                :autosize="{minRows: 2, maxRows: 2}" :style="{width: '80%'}"></el-input>
    </el-form-item>
    <el-form-item size="large">
      <el-button type="primary" @click.native="submitForm">提交</el-button>
      <el-button @click="cancel">返回</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import { getToken } from '@/utils/auth'
import { optionSelect } from '@/api/news/type'
import { getNews, updateNews } from '@/api/news/news'
import Tinymce from '@/components/Tinymce/index'

export default {
  name: 'edit',
  components: {
    Tinymce
  },
  created () {
    // 获取新闻类别下拉列表
    this.getOptionSelect()
    // 重置表单
    this.reset()
  },
  data () {
    return {
      headers: {
        // 上传文件的请求头
        Authorization: 'Bearer ' + getToken()
      },
      imageUrl: null, // 上传图片回显
      // 新闻封面请求地址
      newsImageAction: process.env.VUE_APP_BASE_API + process.env.VUE_APP_COMMON_FAST_UPLOAD, // 上传的图片服务器地址
      // 新闻类别列表
      newsTypeOptions: [],
      // 是否公开选择
      isPublicOptions: [
        {
          'label': '公开',
          'value': '0'
        },
        {
          'label': '私有',
          'value': '1'
        }
      ],
      // 新闻属性
      newsAttrOptions: [
        {
          'label': '头条区新闻',
          'value': '0'
        },
        {
          'label': '幻灯片区新闻',
          'value': '1'
        },
        {
          'label': '热点区新闻',
          'value': '2'
        }
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        newsTypeId: [{
          required: true,
          message: '请输入新闻类别',
          trigger: 'change'
        }],
        newsTitle: [{
          required: true,
          message: '请输入新闻标题',
          trigger: 'blur'
        }],
        isPublic: [{
          required: true,
          message: '是否公开不能为空',
          trigger: 'change'
        }],
        orderNum: [{
          required: true,
          message: '显示顺序',
          trigger: 'blur'
        }],
        newsAttr: [{
          required: true,
          message: '新闻属性不能为空',
          trigger: 'change'
        }],
        newsContent: [{
          required: true,
          message: '请输入新闻内容',
          trigger: 'blur'
        }],
        remark: [],
      },
    }
  },
  methods: {
    /** 查询新闻类型列表 */
    getOptionSelect () {
      optionSelect().then(response => {
        // console.log(response)
        this.newsTypeOptions = response.data
      })
    },
    /** 上传封面成功 **/
    uploadSuccess (res, file) {
      // console.log(res)
      if (res.code === 200) {
        const { url } = res.data
        this.msgSuccess(res.msg)
        this.imageUrl = url
        this.form.newsImage = url
      }
    },
    // 取消按钮
    cancel () {
      this.reset()
      // 返回列表页
      this.$router.push({ name: 'News' })
    },
    // 表单重置
    reset () {
      // 从路由中按到这个newsId
      const newsId = this.$route.params.newsId
      // 获取表单信息 回显
      getNews(newsId).then(response => {
        this.form = response.data
        // 图片回显
        this.imageUrl = this.form.newsImage
      })
    },
    /** 提交按钮 */
    submitForm () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          updateNews(this.form).then(response => {
            if (response.code === 200) {
              this.msgSuccess('修改成功')
              this.reset()
              // 返回列表页
              this.$router.push({ name: 'News' })
              // // 刷新一下 整体页面
              // location.reload()
            }
          })
        }
      })
    },
  },

}

</script>

<style lang="scss" scoped>

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.news-image-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #000000;
  background-color: #e8e8e8;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}

.news-image {
  width: 100px;
  height: 100px;
  display: block;
}
</style>
