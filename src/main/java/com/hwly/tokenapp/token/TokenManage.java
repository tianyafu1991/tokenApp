package com.hwly.tokenapp.token;

import com.hwly.tokenapp.modal.Token;

/**
 * @Description token管理对象，负责管理token的生命周期，负责验签，构建以及校验。
 **/
public interface TokenManage<T,R> {
    /**
     * 构建token。
     * @param getTokenParam 这个是用于构建token的参数，对应不同类型的token，会有不同的参数，具体的由实现类进行指定。
     * @return 返回构建的token。
     * @throws Exception 验签失败的话会抛出异常。
     */
    public Token build(T getTokenParam) throws Exception;

    /**
     *token校验。同时会返回这个token对应的业务参数。
     * @param token 用户输入的token值。
     * @return  返回值是token对应的业务参数。
     */
    public R validate(String token);
}

