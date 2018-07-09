/**
 * Author:   shitian
 * Date:     2018/7/9 9:34
 * Description:
 */
package com.core.redission;

import com.core.utils.RedissonUtil;
import org.redisson.Redisson;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 〈〉
 *
 * @author shitian
 * @create 2018/7/9
 * @since 1.0.0
 */
public class CountDownLatchTest {
    private static RedissonClient client = RedissonUtil.getRedissonClient();

    public static void main(String[] args) throws Exception {
        RCountDownLatch latch = client.getCountDownLatch("COUNT_DOWN_LATCH_LOCK");
        latch.trySetCount(1);
        Thread t = new Thread(new CountDownLatchThread(client));
        t.start();
        latch.await();
        System.out.println("Main:闭锁结束");
        client.shutdown();
    }

}

class CountDownLatchThread implements Runnable {
    private RCountDownLatch latch;

    public CountDownLatchThread(RedissonClient client) {
        this.latch = client.getCountDownLatch("COUNT_DOWN_LATCH_LOCK");
    }

    @Override
    public void run() {
        try {
            System.out.println("==========sleep 3s==============");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("========================" + latch.getCount());
    }
}
