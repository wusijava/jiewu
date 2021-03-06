<template>
  <div class="app-container">
    <el-row :gutter="10">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input v-model="deptName" placeholder="请输入部门名称" size="small" prefix-icon="el-icon-search"
                    style="margin-bottom: 20px"/>
        </div>
        <div class="head-container">
          <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false"
                   :filter-node-method="filterNode" ref="tree" default-expand-all @node-click="handleNodeClick"
          />
        </div>
      </el-col>

      <!--查询条件-->
      <el-col :span="20" :xs="24">

        <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
          <el-form-item label="用户名称" prop="trueName">
            <el-input v-model="queryParams.trueName" placeholder="请输入用户名称" clearable size="small" style="width: 240px"
                      @keyup.enter.native="handleQuery"/>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="queryParams.phone" placeholder="请输入手机号码" clearable size="small" style="width: 240px"
                      @keyup.enter.native="handleQuery"/>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="用户状态" clearable size="small" style="width: 240px">
              <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel"
                         :value="dict.dictValue"/>
            </el-select>
          </el-form-item>
          <el-form-item label="创建时间">
            <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                            type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <!--操作栏-->
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd"
                       v-hasPermi="['system:user:add']">
              新增
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                       v-hasPermi="['system:user:edit']">
              修改
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
                       v-hasPermi="['system:user:remove']">
              删除
            </el-button>
          </el-col>
        </el-row>

        <!--数据列表-->
        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange" size="mini"
                  :height="tableHeight">
          <el-table-column type="selection" width="50" align="center"/>
          <el-table-column label="用户编号" align="center" prop="userId"/>
          <el-table-column label="用户名称" align="center" prop="trueName" :show-overflow-tooltip="true"/>
          <el-table-column label="头像" align="center" prop="avatar" width="60px">
            <template slot-scope="scope">
              <span v-if="scope.row.avatar===''">无</span>
              <img :src="getAvatarUrl(scope.row.avatar)" alt="" class="img-mini">
            </template>
          </el-table-column>
          <el-table-column label="性别" align="center" prop="sex" width="80px">
            <template slot-scope="scope">
              <span v-if="scope.row.sex==='0'">男</span>
              <span v-else-if="scope.row.sex==='1'">女</span>
              <span v-else>未知</span>
            </template>
          </el-table-column>
          <el-table-column label="用户昵称" align="center" prop="nickName" :show-overflow-tooltip="true"/>
          <el-table-column label="部门" align="center" prop="dept.deptName" :show-overflow-tooltip="true"/>
          <el-table-column label="手机号码" align="center" prop="phone" width="120"/>
          <el-table-column label="状态" align="center" prop="status">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.status" active-value="0" inactive-value="1"
                         @change="handleStatusChange(scope.row)"/>
            </template>
          </el-table-column>
          <el-table-column label="最后登陆IP" align="center" prop="loginIp" width="120"/>
          <el-table-column label="创建时间" align="center" prop="createTime" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                         v-hasPermi="['system:user:edit']">
                修改
              </el-button>
              <el-button v-if="scope.row.userId !== 1" size="mini" type="text" icon="el-icon-delete"
                         @click="handleDelete(scope.row)" v-hasPermi="['system:user:remove']">
                删除
              </el-button>
              <el-button size="mini" type="text" icon="el-icon-key" @click="handleResetPwd(scope.row)"
                         v-hasPermi="['system:user:resetPwd']">
                重置
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <!--分页插件-->
        <pagination
          v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" @pagination="getList"/>
      </el-col>
    </el-row>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户昵称" prop="nickName">
              <el-input v-model="form.nickName" placeholder="请输入用户昵称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="deptId">
              <tree-select v-model="form.deptId" :options="deptOptions" :disable-branch-nodes="true" :show-count="true"
                           placeholder="请选择归属部门"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入手机号码" :maxlength="11" show-word-limit
                        clearable prefix-icon='el-icon-phone-outline' suffix-icon='el-icon-warning-outline'/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item v-if="form.userId === undefined" label="用户名称" prop="trueName">
              <el-input v-model="form.trueName" placeholder="请输入用户名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.userId === undefined" label="用户密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入用户密码" type="password"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户性别">
              <el-select v-model="form.sex" placeholder="请选择">
                <el-option v-for="dict in sexOptions" :key="dict.dictValue" :label="dict.dictLabel"
                           :value="dict.dictValue"/>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{ dict.dictLabel }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="角色">
              <el-select v-model="form.roleIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in roleOptions"
                  :key="item.roleId"
                  :label="item.roleName"
                  :value="item.roleId"
                  :disabled="item.status === '1'"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
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
import {
  listUser,
  getUser,
  delUser,
  addUser,
  updateUser,
  resetUserPwd,
  changeUserStatus
} from '@/api/system/user'
import { treeSelect } from '@/api/system/dept'
import TreeSelect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'

