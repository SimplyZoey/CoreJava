/**
 * Author:   shitian
 * Date:     2018/7/3 14:08
 * Description:
 */
package com.core.examination;

/**
 * 〈〉
 *
 * @author shitian
 * @create 2018/7/3
 * @since 1.0.0
 */
public class DrinkActivity {

    public static void main(String[] args){
        int num = maxDrinking(3,4,2,30);
        System.out.println(num);
    }

    /**
     *
     * @param price 单价
     * @param cap   瓶盖兑换比
     * @param emptyBottle   空瓶兑换比
     * @param money 总金额
     * @return
     */
    private static int maxDrinking(int price,int cap,int emptyBottle,int money){
        int count = money/price;
        int temp_cap = count;
        int temp_bottle = count;
        int  remainder_cap;
        int remainder_bottle;
        while(temp_cap >= cap || temp_bottle >= emptyBottle){
            //兑换后剩余瓶盖
            remainder_cap = temp_cap%cap;
            //瓶盖兑换的瓶数
            temp_cap = temp_cap/cap;
            //总数加上兑换的瓶数
            count += temp_cap;
            //空瓶数量增加
            temp_bottle += temp_cap;
            //剩余的所有瓶盖数
            temp_cap += remainder_cap;

            //兑换后剩余的空瓶
            remainder_bottle = temp_bottle%emptyBottle;
            //空瓶兑换的数量
            temp_bottle = temp_bottle/emptyBottle;
            //总数加上空瓶兑换数量
            count += temp_bottle;
            //剩余的瓶盖数增加
            temp_cap += temp_bottle;
            //剩余的空瓶数要增加
            temp_bottle += remainder_bottle;
        }
        return count;
    }

}
