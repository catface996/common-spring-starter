package com.catface.common.api.mapping;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import com.catface.common.api.filter.BodyReaderHttpServletRequestWrapper;
import com.catface.common.util.JsonUtil;

/**
 * @author catface
 * @date 2020/3/6
 */
public class ParamMappingUtil {

    private static final Map<String, Map<String, String>> PARAM_MAPPING_MAP = new HashMap<>();

    public static void addMappingRelation(String url, String sourceParamName,
                                          String targetParamName) {
        Map<String, String> paramMap = PARAM_MAPPING_MAP.computeIfAbsent(url, k -> new HashMap<>(8));
        paramMap.put(sourceParamName, targetParamName);
    }

    private static Map<String, String> getTargetParamName(String url) {
        return PARAM_MAPPING_MAP.get(url);
    }

    public static BodyReaderHttpServletRequestWrapper mappingParam(HttpServletRequest request,
                                                                   JSONObject requestBody) {
        String url = request.getRequestURI();
        Map<String, String> paramMappingMap = getTargetParamName(url);
        if (paramMappingMap != null) {
            paramMappingMap.forEach(
                (sourceField, targetField) -> JsonUtil.overrideField(requestBody, sourceField, targetField));
        }
        return new BodyReaderHttpServletRequestWrapper(request, requestBody);
    }

}
