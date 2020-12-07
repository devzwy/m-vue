package com.ruoyi.common.utils;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GMUtils {
    private static final Logger log = LoggerFactory.getLogger(GMUtils.class);
    @Autowired
    RuoYiConfig ruoYiConfig;

    public void sendNotice(Integer serverId, String notice) {
        String cmd = "notifymsg ";
        String state = "error";
        String sign = Md5Utils.hash(serverId + cmd + notice + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + notice;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("发送公告： 区服{}，公告内容{}，返回状态{}", new Object[]{serverId, notice, state}));
    }

    public void testCMD() {
        String cmd = "123 ";
        String state = "error";
        String sign = Md5Utils.hash("1" + cmd + "1111" + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + "1" + "&content=" + cmd + "1111";
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("发送公告： 区服{}，公告内容{}，返回状态{}", new Object[]{"1", "1111", state}));
    }

    public void sendOnlineCount(Integer serverId, String playerId) {
        String cmd = "countonline ";
        String state = "error";
        String sign = Md5Utils.hash(serverId + cmd + playerId + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + playerId;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("查询在线玩家数： 区服{}，玩家ID{}，返回状态{}", new Object[]{serverId, playerId, state}));
    }


    public int readymaintain(Integer serverId, String startTime, String endTime) {
        String cmd = "readymaintain ";
        String state = "error";
        String sign = Md5Utils.hash(serverId + cmd + startTime + " " + endTime + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + startTime + " " + endTime;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("准备维护： 区服{}，开始时间{}，结束时间{}，返回状态{}", new Object[]{serverId, startTime, endTime, state}));
        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }


    public int gmBanlogin(Integer serverId, String pid) {
        String cmd = "banlogin ";
        String state = "error";
        String sign = Md5Utils.hash(serverId + cmd + pid + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + pid;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("登陆限制： 区服{}，状态{}，返回状态{}", new Object[]{serverId, pid, state}));
        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }


    public int startmaintain(Integer serverId, String pid) {
        String cmd = "startmaintain ";
        String state = "error";
        String sign = Md5Utils.hash(serverId + cmd + pid + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + pid;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("开始维护： 区服{}，登陆状态{}，返回状态{}", new Object[]{serverId, pid, state}));
        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }


    public int setHuoDongState(Long serverId, String name, Long states) {
        String cmd = "sethuodongstate ";
        String state = "error";
        String sign = Md5Utils.hash(serverId + cmd + name + " " + states + this.ruoYiConfig.getGameSign());
        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + name + " " + states;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        log.debug(StringUtils.format("设置活动状态： 区服{}，活动名称{}，状态{}，返回状态{}", new Object[]{serverId, name, states, state}));
        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }

    public int setHeroPro(Integer serverId, Long lv, Long zs, Long playerId) {
        String cmd = "setheropro ";
        String state = "error";
        String hid = "1";
        String Lv = "d_lv";
        String lvSign = Md5Utils.hash(serverId + cmd + hid + " " + Lv + " " + lv + " " + playerId + this.ruoYiConfig.getGameSign());

        String lvData = "sign=" + lvSign + "&kid=" + serverId + "&content=" + cmd + hid + " " + Lv + " " + lv + " " + playerId;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), lvData);

        log.debug(StringUtils.format("设置玩家等级： 区服{}，玩家Id{}，等级{}，返回状态{}", new Object[]{serverId, playerId, lv, state}));
        String ZhuanShen = "d_rbnum";
        String zsSign = Md5Utils.hash(serverId + cmd + hid + " " + ZhuanShen + " " + zs + " " + playerId + this.ruoYiConfig.getGameSign());

        String zsData = "sign=" + zsSign + "&kid=" + serverId + "&content=" + cmd + hid + " " + ZhuanShen + " " + zs + " " + playerId;
        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), zsData);
        log.debug(StringUtils.format("设置玩家转生： 区服{}，玩家Id{}，转生{}，返回状态{}", new Object[]{serverId, playerId, zs, state}));

        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }

    public int setHeroShuXing(Integer serverId, Integer liliang, Integer gengu, Integer minjie, Integer linxing, String playerId) {
        String cmd = "setheropro ";
        String state = "error";
        String hid = "1";
        if ((liliang != null) && (liliang.intValue() > 0)) {
            String d_liliang = "d_liliang";
            String llSign = Md5Utils.hash(serverId + cmd + hid + " " + d_liliang + " " + liliang + " " + playerId + this.ruoYiConfig.getGameSign());

            String llData = "sign=" + llSign + "&kid=" + serverId + "&content=" + cmd + hid + " " + d_liliang + " " + liliang + " " + playerId;
            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), llData);
            log.debug(StringUtils.format("设置玩家属性： 区服{}，玩家Id{}，力量{}，返回状态{}", new Object[]{serverId, playerId, liliang, state}));
        }
        if ((gengu != null) && (gengu.intValue() > 0)) {
            String d_gengu = "d_gengu";
            String ggSign = Md5Utils.hash(serverId + cmd + hid + " " + d_gengu + " " + gengu + " " + playerId + this.ruoYiConfig.getGameSign());

            String ggData = "sign=" + ggSign + "&kid=" + serverId + "&content=" + cmd + hid + " " + d_gengu + " " + gengu + " " + playerId;
            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), ggData);
            log.debug(StringUtils.format("设置玩家属性： 区服{}，玩家Id{}，根骨{}，返回状态{}", new Object[]{serverId, playerId, gengu, state}));
        }

        if ((minjie != null) && (minjie.intValue() > 0)) {
            String d_minjie = "d_minjie";
            String mjSign = Md5Utils.hash(serverId + cmd + hid + " " + d_minjie + " " + minjie + " " + playerId + this.ruoYiConfig.getGameSign());

            String mjData = "sign=" + mjSign + "&kid=" + serverId + "&content=" + cmd + hid + " " + d_minjie + " " + minjie + " " + playerId;
            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), mjData);
            log.debug(StringUtils.format("设置玩家属性： 区服{}，玩家Id{}，敏捷{}，返回状态{}", new Object[]{serverId, playerId, minjie, state}));
        }
        if ((linxing != null) && (linxing.intValue() > 0)) {
            String d_lingxing = "d_linxing";
            String lxSign = Md5Utils.hash(serverId + cmd + hid + " " + d_lingxing + " " + linxing + " " + playerId + this.ruoYiConfig.getGameSign());

            String lxData = "sign=" + lxSign + "&kid=" + serverId + "&content=" + cmd + hid + " " + d_lingxing + " " + linxing + " " + playerId;
            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), lxData);
            log.debug(StringUtils.format("设置玩家属性： 区服{}，玩家Id{}，灵性{}，返回状态{}", new Object[]{serverId, playerId, linxing, state}));
        }
        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }

    public int mainTask(Integer serverId, String playerId, String type, Integer num) {
        String cmd = "finishatask ";
        playerId = playerId.replace("；", ";");

        String[] uidsarr = playerId.split(";");
        String state = "error";
        if ((type.equals("")) || (playerId.equals(""))) {
            return 2;
        }
        for (String uidsstr : uidsarr) {
            String sign = Md5Utils.hash(serverId + cmd + type + " " + num + " " + uidsstr + this.ruoYiConfig.getGameSign());
            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + type + " " + num + " " + uidsstr;
            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        }
        log.debug(StringUtils.format("完成任务： 区服{}，玩家Id{}，任务类型{}，任务环数{}，返回状态{}", new Object[]{serverId, playerId, type, num, state}));
        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
            return 1;
        }
        return 2;
    }

    public int OnlineMail(Integer serverId, Integer itemId, Integer num, String title, String text, Integer itemtype) {
        if (itemId.intValue() == 0) {
            return 2;
        }
        if (itemtype.intValue() == 1) {
            String cmd = "onlineitemmail ";
            String state = "error";
            String sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + this.ruoYiConfig.getGameSign());
            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num;

            if ((!title.equals("")) && (text.equals(""))) {
                sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + " " + title + this.ruoYiConfig.getGameSign());
                data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num + " " + title;
            }

            if ((!text.equals("")) && (title.equals(""))) {
                sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + " " + text + this.ruoYiConfig.getGameSign());
                data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num + " " + text;
            }

            if ((!text.equals("")) && (!title.equals(""))) {
                sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + " " + title + " " + text + this.ruoYiConfig.getGameSign());
                data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num + " " + title + " " + text;
            }
            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);

            log.debug(StringUtils.format("发送全服邮件： 区服{}，物品Id{}，物品数量{}，标题{}，邮件内容{}，返回状态{}", new Object[]{serverId, itemId, num, title, text, state}));
            if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {
                return 1;
            }
        } else if (itemtype.intValue() == 2) {

            String cmd = "onlinepetmail ";

            String state = "error";

            num = Integer.valueOf(1);

            String sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + this.ruoYiConfig.getGameSign());

            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num;

            if ((!title.equals("")) && (text.equals(""))) {

                sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + " " + title + this.ruoYiConfig.getGameSign());

                data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num + " " + title;
            }


            if ((!text.equals("")) && (title.equals(""))) {

                sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + " " + text + this.ruoYiConfig.getGameSign());

                data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num + " " + text;
            }


            if ((!text.equals("")) && (!title.equals(""))) {

                sign = Md5Utils.hash(serverId + cmd + itemId + " " + num + " " + title + " " + text + this.ruoYiConfig.getGameSign());

                data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + itemId + " " + num + " " + title + " " + text;
            }

            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);


            log.debug(StringUtils.format("发送全服邮件： 区服{}，宠物Id{}，物品数量{}，标题{}，邮件内容{}，返回状态{}", new Object[]{serverId, itemId, num, title, text, state}));

            if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                return 1;
            }
        } else {

            return 2;
        }

        return 2;
    }

    public int sendItems(Integer serverId, String playerId, Integer itemId, Integer num, Integer itemtype) {

        if ((itemId.intValue() == 0) || (playerId.equals(""))) {

            return 2;
        }

        if (itemtype.intValue() == 1) {

            String cmd = "additembymail ";

            String state = "error";

            playerId = playerId.replace("；", ";");


            String[] uidsarr = playerId.split(";");

            for (String uidsstr : uidsarr) {

                String sign = Md5Utils.hash(serverId + cmd + uidsstr + " " + itemId + " " + num + this.ruoYiConfig.getGameSign());

                String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + uidsstr + " " + itemId + " " + num;

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
            }

            log.debug(StringUtils.format("发送物品： 区服{}，玩家Id{}，物品Id{}，物品数量{}，返回状态{}", new Object[]{serverId, playerId, itemId, num, state}));

            if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                return 1;
            }

        } else if (itemtype.intValue() == 2) {

            String cmd = "addpetbymail ";

            String state = "error";

            playerId = playerId.replace("；", ";");

            num = Integer.valueOf(1);

            String[] uidsarr = playerId.split(";");

            for (String uidsstr : uidsarr) {

                String sign = Md5Utils.hash(serverId + cmd + uidsstr + " " + itemId + " " + num + this.ruoYiConfig.getGameSign());

                String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + uidsstr + " " + itemId + " " + num;

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
            }

            log.debug(StringUtils.format("发送物品： 区服{}，玩家Id{}，物品Id{}，物品数量{}，返回状态{}", new Object[]{serverId, playerId, itemId, num, state}));

            if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                return 1;
            }
        } else {

            return 2;
        }

        return 2;
    }

    public int worldBan(Integer serverId, String playerId, Integer num) {

        String cmd = "worldban ";

        playerId = playerId.replace("；", ";");


        String[] uidsarr = playerId.split(";");

        String state = "error";

        if ((num.equals("")) || (playerId.equals(""))) {

            return 2;
        }

        for (String uidsstr : uidsarr) {

            String sign = Md5Utils.hash(serverId + cmd + uidsstr + " " + num + this.ruoYiConfig.getGameSign());

            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + uidsstr + " " + num;

            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        }

        log.debug(StringUtils.format("禁言： 区服{}，玩家Id{}，禁言时间{}，返回状态{}", new Object[]{serverId, playerId, num, state}));

        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

            return 1;
        }

        return 2;
    }

    public int fenghao(Integer serverId, String playerId, Integer states) {

        if (playerId.equals("")) {

            return 2;
        }

        String cmd = "fenghao";

        String state = "error";

        playerId = playerId.replace("；", ";");


        String[] uidsarr = playerId.split(";");

        for (String uidsstr : uidsarr) {

            String sign = Md5Utils.hash(serverId + cmd + uidsstr + " " + states + this.ruoYiConfig.getGameSign());


            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + uidsstr + " " + states;

            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        }

        log.debug(StringUtils.format("封号： 区服{}，玩家Id{}，封号状态{}，返回状态{}", new Object[]{serverId, playerId, states, state}));

        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

            return 1;
        }

        return 2;
    }

    public int Gold(Integer serverId, String playerId, Integer gold) {

        if ((gold.intValue() == 0) || (playerId.equals(""))) {

            return 2;
        }

        String cmd = "addgoldbymail ";

        String state = "error";

        playerId = playerId.replace("；", ";");


        String[] uidsarr = playerId.split(";");

        for (String uidsstr : uidsarr) {

            String sign = Md5Utils.hash(serverId + cmd + uidsstr + " " + gold + this.ruoYiConfig.getGameSign());

            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + uidsstr + " " + gold;

            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        }

        log.debug(StringUtils.format("发送元宝： 区服{}，玩家Id{}，元宝数量{}，返回状态{}", new Object[]{serverId, playerId, gold, state}));

        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

            return 1;
        }

        return 2;
    }

    public int Recharge(Integer serverId, String playerId, Integer gid) {

        if ((gid.intValue() == 0) || (playerId.equals(""))) {

            return 2;
        }

        String cmd = "chongzhi ";

        String state = "error";

        playerId = playerId.replace("；", ";");

        String[] uidsarr = playerId.split(";");

        for (String uidsstr : uidsarr) {

            String sign = Md5Utils.hash(serverId + cmd + " " + gid + " false 0 " + uidsstr + this.ruoYiConfig.getGameSign());

            String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + " " + gid + " false 0 " + uidsstr;

            state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);
        }

        log.debug(StringUtils.format("发送元宝： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{serverId, playerId, gid, state}));

        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

            return 1;
        }

        return 2;
    }

    public int sendfanli(Integer kid, String playerId, String chongzhinum) {

        if (playerId.equals("")) {

            return 2;
        }

        String cmd = "additembymail ";

        String cmd2 = "addgoldbymail ";

        String cmd3 = "addpetbymail ";

        String state = "error";

        playerId = playerId.replace("；", ";");


        String[] uidsarr = playerId.split(";");

        for (String uidsstr : uidsarr) {

            if (chongzhinum.equals("6")) {

                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 3000" + this.ruoYiConfig.getGameSign());

                String data = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 3000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20050" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20050";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok")))
                    return 1;
            }

            if (chongzhinum.equals("30")) {

                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 15000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 15000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String[] item_ids1 = {"93017", "93005"};

                String[] item_num1 = {"450", "5"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20049" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20049";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }
            } else if (chongzhinum.equals("98")) {

                String[] item_ids1 = {"75005", "93005", "93006", "93007"};

                String[] item_num1 = {"400", "5", "10", "2"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 549000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 549000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20054" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20054";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("198")) {

                String[] item_ids1 = {"93017", "93005", "93006", "93007", "90009"};

                String[] item_num1 = {"800", "7", "13", "3", "200"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 3099000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 3099000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20056" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20056";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("500")) {

                String[] item_ids1 = {"90009", "90012", "93017", "93036", "93010", "90038"};

                String[] item_num1 = {"400", "400", "500", "4", "10", "200"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 4250000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 4250000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20055" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20055";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("1000")) {

                String[] item_ids1 = {"90009", "90012", "93017", "93036", "93010", "90038"};

                String[] item_num1 = {"800", "800", "1500", "8", "10", "300"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 9050000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 9050000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20057" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20057";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("2000")) {

                String[] item_ids1 = {"90009", "90012", "93017", "93036", "93010", "90038", "93006", "93005"};

                String[] item_num1 = {"1200", "1200", "2400", "16", "20", "400", "15", "15"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 21000000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 21000000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20063" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20063";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("3000")) {

                String[] item_ids1 = {"90009", "90012", "93017", "93036", "93010", "90038", "93006", "93005"};

                String[] item_num1 = {"1600", "1600", "3000", "30", "25", "600", "20", "20"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 36500000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 36500000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20058" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20058";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("5000")) {

                String[] item_ids1 = {"90009", "90012", "93017", "93036", "92203", "93010", "90038", "93006", "93005"};

                String[] item_num1 = {"2000", "2000", "4000", "30", "6", "40", "800", "30", "30"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 45500000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 45500000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20062" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20062";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("10000")) {

                String[] item_ids1 = {"90009", "90012", "93017", "93036", "92203", "93010", "90038", "93006", "93005"};

                String[] item_num1 = {"3000", "3000", "5000", "40", "8", "50", "800", "40", "40"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 55000000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 55000000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("guoqing1")) {

                String[] item_ids1 = {"93016", "93017", "93005", "93006", "93007"};

                String[] item_num1 = {"666", "666", "20", "20", "5"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 2000000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 2000000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("guoqing2")) {

                String[] item_ids1 = {"93016", "90009", "90012", "93036"};

                String[] item_num1 = {"666", "999", "999", "5"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 5000000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 5000000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20055" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20055";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);

                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }

            } else if (chongzhinum.equals("guoqing3")) {

                String[] item_ids1 = {"93016", "90009", "90012", "93036", "92203", "90049", "93005", "93007", "90038"};

                String[] item_num1 = {"999", "999", "999", "5", "2", "99", "20", "20", "200"};

                for (int i = 0; i < item_ids1.length; i++) {

                    String sign = Md5Utils.hash(kid + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i] + this.ruoYiConfig.getGameSign());

                    String getdata = "sign=" + sign + "&kid=" + kid + "&content=" + cmd + uidsstr + " " + item_ids1[i] + " " + item_num1[i];

                    state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata);
                }


                String sign2 = Md5Utils.hash(kid + cmd2 + uidsstr + " 10000000" + this.ruoYiConfig.getGameSign());

                String getdata2 = "sign=" + sign2 + "&kid=" + kid + "&content=" + cmd2 + uidsstr + " 10000000";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata2);


                String sign3 = Md5Utils.hash(kid + cmd3 + uidsstr + " 20064" + this.ruoYiConfig.getGameSign());

                String getdata3 = "sign=" + sign3 + "&kid=" + kid + "&content=" + cmd3 + uidsstr + " 20064";

                state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), getdata3);

                log.debug(StringUtils.format("发送福利： 区服{}，玩家Id{}，充值礼包ID{}，返回状态{}", new Object[]{kid, playerId, chongzhinum, state}));

                if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

                    return 1;
                }
            }
        }

        return 2;
    }

    public int PaiMaiPet(Integer serverId, String pet1, String pet2) {

        if ((pet2.equals("")) || (pet1.equals(""))) {

            return 2;
        }

        String cmd = "pmpet ";

        String state = "error";

        String sign = Md5Utils.hash(serverId + cmd + pet1 + " " + pet2 + this.ruoYiConfig.getGameSign());

        String data = "sign=" + sign + "&kid=" + serverId + "&content=" + cmd + pet1 + " " + pet2;

        state = HttpUtils.sendGet(this.ruoYiConfig.getGameAddress(), data);

        log.debug(StringUtils.format("拍卖宠物： 区服{}，宠物位置1{}，宠物位置2{}，返回状态{}", new Object[]{serverId, pet1, pet2, state}));

        if ((state.equals("OK")) || (state.equals("Ok")) || (state.equals("ok"))) {

            return 1;
        }

        return 2;
    }
}