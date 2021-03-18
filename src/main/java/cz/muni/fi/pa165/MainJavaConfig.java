package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Jakub Fajkus
 */
public class MainJavaConfig {
    //Task 07 Create JavaConfig Spring application context which will configure
    //components ExchangeRateTableImpl and CurrencyConvertorImpl. Create MainJavaConfig
    //class in package cz.muni.fi.pa165 with main method which will create Spring
    //ApplicationContext based on this JavaConfig application context configuration.
    //Then get instance of CurrencyConvertor and try convert one euro to czk. Test, if
    //the main method is working well.
    //Warning: You may unknowingly create two instances of CurrencyConvertor if you
    //both have the method annotated with @Bean and enabled scanning of components which finds the class annotated with @Named. In that case remove the scanning of
    //components, or specify the bean name as the first parameter of the getBean() method.

    private static final Currency CZK = Currency.getInstance("CZK");
    private static final Currency EUR = Currency.getInstance("EUR");

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(JavaConfig.class);

        CurrencyConvertor convertor = applicationContext.getBean("currencyConvertorImpl", CurrencyConvertor.class);
        System.out.println("Config " + convertor.convert(EUR, CZK, new BigDecimal("1")));
    }
}
