/**
 * Author:   shitian
 * Date:     2018/5/17 10:52
 * Description:
 */
package com.core.classload;

import java.io.File;
import java.net.*;

/**
 * 〈URLClassLoader：可以加载任何目录下的class〉
 *
 * @author shitian
 * @create 2018/5/17
 * @since 1.0.0
 */
public class MyURLClassLoader extends URLClassLoader {


    public MyURLClassLoader(URL[] urls) {
        super(urls);
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        //url类加载器可以加载任何目录下的类文件
        String rootDir= Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //创建自定义文件类加载器
        File file = new File(rootDir);
        //File to URI
        URI uri=file.toURI();
        URL[] urls={uri.toURL()};

        MyURLClassLoader loader = new MyURLClassLoader(urls);

        try {
            //加载指定的class文件
            Class<?> object1=loader.loadClass("com.core.bean.DemoObj");
            System.out.println(object1.newInstance().toString());

            //输出结果:I am Obj
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
