/**
 * Author:   shitian
 * Date:     2018/7/3 14:42
 * Description:
 */
package com.core.examination;

import java.util.Random;

/**
 * 〈求冰田个数，如果两个小块之间能够在不穿越其他块的情况下连成直线，则属于同一冰田区块〉
 *
 * @author shitian
 * @create 2018/7/3
 * @since 1.0.0
 */
public class KameArea {

    public static void main(String[] args){
        generatorKameArea(5,5);
    }

    /**
     * 随机生成冰田
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return
     */
    private static int[][] generatorKameArea(int x, int y) {
        Random random = new Random();
        int[][] result = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result[i][j] = random.nextInt(2);
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
        return result;
    }

}
