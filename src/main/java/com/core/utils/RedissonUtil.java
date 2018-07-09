/**
 * Author:   shitian
 * Date:     2018/7/9 9:51
 * Description:
 */
package com.core.utils;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.io.IOException;
import java.net.URL;

/**
 * 〈〉
 *
 * @author shitian
 * @create 2018/7/9
 * @since 1.0.0
 */
public class RedissonUtil {

    public static RedissonClient getRedissonClient() {
        RedissonClient redisson = null;
        try {
//            URL url = RedissonUtil.class.getClassLoader().getResource("redisson.yml");
//            redisson = Redisson.create(Config.fromYAML(url.getFile()));
            Config config = new Config();
            config.setTransportMode(TransportMode.NIO);
            config.useSingleServer().setAddress("redis://10.10.13.14:6379").setDatabase(1).setPassword("123456");
            redisson = Redisson.create(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return redisson;
    }
}
