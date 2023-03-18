package com.hwly.tokenapp.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class Token {
    //生成时间,ms时间戳
    private long generateTime;
    //过期时间,ms时间戳
    private long expireTime;
    //有效期，ms
    private long expireIn;
    //令牌
    private String accessToken;

    public static Token createToken(long expireIn) {
        Token token = new Token();
        token.setAccessToken(UUID.randomUUID().toString().replace("-", ""));
        token.setGenerateTime(System.currentTimeMillis());
        token.setExpireIn(expireIn);
        token.setExpireTime(token.getGenerateTime() + expireIn);
        return token;
    }
}
