//package com.goldcow.sframe.util.aspect;
//
//import java.lang.reflect.Method;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.goldcow.platform.syscontext.util.MD5Util;
//import com.goldcow.sframe.util.JsonUtil;
//import com.goldcow.sframe.util.cache.IDataCache;
//
///**
// * 
// * ClassName: DataCacheAspect <br/>   
// * Function:  * 用于处理缓存的切片，切片的处理点是系统中使用了自定义注解的所有方法
// *              方式是使用“类名+方法名+参数的json串”组成的字符串的md5值作为key，函数结果作为value，存入缓存<br/>   
// * date: 2014年5月7日 上午11:28:32 <br/>   
// *   
// * @author Administrator   
// * @version    
// * @since JDK 1.6
// */
//@Aspect
//@Component
//public class DataCacheAspect {
//	@Autowired
//	private IDataCache dataEhcache;
//	private static Logger log = LoggerFactory.getLogger(DataCacheAspect.class);
//	
//	@Around(value="execution(@com.goldcow.sframe.util.annotation.ShortDataCacheAnnotation * *(..))")
//	public Object doShortCache(ProceedingJoinPoint joinPoint) throws Throwable{
//		return realCache(joinPoint, "short");
//	}
//	
//	@Around(value="execution(@com.goldcow.sframe.util.annotation.MediumDataCacheAnnotation * *(..))")
//	public Object doMediumCache(ProceedingJoinPoint joinPoint) throws Throwable{
//		return realCache(joinPoint, "medium");
//	}
//	
//	@Around(value="execution(@com.goldcow.sframe.util.annotation.LongDataCacheAnnotation * *(..))")
//	public Object doLongCache(ProceedingJoinPoint joinPoint) throws Throwable{
//		return realCache(joinPoint, "long");
//	}
//	
//	private Object realCache(ProceedingJoinPoint joinPoint, String cacheName)
//			throws Throwable {
//		log.debug("===CALL THE DATA CACHE ASPECT===");
//		Object result = null;
//		Signature signature = joinPoint.getSignature();
//		String clazzName = signature.getDeclaringTypeName();
//		String methodName = signature.getName();
//		Object[] args = joinPoint.getArgs();
//		String argsJson = JsonUtil.allToJson(args);
//
//		StringBuilder sb = new StringBuilder();
//		sb.append(clazzName).append("##");
//		sb.append(methodName).append("##");
//		sb.append(argsJson);
//
//		String key = MD5Util.get32BitMd5EncString(sb.toString());
//
//		result = dataEhcache.getCacheValue(key, cacheName);
//		if (result != null) {
//			Class[] paramType = new Class[args.length];
//			for (int i = 0; i < paramType.length; i++) {
//				paramType[i] = args[i].getClass();
//			}
//			Method method = signature.getDeclaringType().getMethod(methodName, paramType);
//			Class returnClazz = method.getReturnType();
//
//			log.debug("===GET VALUE FROM DATA CACHE===");
//			return returnClazz.cast(result);
//		}
//
//		result = joinPoint.proceed(); // continue on the intercepted method
//		dataEhcache.putCacheValue(key, result, cacheName);
//
//		return result;
//	}
//}
