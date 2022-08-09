package com.catface.common.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author catface
 * @date 2020/3/6
 */
public class JsonUtil {

    public static JSONObject overrideField(JSONObject jsonObject, String sourceFileName,
                                           String targetFieldName) {
        String[] sourceParamArray = sourceFileName.split("\\.");
        String[] targetParamArray = targetFieldName.split("\\.");
        String finalSourceFieldName = sourceParamArray[sourceParamArray.length - 1];
        String finalTargetFieldName = targetParamArray[targetParamArray.length - 1];
        JSONObject sourceParamJsonObject = jsonObject;
        for (int i = 0; i < sourceParamArray.length - 1; i++) {
            sourceParamJsonObject = sourceParamJsonObject.getJSONObject(sourceParamArray[i]);
        }
        if (sourceParamJsonObject == null) {
            return jsonObject;
        }
        JSONObject targetParamJsonObject = jsonObject;
        for (int i = 0; i < targetParamArray.length - 1; i++) {
            JSONObject tempTargetObject = targetParamJsonObject.getJSONObject(targetParamArray[i]);
            if (tempTargetObject == null) {
                tempTargetObject = new JSONObject();
                targetParamJsonObject.put(targetParamArray[i], tempTargetObject);
            }
            targetParamJsonObject = tempTargetObject;
        }

        Object sourceValue = sourceParamJsonObject.get(finalSourceFieldName);
        targetParamJsonObject.put(finalTargetFieldName, sourceValue);
        return jsonObject;
    }

}
