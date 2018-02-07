package com.lebaoxun.soa.core.redis.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author caiqianyi
 * @date 2018年1月15日 下午13:10:36
 * @version 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

    /** 锁的资源，redis的key*/
    String value() default "default";
    
    /** 持锁时间,单位毫秒*/
    long keepMills() default 30000;
    
    /** 当获取失败时候动作*/
    LockFailAction action() default LockFailAction.CONTINUE;
    
    public enum LockFailAction{
        /** 放弃 */
        GIVEUP,
        /** 继续 */
        CONTINUE;
    }
    
    /** 重试的间隔时间,设置GIVEUP忽略此项*/
    long sleepMills() default 200;
    
    /** 重试次数*/
    int retryTimes() default 5;
}