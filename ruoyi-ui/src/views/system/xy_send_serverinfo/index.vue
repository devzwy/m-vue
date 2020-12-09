<template>
  <div style="margin: 25px">


    <el-row :gutter="10" align="center" style="margin-bottom: 10px">

      <el-col :span="12">
        <el-input v-hasPermi="['system:xyserver:all']" placeholder="输入宠物或物品名称或者ID自行搜索" v-model="xyItem.inputContent" @input="startSearch"/>
      </el-col>
      <el-col :span="11">
        <el-input v-model="noticeContent" v-hasPermi="['system:xyserver:all']" placeholder="请输入要发布的弹窗公告内容"/>
      </el-col>
      <el-col :span="1">
        <el-button type="primary" v-hasPermi="['system:xyserver:all']" @click="sendNotece()">发布公告</el-button>
      </el-col>
    </el-row>

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
            @click="showAllMailDialog(scope.row)"
            v-hasPermi="['system:xyserver:all']"
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

    <!--    全服福利-->
    <el-dialog title="全服福利" :visible.sync="giftItem.open" center width="30%" append-to-body>

      <el-form label-width="80px">
        <el-form-item label="发送数量">
          <el-input v-model="giftItem.itemNumber" placeholder="请输入发送数量"></el-input>
        </el-form-item>
      </el-form>
      <el-form label-width="80px">
        <el-form-item label="邮件标题">
          <el-input v-model="giftItem.mailTitle" placeholder="请输入邮件标题"></el-input>
        </el-form-item>
      </el-form>
      <el-form label-width="80px">
        <el-form-item label="邮件内容">
          <el-input v-model="giftItem.mailText" placeholder="请输入邮件内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sendAllGift">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>

  </div>


</template>

<script>
import {listJob, getJob, delJob, addJob, updateJob, exportJob, runJob, changeJobStatus} from "@/api/monitor/job";
import {listXy_item} from "@/api/system/xy_item";
import {sendNotice, sendAllGift} from "@/api/system/xy_server";


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
      },
      giftItem: {
        //是否显示对话框
        open: false,
        //物品ID
        itemId: null,
        //物品类型
        itemType: null,
        //物品数量
        itemNumber: null,
        //右键标题
        mailTitle: "维护补偿",
        //右键内容
        mailText: "尊敬的玩家您好，本次维护已经完成，为您送上xxx一份,祝您游戏愉快!"
      }
    }
  }
  ,
  created() {
    this.getDicts("xy_item_type").then(response => {
      this.itemTypeOptions = response.data;
    });
  }
  ,
  methods: {

    cancelDialog() {
      this.giftItem.open = false;
    },

    //发送公告
    sendNotece() {
      this.loading = true
      sendNotice(this.noticeContent).then(response => {
        this.loading = false
        this.$message.success("发送成功，如需定时发送请至定时任务处操作")
        this.noticeContent = null
      });
    },
    //显示补偿对话框
    showAllMailDialog(row) {
      this.giftItem.open = true;
      this.giftItem.itemId = row.itemId;
      this.giftItem.itemType = row.itemType;
    },
    //发送补偿
    sendAllGift() {
      if (this.giftItem.itemNumber === '' || this.giftItem.itemNumber == null) {
        this.$message.error("数量不能为空")
        return ;
      }
      this.loading = true
      sendAllGift(this.giftItem.itemId, this.giftItem.itemNumber, this.giftItem.mailTitle, this.giftItem.mailText, this.giftItem.itemType).then(response => {
        this.loading = false;
        this.giftItem.open = false;
        this.$message.success("发送成功")
        this.xyItem.inputContent = null;
      });
    },
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
      console.log(row.itemType + '物品类型')
      return this.selectDictLabel(this.itemTypeOptions, row.itemType);
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
