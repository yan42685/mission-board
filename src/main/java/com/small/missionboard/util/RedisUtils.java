package com.small.missionboard.util;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

public class RedisUtils {
    // 静态注入bean
    private static RedisTemplate<String, String> redisTemplate = SpringContextUtils
            .getBean("redisTemplate", RedisTemplate.class);

    /**
     * 指定缓存失效时间 (秒)
     */
    public static Boolean expire(String key, Long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效 失效时间为负数，说明该主键未设置失效时间（失效时间默认为-1）
     */
    public static Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     */
    public static Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 默认获取字符串类型缓存
     */
    public static String get(String key) {
        return key == null ? null :
                JsonUtils.json2Bean(redisTemplate.opsForValue().get(key), String.class);
    }

    /**
     * 获取指定类型的缓存
     */
    public static <T> T get(String key, Class<T> objClass) {
        return key == null ? null :
                JsonUtils.json2Bean(redisTemplate.opsForValue().get(key), objClass);
    }

    /**
     * 以JSON格式放入缓存
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, JsonUtils.bean2Json(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 放入缓存并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public static boolean set(String key, Object value, Long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, JsonUtils.bean2Json(value), time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
