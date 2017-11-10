package com.harry.web.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 统一返回结果工具类
 * Created by chenhaibo on 2017/11/9.
 */
public class ApiRestfulResponse {


    private static final ObjectMapper MAPPER=new ObjectMapper();

    public static ApiResult build(Integer code, String info, Object data) {
        return new ApiResult(code, info, data);
    }


    public static ApiResult success(Object data) {

        ApiResult<Object> result = new ApiResult<>();
        result.setCode(ApiResultCode.C200.getCode());
        result.setData(data);
        return result;
    }

    public static ApiResult success(Object data, String info) {

        ApiResult<Object> result = new ApiResult<>();
        result.setCode(ApiResultCode.C200.getCode());
        result.setInfo(info);
        result.setData(data);
        return result;
    }

    public static ApiResult error(ApiResultCode code, String info) {

        ApiResult<Object> result = new ApiResult<>();
        result.setCode(code.getCode());
        result.setInfo(info);
        return result;
    }

    public static ApiResult error(ApiResultCode code) {

        ApiResult<Object> result = new ApiResult<>();
        result.setCode(code.getCode());
        result.setInfo(code.getDesc());
        return result;
    }

    /**
     * 将json结果集转化为ApiResult对象
     *
     * @param jsonData json数据
     * @param clazz ApiResult中的object类型
     * @return
     */
    public static ApiResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ApiResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("info").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ApiResult format(String json) {
        try {
            return MAPPER.readValue(json, ApiResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ApiResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("code").intValue(), jsonNode.get("info").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
