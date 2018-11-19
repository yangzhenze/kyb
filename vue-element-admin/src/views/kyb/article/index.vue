<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus" @click="handleCreate">{{ $t('table.add') }}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="delConfirm">{{ $t('table.delete') }}</el-button>
    </div>
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :edit-fun="getData" :del-fun="deleteData" :columns="columns"/>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="50%">

      <el-form ref="dataForm" :model="dataForm" :rules="rules" label-position="left" label-width="100%" style="width: 80%; margin-left:50px;">
        <el-form-item style="margin-bottom: 40px;" label-width="60px" label="标题:" prop="title">
          <el-input :rows="1" v-model="dataForm.title" type="textarea" class="article-textarea" autosize placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item style="margin-bottom: 40px;" label-width="60px" label="内容:" prop="content">
          <div class="editor-container" prop="content">
            <Tinymce ref="editor" :height="400" v-model="dataForm.content" />
          </div>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData('dataForm')">{{ $t('table.add') }}</el-button>
        <el-button v-else type="primary" @click="updateData('dataForm')">{{ $t('table.edit') }}</el-button>
      </div>

    </el-dialog>
  </div>
</template>
<script>
import tableModel from '@/components/Table'
import Tinymce from '@/components/Tinymce'
import { getList, addArticle, updateArticle, getArticle, delArticle } from '@/api/kyb/article'
import { objectToFormData } from '@/utils'

export default {
  name: 'Article',
  components: { tableModel, Tinymce },
  data() {
    return {
      dialogStatus: '',
      textMap: {
        update: this.$t('table.edit'),
        create: this.$t('table.add')
      },
      dialogFormVisible: false,
      dialogPermission: false,
      columns: [
        {
          text: '发布时间',
          value: 'createDate',
          width: 200
        },
        {
          text: '标题',
          value: 'title'
        }
      ],
      dataForm: {
        id: '',
        title: '',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: '请输入文章标题', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入文章内容', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getPage(curPage, pageSize) {
      const params = { 'pageSize': pageSize }
      return new Promise((resolve, reject) => {
        getList(curPage, params).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    createData(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          other.dialogFormVisible = false
          addArticle(objectToFormData(this.dataForm)).then(response => {
            debugger
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.$refs.tableModel.getPage(1, other.$refs.tableModel.pagination.pagesize)
              other.$refs['dataForm'].resetFields()
              other.resetForm()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    updateData(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          other.dialogFormVisible = false
          updateArticle(this.dataForm).then(response => {
            debugger
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.$refs.tableModel.getPage(1, other.$refs.tableModel.pagination.pagesize)
              other.$refs['dataForm'].resetFields()
              other.resetForm()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    getData(id) {
      const other = this
      other.dialogFormVisible = true
      other.dialogStatus = 'update'
      if (this.$refs['dataForm'] !== undefined) {
        this.$refs['dataForm'].resetFields()
      }
      getArticle(id).then(response => {
        debugger
        other.dataForm.id = response.data.id
        other.dataForm.title = response.data.title
        other.$refs.editor.setContent(response.data.content)
      })
    },
    deleteData(id) {
      return new Promise((resolve, reject) => {
        delArticle(id).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    delConfirm() {
      const ids = this.$refs.tableModel.sels.map(item => item.id)
      const other = this
      if (ids.length < 1) {
        return this.$message.warning('请选择要删除的数据')
      }

      this.$confirm('确认删除选中的' + ids.length + '条数据', { type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消' })
        .then(_ => {
          this.deleteData(ids).then(response => {
            other.$message.success(response.msg)
            other.$refs.tableModel.getPage(1, other.$refs.tableModel.pagination.pagesize)
          })
        })
        .catch(_ => {})
    },
    handleCreate() {
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
      if (this.dataForm.id !== '') {
        this.$refs['dataForm'].resetFields()
        this.resetForm()
      }
    },
    resetForm() {
      this.dataForm.id = ''
      this.dataForm.title = ''
      this.dataForm.content = ''
      this.clearEdit()
    },
    clearEdit() {
      this.$refs.editor.setContent('')
    }
  }

}
</script>

<style scoped>

</style>
