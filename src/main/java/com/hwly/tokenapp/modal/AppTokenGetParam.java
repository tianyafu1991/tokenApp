package com.hwly.tokenapp.modal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppTokenGetParam {
    private String appKey;
    private String random;
    private String sign;
    private String appSecret;
}
