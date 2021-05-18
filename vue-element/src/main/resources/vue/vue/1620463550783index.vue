<template>
    <div style="margin-top: 1%">
        <el-form :model="dataForm" label-width="100px" class="demo-ruleForm">
            <el-row>
                <el-col :span="6">
                    <el-form-item>
                        <el-button type="primary" @click="seachForm">查询</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <el-table
                ref="multipleTable"
                :data="tableData"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
                      
             <el-table-column
                    prop="bookName"
                    label="书名称">
            </el-table-column>
           
           
             <el-table-column
                    prop="bookAuthor"
                    label="作者名称">
            </el-table-column>
           
           
             <el-table-column
                    prop="bookKind"
                    label="书种类">
            </el-table-column>
           
           
             <el-table-column
                    prop="createTime"
                    label="创建时间">
            </el-table-column>
           
        </el-table>
        <el-pagination
                background
                layout="sizes,prev, pager, next"
                @current-change="change"
                @size-change="sizeChage"
                :page-size="size"
                :current-page="page"
                :page-sizes="[10,20,30]"
                :total="total">
        </el-pagination>
    </div>
</template>

<script>
  export default {
    name: 'Index',
    data() {
      return {
        page: 1,
        size: 10,
        tableData: [],
        total: 0,
        multipleSelection: [],
        dataForm: {
          operator: '',
          isAbnormal: ''
        }
      }
    },
    mounted() {
      this.pageFound()
    },
    methods: {
      seachForm() {
        this.pageFound()
      },
      pageFound() {
        const param = {
          page: this.page,
          limit: this.size
        }
        console.log(1111)
        let p = Object.assign({}, this.dataForm)
        p = Object.assign({}, param, p)
        this.global_delete(p)
        this.$store.dispatch('book/pageFound', p).then((res) => {
          const { code, data } = res
          if (code > 0) {
            this.tableData = data.data
            this.total = data.count
          }
        }).catch((err) => {
          console.log(err)
        })
      },
      sizeChage(size) {
        this.size = size
        this.pageFound()
      },
      change(page) {
        this.page = page
        this.pageFound()
      },
      handleSelectionChange(val) {
        this.multipleSelection = val
      }
    }
  }
</script>

<style scoped>

</style>
