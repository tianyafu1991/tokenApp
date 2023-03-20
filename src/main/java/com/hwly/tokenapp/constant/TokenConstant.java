package com.hwly.tokenapp.constant;

import java.io.Serializable;

public class TokenConstant implements Serializable {

    public static final String APP_KEY = "appKey";
    public static final String APP_SECRET = "appSecret";


    public static final String GENERATE_TIME = "generateTime";
    public static final String EXPIRE_TIME = "expireTime";
    public static final String EXPIRE_IN = "expireIn";
    public static final String ACCESS_TOKEN = "accessToken";

    public static final String ACCESS_TOKEN_SUFFIX = "appKey-token:";
    public static final String TOKEN_SUFFIX = "token:";
    public static final String APP_KEY_SUFFIX = "appKey:";

    public static Long EXPIRE_IN_TIME_SECOND = 2 * 3600L;


}
