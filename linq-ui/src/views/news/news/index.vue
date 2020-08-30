<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="新闻类别" prop="newsTypeId">
        <el-select v-model="queryParams.newsTypeId" placeholder="请选择新闻类别" clearable size="small">
          <el-option v-for="item in newsTypeOptions" :key="item.newsTypeId" :label="item.newsTypeName"
                     :value="item.newsTypeId"/>
        </el-select>
      </el-form-item>
      <el-form-item label="新闻标题" prop="newsTitle">
        <el-input v-model="queryParams.newsTitle" placeholder="请输入新闻标题" clearable size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="新闻属性" prop="newsAttr">
        <el-select v-model="queryParams.newsAttr" placeholder="请选择新闻属性" clearable size="mini">
          <el-option label="头条区新闻" :value="0"/>
          <el-option label="幻灯片区新闻" :value="1"/>
          <el-option label="热点区新闻" :value="2"/>
        </el-select>
      </el-form-item>
      <el-form-item label="新闻是否公开" prop="isPublic">
        <el-select v-model="queryParams.isPublic" placeholder="请选择新闻是否公开" clearable size="mini">
          <el-option label="公开" :value="0"/>
          <el-option label="私有" :value="1"/>
        </el-select>
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable size="mini">
          <el-option label="审核中" :value="0"/>
          <el-option label="审核成功" :value="1"/>
          <el-option label="审核失败" :value="2"/>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRange" size="mini" style="width: 240px" value-format="yyyy-MM-dd" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['news:news:add']">
          新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                   v-hasPermi="['news:news:edit']">
          修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
                   v-hasPermi="['news:news:remove']">
          删除
        </el-button>
      </el-col>
    </el-row>

    <!--数据列表-->
    <el-table v-loading="loading" :data="newsList" @selection-change="handleSelectionChange" size="mini"
              :height="tableHeight">
      <el-table-column type="selection" width="50" align="center"/>
      <el-table-column label="新闻编号" align="center" prop="newsId"/>
      <el-table-column label="新闻类别" align="center" prop="newsType.newsTypeName">
        <template slot-scope="scope">
          <span style="font-weight: bolder">{{ scope.row.newsType.newsTypeName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="新闻标题" align="center" prop="newsTitle" width="150">
        <template slot-scope="scope">
          <span style="font-weight: bolder">{{ scope.row.newsTitle }}</span>
        </template>
      </el-table-column>
      <el-table-column label="新闻属性" align="center" prop="newsAttr" width="120">
        <template slot-scope="scope">
          <span v-if="scope.row.newsAttr === '0'" style="color: #fb9601;font-weight: bolder">头条区新闻</span>
          <span v-else-if="scope.row.newsAttr === '1' " style="color: #1ac3f9;font-weight: bolder">幻灯片区新闻</span>
          <span v-else style="color: #d62c2c;font-weight: bolder">热点区新闻</span>
        </template>
      </el-table-column>
      <el-table-column label="新闻封面" align="center" prop="newsImage" width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.newsImage===''">无</span>
          <img v-else :src="scope.row.newsImage" alt="" class="img-mini">
        </template>
      </el-table-column>
      <el-table-column label="是否公开" align="center" prop="isPublic">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.isPublic" active-value="0" inactive-value="1"
                     @change="handleIsPublicChange(scope.row)"/>
        </template>
      </el-table-column>
      <el-table-column label="点赞数" align="center" prop="thumbs" width="60"/>
      <el-table-column label="浏览量" align="center" prop="visits" width="60"/>
      <el-table-column label="评论数" align="center" prop="comments" width="60"/>
      <el-table-column label="收藏数" align="center" prop="collects" width="60"/>
      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-link type="primary" v-if="scope.row.status === '0'" style="font-weight: bolder">审核中...</el-link>
          <el-link type="success" v-else-if="scope.row.status === '1'" style="font-weight: bolder">审核成功</el-link>
          <el-link type="danger" v-else-if="scope.row.status === '2'" style="font-weight: bolder">审核失败</el-link>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="140">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createBy" width="60">
        <template slot-scope="scope">
          <span style="font-weight: bolder">{{ scope.row.createBy }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-view" @click="handleRead(scope.row)"
                     v-hasPermi="['news:news:edit']">
            查看
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-check" @click="handleInspect(scope.row)"
                     v-hasPermi="['news:news:inspect']">
            审核
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['news:news:edit']">
            修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['news:news:remove']">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--分页插件-->
    <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" @pagination="getList"/>


    <!-- 浏览新闻 以及 审核新闻弹框 -->
    <el-dialog :title="title" :visible.sync="open" class="dialog-mid">
      <el-card shadow="always">
        <div style="font-size: 20px;font-weight: bolder;margin-top: 5px">新闻标题: {{ form.newsTitle }}</div>
        <div style="font-size: 10px;font-weight: bolder;margin-top: 5px">作者: {{ form.createBy }}</div>
        <div style="font-size: 10px;font-weight: bolder;margin-top: 5px">本次创作时间: {{ parseTime(form.createTime) }}</div>
        <div style="font-size: 10px;font-weight: bolder;margin-top: 5px">上次更改时间: {{ parseTime(form.updateTime) }}</div>
      </el-card>
      <el-card shadow="hover">
        <div v-html="form.newsContent"/>
      </el-card>
      <el-card v-if="dialogFlag===1" shadow="hover" style="margin-top: 20px">
        <el-form ref="form" :model="form" size="small" label-width="100px">
          <el-form-item label="审核新闻" prop="status">
            <el-radio-group v-model="form.status" size="medium">
              <el-radio v-for="(item, index) in statusOptions" :key="index" :label="item.value" border>
                {{ item.label }}
              </el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
      </el-card>
      <div slot="footer" v-if="dialogFlag===1">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import { listNews, getNews, delNews, changeNewsIsPublic, changeNewsStatus } from '@/api/news/news'
import { optionSelect } from '@/api/news/type'
import Tinymce from '@/components/Tinymce/index'
import { getToken } from '@/utils/auth'

export default {
  name: 'News',
  components: {
    Tinymce
  },
  data() {
    return {
      // 控制阅读弹窗显示
      open: false,
      // 弹框标题0
      title: '',
      //  1 代表审核新闻
      dialogFlag: undefined,
      statusOptions: [
        {
          'label': '审核中',
          'value': '0'
        },
        {
          'label': '审核成功',
          'value': '1'
        },
        {
          'label': '审核失败',
          'value': '2'
        }],
      // 表单内容
      form: {},
      // 新闻内容
      // newsContent: '',
      //表格高度 window.innerHeight窗口文档显示高度
      tableHeight: window.innerHeight - 340,
      headers: {
        // 上传文件的请求头
        Authorization: 'Bearer ' + getToken()
      },
      imageUrl: null, // 上传图片回显
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
      // 新闻表格数据
      newsList: [],
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
      // 查询参数
      queryParams: {
        newsTypeId: undefined,
        newsTitle: undefined,
        newsAttr: undefined,
        isPublic: undefined,
        status: undefined
      }
    }
  },
  mounted() {
    //后面的50：根据需求空出的高度，自行调整
    this.$nextTick(() => {
      this.tableHeight = window.innerHeight - 340
    })
  },
  created() {
    this.getList()
    this.getOptionSelect()
  },
  methods: {
    /** 查询新闻类型列表 */
    getOptionSelect() {
      optionSelect().then(response => {
        // console.log(response)
        this.newsTypeOptions = response.data
      })
    },
    /** 查询新闻列表 */
    getList() {
      this.loading = true
      listNews(this.pageNum, this.pageSize, this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.newsList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.newsId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 表单重置
    reset() {
      this.form = {
        newsId: undefined,
        status: '0'
      }
      this.resetForm('form')
    },
    /** 审核新闻 **/
    handleInspect(row) {
      // 重置表单
      this.reset()
      // 标识为1审核新闻
      this.dialogFlag = 1
      this.title = `审核新闻`
      getNews(row.newsId).then(res => {
        if (res.flag) {
          this.form = res.data
          this.open = true
        }
      })
    },
    // 关闭审核弹框
    close() {
      this.dialogFlag = undefined
      this.open = false
    },
    /** 审核表单提交 **/
    handelConfirm() {
      changeNewsStatus(this.form).then(res => {
        if (res.flag) {
          this.msgNoticeSuccess('审核成功')
          this.open = false
          this.getList()
          this.dialogFlag = undefined
        }
      })
    },
    /** 设置公开私有 **/
    handleIsPublicChange(row) {
      // console.log(row)
      let text = row.isPublic === '0' ? '公开' : '私有'
      this.$confirm('确认要"' + text + '""' + row.newsTitle + '"新闻吗?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        // 调用设置公开私有
        return changeNewsIsPublic(row.newsId, row.isPublic)
      }).then(() => {
        this.msgNoticeSuccess(text + '成功')
        //this.msgSuccess(text + '成功')
      }).catch(function() {
        row.isPublic = row.isPublic === '0' ? '1' : '0'
      })
    },
    /** 新增按钮操作 */
    handleAdd() {
      // 跳转到便捷新闻页面
      this.$router.push({ name: 'Add' })
    },
    /** 浏览新闻信息 **/
    handleRead(row) {
      this.title = `查看新闻内容`
      getNews(row.newsId).then(res => {
        if (res.flag) {
          this.form = res.data
          this.open = true
        }
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log(row.newsId)
      const newsId = row.newsId || this.ids
      // 跳转到编辑新闻页面
      this.$router.push({
        name: 'Edit',
        params: {
          newsId: newsId
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const newsIds = row.newsId || this.ids
      this.$confirm('是否确认删除新闻编号为"' + newsIds + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function() {
        return delNews(newsIds)
      }).then(() => {
        this.getList()
        this.msgSuccess('删除成功')
      }).catch(function() {
      })
    }
  }
}
</script>
