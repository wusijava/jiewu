<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="120px">
      <el-form-item label="新闻标题" prop="newsId">
        <el-select v-model="queryParams.newsId" placeholder="请选择新闻标题" clearable size="mini">
          <el-option v-for="item in newsOptions" :key="item.newsId" :label="item.newsTitle"
                     :value="item.newsId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['news:comment:add']">
          新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                   v-hasPermi="['news:comment:edit']">
          修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
                   v-hasPermi="['news:comment:remove']">
          删除
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="新闻评论编号" align="center" prop="commentId"/>
      <el-table-column label="新闻标题" align="center" prop="news.newsTitle">
        <template slot-scope="scope">
          {{ scope.row.news.newsTitle }}
        </template>
      </el-table-column>
      <el-table-column label="点赞数" align="center" prop="thumbs"/>
      <el-table-column label="创建者" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleRead(scope.row)"
                     v-hasPermi="['news:comment:edit']">
            查看
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['news:comment:edit']">
            修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['news:comment:remove']">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页插件-->
    <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" @pagination="getList"/>

    <!-- 添加或修改新闻评论对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-card shadow="always" v-if="dialogFlag === 1" :class="dialogFlag ===1?'dialog-mid':''">
        <div v-html="form.commentContent"/>
      </el-card>
      <!-- dialogFlag===undefined时候显示 -->
      <el-form ref="form" :model="form" :rules="rules" label-width="120px" v-if="dialogFlag === undefined">
        <el-row>
          <el-col :span="12">
            <el-form-item label="新闻id" prop="newsId">
              <el-select v-model="form.newsId" placeholder="请选择新闻标题">
                <el-option v-for="item in newsOptions" :key="item.newsId" :label="item.newsTitle"
                           :value="item.newsId"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="点赞数" prop="thumbs">
              <el-input-number v-model="form.thumbs" placeholder="请输入点赞数"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="新闻评论内容" prop="commentContent" required>
              <Editor v-model="form.commentContent"/>
            </el-form-item>
          </el-col>
          <el-col :span="24" style="padding-top: 30px">
            <el-form-item label="备注">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" v-if="dialogFlag === undefined">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listComment, getComment, delComment, addComment, updateComment, exportComment } from '@/api/news/comment'
import { optionSelect } from '@/api/news/news'
import Editor from '@/components/Editor'

export default {
  name: 'Comment',
  components: {
    Editor
  },
  data() {
    return {
      // 弹框标识 1 代表查看
      dialogFlag: undefined,
      // 新闻标题选择列表
      newsOptions: [],
      // 日期范围
      dateRange: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      pageNum: 1,
      pageSize: 10,
      // 总条数
      total: 0,
      // 新闻评论表格数据
      commentList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        newsId: undefined,
        createTime: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        newsId: [
          { required: true, message: '新闻标题不能为空', trigger: 'blur' }
        ],
        commentContent: [
          { required: true, message: '评论内容不能为空', trigger: 'blur' }
        ],
        thumbs: [
          { required: true, message: '点赞数不能为空', trigger: 'blur' }
        ]

      }
    }
  },
  created() {
    // 获取下拉列表
    this.getOptionSelect()
    this.getList()
  },
  methods: {
    /** 浏览新闻信息 **/
    handleRead(row) {
      this.dialogFlag = 1
      this.title = `查看评论内容`
      getComment(row.commentId).then(res => {
        if (res.flag) {
          this.form = res.data
          this.open = true
        }
      })
    },
    /** 查询新闻标题列表 */
    getOptionSelect() {
      optionSelect().then(response => {
        // console.log(response)
        this.newsOptions = response.data
      })
    },
    /** 查询新闻评论列表 */
    getList() {
      this.loading = true
      listComment(this.pageNum, this.pageSize, this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.commentList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        commentId: undefined,
        newsId: undefined,
        commentContent: undefined,
        thumbs: undefined,
        remark: undefined
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.commentId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.dialogFlag = undefined
      this.reset()
      this.open = true
      this.title = '添加新闻评论'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.dialogFlag = undefined
      this.reset()
      const commentId = row.commentId || this.ids
      getComment(commentId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改新闻评论'
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.commentId !== undefined) {
            updateComment(this.form).then(response => {
              if (response.code === 200) {
                this.msgNoticeSuccess('修改成功')
                this.open = false
                this.getList()
              }
            })
          } else {
            addComment(this.form).then(response => {
              if (response.code === 200) {
                this.msgNoticeSuccess('新增成功')
                this.open = false
                this.getList()
              }
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const commentIds = row.commentId || this.ids
      this.$confirm('是否确认删除新闻评论编号为"' + commentIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delComment(commentIds)
      }).then(() => {
        this.getList()
        this.msgNoticeSuccess('删除成功')
      }).catch(function() {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams
      this.$confirm('是否确认导出所有新闻评论数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return exportComment(queryParams)
      }).then(response => {
        this.download(response.msg)
      }).catch(function() {
      })
    }
  }
}
</script>
