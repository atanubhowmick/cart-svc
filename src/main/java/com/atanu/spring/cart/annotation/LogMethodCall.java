/**
 * 
 */
package com.atanu.spring.cart.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.slf4j.event.Level;

/**
 * Custom Annotation for method call logging
 * <br>
 * https://www.yegor256.com/2014/06/01/aop-aspectj-java-method-logging.html
 * 
 * @author Atanu Bhowmick
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface LogMethodCall {

	Level logLevel() default Level.DEBUG;

	boolean showParams() default false;

	boolean showResult() default true;
}
