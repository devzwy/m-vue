package com.ruoyi.quartz.task;


import com.ruoyi.system.domain.XyRole;
import com.ruoyi.system.service.IXyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Component("XyTask")
public class XyTask {

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    IXyRoleService iXyRoleService;

    public void loadXiyouDataFromMongoDB() {

        //查找账号数据
        List<HashMap> mongoAccounts = mongoTemplate.findAll(HashMap.class, "account_xiyou");

        for (int i = 0; i < mongoAccounts.size(); i++) {
            String account = String.valueOf(mongoAccounts.get(i).get("key"));

            //根据账号查询角色信息(可能是空的，未创建角色)
            Query query = Query.query(Criteria.where("key").is(account));

            List<HashMap> mongoRoles = mongoTemplate.find(query, HashMap.class, "roleinfo_xiyou");

            for (int i1 = 0; i1 < mongoRoles.size(); i1++) {
                //zwy666 -> {roles={}, _id=5fc2288d28dcca510df73e83, rolesx={md_s_1={100036={rid=100036, lv=102, name=Null, rtype=12006, rb=0}, 100048={rid=100048, lv=102, name=戏阳阳飙, rtype=13001, rb=0}, 100050={rid=100050, lv=0, name=頋宏伯, rb=0, rtype=14001}}}, key=zwy666}
                System.out.println(account+" -> "+ mongoRoles.get(i1));
            }
        }
    }
}
