<template>
  <div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:xy_role:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:xy_role:export']"
        >导出
        </el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="xy_roleList" center @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="角色账号" width="200" align="center" prop="xyAccount"/>
      <el-table-column label="角色ID" width="200" align="center" prop="xyRoleId"/>
      <el-table-column label="角色名称" width="200" align="center" prop="xyRoleName"/>
      <el-table-column label="角色等级" width="200" align="center" prop="xyRoleLevel"/>
      <el-table-column label="角色转生等级" width="200" align="center" prop="xyRoleLevelZs"/>
      <el-table-column label="角色类型" width="200" align="center" prop="xyRoleType" :formatter="formatRoleType"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            icon="el-icon-present"
            @click="sendItem(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >发送物资
          </el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-present"
            @click="addMonney(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >充值元宝
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-present"
            @click="sendRank(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >发送福利
          </el-button>


          <el-button
            size="mini"
            type="text"
            icon="el-icon-thumb"
            @click="addMonney2(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >充值工具
          </el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-ice-cream-round"
            @click="banSend(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >禁言角色
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-lock"
            @click="banRole(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >角色封号
          </el-button>


          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="editRole(scope.row)"
            v-hasPermi="['system:xy_role:edit']"
          >修改属性
          </el-button>


          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:xy_role:remove']"
          >删除角色
          </el-button>


        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改西游角色对话框 -->
    <el-dialog :title="title" :visible.sync="open" center width="500px" center append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="角色名称:" prop="xyRoleName">
          <label>{{ form.xyRoleName }}</label>
        </el-form-item>
        <el-form-item label="角色类型:" prop="xyRoleType">
          <label>{{ formatRoleType(form) }}</label>
        </el-form-item>
        <el-form-item label="角色等级" prop="xyRoleLevel">
          <el-input v-model="form.xyRoleLevel" placeholder="请输入角色等级"/>
        </el-form-item>
        <el-form-item label="转生等级" prop="xyRoleLevelZs">
          <el-input v-model="form.xyRoleLevelZs" placeholder="请输入角色转生等级"/>
        </el-form-item>


        <el-form-item label="角色力量" prop="xyRoleLevelZs">
          <el-input v-model="form.p1" placeholder="请输入角色力量"/>
        </el-form-item>

        <el-form-item label="角色根骨" prop="xyRoleLevelZs">
          <el-input v-model="form.p2" placeholder="请输入角色根骨"/>
        </el-form-item>

        <el-form-item label="角色敏捷" prop="xyRoleLevelZs">
          <el-input v-model="form.p3" placeholder="请输入角色敏捷"/>
        </el-form-item>

        <el-form-item label="角色灵力" prop="xyRoleLevelZs">
          <el-input v-model="form.p4" placeholder="请输入角色力"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 禁言对话框 -->
    <el-dialog :title="title" :visible.sync="banMsgInfo.open" center width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules">

        <el-form-item label="禁言时长(单位秒)" prop="xyRoleLevelZs">
          <el-input v-model="banMsgInfo.bantime" placeholder="单位秒，设置为0表示解除禁言"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="banSendSub">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>

    <!-- fenghao对话框 -->
    <el-dialog :title="title" :visible.sync="banRoleParam.open" center width="500px" append-to-body>
      <label>状态：
        <el-select v-model="banRoleParam.banType">
          <el-option value="封禁">封禁</el-option>
          <el-option value="解封">解封</el-option>
        </el-select>
      </label>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="banRoleFh">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 充值元宝对话框 -->
    <el-dialog :title="title" :visible.sync="addYB.open" center width="500px" append-to-body>
      <el-input v-model="addYB.number" placeholder="0"/>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="banSendYB">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>


    <!--    充值套餐选择-->
    <el-dialog :title="title" :visible.sync="addYBTC.open" center width="500px" append-to-body>
      <el-select style="width: 100%" v-model="addYBTC.selectedRecharge" placeholder="选择套餐" clearable size="small">
        <el-option
          v-for="dict in addYBTC.rechargeOptions"
          :key="dict.dictValue"
          :label="dict.dictLabel"
          :value="dict.dictValue"
        />
      </el-select>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="banSendYBTC">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>
    <!--   福利套餐选择-->
    <el-dialog :title="title" :visible.sync="addFuli.open" center width="500px" append-to-body>
      <el-select style="width: 100%" v-model="addFuli.selectedfuli" placeholder="选择套餐" clearable size="small">
        <el-option
          v-for="dict in addFuli.fuliOptions"
          :key="dict.dictValue"
          :label="dict.dictLabel"
          :value="dict.dictValue"
        />
      </el-select>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="banSendFuli">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>


    <!--   发送物资-->
    <el-dialog :title="title" :visible.sync="sendItemParam.open" center width="70%" append-to-body>

      <el-form :model="sendItemParam.queryParams" ref="sendItemParam.queryForm" :inline="true" label-width="68px">
        <el-form-item label="物资ID" prop="itemId">
          <el-input
            v-model="sendItemParam.queryParams.itemId"
            placeholder="请输入物资ID"
            clearable
            size="small"
            @keyup.enter.native="itemHandleQuery"
          />
        </el-form-item>
        <el-form-item label="物资类型" prop="itemType">
          <el-select v-model="sendItemParam.queryParams.itemType" placeholder="请选择物资类型" clearable size="small">
            <el-option
              v-for="dict in sendItemParam.itemTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="物资名称" prop="itemName">
          <el-input
            v-model="sendItemParam.queryParams.itemName"
            placeholder="请输入物资名称"
            clearable
            size="small"
            @keyup.enter.native="itemHandleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="mini" @click="itemHandleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="itemResetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="sendItemParam.xy_itemList">
        <el-table-column label="物资ID" align="center" prop="itemId"/>
        <el-table-column label="物资类型" align="center" prop="itemType" :formatter="itemTypeFormat"/>
        <el-table-column label="物资名称" align="center" prop="itemName"/>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="showSendItemDialog(scope.row)"
              v-hasPermi="['system:xy_role:list']"
            >发送
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="sendItemParam.total>0"
        :total="sendItemParam.total"
        :page.sync="sendItemParam.queryParams.pageNum"
        :limit.sync="sendItemParam.queryParams.pageSize"
        @pagination="getList"
      />
    </el-dialog>


    <el-dialog title="发送物资" :visible.sync="giftItem.open" center width="30%" append-to-body>

      <el-form label-width="80px">
        <el-form-item label="发送数量">
          <el-input v-model="giftItem.itemNumber" placeholder="请输入发送数量"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="sendGift">确 定</el-button>
        <el-button @click="cancelDialog">取 消</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
