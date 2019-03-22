<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="名称"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="filters.categoryId" clearable placeholder="请选择分类">
            <el-option v-for="item in categorys" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-select v-model="filters.dr" clearable placeholder="请选择状态">
            <el-option v-for="item in drs" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getComponents">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="components" highlight-current-row v-loading="listLoading" style="width: 100%;" 
        @selection-change="selsChange" @sort-change="sortChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="名称:">
              <span>{{ props.row.name }}</span>
            </el-form-item>
            <el-form-item label="分类:">
              <span>{{ props.row.categoryName }}</span>
            </el-form-item>
            <el-form-item label="版本:">
              <span>{{ props.row.version }}</span>
            </el-form-item>
            <el-form-item label="状态:">
              <span>{{ formatDr(props.row) }}</span>
            </el-form-item>
            <el-form-item label="创建时间:">
              <span>{{ formatDate(props.row.createdAt) }}</span>
            </el-form-item>
            <el-form-item label="更新时间:">
              <span>{{ formatDate(props.row.updatedAt) }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <!-- <el-table-column type="index" min-width="60">
      </el-table-column> -->
      <el-table-column prop="name" label="名称" min-width="120" sortable>
      </el-table-column>
      <el-table-column prop="categoryName" label="分类" min-width="100" sortable>
      </el-table-column>
      <el-table-column prop="version" label="版本" min-width="100" sortable>
      </el-table-column>
      <el-table-column prop="dr" label="状态" min-width="120" :formatter="formatDr" sortable>
      </el-table-column>
      
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="small" @click="handleEdit(scope.$id, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
      <el-button @click="modifyDr(0)" :disabled="this.sels.length===0">上架</el-button>
      <el-button @click="modifyDr(1)" :disabled="this.sels.length===0">下架</el-button>
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total"
        style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="FormRules" ref="editForm">
        <el-form-item label="名称" prop="name">
          <el-input v-model="editForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="editForm.categoryId" placeholder="请选择分类">
            <el-option v-for="item in categorys" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="版本" prop="version">
          <el-input v-model="editForm.version" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="dr">
          <el-select v-model="editForm.dr" placeholder="请选择状态">
            <el-option v-for="item in drs" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增界面-->
    <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="FormRules" ref="addForm">
        <el-form-item label="名称" prop="name">
          <el-input v-model="addForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="addForm.categoryId" placeholder="请选择分类">
            <el-option v-for="item in categorys" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="版本" prop="version">
          <el-input v-model="addForm.version" autocomplete></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="dr">
          <el-select v-model="addForm.dr" placeholder="请选择状态">
            <el-option v-for="item in drs" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  import util from '../../common/js/util'
  import {
    getComponentPage,
    removeComponent,
    batchRemoveComponent,
    editComponent,
    addComponent,
    getComponentCategorySelect,
    addComponentCategory,
    updateComponentDr
  } from '../../api/api';
  
  export default {
    data() {
      return {
        filters: {
          name: '',
          categoryId: '',
          dr: null,
          page: 1,
          orderColumn: null,
          sort: null
        },
        total: 0,
        components: [],

        categorys: [],
        drs: [{
          value: 0,
          label: "上架"
        }, {
          value: 1,
          label: "下架"
        }],

        listLoading: false,
        sels: [], //列表选中列

        editFormVisible: false, //编辑界面是否显示
        editLoading: false,
        editForm: {}, //编辑界面数据 

        addFormVisible: false, //新增界面是否显示
        addLoading: false,
        addForm: {}, //新增界面数据

        // 表单验证
        FormRules: {
          name: [{
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }],
          categoryId: [{
            required: true,
            message: '请选择分类',
            trigger: 'blur'
          }],
          version: [{
            required: true,
            message: '请输入版本',
            trigger: 'blur'
          }],
          dr: [{
            required: true,
            message: '请选择状态',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      formatDate: function(timestamp){
        if(timestamp){
          return util.formatDate.format(new Date(timestamp));
        }
      },
      //状态显示转换
      formatDr: function(row, column) {
        return row.dr == 1 ? '下架' : row.dr == 0 ? '上架' : '未知';
      },
      handleCurrentChange(val) {
        this.filters.page = val;
        this.getComponents();
      },
      //获取组件列表
      getComponentCategory() {
        getComponentCategorySelect().then((res) => {
          let data = res.data;
          if (data.code == "0") {
            this.categorys = data.payload;
          }
        });
      },
      //获取组件列表
      getComponents() {
        let param = Object.assign({}, this.filters);
        param.dr = param.dr == "" ? null : param.dr
        this.listLoading = true;
        getComponentPage(param).then((res) => {
          let data = res.data;
          if (data.code == "0") {
            this.total = data.payload.totalElements;
            this.components = data.payload.content;
          } else {
            this.$message.error(data.msg);
          }
          this.listLoading = false;
        });
      },
      sortChange(data) {
        this.filters.page = 1;
        if (data.column) {
          this.filters.orderColumn = data.prop == data.prop;
          this.filters.sort = data.order == "descending" ? "desc" : null;
        } else {
          this.filters.orderColumn = null;
          this.filters.sort = null;
        }
        this.getComponents();
      },
      //删除
      handleDel: function(index, row) {
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          removeComponent(row.id).then((res) => {
            let data = res.data;
            if (data.code == "0") {
              this.$message({
                message: '删除成功',
                type: 'success'
              });
            } else {
              this.$message.error(data.msg);
            }
            this.getComponents();
          });
        }).catch(() => {});
      },
      //显示编辑界面
      handleEdit: function(index, row) {
        this.editFormVisible = true;
        // this.editForm = Object.assign({}, row);
        this.editForm = {
          id: row.id,
          name: row.name,
          categoryId: row.categoryId,
          version: row.version,
          dr: row.dr
        }
      },
      //显示新增界面
      handleAdd: function() {
        this.addFormVisible = true;
        this.addForm = {};
      },
      //编辑
      editSubmit: function() {
        this.$refs.editForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.editLoading = true;
              let para = Object.assign({}, this.editForm);

              editComponent(para.id, para).then((res) => {
                this.editLoading = false;
                let data = res.data;
                if (data.code == "0") {
                  this.$message({
                    message: '修改成功',
                    type: 'success'
                  });
                  this.getComponents();
                } else {
                  this.$message.error(data.msg);
                }
                this.$refs['editForm'].resetFields();
                this.editFormVisible = false;

              }).catch((error) =>{
                this.editLoading = false;
                alert(JSON.stringify(error))
              });
            });
          }
        });
      },
      //新增
      addSubmit: function() {
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.addLoading = true;
              let para = Object.assign({}, this.addForm);

              addComponent(para).then((res) => {
                let data = res.data;
                this.addLoading = false;
                if (data.code == "0") {
                  this.$message({
                    message: '新增成功',
                    type: 'success'
                  });
                  this.getComponents();
                } else {
                  this.$message.error(data.msg);
                }
                this.$refs['addForm'].resetFields();
                this.addFormVisible = false;
              }).catch(() => {
                this.addFormVisible = false;
              });
            });
          }
        });
      },
      selsChange: function(sels) {
        this.sels = sels;
      },
      //批量删除
      batchRemove: function() {
        var ids = this.sels.map(item => item.id).toString();
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          let para = {
            ids: ids.split(",")
          };
          batchRemoveComponent(para).then((res) => {
            let data = res.data;
            if (data.code == "0") {
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getComponents();
            } else {
              this.$message.error(data.msg);
            }
          });
        }).catch(() => {

        });
      },
      //批量上下架
      modifyDr: function(dr) {
        var ids = this.sels.map(item => item.id).toString();
        this.$confirm('确认' + this.formatDr({dr:dr}) + '选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          let para = {
            ids: ids.split(",")
          };
          updateComponentDr(dr, para).then((res) => {
            let data = res.data;
            if (data.code == "0") {
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.getComponents();
            } else {
              this.$message.error(data.msg);
            }
          });
        }).catch(() => {
        });
      }
    },
    mounted() {
      this.getComponentCategory();
      this.getComponents();
    }
  }
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
