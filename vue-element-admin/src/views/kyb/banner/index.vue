<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus" @click="handleCreate">{{ $t('table.add') }}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="delConfirm">{{ $t('table.delete') }}</el-button>
    </div>
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :edit-fun="getData" :del-fun="deleteData" :columns="columns"/>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" >
      <el-form ref="dataForm" :model="dataForm" :rules="rules" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="跳转地址" prop="url">
          <el-input v-model="dataForm.url"/>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="dataForm.sort" type="number" value="0"/>
        </el-form-item>
        <el-form-item label="图标" prop="image">
          <el-upload
            :http-request="uploadFile"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
            class="avatar-uploader"
            action="">
            <img v-if="dataForm.image" :src="dataForm.image" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
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
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
<script>
import tableModel from '@/components/Table'
import { getList, addBanner, updateBanner, getBanner, delBanner, upload } from '@/api/kyb/banner'
import { objectToFormData } from '@/utils'

export default {
  name: 'Banner',
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
          text: '横幅',
          value: 'image',
          width: 200
        },
        {
          text: '跳转地址',
          value: 'url'
        }
      ],
      productType: [],
      needType: [],
      dataForm: {
        id: '',
        image: '',
        url: '',
        sort: ''
      },
      rules: {
        image: [
          { required: true, message: '请上传横幅', trigger: 'blur' }
        ],
        sort: [
          { required: true, message: '请输入排序', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入跳转地址', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getPage(curPage, pageSize) {
      const params = { 'pageSize': pageSize }
      return new Promise((resolve, reject) => {
        getList(curPage, params).then(response => {
          response.data.list.forEach(d => {
            this.productType.forEach(t => {
              if (d.productType === t.dicValue) {
                d.productType = t.dicName
              }
            })
          })
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    uploadFile(item) {
      const formData = new FormData()
      formData.append('file', item.file)
      const other = this
      upload(formData).then(response => {
        other.dataForm.image = response.data
      })
    },
    createData(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          other.dialogFormVisible = false
          addBanner(objectToFormData(this.dataForm)).then(response => {
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
          updateBanner(this.dataForm).then(response => {
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
      getBanner(id).then(response => {
        other.dataForm.id = response.data.id
        other.dataForm.image = response.data.image
        other.dataForm.url = response.data.url
        other.dataForm.sort = response.data.sort
      })
    },
    deleteData(id) {
      return new Promise((resolve, reject) => {
        delBanner(id).then(response => {
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
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isLt2M
    },
    resetForm() {
      this.dataForm.id = ''
      this.dataForm.image = ''
      this.dataForm.url = ''
      this.dataForm.sort = ''
    }
  }

}
</script>

<style scoped>

</style>
