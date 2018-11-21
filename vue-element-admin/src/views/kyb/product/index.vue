<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-circle-plus" @click="handleCreate">{{ $t('table.add') }}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="delConfirm">{{ $t('table.delete') }}</el-button>
    </div>
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :edit-fun="getData" :del-fun="deleteData" :columns="columns"/>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" >
      <el-form ref="dataForm" :model="dataForm" :rules="rules" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="dataForm.productName"/>
        </el-form-item>
        <el-form-item label="产品类型" prop="productType">
          <el-select v-model="dataForm.productType" placeholder="请选择产品类型">
            <el-option v-for="item in productType" :key="item.dicValue" :label="item.dicName" :value="item.dicValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="额度范围" prop="money">
          <el-input v-model="dataForm.money"/>
        </el-form-item>
        <el-form-item label="申请人数" prop="applicationNum">
          <el-input v-model="dataForm.applicationNum"/>
        </el-form-item>
        <el-form-item label="月费率" prop="monthFeePercent">
          <el-input v-model="dataForm.monthFeePercent"/>
        </el-form-item>
        <el-form-item label="通过率" prop="passPercent">
          <el-input v-model="dataForm.passPercent"/>
        </el-form-item>
        <el-form-item label="成功标准" prop="passCriteria">
          <el-input v-model="dataForm.passCriteria"/>
        </el-form-item>
        <el-form-item label="所需类型" prop="needType">
          <el-select v-model="dataForm.needType" placeholder="请选择管理员类型">
            <el-option v-for="item in needType" :key="item.dicValue" :label="item.dicName" :value="item.dicValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-upload
            :http-request="uploadFile"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
            class="avatar-uploader"
            action="">
            <img v-if="dataForm.icon" :src="dataForm.icon" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item label="独家" prop="isExclusive">
          <el-radio-group v-model="dataForm.isExclusive">
            <el-radio label="0">否</el-radio>
            <el-radio label="1">是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="申请条件" prop="applicationCriteria">
          <el-input :autosize="{ minRows: 10, maxRows: 20}" v-model="dataForm.applicationCriteria" type="textarea"/>
        </el-form-item>
        <el-form-item label="申请详情" prop="applicationDesc">
          <el-input :autosize="{ minRows: 10, maxRows: 20}" v-model="dataForm.applicationDesc" type="textarea"/>
        </el-form-item>
        <el-form-item label="下款数量" prop="dealNum">
          <el-input v-model="dataForm.dealNum"/>
        </el-form-item>
        <el-form-item label="第三方连接" prop="url">
          <el-input v-model="dataForm.url"/>
        </el-form-item>
        <el-form-item label="佣金率" prop="commissionPercent">
          <el-input v-model="dataForm.commissionPercent"/>
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
import { getList, addProduct, updateProduct, getProduct, delProduct, upload } from '@/api/kyb/product'
import { getDicListByCode } from '@/api/dictionary'
import { objectToFormData } from '@/utils'

export default {
  name: 'Article',
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
          text: '产品名称',
          value: 'productType',
          width: 200
        },
        {
          text: '产品类型',
          value: 'productType'
        },
        {
          text: '申请人数',
          value: 'applicationNum'
        },
        {
          text: '额度范围',
          value: 'money'
        },
        {
          text: '下款数量',
          value: 'dealNum'
        },
        {
          text: '佣金率',
          value: 'commissionPercent'
        }
      ],
      productType: [],
      needType: [],
      dataForm: {
        id: '',
        productName: '',
        productType: '',
        money: '',
        applicationNum: '',
        monthFeePercent: '',
        passPercent: '',
        passCriteria: '',
        needType: '',
        icon: '',
        isExclusive: '0',
        applicationCriteria: '',
        applicationDesc: '',
        dealNum: '',
        url: '',
        commissionPercent: ''
      },
      rules: {
        productName: [
          { required: true, message: '请输入产品名称', trigger: 'blur' }
        ],
        productType: [
          { required: true, message: '请选择产品类型', trigger: 'change' }
        ],
        money: [
          { required: true, message: '请输入额度范围', trigger: 'blur' }
        ],
        applicationNum: [
          { required: true, message: '请输入申请人数', trigger: 'blur' }
        ],
        monthFeePercent: [
          { required: true, message: '请输入月费率', trigger: 'blur' }
        ],
        passPercent: [
          { required: true, message: '请输入通过率', trigger: 'blur' }
        ],
        passCriteria: [
          { required: true, message: '请输入成功标准', trigger: 'blur' }
        ],
        needType: [
          { required: true, message: '请选择所需类型', trigger: 'change' }
        ],
        icon: [
          { required: true, message: '请上传图标', trigger: 'blur' }
        ],
        applicationCriteria: [
          { required: true, message: '请输入申请条件', trigger: 'blur' }
        ],
        applicationDesc: [
          { required: true, message: '请输入申请详情', trigger: 'blur' }
        ],
        dealNum: [
          { required: true, message: '请输入下款数量', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请输入第三方连接', trigger: 'blur' }
        ],
        commissionPercent: [
          { required: true, message: '请输入佣金率', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    getDicListByCode({ code: 'productType' }).then(response => {
      this.productType = response.data
    })
    getDicListByCode({ code: 'needType' }).then(response => {
      this.needType = response.data
    })
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
        other.dataForm.icon = response.data
      })
    },
    createData(formName) {
      const other = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          other.dialogFormVisible = false
          addProduct(objectToFormData(this.dataForm)).then(response => {
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
          updateProduct(this.dataForm).then(response => {
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
      getProduct(id).then(response => {
        other.dataForm.id = response.data.id
        other.dataForm.productName = response.data.productName
        other.dataForm.productType = response.data.productType
        other.dataForm.money = response.data.money
        other.dataForm.applicationNum = response.data.applicationNum
        other.dataForm.monthFeePercent = response.data.monthFeePercent
        other.dataForm.passPercent = response.data.passPercent
        other.dataForm.passCriteria = response.data.passCriteria
        other.dataForm.needType = response.data.needType
        other.dataForm.icon = response.data.icon
        other.dataForm.isExclusive = response.data.isExclusive
        other.dataForm.applicationCriteria = response.data.applicationCriteria
        other.dataForm.applicationDesc = response.data.applicationDesc
        other.dataForm.dealNum = response.data.dealNum
        other.dataForm.url = response.data.url
        other.dataForm.commissionPercent = response.data.commissionPercent
      })
    },
    deleteData(id) {
      return new Promise((resolve, reject) => {
        delProduct(id).then(response => {
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
      this.dataForm.productName = ''
      this.dataForm.productType = ''
      this.dataForm.money = ''
      this.dataForm.applicationNum = ''
      this.dataForm.monthFeePercent = ''
      this.dataForm.passPercent = ''
      this.dataForm.passCriteria = ''
      this.dataForm.needType = ''
      this.dataForm.icon = ''
      this.dataForm.isExclusive = '0'
      this.dataForm.applicationCriteria = ''
      this.dataForm.applicationDesc = ''
      this.dataForm.dealNum = ''
      this.dataForm.url = ''
      this.dataForm.commissionPercent = ''
    }
  }

}
</script>

<style scoped>

</style>
