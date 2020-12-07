<template>
  <div style="margin: 25px">


    <el-row :gutter="10" align="center" style="margin-bottom: 10px;height: 200px;">
      <el-col :span="10" style="height: 100%;background: #00afff; width: 49%">
        <label>礼包码列表展示，生成删除礼包码CDK</label>

      </el-col>
      <el-col :span="10" style="height: 100%; width: 2%">
        <label> </label>
      </el-col>

      <el-col :span="10" style="height: 100%;background: #00afff; width: 49%">
        <label >服务器状态设置，准备维护 维护中 开始维护</label>

      </el-col>

    </el-row>
    <el-row :gutter="10" align="center" style="margin-bottom: 10px">
      <el-col :span="23">
        <el-input v-model="noticeContent" placeholder="请输入要发布的弹窗公告内容"/>
      </el-col>
      <el-col :span="1">
        <el-button type="primary">发布公告</el-button>
      </el-col>
    </el-row>

    <el-row :gutter="10" align="center">
      <el-col :span="23">
        <el-input placeholder="全服补偿" v-model="xyItem.inputContent" @input="startSearch"/>
        <el-table v-loading="loading" :data="xyItem.xy_itemList"
                  v-show="xyItem.inputContent !=='' && xyItem.inputContent!==null">
          <el-table-column label="物资ID" align="center" prop="itemId"/>
          <el-table-column label="物资类型" align="center" prop="itemType" :formatter="itemTypeFormat"/>
          <el-table-column label="物资名称" align="center" prop="itemName"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="cancelDialog(scope.row)"
                v-hasPermi="['system:xy_item:edit']"
              >发送
              </el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="xyItem.total>0 && xyItem.inputContent !==''  && xyItem.inputContent!==null"
          :total="xyItem.total"
          :page.sync="xyItem.queryParams.pageNum"
          :limit.sync="xyItem.queryParams.pageSize"
          @pagination="getXyItemList"
        />
      </el-col>
      <el-col :span="1">
        <el-button type="primary">发送补偿</el-button>
      </el-col>
    </el-row>

    <!--    <p>-->
    <!--      <label >拍卖宠物</label>-->
    <!--    </p>-->

    <!--    <p>-->
    <!--      <label >设置服务器状态</label>-->
    <!--    </p>-->
  </div>


</template>

<script>
import {listJob, getJob, delJob, addJob, updateJob, exportJob, runJob, changeJobStatus} from "@/api/monitor/job";
import {listXy_item} from "@/api/system/xy_item";

export default {
  name: "Xy_SendServerInfo",
  components: {},
  props: {

    account: ""
  }
  ,
  data() {
    return {
      noticeContent: "",
      loading: false,
      xyItem: {
        // 西游物资表格数据
        xy_itemList: [],
        //条数
        total: 0,
        inputContent: null,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          itemId: null,
          itemType: null,
          itemName: null
        },
        // 物资类型字典
        itemTypeOptions: []
      }
    }
  }
  ,
  created() {

  }
  ,
  methods: {
    startSearch() {
      if (this.isNumber(this.xyItem.inputContent)) {
        //用ID索引
        this.xyItem.queryParams.itemName = null;
        this.xyItem.queryParams.itemId = this.xyItem.inputContent;
      } else {
        this.xyItem.queryParams.itemName = this.xyItem.inputContent;
        this.xyItem.queryParams.itemId = null;
      }
      this.getXyItemList()
    },
    // 物资类型字典翻译
    itemTypeFormat(row, column) {
      return this.selectDictLabel(this.xyItem.itemTypeOptions, row.itemType);
    },
    /** 查询西游物资列表 */
    getXyItemList() {
      console.log(this.xyItem.queryParams)
      this.loading = true;
      listXy_item(this.xyItem.queryParams).then(response => {
        this.xyItem.xy_itemList = response.rows;
        this.xyItem.total = response.total;
        this.loading = false;
      });
    },

    isNumber(value) {
      var patrn = /^(-)?\d+(\.\d+)?$/;
      if (patrn.exec(value) == null || value == "") {
        return false
      } else {
        return true
      }
    }

  }
}
</script>
