package com.hwly.tokenapp.controller;


import com.alibaba.fastjson.JSONObject;
import com.hwly.tokenapp.modal.AppParam;
import com.hwly.tokenapp.modal.Token;
import com.hwly.tokenapp.response.BaseResponse;
import com.hwly.tokenapp.service.AppTokenService;
import com.hwly.tokenapp.token.AppTokenManage;
import com.hwly.tokenapp.token.RedisTokenDao;
import com.hwly.tokenapp.utils.RedisUtil;
import com.hwly.tokenapp.utils.SHA256Util;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Resource
    private AppTokenService appTokenService;
    @Resource
    private AppTokenManage appTokenManage;
    @Resource
    private RedisUtil redisUtil;



    @GetMapping("/getToken")
    public BaseResponse getToken() {
        String appKey = "1a748b70d0cb4a8a8e37e959f4a4f1e6";
        String appSecret = "0c128efdc2ef45c1a7be39462701e65b";

        String random = UUID.randomUUID().toString();
        String sign = SHA256Util.sign(appKey + random, appSecret);
        try {
            Token token = appTokenService.buildToken(appKey, random, sign);
            String accessToken = token.getAccessToken();
            return BaseResponse.successInstance(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BaseResponse.errorInstance("错误!!!!!!!!!");
    }

    /**
     * hset appKey:1a748b70d0cb4a8a8e37e959f4a4f1e6 appKey 1a748b70d0cb4a8a8e37e959f4a4f1e6
     * hset appKey:1a748b70d0cb4a8a8e37e959f4a4f1e6 appSecret 0c128efdc2ef45c1a7be39462701e65b
     * @return
     */
    @PostMapping("/setToken")
    public BaseResponse setToken() {
        String appKey = "1a748b70d0cb4a8a8e37e959f4a4f1e6";
        String appSecret = "0c128efdc2ef45c1a7be39462701e65b";
        String key = "appKey:" + appKey;
        redisUtil.hset(key,"appKey",appKey);
        redisUtil.hset(key,"appSecret",appSecret);

        return BaseResponse.successInstance("成功");
    }



    @GetMapping("/businssCall")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token",defaultValue = "5e5bfb92233f490fb23b144463d841b3"),
    })
    public BaseResponse businssCall(String token){
//        String accessToken="9a07e90961a94e11b5ab110b9d1b7905";

        AppParam appParam = appTokenManage.validate(token);
        String s = JSONObject.toJSONString(appParam);
        return BaseResponse.successInstance(s);
    }
}
