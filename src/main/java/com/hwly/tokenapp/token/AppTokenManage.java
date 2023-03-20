package com.hwly.tokenapp.token;

import com.alibaba.fastjson.JSONObject;
import com.hwly.tokenapp.constant.TokenConstant;
import com.hwly.tokenapp.modal.AppParam;
import com.hwly.tokenapp.modal.AppTokenGetParam;
import com.hwly.tokenapp.modal.Token;
import com.hwly.tokenapp.token.RedisTokenDao;
import com.hwly.tokenapp.token.TokenDao;
import com.hwly.tokenapp.token.TokenManage;
import com.hwly.tokenapp.utils.SHA256Util;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description 应用token管理对象
 **/
@Component
public class AppTokenManage implements TokenManage<AppTokenGetParam, AppParam> {


    @Resource
    private TokenDao tokenDao;


    @Override
    public Token build(AppTokenGetParam getTokenParam) throws Exception {
        signValidate(getTokenParam);
        String key=getSaveKey(getTokenParam);
        AppParam param=new AppParam();
        param.setAppKey(getTokenParam.getAppKey());
        return tokenDao.buildOrQuery(key,(Map<String,String>) JSONObject.toJSON(param));
    }

    @Override
    public AppParam validate(String token) {
        Map<String,String> saveValues=tokenDao.tokenQuery(token);
        if (saveValues!=null&&saveValues.size()>0){
            return JSONObject.parseObject(JSONObject.toJSONString(saveValues),AppParam.class);
        }
        return null;
    }

    private void signValidate(AppTokenGetParam getTokenParam) throws Exception {
        String sign= SHA256Util.sign(getTokenParam.getAppKey()+getTokenParam.getRandom(),getTokenParam.getAppSecret());
        if (!sign.equals(getTokenParam.getSign())){
            throw new Exception("签名验证失败");
        }
    }

    private String getSaveKey(AppTokenGetParam getTokenParam){
        return TokenConstant.ACCESS_TOKEN_SUFFIX +getTokenParam.getAppKey();
    }
}