package cz.muni.fi.pa165;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import javax.inject.Named;


/**
 * @author Jakub Fajkus
 */
@Named
@Aspect
public class StopwatchAspect {
    //Task 09 Create and configure aspect which will print duration of each
    //public method call. Use example from lecture for inspiration (see LoggingAspect
    //and SpringJavaConfig classes). Don't forget to enable automatic aspectj proxy

//        @Around("execution(public * *(..))")
    @Around("execution(public * cz.muni.fi.pa165.currency.*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();

        System.err.println("Calling method: "
                + joinPoint.getSignature());

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        System.err.println("Method finished: "
                + joinPoint.getSignature() + " with time: " + stopWatch.getLastTaskTimeNanos());


        return result;
    }
    //creation (with @EnableAspectJAutoProxy annotation in JavaConfig class.
}
