<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="友情链接名称" prop="linkName">
        <el-input
          v-model="queryParams.linkName"
          placeholder="请输入友情链接名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="友情链接地址" prop="linkUrl">
        <el-input
          v-model="queryParams.linkUrl"
          placeholder="请输入友情链接地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人邮件" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入联系人邮件"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['news:link:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['news:link:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['news:link:remove']"
        >删除
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="linkList" @selection-change="handleSelectionChange" size="mini" :height="tableHeight">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="友情链接编号" align="center" prop="linqId"/>
      <el-table-column label="友情链接名称" align="center" prop="linkName"/>
      <el-table-column label="友情链接地址" align="center" prop="linkUrl" width="180">
        <template slot-scope="scope">
          <a class="link-url" :href="scope.row.linkUrl" target="_blank">{{ scope.row.linkUrl }}</a>
        </template>
      </el-table-column>
      <el-table-column label="联系人邮件" align="center" prop="email" width="180"/>
      <el-table-column label="显示顺序" align="center" prop="orderNum"/>
      <el-table-column label="创建者" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['news:link:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['news:link:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="pageNum"
      :limit.sync="pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改友情链接对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="友情链接名称" prop="linkName">
          <el-input v-model="form.linkName" placeholder="请输入友情链接名称" clearable :maxlength="30" show-word-limit/>
        </el-form-item>
        <el-form-item label="链接地址" prop="linkUrl">
          <el-input v-model="form.linkUrl" placeholder="https://或http://"/>
        </el-form-item>
        <el-form-item label="联系人邮件" prop="email">
          <el-input v-model="form.email" placeholder="请输入联系人邮件"/>
        </el-form-item>
        <el-form-item label="显示排序" prop="orderNum">
          <el-input-number v-model="form.orderNum" controls-position="right" :min="0"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLink, getLink, delLink, addLink, updateLink } from '@/api/news/link'

export default {
  name: 'Link',
  data () {
    return {
      tableHeight: window.innerHeight - 340,
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
      // 友情链接表格数据
      linkList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        linkName: undefined,
        linkUrl: undefined,
        email: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        linkName: [
          { required: true, message: '友情链接名称不能为空', trigger: 'blur' }
        ],
        linkUrl: [
          { required: true, message: '友情链接地址不能为空', trigger: 'blur' },
          {
            pattern: /[a-zA-z]+:\/\/[^\s]*/,
            message: '请输入正确的链接地址',
            trigger: 'blur'
          }
        ],
        email: [
          { required: true, message: '邮箱地址不能为空', trigger: 'blur' },
          {
            type: 'email',
            message: '\'请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        orderNum: [
          { required: true, message: '链接显示顺序不能为空', trigger: 'blur' }
        ],
      }
    }
  },
  mounted () {
    //后面的50：根据需求空出的高度，自行调整
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 340
    })
  },
  created () {
    this.getList()
  },
  methods: {
    /** 查询友情链接列表 */
    getList () {
      this.loading = true
      listLink(this.pageNum, this.pageSize, this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.linkList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel () {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset () {
      this.form = {
        linqId: undefined,
        linkName: undefined,
        linkUrl: undefined,
        email: undefined,
        orderNum: undefined,
        remark: undefined
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.linqId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.reset()
      this.open = true
      this.title = '添加友情链接'
    },
    /** 修改按钮操作 */
    handleUpdate (row) {
      this.reset()
      const linqId = row.linqId || this.ids
      getLink(linqId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改友情链接'
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.linqId !== undefined) {
            updateLink(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }
            })
          } else {
            addLink(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess('新增成功')
                this.open = false
                this.getList()
              }
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete (row) {
      const linqIds = row.linqId || this.ids
      this.$confirm('是否确认删除友情链接编号为"' + linqIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delLink(linqIds)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function () {
      })
    },
  }
}
</script>
<style lang="scss">
.link-url {
  color: #7a6df0;
  text-decoration: none;
  font-weight: bolder;
}

.link-url:hover {
  color: #1482f0;
}
</style>
