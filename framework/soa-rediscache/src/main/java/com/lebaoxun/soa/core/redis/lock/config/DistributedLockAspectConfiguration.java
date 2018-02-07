package com.lebaoxun.soa.core.redis.lock.config;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.soa.core.redis.lock.DistributedLock;
import com.lebaoxun.soa.core.redis.lock.RedisLock;
import com.lebaoxun.soa.core.redis.lock.RedisLock.LockFailAction;

/**
 * @author caiqianyi
 * @date 2017年6月14日 下午3:11:22
 * @version 1.0.0
 */
@Aspect
@Configuration
@ConditionalOnClass(DistributedLock.class)
@AutoConfigureAfter(DistributedLockAutoConfiguration.class)
public class DistributedLockAspectConfiguration {
    
    private final Logger logger = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);
    
    @Autowired
    private DistributedLock distributedLock;

    @Pointcut("@annotation(com.lebaoxun.soa.core.redis.lock.RedisLock)")
    private void lockPoint(){
        
    }
    
    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String key = redisLock.value();
        if(StringUtils.isEmpty(key)){
            Object[] args = pjp.getArgs();
            key = Arrays.toString(args);
        }else{
        	Object[] args = pjp.getArgs();
        	for(int i=0;i<args.length;i++){
        		Object arg = args[i];
        		if(arg != null){
        			String v = "#arg"+i;
        			key = key.replaceAll(v, arg.toString());
        		}
        	}
        }
        int retryTimes = redisLock.action().equals(LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;
        boolean lock = distributedLock.lock(key, redisLock.keepMills(), retryTimes, redisLock.sleepMills());
        if(!lock) {
            logger.error("get lock failed : " + key);
            throw new I18nMessageException("10016");
        }
        
        //得到锁,执行方法，释放锁
        logger.debug("get lock success : " + key);
        try {
            return pjp.proceed();
        } finally {
            boolean releaseResult = distributedLock.releaseLock(key);
            logger.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
        }
    }
}