package com.baizhi.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述信息 (通用BaseApi 父类)
 *
 * @author : buxiaoyu
 * @date : 2019-03-10 15:13
 * @version: V_1.0.0
 */
public class BaseApiService {

    public Map<String, Object> setResultSuccess() {
        return setResult(BaseApiContents.RES_HTTP_CODE_200_VALUE, BaseApiContents.RES_HTTP_CODE_SUCCESS, null);
    }

    public Map<String, Object> setResultSuccessMsg(String msg) {
        return setResult(BaseApiContents.RES_HTTP_CODE_200_VALUE, msg, null);
    }

    public Map<String, Object> setResultSuccessData(Object data) {
        return setResult(BaseApiContents.RES_HTTP_CODE_200_VALUE, BaseApiContents.RES_HTTP_CODE_SUCCESS, data);
    }

    public Map<String, Object> setResultError() {
        return setResult(BaseApiContents.RES_HTTP_CODE_500_VALUE, BaseApiContents.RES_HTTP_CODE_ERROR, null);
    }

    public Map<String, Object> setResultErrorMsg(String msg) {
        return setResult(BaseApiContents.RES_HTTP_CODE_500_VALUE, msg, null);
    }


    /**
     * 自定义查询结果
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public Map<String, Object> setResult(Integer code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put(BaseApiContents.RES_HTTP_CODE_NAME, code);
        map.put(BaseApiContents.RES_HTTP_CODE_MSG, msg);
        map.put(BaseApiContents.RES_HTTP_CODE_DATA, data);
        return map;
    }
}
