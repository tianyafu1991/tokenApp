package com.hwly.tokenapp.service;

import com.hwly.tokenapp.modal.Token;

public interface AppTokenService {

    Token buildToken(String appKey, String random, String sign) throws Exception;
}
