/**
 * Author:   shitian
 * Date:     2018/7/3 13:51
 * Description:
 */
package com.core.lock;

/**
 * 〈〉
 *
 * @author shitian
 * @create 2018/7/3
 * @since 1.0.0
 */
public class MyLock {
    private static volatile int state = 0;
    Thread lockThread;

    public void lock(){
        while(true){
            if(state == 0){
                synchronized (MyLock.class){
                    if(state == 0){
                        state++;
                        lockThread = Thread.currentThread();
                        break;
                    }
                }
            }
        }
    }

    public void unLock(){
        if(Thread.currentThread() == lockThread){
            state--;
        }
    }

    public boolean tryLock(){
        if(state == 0){
            lock();
            return true;
        }
        return false;
    }
}
