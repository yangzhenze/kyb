<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus" @click="handleCreate">{{ $t('table.add') }}</el-button>
    </div>
    <tree-table :data="list" :columns="columns" :expand-all="true" border>
      <el-table-column :label="$t('dictionary.column.actions')" align="left">
        <template slot-scope="scope">
          <el-button v-if="!scope.row.dic_parent_id" type="success" size="small" icon="el-icon-edit" @click="getDic(scope.row.id)" >{{ $t('table.edit') }}</el-button>
          <el-button v-if="scope.row.dic_parent_id" type="success" size="small" icon="el-icon-edit" @click="getValue(scope.row.id)" >{{ $t('table.edit') }}</el-button>
          <el-button type="danger" size="small" icon="el-icon-delete" @click="delData(scope.row.id)" >{{ $t('table.delete') }}</el-button>
          <el-button v-if="!scope.row.dic_parent_id" type="primary" size="small" icon="el-icon-circle-plus" @click="handleValueCreate(scope.row.id,scope.row.dic_code, scope.row.dic_name)" >{{ $t('table.add') }}</el-button>
        </template>
      </el-table-column>
    </tree-table>

    <div class="pagination-container">
      <el-pagination :current-page="pagination.curPage" :page-sizes="[10,20,30,50]" :page-size="pagination.pageSize" :total="pagination.totalRow" background layout="total, sizes, prev, pager, next, jumper" @size-change="handleSizeChange" @current-change="handleCurrentChange"/>
    </div>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dicDataForm" :model="dicDataForm" :rules="dicRules" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item :label="$t('dictionary.form.name')" prop="dicName">
          <el-input v-model="dicDataForm.dicName"/>
        </el-form-item>
        <el-form-item :label="$t('dictionary.form.code')" prop="dicCode">
          <el-input v-model="dicDataForm.dicCode"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createDic('dicDataForm')">{{ $t('table.add') }}</el-button>
        <el-button v-else type="primary" @click="updateDic('dicDataForm')">{{ $t('table.edit') }}</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogValueFormVisible">
      <el-form ref="valueDataForm" :model="valueDataForm" :rules="valueRules" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item :label="$t('dictionary.form.value')" prop="dicValue">
          <el-input v-model="valueDataForm.dicValue"/>
        </el-form-item>
        <el-form-item :label="$t('dictionary.form.view')" prop="dicName">
          <el-input v-model="valueDataForm.dicName"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogValueFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createValue('valueDataForm')">{{ $t('table.add') }}</el-button>
        <el-button v-else type="primary" @click="updateValue('valueDataForm')">{{ $t('table.edit') }}</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import treeTable from '@/components/TreeTable'
