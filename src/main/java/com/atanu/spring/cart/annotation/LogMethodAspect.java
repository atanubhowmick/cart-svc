/**
 * 
 */
package com.atanu.spring.cart.annotation;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;

/**
 * https://www.yegor256.com/2014/06/01/aop-aspectj-java-method-logging.html
 * 
 * @author Atanu Bhowmick
 *
 */
@Aspect
@Component
public class LogMethodAspect {

	@Around("@within(LogMethodCall) || @annotation(LogMethodCall)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) point.getSignature();
		Method method = methodSignature.getMethod();
		LogMethodCall loggedMethod = method.getAnnotation(LogMethodCall.class);

		Class<?> targetClass = point.getTarget().getClass();
		LogMethodCall loggedClass = targetClass.getAnnotation(LogMethodCall.class);

		Level level = loggedMethod != null ? loggedMethod.logLevel() : loggedClass.logLevel();

		boolean showParams = loggedMethod != null ? loggedMethod.showParams() : loggedClass.showParams();
		boolean showResult = loggedMethod != null ? loggedMethod.showResult() : loggedClass.showResult();

		StringBuilder startBuilder = new StringBuilder(String.format("Enter : %s()", method.getName()));

		if (showParams) {
			int paramCount = method.getParameterCount();
			Object[] arguments = point.getArgs();
			if (paramCount > 0 && arguments != null && arguments.length > 0) {
				startBuilder.append(", Parameters: [");
				for (int i = 0; i < paramCount; i++) {
					startBuilder.append(method.getParameters()[i].getName());
					startBuilder.append(" : ");
					startBuilder.append(arguments[i]);
					if (i != arguments.length - 1) {
						startBuilder.append(", ");
					}
				}
				startBuilder.append("]");
			}
		}
		
		// Writing method enter with Parameters
		this.writeToLog(targetClass, level, startBuilder.toString());

		// Start time
		long startTime = System.currentTimeMillis();

		// Method execution started
		Object result = point.proceed();

		// End time
		long endTime = System.currentTimeMillis();

		// Method execution time
		long executionTime = endTime - startTime;

		StringBuilder endBuilder = new StringBuilder(
				String.format("Exit : %s() with execution time : %s ms", method.getName(), executionTime));
		if (showResult) {
			endBuilder.append(" and result : ");
			endBuilder.append(result);
		}
		
		// Writing method exit with Result
		this.writeToLog(targetClass, level, endBuilder.toString());

		return result;
	}

	/**
	 * Writing to Log
	 * 
	 * @param clazz
	 * @param level
	 * @param logString
	 */
	private void writeToLog(Class<?> clazz, Level level, String logString) {
		Logger log = LoggerFactory.getLogger(clazz);
		switch (level) {
		case TRACE:
			log.trace(logString);
			break;
		case DEBUG:
			log.debug(logString);
			break;
		case INFO:
			log.info(logString);
			break;
		case WARN:
			log.warn(logString);
			break;
		case ERROR:
			log.error(logString);
			break;
		default:
			log.debug(logString);
			break;
		}
	}
}