export default {
  name: 'User',
  components: { TreeSelect },
  data() {
    return {
      tableHeight: window.innerHeight - 340,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 当前页
      pageNum: 1,
      // 每页显示记录数
      pageSize: 10,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: '',
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 状态数据字典
      statusOptions: [
        // {
        //   'dictValue': '0',
        //   'dictLabel': '正常'
        // },
        // {
        //   'dictValue': '1',
        //   'dictLabel': '停用'
        // },
      ],
      // 性别状态字典
      sexOptions: [
        // {
        //   'dictValue': '0',
        //   'dictLabel': '男',
        // },
        // {
        //   'dictValue': '1',
        //   'dictLabel': '女'
        // },
        // {
        //   'dictValue': '2',
        //   'dictLabel': '未知'
        // }
      ],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      // 查询参数
      queryParams: {
        trueName: undefined,
        phone: undefined,
        status: undefined,
        deptId: undefined
      },
      // 表单校验
      rules: {
        trueName: [
          { required: true, message: '用户名称不能为空', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: '用户昵称不能为空', trigger: 'blur' }
        ],
        deptId: [
          { required: true, message: '归属部门不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '用户密码不能为空', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '邮箱地址不能为空', trigger: 'blur' },
          {
            type: 'email',
            message: '\'请输入正确的邮箱地址',
            trigger: ['blur', 'change']
          }
        ],
        phone: [
          { required: true, message: '手机号码不能为空', trigger: 'blur' },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: '请输入正确的手机号码',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val)
    }
  },
  mounted() {
    //后面的50：根据需求空出的高度，自行调整
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 340
    })
  },
  created() {
	  alert("123");
    this.getList()
    this.getTreeSelect()
    // 获取数据字典
    this.getDicts('sys_normal_disable').then(response => {
		alert("状态"+JSON.stringify(response));
      this.statusOptions = response.data
    })
    this.getDicts('sys_user_sex').then(response => {
		alert("性别"+JSON.stringify(response));
      this.sexOptions = response.data
    })
    this.getConfigKey('sys.user.initPassword').then(response => {
      this.initPassword = response.msg
    })
  },
  methods: {
    // 获取头像地址
    getAvatarUrl(avatar) {
      return process.env.VUE_APP_BASE_API + avatar
    },
    /** 查询用户列表 */
    getList() {
      this.loading = true
      listUser(this.pageNum, this.pageSize, this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          // console.log(response)
          this.userList = response.rows
          this.total = response.total
          this.loading = false
        }
      )
    },
    /** 查询部门下拉树结构 */
    getTreeSelect() {
      treeSelect().then(response => {
        this.deptOptions = response.data
      })
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id
      this.getList()
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === '0' ? '启用' : '停用'
      this.$confirm('确认要"' + text + '""' + row.trueName + '"用户吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return changeUserStatus(row.userId, row.status)
      }).then(() => {
        this.msgSuccess(text + '成功')
      }).catch(function() {
        row.status = row.status === '0' ? '1' : '0'
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
        userId: undefined,
        deptId: undefined,
        trueName: undefined,
        nickName: undefined,
        password: undefined,
        phone: undefined,
        email: undefined,
        sex: undefined,
        status: '0',
        remark: undefined,
        roleIds: []
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.pageNum = 1;
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.getTreeSelect()
      getUser().then(response => {
        // 拿到角色信息
        const { roles } = response.data
        console.log(response)
        this.roleOptions = roles
        this.open = true
        this.title = '添加用户'
        this.form.password = this.initPassword // 初始化密码
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.getTreeSelect()
      console.log(row)
      const userId = row.userId || this.ids
      getUser(userId).then(response => {
        console.log(response)
        const { roles, roleIds, userInfo } = response.data
        this.form = userInfo
        this.roleOptions = roles
        this.form.roleIds = roleIds
        this.open = true
        this.title = '修改用户'
        this.form.password = ''
      })
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.trueName + '"的新密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        resetUserPwd(row.userId, value).then(response => {
          if (response.code === 200) {
            this.msgSuccess('修改成功，新密码是：' + value)
          }
        })
      }).catch(() => {
      })
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.userId !== undefined) {
            updateUser(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }
            })
          } else {
            addUser(this.form).then(response => {
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
    handleDelete(row) {
      const userIds = row.userId || this.ids
      this.$confirm('是否确认删除用户编号为"' + userIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delUser(userIds)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    }
  }
}
</script>
