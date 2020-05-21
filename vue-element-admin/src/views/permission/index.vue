<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" icon="el-icon-circle-plus" type="primary" @click="handleCreate">{{ $t('table.add') }}</el-button>
    </div>

    <tree-table :data="list" :columns="columns" :expand-all="true" border>
      <el-table-column :label="$t('permission.column.actions')" align="left" >
        <el-table-column>
          <template slot-scope="scope">
            <el-button type="success" size="small" icon="el-icon-edit" @click="getData(scope.row.id)" >{{ $t('table.edit') }}</el-button>
            <el-button type="danger" size="small" icon="el-icon-delete" @click="delData(scope.row.id)" >{{ $t('table.delete') }}</el-button>
            <el-button v-if="scope.row.permission_type == 0" type="primary" size="small" icon="el-icon-circle-plus" @click="handleChildrenCreate(scope.row.id, scope.row.permission_name)" >{{ $t('table.add') }}</el-button>
          </template>
        </el-table-column>
        <el-table-column>
          <template slot-scope="scope">
            <el-button size="small" type="warning" icon="el-icon-sort-up" @click="changePerSort(scope.row.parent_id, scope.row.sort,'up')" >{{ $t('table.up') }}</el-button>
            <el-button size="small" type="warning" icon="el-icon-sort-down" @click="changePerSort(scope.row.parent_id, scope.row.sort,'down')" >{{ $t('table.down') }}</el-button>
          </template>
        </el-table-column>
      </el-table-column>
    </tree-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="dataForm" :rules="formRoles" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item v-if="parentName" label="父级">
          <el-input v-model="parentName" disabled />
        </el-form-item>
        <el-form-item :label="$t('permission.form.name')" prop="permissionName">
          <el-input v-model="dataForm.permissionName"/>
        </el-form-item>
        <el-form-item :label="$t('permission.form.path')" prop="visitUrl">
          <el-input v-model="dataForm.visitUrl"/>
        </el-form-item>
        <el-form-item :label="$t('permission.form.type')">
          <el-select v-model="dataForm.permissionType">
            <el-option v-for="item in permissionOption" :key="item.value" :label="item.lable" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item v-if="inputMethodVisible" :label="$t('permission.form.method')" prop="method">
          <el-select v-model="dataForm.method">
            <el-option v-for="item in methodList" :key="item" :label="item" :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('permission.form.icon')">
          <!--<el-input v-model="dataForm.icon"></el-input>-->
          <el-select v-model="dataForm.icon">
            <el-option value="" >{{ $t('permission.form.iconValue') }}</el-option>
            <el-option v-for="item of iconsMap" :value="item" :key="item" ><svg-icon :icon-class="item" class-name="disabled" /></el-option>
          </el-select>
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
import treeTable from '@/components/TreeTable'
import { getList, addPermission, updatePermission, getPermission, delPermission, checkPath, changeSort } from '@/api/permission'
import { objectToFormData } from '@/utils'
import icons from './generateIconsView'
export default {
  name: 'Permission',
  components: { treeTable },
  data() {
    const validatePath = (rule, value, callback) => {
      const params = {}
      params.path = value
      params.parentId = this.dataForm.parentId
      params.id = this.dataForm.id
      checkPath(params).then(response => {
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
      list: [],
      parentName: '',
      dialogStatus: 'create',
      inputMethodVisible: false,
      textMap: {
        update: this.$t('table.edit'),
        create: this.$t('table.add')
      },
      methodList: [
        'GET',
        'POST',
        'PUT',
        'DELETE'
      ],
      iconsMap: icons,
      dataForm: {
        id: '',
        permissionName: '',
        permissionType: 0,
        method: '',
        visitUrl: '',
        icon: '',
        sort: '',
        parentId: ''
      },
      firstRoles: {
        visitUrl: [
          { required: true, message: this.$t('permission.role.visitUrl'), trigger: 'blur' },
          { trigger: 'blur', validator: validatePath }
        ],
        permissionName: [
          { required: true, message: this.$t('permission.role.permissionName'), trigger: 'blur' }
        ]
      },
      secondRules: {
        visitUrl: [
          { required: true, message: this.$t('permission.role.visitUrl'), trigger: 'blur' },
          { trigger: 'blur', validator: validatePath }
        ],
        permissionName: [
          { required: true, message: this.$t('permission.role.permissionName'), trigger: 'blur' }
        ],
        method: [
          { required: true, message: this.$t('permission.role.method'), trigger: 'blur' }
        ]
      },
      formRoles: this.firstRoles,
      permissionOption: [
        { value: 0, lable: this.$t('permission.form.menu') },
        { value: 1, lable: this.$t('permission.form.method') }
      ],
      columns: [
        {
          text: this.$t('permission.column.name'),
          value: 'permission_name',
          width: 200
        },
        {
          text: this.$t('permission.column.path'),
          value: 'visit_url'
        },
        {
          text: this.$t('permission.column.icon'),
          value: 'icon'
        }
      ]
    }
  },
  computed: {
    permissionType() {
      return this.dataForm.permissionType
    }
  },
  watch: {
    permissionType(val) {
      if (val === 0) {
        this.formRoles = this.firstRoles
        this.inputMethodVisible = false
      } else {
        this.formRoles = this.secondRules
        this.inputMethodVisible = true
      }
      // this.$refs['dataForm'].resetFields()
    }
  },
  created() {
    this.fetchData()
    this.formRoles = this.firstRoles
  },
  methods: {
    fetchData() {
      const other = this
      getList().then(response => {
        other.list = response.data
      })
    },
    update(id) {
      console.log('update')
    },
    handleCreate() {
      if (this.dataForm.id !== '' || this.dataForm.parentId !== '') {
        this.$refs['dataForm'].resetFields()
        this.resetForm()
      }
      this.parentName = ''
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
    },
    handleChildrenCreate(id, parentName) {
      if (this.$refs['dataForm'] !== undefined) {
        this.$refs['dataForm'].resetFields()
      }
      this.resetForm()
      this.dataForm.parentId = id
      this.dialogFormVisible = true
      this.parentName = parentName
      this.dialogStatus = 'create'
    },
    resetForm() {
      this.dataForm.id = ''
      this.dataForm.parentId = ''
      this.dataForm.permissionName = ''
      this.dataForm.permissionType = 0
      this.dataForm.visitUrl = ''
      this.dataForm.method = ''
      this.dataForm.icon = ''
      this.dataForm.sort = ''
    },
    createData(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          other.dialogFormVisible = false
          addPermission(objectToFormData(other.dataForm)).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.fetchData()
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
          updatePermission(this.dataForm).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.fetchData()
              other.$refs['dataForm'].resetFields()
              other.resetForm()
            } else {
              other.$message.error(response.msg)
            }
          })
        }
      })
    },
    getData(id, parentName) {
      const other = this
      if (this.$refs['dataForm'] !== undefined) {
        this.$refs['dataForm'].resetFields()
      }

      this.parentName = parentName

      getPermission(id).then(response => {
        other.dataForm.id = response.data.id
        other.dataForm.parentId = response.data.parentId
        other.dataForm.permissionName = response.data.permissionName
        other.dataForm.permissionType = response.data.permissionType
        other.dataForm.visitUrl = response.data.visitUrl
        other.dataForm.method = response.data.method
        other.dataForm.icon = response.data.icon
        other.dataForm.sort = response.data.sort
        other.dialogFormVisible = true
        other.dialogStatus = 'update'
      })
    },
    delData(id) {
      const other = this
      this.$confirm('确认删除该权限', { type: 'warning', confirmButtonText: '删除', cancelButtonText: '取消' })
        .then(_ => {
          delPermission(id).then(response => {
            if (response.code === 0) {
              other.$message.success(response.msg)
              other.fetchData()
            } else {
              other.$message.error(response.msg)
            }
          })
        })
        .catch(_ => {})
    },
    changePerSort(parentId, sort, flag) {
      const other = this
      const params = {}
      params.parentId = parentId
      params.sort = sort
      params.flag = flag
      changeSort(params).then(response => {
        other.$message.success('移动成功')
        other.fetchData()
      })
    }
  }
}
</script>
