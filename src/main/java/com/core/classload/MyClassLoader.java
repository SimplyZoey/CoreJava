/**
 * Author:   shitian
 * Date:     2018/5/17 10:13
 * Description: 自定义类加载器
 */
package com.core.classload;

import java.io.*;

/**
 * 〈自定义类加载器：只能加载classpath下的类〉
 *
 * @author shitian
 * @create 2018/5/17
 * @since 1.0.0
 */
public class MyClassLoader extends ClassLoader {
    private String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();


    /**
     * 类加载机制
     * 1.调用ClassLoader中的loadClass方法
     * 2.调用findLoadedClass，判断类是否已经加载，是则返回
     * 3.调用父加载器来尝试加载该类，成功则返回
     * 4.父类无法加载，那就只能自己调用findClass来加载
     *
     * 为了避免破坏类加载器的代理模式，强烈建议重写findClass，而不是loadClass
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 获取类的class文件字节数组
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            //直接生成class对象
            return defineClass(name, classData, 0, classData.length);
        }
    }

    /**
     * 编写获取class文件并转换为字节码流的逻辑
     *
     * @param className
     * @return
     */
    private byte[] getClassData(String className) {
        // 读取类文件的字节
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            // 读取类文件的字节码
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据类名获取类的class文件的绝对路径
     *
     * @param className
     * @return
     */
    private String classNameToPath(String className) {
        path = path + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
        return path;
    }


    public static void main(String[] args) throws Exception {
        MyClassLoader loader = new MyClassLoader();
        MyClassLoader loader1 = new MyClassLoader();
        //已经加载过就不会加载了
        Class<?> object1 = loader.loadClass("com.core.bean.DemoObj");
        Class<?> object2 = loader1.loadClass("com.core.bean.DemoObj");
        System.out.println("object1:" + object1.hashCode());
        System.out.println("object2:" + object2.hashCode());

        //绕过缓存检查，直接创建类
        Class<?> object3 = loader.findClass("com.core.bean.DemoObj");
        Class<?> object4 = loader1.findClass("com.core.bean.DemoObj");
        System.out.println("object3:" + object3.hashCode());
        System.out.println("object4:" + object4.hashCode());
    }
}
