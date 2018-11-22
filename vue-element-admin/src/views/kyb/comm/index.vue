<template>
  <div class="app-container">
    <el-form ref="dataForm" :model="dataForm" :rules="rules" label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
      <el-form-item label="一级代理" prop="oneLevelAgent">
        <el-input v-model="dataForm.oneLevelAgent"/>
      </el-form-item>
      <el-form-item label="二级代理" prop="twoLevelAgent">
        <el-input v-model="dataForm.twoLevelAgent"/>
      </el-form-item>
      <el-form-item label="代理加盟费" prop="initailFree">
        <el-input v-model="dataForm.initailFree"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="updateInfo('dataForm')">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { get, update } from '@/api/kyb/comm'
export default {
  name: 'Comm',
  data() {
    const validateNumber = (rule, value, callback) => {
      const reg = /[^\d^\.]+/g
      if (reg.test(value)) {
        callback(new Error('只能输入数字'))
      } else {
        callback()
      }
    }
    return {
      dataForm: {
        oneLevelAgent: '',
        twoLevelAgent: '',
        initailFree: ''
      },
      rules: {
        oneLevelAgent: [
          { required: true, message: '请输入一级代理费', trigger: 'blur' },
          { trigger: 'change', message: '只能输入数字', validator: validateNumber }
        ],
        twoLevelAgent: [
          { required: true, message: '请输入二级代理费', trigger: 'blur' },
          { trigger: 'change', message: '只能输入数字', validator: validateNumber }
        ],
        initailFree: [
          { required: true, message: '请输入代理加盟费', trigger: 'blur' },
          { trigger: 'change', message: '只能输入数字', validator: validateNumber }
        ]
      }
    }
  },
  created() {
    this.getInfo()
  },
  methods: {
    getInfo() {
      get().then(response => {
        this.dataForm.oneLevelAgent = response.data.oneLevelAgent
        this.dataForm.twoLevelAgent = response.data.twoLevelAgent
        this.dataForm.initailFree = response.data.initailFree
      })
    },
    updateInfo() {
      update(this.dataForm).then(response => {
        if (response.code === 0) {
          this.$message.success(response.msg)
        } else {
          this.$message.error(response.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
