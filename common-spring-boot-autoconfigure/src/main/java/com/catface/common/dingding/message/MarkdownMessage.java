package com.catface.common.dingding.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * @author catface
 * @date 2020-08-01
 */
public class MarkdownMessage implements Message {

    private static final Integer HEADER_TYPE_MAX = 6;

    private static final Integer HEADER_TYPE_MIN = 1;

    private String title;

    private List<String> items = new ArrayList<>();

    private List<String> mobiles = new ArrayList<>();

    private Boolean atAll = false;

    public String getTitle() {
        return title;
    }

    public List<String> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<String> mobiles) {
        this.mobiles = mobiles;
    }

    public Boolean getAtAll() {
        return atAll;
    }

    public void setAtAll(Boolean atAll) {
        this.atAll = atAll;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void add(String text) {
        items.add(text);
    }

    public static String getBoldText(String text) {
        return "**" + text + "**";
    }

    public static String getItalicText(String text) {
        return "*" + text + "*";
    }

    public static String getLinkText(String text, String href) {
        return "[" + text + "](" + href + ")";
    }

    public static String getImageText(String imageUrl) {
        return "![image](" + imageUrl + ")";
    }

    public static String getHeaderText(int headerType, String text) {
        if (headerType < HEADER_TYPE_MIN || headerType > HEADER_TYPE_MAX) {
            throw new IllegalArgumentException("headerType should be in [1, 6]");
        }

        StringBuilder numbers = new StringBuilder();
        for (int i = 0; i < headerType; i++) {
            numbers.append("#");
        }
        return numbers + " " + text;
    }

    public static String getReferenceText(String text) {
        return "> " + text;
    }

    public static String getOrderListText(List<String> orderItem) {
        if (orderItem.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= orderItem.size() - 1; i++) {
            sb.append(String.valueOf(i)).append(". ").append(orderItem.get(i - 1)).append("\n");
        }
        sb.append(String.valueOf(orderItem.size())).append(". ").append(orderItem.get(orderItem.size() - 1));
        return sb.toString();
    }

    public static String getUnorderListText(List<String> unorderItem) {
        if (unorderItem.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < unorderItem.size() - 1; i++) {
            sb.append("- ").append(unorderItem.get(i)).append("\n");
        }
        sb.append("- ").append(unorderItem.get(unorderItem.size() - 1));
        return sb.toString();
    }

    @Override
    public String toJsonString() {
        Map<String, Object> result = new HashMap<>(3);
        result.put("msgtype", "markdown");

        Map<String, Object> markdown = new HashMap<>(2);
        markdown.put("title", title);

        Map<String, Object> at = new HashMap<>(3);
        at.put("atMobiles", mobiles);
        at.put("isAtAll", atAll);
        result.put("at", at);

        StringBuilder markdownText = new StringBuilder();
        for (String item : items) {
            markdownText.append(item).append("\n");
        }
        if (getMobiles() != null && getMobiles().size() > 0) {
            StringBuilder atMobiles = new StringBuilder();
            for (String mobile : mobiles) {
                atMobiles.append("@").append(mobile);
            }
            markdownText.append("- ").append(atMobiles.toString()).append("\n");
        }
        markdown.put("text", markdownText.toString());
        result.put("markdown", markdown);

        return JSON.toJSONString(result);
    }
}
