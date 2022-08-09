package com.catface.common.dingding.message;

/**
 * @author catface
 * @date 2020-08-01
 */
public interface Message {

    /**
     * 返回消息的Json格式字符串
     *
     * @return 消息的Json格式字符串
     */
    String toJsonString();

}
