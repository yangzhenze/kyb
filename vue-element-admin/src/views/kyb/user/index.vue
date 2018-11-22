<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="params.userType" placeholder="用户类型" clearable style="width: 130px" class="filter-item" @keyup.enter.native="handleSearch" >
        <el-option v-for="item in userType" :key="item.dicValue" :label="item.dicName" :value="item.dicValue"/>
      </el-select>
      <el-select v-model="params.userStatus" placeholder="用户状态" clearable style="width: 130px" class="filter-item" @keyup.enter.native="handleSearch" >
        <el-option v-for="item in userStatus" :key="item.dicValue" :label="item.dicName" :value="item.dicValue"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
    </div>
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :columns="columns">
      <template slot="Button" slot-scope="button">
        <el-button v-if="button.row.userStatus === '0'" type="danger" size="small" icon="el-icon-error" @click="reviewFee(button.row.id, 1)" >冻结</el-button>
        <el-button v-if="button.row.userStatus === '1'" type="success" size="small" icon="el-icon-success" @click="reviewFee(button.row.id, 0)" >解冻</el-button>
      </template>
    </table-model>
  </div>
</template>
<script>
import tableModel from '@/components/Table'
import { getList, frozen } from '@/api/kyb/user'
import { getDicListByCode } from '@/api/dictionary'

export default {
  name: 'User',
  components: { tableModel },
  data() {
    return {
      dialogStatus: '',
      dialogFormVisible: false,
      dialogPermission: false,
      params: {},
      columns: [
        {
          text: 'ID',
          value: 'userCode',
          width: 200
        },
        {
          text: '用户类型',
          value: 'userTypeStr'
        },
        {
          text: '绑定手机',
          value: 'mobile'
        },
        {
          text: '帐户状态',
          value: 'userStatusStr'
        },
        {
          text: '注册时间',
          value: 'createDate'
        }
      ],
      userStatus: [],
      userType: []
    }
  },
  created() {
    getDicListByCode({ code: 'userStatus' }).then(response => {
      this.userStatus = response.data
    })
    getDicListByCode({ code: 'userType' }).then(response => {
      this.userType = response.data
    })
  },
  methods: {
    getPage(curPage, pageSize) {
      const params = { 'pageSize': pageSize, 'userStatus': this.params.userStatus, 'userType': this.params.userType }
      return new Promise((resolve, reject) => {
        getList(curPage, params).then(response => {
          response.data.list.forEach(d => {
            this.userStatus.forEach(t => {
              if (d.userStatus === t.dicValue) {
                d.userStatusStr = t.dicName
              }
            })
            this.userType.forEach(t => {
              if (d.userType === t.dicValue) {
                d.userTypeStr = t.dicName
              }
            })
          })
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    reviewFee(id, status) {
      let msg = '冻结'
      if (status === 0) {
        msg = '解冻'
      }
      this.$confirm('确认' + msg + '该用户', { type: 'warning', confirmButtonText: msg, cancelButtonText: '取消' }).then(_ => {
        frozen(id, status).then(response => {
          if (response.code === 0) {
            this.$message.success('操作成功')
            this.$refs.tableModel.getPage(this.$refs.tableModel.pagination.curPage, this.$refs.tableModel.pagination.pageSize)
          } else {
            this.$message.error(response.msg)
          }
        })
      })
    },
    handleSearch() {
      this.$refs.tableModel.getPage(1, 10)
    }
  }

}
</script>

<style scoped>

</style>
