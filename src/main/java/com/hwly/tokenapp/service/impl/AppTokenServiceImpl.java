package com.hwly.tokenapp.service.impl;

import com.hwly.tokenapp.modal.AppTokenGetParam;
import com.hwly.tokenapp.service.AppTokenService;
import com.hwly.tokenapp.token.AppTokenManage;
import com.hwly.tokenapp.modal.Token;
import com.hwly.tokenapp.utils.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AppTokenServiceImpl implements AppTokenService {

    @Resource
    private AppTokenManage appTokenManage;
    @Resource
    private RedisUtil redisUtil;

//    public AppTokenServiceImpl() {
//        this.appTokenManage = new AppTokenManage();
//    }

    @Override
    public Token buildToken(String appKey, String random, String sign) throws Exception {
        //校验参数不能为空。
        String appSecret = validateApp(appKey);
        AppTokenGetParam appTokenGetParam = new AppTokenGetParam();
        appTokenGetParam.setAppKey(appKey);
        appTokenGetParam.setAppSecret(appSecret);
        appTokenGetParam.setRandom(random);
        appTokenGetParam.setSign(sign);
        Token token = appTokenManage.build(appTokenGetParam);
        return token;
    }

    /**
     * 校验appKey的合法性，同时返回这个app相关的一些属性，比如appSecret。
     *
     * @param appKey
     * @return
     */
    private String validateApp(String appKey) throws Exception {
        Map<String, String> appInfo = redisUtil.hgetall("appKey:" + appKey);
        if (appInfo != null && appInfo.size() > 0) {
            return appInfo.get("appSecret");
        } else {
            throw new Exception("appKey不存在");
        }
    }
}

