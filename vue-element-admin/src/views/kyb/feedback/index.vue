<template>
  <div class="app-container">
    <table-model ref="tableModel" :page-fun="getPage" :control-column="true" :columns="columns">
      <template slot="Button" slot-scope="button">
        <el-button v-if="button.row.feedbackStatus === '0'" type="success" size="small" icon="el-icon-success" @click="reviewFee(button.row.id, 1)" >已读</el-button>
      </template>
    </table-model>
  </div>
</template>
<script>
import tableModel from '@/components/Table'
import { getList, review } from '@/api/kyb/feedback'
import { getDicListByCode } from '@/api/dictionary'

export default {
  name: 'Feedback',
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
          text: '反馈时间',
          value: 'createDate'
        },
        {
          text: '反馈内容',
          value: 'feedback'
        },
        {
          text: '状态',
          value: 'feedbackStatusStr'
        }
      ],
      feedbackStatus: []
    }
  },
  created() {
    getDicListByCode({ code: 'feedbackStatus' }).then(response => {
      this.feedbackStatus = response.data
    })
  },
  methods: {
    getPage(curPage, pageSize) {
      const params = { 'pageSize': pageSize }
      return new Promise((resolve, reject) => {
        getList(curPage, params).then(response => {
          response.data.list.forEach(d => {
            this.feedbackStatus.forEach(t => {
              if (d.feedbackStatus === t.dicValue) {
                d.feedbackStatusStr = t.dicName
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
      this.$confirm('确认已读该条数据', { type: 'warning', confirmButtonText: '已读', cancelButtonText: '取消' }).then(_ => {
        review(id, status).then(response => {
          if (response.code === 0) {
            this.$message.success('设置成功')
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
