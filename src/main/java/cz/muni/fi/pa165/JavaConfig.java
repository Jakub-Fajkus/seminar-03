package cz.muni.fi.pa165;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//Exception in thread "main" org.springframework.beans.factory.BeanCreationException:
// Error creating bean with name 'currencyConvertorImpl' defined in cz.muni.fi.pa165.JavaConfig:
// Bean instantiation via factory method failed; nested exception is
// org.springframework.beans.BeanInstantiationException: Failed to instantiate
// [cz.muni.fi.pa165.currency.CurrencyConvertorImpl]: Factory method 'currencyConvertorImpl'
// threw exception; nested exception is java.lang.IllegalStateException: @Bean method
// JavaConfig.exchangeRateTableImpl called as bean reference for type
// [cz.muni.fi.pa165.currency.ExchangeRateTableImpl] but overridden by non-compatible bean instance of
// type [com.sun.proxy.$Proxy21]. Overriding bean of same name declared in: cz.muni.fi.pa165.JavaConfig

/**
 * @author Jakub Fajkus
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {StopwatchAspect.class})
public class JavaConfig {
    //Task 07 Create JavaConfig Spring application context which will configure
    //components ExchangeRateTableImpl and CurrencyConvertorImpl. Create MainJavaConfig
    //class in package cz.muni.fi.pa165 with main method which will create Spring
    //ApplicationContext based on this JavaConfig application context configuration.
    //Then get instance of CurrencyConvertor and try convert one euro to czk. Test, if
    //the main method is working well.
    //Warning: You may unknowingly create two instances of CurrencyConvertor if you
    //both have the method annotated with @Bean and enabled scanning of components which finds the class annotated with @Named. In that case remove the scanning of
    //components, or specify the bean name as the first parameter of the getBean() method.


//    @Bean
//    public ExchangeRateTableImpl exchangeRateTableImpl() {
//        return new ExchangeRateTableImpl();
//    }

//    @Bean
//    public CurrencyConvertorImpl currencyConvertorImpl() {
//        return new CurrencyConvertorImpl(exchangeRateTableImpl());
//    }

}
