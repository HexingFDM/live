package com.angelatech.yeyelive.activity.function;


import android.content.Context;
import android.content.Intent;

import com.angelatech.yeyelive.TransactionValues;
import com.angelatech.yeyelive.model.LoginServerModel;
import com.angelatech.yeyelive.service.IServiceValues;
import com.angelatech.yeyelive.util.BroadCastHelper;
import com.angelatech.yeyelive.web.HttpFunction;
import com.will.common.string.security.Md5;
import com.will.common.tool.DeviceTool;
import com.angelatech.yeyelive.db.model.BasicUserInfoDBModel;
import com.angelatech.yeyelive.service.IServiceHelper;
import com.angelatech.yeyelive.util.CacheDataManager;
import com.will.web.handle.HttpBusinessCallback;

import java.util.HashMap;
import java.util.Map;

public class Login extends HttpFunction {
    public Login(Context context){
        super(context);
    }

    //
    public void login(String url,String userid, String password,HttpBusinessCallback callback){
        Map<String, String> params = new HashMap<>();
        params.put("account", userid.replace("+", ""));
        params.put("password", Md5.get32MD5Lower(password));
        params.put("deviceid", DeviceTool.getUniqueID(mContext));
        params.put("sourcesType", 2 + "");//登录方式
        params.put("areaId", "");
        params.put("sources", SOURCES_ANDROID+"");//android or ios
        httpGet(url,params,callback);
    }


    public static boolean checkUserInfo(String userid) {
        CacheDataManager cacheDataManager = CacheDataManager.getInstance();
        BasicUserInfoDBModel model = cacheDataManager.loadUser(userid);
        if (model == null) {
            throw new RuntimeException("not ");
        }

        return !(model.nickname == null || "".equals(model.nickname));
    }

    public void attachIM(LoginServerModel param){
        Intent i = IServiceHelper.getParcelableIntent(
                IServiceValues.ACTION_CMD_WAY,
                IServiceValues.CMD_LOGIN,
                TransactionValues.UI_2_SERVICE_KEY1,param);
        BroadCastHelper.sendBroadcast(mContext,i);
    }
}