import {
  listXy_role,
  getXy_role,
  delXy_role,
  addXy_role,
  updateXy_role,
  exportXy_role,
  ban_role_send_msg,
  ban_role_fh,
  addYb,
  addYbTC,
  addFuli
} from "@/api/system/xy_role";
import {listXy_item} from "@/api/system/xy_item";
import {sendItems} from "@/api/system/xy_server";

export default {
  name: "Xy_role",
  components: {},
  props: {account: null},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 西游角色表格数据
      xy_roleList: [],
      //角色类型字典
      xy_role_options: [],
      // 弹出层标题

      title: "",

      // 弹出层
      open: false,

      roleName: null,

      roleId: null,
      giftItem: {
        //是否显示对话框
        open: false,
        //物品ID
        itemId: null,
        //物品类型
        itemType: null,
        //物品数量
        itemNumber: null
      },

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xyRoleName: null,
        xyRoleType: null,
        xyRoleLevel: null,
        xyRoleLevelZs: null,
        xyAccount: this.account,
        p1: null,
        p2: null,
        p3: null,
        p4: null,
      },
      //禁言用户相关参数
      banMsgInfo: {
        bantime: 10000,
        open: false
      },
      //封号相关参数
      banRoleParam: {
        banType: "封禁",
        open: false
      },
      //充值元宝参数
      addYB: {
        open: false,
        number: 0,
      },

      //充值套餐参数
      addYBTC: {
        // 状态数据字典
        rechargeOptions: [],
        open: false,
        selectedRecharge: null
      },
      addFuli: {
        fuliOptions: [],
        open: false,
        selectedfuli: null
      },
      sendItemParam: {
        // itemOptions: [],
        open: false,
        // 西游物资表格数据
        xy_itemList: [],
        //条数
        total: 0,
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          itemId: null,
          itemType: null,
          itemName: null
        },
        // 物资类型字典
        itemTypeOptions: []
        // selectedfuli: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  }
  ,
  created() {
    console.log('传入account:' + this.account)
    this.getDicts("xy_role_type").then(response => {
      this.xy_role_options = response.data;
    });
    this.getDicts("xy_recharge_id").then(response => {
      this.addYBTC.rechargeOptions = response.data;
      this.addYBTC.selectedRecharge = this.addYBTC.rechargeOptions[0].dictValue
    });
    this.getDicts("xy_fuli_id").then(response => {
      this.addFuli.fuliOptions = response.data;
      this.addFuli.selectedfuli = this.addFuli.fuliOptions[0].dictValue
    });
    this.getDicts("xy_item_type").then(response => {
      this.sendItemParam.itemTypeOptions = response.data;
    });

    this.getList();
    this.getXyItemList();
  }
  ,
  methods: {
    //发送补偿
    sendGift() {
      if (this.giftItem.itemNumber === '' || this.giftItem.itemNumber == null) {
        this.$message.error("数量不能为空")
        return;
      }
      this.loading = true
      sendItems(this.roleId, this.giftItem.itemId, this.giftItem.itemNumber, this.giftItem.itemType).then(response => {
        this.loading = false;
        this.giftItem.open = false;
        this.$message.success("发送成功")
      });
    },

    // 物资类型字典翻译
    itemTypeFormat(row, column) {
      return this.selectDictLabel(this.sendItemParam.itemTypeOptions, row.itemType);
    }
    ,
    //发送充值福利
    banSendFuli() {
      if (this.addFuli.selectedfuli === '') return
      this.loading = true;
      // console.log('福利：'+this.addFuli.selectedfuli)
      // var selectedValue = this.addFuli.fuliOptions[this.addFuli.selectedfuli - 1].dictValue;

      addFuli(this.roleId, this.addFuli.selectedfuli).then(response => {
        this.msgSuccess("操作成功")
        this.loading = false;
        this.addFuli.open = false;
        this.roleId = "";
      });
    }
    ,

    //发送元宝套餐
    banSendYBTC() {
      if (this.addYBTC.selectedRecharge === '') return
      this.loading = true;

      addYbTC(this.roleId, this.addYBTC.selectedRecharge).then(response => {
        this.msgSuccess("操作成功")
        this.loading = false;
        this.addYBTC.open = false;
        this.roleId = "";
      });
    }
    ,

    //发送元宝
    banSendYB() {
      this.loading = true;
      addYb(this.roleId, this.addYB.number).then(response => {
        this.msgSuccess("操作成功")
        this.loading = false;
        this.addYB.open = false;
        this.addYB.number = 0;
        this.roleId = "";
      });
    }
    ,

    /** 查询西游物资列表 */
    getXyItemList() {
      this.loading = true;
      listXy_item(this.sendItemParam.queryParams).then(response => {
        this.sendItemParam.xy_itemList = response.rows;
        this.sendItemParam.total = response.total;
        this.loading = false;
      });
    }
    ,
    /** 查询西游角色列表 */
    getList() {
      this.loading = true;
      listXy_role(this.queryParams).then(response => {
        this.xy_roleList = response.rows;
        console.log('角色数量：' + this.xy_roleList.length)
        this.total = response.total;
        this.loading = false;
      });
    }
    ,
    // 任务组名字典翻译
    formatRoleType(row) {
      return this.selectDictLabel(this.xy_role_options, row.xyRoleType);
    }
    ,
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    }
    ,
    //取消对话框
    cancelDialog() {
      this.banMsgInfo.open = false;
      this.banRoleParam.open = false;
      this.addYB.open = false;
      this.addYBTC.open = false;
      this.addFuli.open = false;
      this.sendItemParam.open = false;
      this.giftItem.open = false;
    }
    ,
    // 表单重置
    reset() {
      this.form = {
        xyRoleId: null,
        xyRoleName: null,
        xyRoleType: null,
        xyRoleLevel: null,
        xyRoleLevelZs: null,
        xyAccount: null
      };
      this.resetForm("form");
    }
    ,
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    }
    ,
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    }
    ,

    /** 搜索按钮操作 */
    itemHandleQuery() {
      this.sendItemParam.queryParams.pageNum = 1;
      this.getXyItemList();
    }
    ,
    /** 重置按钮操作 */
    itemResetQuery() {
      this.resetForm("sendItemParam.queryForm");
      this.itemHandleQuery();
    }
    ,
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.xyRoleId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    }
    ,
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加西游角色";
    }
    ,
    //编辑角色属性
    editRole(row) {
      this.reset();
      const xyRoleId = row.xyRoleId || this.ids
      getXy_role(xyRoleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改角色属性";
      });
    }
    ,
    showSendItemDialog(row) {
      this.giftItem.itemId = row.itemId;
      this.giftItem.itemType = row.itemType;
      this.giftItem.open = true;
    },

    //显示禁言对话框
    sendItem(row) {
      this.roleName = row.xyRoleName
      this.roleId = row.xyRoleId
      this.title = "发送物资";
      this.sendItemParam.open = true;
    }
    ,

    //显示禁言对话框
    banSend(row) {
      this.roleName = row.xyRoleName
      this.roleId = row.xyRoleId
      this.title = "禁言";
      this.banMsgInfo.open = true
    }
    ,

    //调用禁言API
    banSendSub() {
      this.loading = true;
      ban_role_send_msg(this.roleId, this.banMsgInfo.bantime).then(response => {
        this.msgSuccess("操作成功")
        this.loading = false;
        this.banMsgInfo.open = false;
        this.roleId = "";
        this.title = "";
      });

    }
    ,


    //显示封号对话框
    banRole(row) {
      this.roleName = row.xyRoleName
      this.roleId = row.xyRoleId
      this.title = "封号";
      this.banRoleParam.open = true
    }
    ,

    //调用封号API
    banRoleFh() {
      this.loading = true;
      ban_role_fh(this.roleId,
        this.banRoleParam.banType === "封禁" ? 1 : 0
      ).then(response => {
        this.msgSuccess("操作成功")
        this.loading = false;
        this.banRoleParam.open = false;
        this.roleId = "";
        this.title = "";
      });
    }
    ,

    addMonney(row) {
      this.addYB.open = true;
      this.roleId = row.xyRoleId
      this.title = "充值元宝";
    }
    ,
    addMonney2(row) {
      this.addYBTC.open = true;
      this.roleId = row.xyRoleId
      this.title = "充值套餐";
    }
    ,
    sendRank(row) {
      this.addFuli.open = true;
      this.roleId = row.xyRoleId
      this.title = "发送福利";
    }
    ,

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.xyRoleId != null) {
            updateXy_role(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addXy_role(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }
    ,
    /** 删除按钮操作 */
    handleDelete(row) {
      const xyRoleIds = row.xyRoleId || this.ids;
      this.$confirm('是否确认删除西游角色编号为"' + xyRoleIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delXy_role(xyRoleIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    }
    ,
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有西游角色数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return exportXy_role(queryParams);
      }).then(response => {
        this.download(response.msg);
      })
    }
  }
}
;
</script>
