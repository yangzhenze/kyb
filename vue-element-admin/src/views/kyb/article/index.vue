<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus" @click="handleCreate">{{ $t('table.add') }}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="delConfirm">{{ $t('table.delete') }}</el-button>
    </div>
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :edit-fun="getData" :del-fun="deleteData" :columns="columns">
      <template slot="Button" slot-scope="button">
        <el-button type="warning" size="small" icon="el-icon-menu" @click="getPer(button.row)" >{{ $t('permission.assign') }}</el-button>
      </template>
    </table-model>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">

      <el-form ref="dataForm" :model="dataForm" :rules="rules" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item :label="$t('role.column.name')" prop="roleName">
          <el-input v-model="dataForm.roleName"/>
        </el-form-item>
        <el-form-item :label="$t('role.column.describe')">
          <el-input :autosize="{ minRows: 2, maxRows: 4}" v-model="dataForm.description" type="textarea"/>
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
  import { getList, addArticle, updateArticle, getArticle, delArticle } from '@/api/kyb/article'
  import { objectToFormData } from '@/utils'
  export default {
    name: 'kyb/article',
    components: { tableModel },
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
            text: this.$t('role.column.name'),
            value: 'roleName',
            width: 200
          },
          {
            text: this.$t('role.column.describe'),
            value: 'description'
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
        if (this.$refs['dataForm'] !== undefined) {
          this.$refs['dataForm'].resetFields()
        }

        getArticle(id).then(response => {
          other.dataForm.id = response.data.id
          other.dataForm.roleName = response.data.roleName
          other.dataForm.description = response.data.description
          other.dialogFormVisible = true
          other.dialogStatus = 'update'
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
        this.dataForm.roleName = ''
        this.dataForm.description = ''
      }
    }

  }
</script>

<style scoped>

</style>
