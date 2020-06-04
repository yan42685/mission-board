package com.small.missionboard.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

public class BeanUtils {
    public static void copyProperties(Object source, Object target) {
        // 禁止source的null字段覆盖target的字段
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true));
    }
}
