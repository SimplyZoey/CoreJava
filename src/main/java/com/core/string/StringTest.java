/**
 * Author:   shitian
 * Date:     2018/5/24 9:26
 * Description: String不可变
 */
package com.core.string;

/**
 * 〈String不可变〉
 *
 * @author shitian
 * @create 2018/5/24
 * @since 1.0.0
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = new String("a");
        System.out.println(s1==s2);
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3==s4);
    }

}
