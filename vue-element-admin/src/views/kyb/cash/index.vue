<template>
  <div class="app-container">
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :columns="columns">
      <template slot="Button" slot-scope="button">
        <el-button v-if="button.row.reviewStatus === '0'" type="success" size="small" icon="el-icon-success" @click="reviewFee(button.row.id, 1)" >通过</el-button>
        <el-button v-if="button.row.reviewStatus === '0'" type="danger" size="small" icon="el-icon-error" @click="reviewFee(button.row.id, 2)" >不通过</el-button>
      </template>
    </table-model>
  </div>
</template>
<script>
import tableModel from '@/components/Table'
import { getList, review } from '@/api/kyb/cash'
import { getDicListByCode } from '@/api/dictionary'

export default {
  name: 'Cash',
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
          text: '订单号',
          value: 'orderNo',
          width: 200
        },
        {
          text: '提现金额',
          value: 'money'
        },
        {
          text: '绑定手机',
          value: 'mobile'
        },
        {
          text: '支付宝帐号',
          value: 'alipay_account'
        },
        {
          text: '真实姓名',
          value: 'realName'
        },
        {
          text: '审核状态',
          value: 'reviewStatusStr'
        },
        {
          text: '审核时间',
          value: 'reviewDate'
        },
        {
          text: '申请时间',
          value: 'createDate'
        }
      ],
      reviewStatus: []
    }
  },
  created() {
    getDicListByCode({ code: 'reviewStatus' }).then(response => {
      this.reviewStatus = response.data
    })
  },
  methods: {
    getPage(curPage, pageSize) {
      const params = { 'pageSize': pageSize }
      return new Promise((resolve, reject) => {
        getList(curPage, params).then(response => {
          response.data.list.forEach(d => {
            this.reviewStatus.forEach(t => {
              if (d.reviewStatus === t.dicValue) {
                d.reviewStatusStr = t.dicName
              }
            })
            this.proxyLevel.forEach(t => {
              if (d.proxyLevel === t.dicValue) {
                d.proxyLevel = t.dicName
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
      let msg = '通过'
      if (status === 2) {
        msg = '不通过'
      }
      this.$confirm('确认' + msg + '该条数据', { type: 'warning', confirmButtonText: msg, cancelButtonText: '取消' }).then(_ => {
        review(id, status).then(response => {
          if (response.code === 0) {
            this.$message.success('审核成功')
            this.$refs.tableModel.getPage(this.$refs.tableModel.pagination.curPage, this.$refs.tableModel.pagination.pageSize)
          } else {
            this.$message.error(response.msg)
          }
        })
      })
    }
  }

}
</script>

<style scoped>

</style>
