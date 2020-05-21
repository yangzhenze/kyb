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

    <el-dialog :title="perTree.dialogPermissionName" :visible.sync="dialogPermission">
      <el-tree
        ref="tree"
        :data="perTree.treeData"
        :default-checked-keys="perTree.defaultData"
        :default-expand-all="true"
        :check-strictly="true"
        :props="perTree.defaultProps"
        show-checkbox
        node-key="id"
        @check="setFunction"/>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPermission = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="confirmPermission">{{ $t('table.confirm') }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import tableModel from '@/components/Table'
import { getList, addRole, updateRole, getRoleById, delRole, getRolePer, addRolePer } from '@/api/role'
import { objectToFormData } from '@/utils'
import { getList as treeList } from '@/api/permission'
export default {
  name: 'Role',
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

      perTree: {
        dialogPermissionName: '',
        permissionId: '',
        treeData: [],
        defaultData: [],
        defaultProps: {
          children: 'children',
          label: 'permission_name'
        }
      },
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
        roleName: '',
        description: ''
      },
      rules: {
        roleName: [
          { required: true, message: this.$t('role.role.roleName'), trigger: 'blur' }
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
          addRole(objectToFormData(this.dataForm)).then(response => {
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
          updateRole(this.dataForm).then(response => {
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

      getRoleById(id).then(response => {
        other.dataForm.id = response.data.id
        other.dataForm.roleName = response.data.roleName
        other.dataForm.description = response.data.description
        other.dialogFormVisible = true
        other.dialogStatus = 'update'
      })
    },
    deleteData(id) {
      return new Promise((resolve, reject) => {
        delRole(id).then(response => {
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
    },
    getPer(row) {
      this.perTree.permissionId = row.id
      this.perTree.dialogPermissionName = row.roleName
      this.dialogPermission = true
      this.getRolePerData(this.perTree.permissionId).then(response => {
        this.perTree.defaultData = response.data.map(item => item.perId)
        this.getTreeList()
      })
    },
    getTreeList() {
      const other = this
      treeList().then(response => {
        other.perTree.treeData = response.data
      })
    },
    confirmPermission() {
      const permission = this.$refs.tree.getCheckedKeys()
      this.addRolePer(this.perTree.permissionId, permission)
    },
    async getRolePerData(id) {
      const role = getRolePer(id)
      return role
    },
    addRolePer(id, ids) {
      const other = this
      addRolePer(id, objectToFormData({ 'permissions': ids.join() })).then(response => {
        other.$message.success(response.msg)
        other.dialogPermission = false
      })
    },
    setFunction(node, data) {
      let checked = false
      const nodes = this.$refs.tree.getNode(node.id)
      checked = nodes.checked

      if (checked) {
        if (nodes.parent.key !== undefined) {
          this.checkParent(nodes.parent, true)
        }

        if (nodes.childNodes.length > 0) {
          this.checkChildren(nodes.childNodes, true)
        }
      } else {
        if (nodes.childNodes.length > 0) {
          this.checkChildren(nodes.childNodes, false)
        }
        if (nodes.parent.key !== undefined) {
          this.checkParent(nodes.parent, false)
        }
      }
    },
    checkParent(node, checked) {
      if (checked) {
        this.$refs.tree.setChecked(node, checked)
        if (node.parent.key !== undefined) {
          this.checkParent(node.parent, checked)
        }
      } else {
        let flag = false
        node.childNodes.forEach(function(item) {
          if (item.checked) {
            flag = true
          }
        })

        if (!flag) {
          this.$refs.tree.setChecked(node, checked)
          if (node.parent.key !== undefined) {
            this.checkParent(node.parent, checked)
          }
        }
      }
    },
    checkChildren(nodes, checked) {
      const other = this
      nodes.forEach(function(node) {
        other.$refs.tree.setChecked(node, checked)
        if (node.childNodes.length > 0) {
          other.checkChildren(node.childNodes, checked)
        }
      })
    }
  }

}
</script>

<style scoped>

</style>
