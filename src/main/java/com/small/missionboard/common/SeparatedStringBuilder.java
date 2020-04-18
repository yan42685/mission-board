package com.small.missionboard.common;

import com.small.missionboard.enums.StringEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 处理用分隔符分隔多个属性的字符串
 */
public class SeparatedStringBuilder {
    /**
     * 分隔符
     */
    protected static final String SEPARATOR = ",";

    protected List<String> stringList;

    public SeparatedStringBuilder(String statusString) {
        stringList = new ArrayList<>(Arrays.asList(statusString.split(SEPARATOR)));
    }

    /**
     * 判断字符串是否是否存在
     */
    public Boolean has(StringEnum target) {
        return stringList.contains(target.getValue());
    }

    /**
     * 添加字符串
     */
    public SeparatedStringBuilder add(StringEnum target) {
        if (!stringList.contains(target.getValue())) {
            stringList.add(target.getValue());
        }
        return this;
    }

    /**
     * 移除字符串
     */
    public SeparatedStringBuilder remove(StringEnum target) {
        stringList.remove(target.getValue());
        return this;
    }

    /**
     * 清除全部全部字符串并添加字符串
     */
    public SeparatedStringBuilder clearAllAndAdd(StringEnum target) {
        stringList.clear();
        stringList.add(target.getValue());
        return this;
    }

    public String build() {
        return StringUtils.join(stringList, SEPARATOR);
    }

}
