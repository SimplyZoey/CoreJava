/**
 * Author:   shitian
 * Date:     2018/7/3 15:53
 * Description:
 */
package com.core.examination;

/**
 * 〈已知每台服务器的最大QPS,服务器的节点数量,每台服务器的响应时间(ms),每台服务器支持的线程数,计算给定量请求任务需要时间(ms)〉
 *
 * @author shitian
 * @create 2018/7/3
 * @since 1.0.0
 */
public class MaxQPS {

    public static void main(String[] args){
        long result = doTime(200,new int[]{1,1,1,10,10},5000,10);
        System.out.println(result);
    }


    /**
     * @param maxQPS     每台服务器的极限qps
     * @param rtList     每台服务器的响应时间（ms）
     * @param requestNum 面临的请求量
     * @param threads    每台服务器的线程池线程数量(并发数)
     *                   qps = threads/rt
     * @return
     */
    private static long doTime(int maxQPS, int[] rtList, int requestNum, int threads) {
        //整个集群的qps总和，近似吞吐量
        int qpsSum = 0;
        for (int temp : rtList) {
            //rt为ms
            int singleQps = threads * 1000 / temp;
            if (singleQps > maxQPS) {
                qpsSum += maxQPS;
            } else {
                //某台服务器达不到极限qps
                qpsSum += singleQps;
            }
        }
        //返回结果是ms，所以需要乘以1000
        return requestNum / qpsSum * 1000;
    }
}