import { getList, checkCode, checkValue, addDic, updateDic, getDicById, delDic } from '@/api/dictionary'
import { objectToFormData } from '@/utils'
export default {
  name: 'Dictionary',
  components: { treeTable },
  data() {
    const validateCode = (rule, value, callback) => {
      const params = {}
      params.code = this.dicDataForm.dicCode
      params.id = this.dicDataForm.id
      checkCode(params).then(response => {
        if (response.data.result === true) {
          callback()
        } else {
          callback(new Error(response.msg))
        }
      }).catch(error => {
        console.log(error)
        callback(new Error('服务器错误！'))
      })
    }
    const validateValue = (rule, value, callback) => {
      const params = {}
      params.value = this.valueDataForm.dicValue
      params.parentId = this.valueDataForm.dicParentId
      params.id = this.valueDataForm.id
      checkValue(params).then(response => {
        if (response.data.result === true) {
          callback()
        } else {
          callback(new Error(response.msg))
        }
      }).catch(error => {
        console.log(error)
        callback(new Error('服务器错误！'))
      })
    }
    return {
      dialogFormVisible: false,
      dialogValueFormVisible: false,
      list: [],
      dialogStatus: 'create',
      textMap: {
        update: this.$t('table.edit'),
        create: this.$t('table.add')
      },
      dicDataForm: {
        id: '',
        dicName: '',
        dicCode: ''
      },
      deepDicForm: {},
      valueDataForm: {
        id: '',
        dicParentId: '',
        dicName: '',
        dicCode: '',
        dicValue: ''
      },
      deepValueForm: {},
      dicRules: {
        dicName: [
          { required: true, message: this.$t('dictionary.role.validateName'), trigger: 'blur' }
        ],
        dicCode: [
          { required: true, message: this.$t('dictionary.role.validateCode'), trigger: 'blur' },
          { trigger: 'blur', validator: validateCode }
        ]
      },
      valueRules: {
        dicName: [
          { required: true, message: this.$t('dictionary.role.validateView'), trigger: 'blur' }
        ],
        dicValue: [
          { required: true, message: this.$t('dictionary.role.validateValue'), trigger: 'blur' },
          { trigger: 'blur', validator: validateValue }
        ]
      },
      pagination: {
        curPage: 1,
        pageSize: 10,
        totalRow: 10
      },
      columns: [
        {
          text: this.$t('dictionary.column.name'),
          value: 'dic_name'
        },
        {
          text: this.$t('dictionary.column.code'),
          value: 'dic_code'
        },
        {
          text: this.$t('dictionary.column.value'),
          value: 'dic_value'
        }
      ]
    }
  },
  created() {
    this.fetchData()
    this.deepDicForm = JSON.parse(JSON.stringify(this.dicDataForm))
    this.deepValueForm = JSON.parse(JSON.stringify(this.valueDataForm))
  },
  methods: {
    fetchData() {
      const other = this
      getList(this.pagination.curPage, { 'pageSize': this.pagination.pageSize }).then(response => {
        other.list = response.data.list
        this.pagination.curPage = response.data.pageNumber
        this.pagination.pageSize = response.data.pageSize
        this.pagination.totalRow = response.data.totalRow
      })
    },
    handleCreate() {
      if (this.dicDataForm.id !== '') {
        this.$refs['dicDataForm'].resetFields()
        this.dicDataForm = JSON.parse(JSON.stringify(this.deepDicForm))
      }
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
    },
    handleValueCreate(id, code) {
      if (this.valueDataForm.id !== '') {
        this.$refs['valueDataForm'].resetFields()
        this.valueDataForm = JSON.parse(JSON.stringify(this.deepValueForm))
      }
      this.valueDataForm.dicParentId = id
      this.valueDataForm.dicCode = code
      this.dialogValueFormVisible = true
      this.dialogStatus = 'create'
    },
    handleSizeChange(val) {
      this.pagination.pageSize = val
      this.fetchData()
    },
    handleCurrentChange(val) {
      this.pagination.curPage = val
      this.fetchData()
    },
    createDic(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addDic(objectToFormData(this.dicDataForm)).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.$refs[formName].resetFields()
              other.dicDataForm = JSON.parse(JSON.stringify(other.deepDicForm))
              other.dialogFormVisible = false
              other.fetchData()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    updateDic(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateDic(this.dicDataForm).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.$refs[formName].resetFields()
              other.dicDataForm = JSON.parse(JSON.stringify(other.deepDicForm))
              other.dialogFormVisible = false
              other.fetchData()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    getDic(id) {
      const other = this
      if (this.$refs['dicDataForm']) {
        this.$refs['dicDataForm'].resetFields()
      }
      getDicById(id).then(response => {
        other.dicDataForm.id = response.data.id
        other.dicDataForm.dicCode = response.data.dicCode
        other.dicDataForm.dicName = response.data.dicName
        other.dialogFormVisible = true
        other.dialogStatus = 'update'
      })
    },
    getValue(id, name) {
      const other = this
      if (this.$refs['valueDataForm']) {
        this.$refs['valueDataForm'].resetFields()
      }
      getDicById(id).then(response => {
        other.valueDataForm.id = response.data.id
        other.valueDataForm.dicParentId = response.data.dicParentId
        other.valueDataForm.dicCode = response.data.dicCode
        other.valueDataForm.dicName = response.data.dicName
        other.valueDataForm.dicValue = response.data.dicValue
        other.dialogValueFormVisible = true
        other.dialogStatus = 'update'
      })
    },
    createValue(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addDic(objectToFormData(this.valueDataForm)).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.$refs[formName].resetFields()
              other.valueDataForm = JSON.parse(JSON.stringify(other.deepValueForm))
              other.dialogValueFormVisible = false
              other.fetchData()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    updateValue(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateDic(this.valueDataForm).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.$refs[formName].resetFields()
              other.valueDataForm = JSON.parse(JSON.stringify(other.deepValueForm))
              other.dialogValueFormVisible = false
              other.fetchData()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    delData(ids) {
      const other = this
      this.$confirm('确认删除该字典', { type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消' })
        .then(_ => {
          delDic({ ids: ids }).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.fetchData()
            } else {
              other.$message.error(response.msg)
            }
          })
        })
    }
  }
}
</script>
