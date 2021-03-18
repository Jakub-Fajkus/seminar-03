package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Jakub Fajkus
 */
public class MainAnnotations {
    //Task 06 Add JSR-330 annotations to ExchangeRateTableImpl and
    //CurrencyConvertorImpl components. Create MainAnnotations class
    //in package cz.muni.fi.pa165 with main method which will create Spring
    //ApplicationContext based on these annotations.
    //Then get instance of CurrencyConvertor and try convert one euro to czk. Test, if
    //the main method is working well.
    private static final Currency CZK = Currency.getInstance("CZK");
    private static final Currency EUR = Currency.getInstance("EUR");

    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext("cz.muni.fi.pa165");

        CurrencyConvertor convertor = applicationContext.getBean("currencyConvertorImpl", CurrencyConvertor.class);
        System.out.println("Annotations " + convertor.convert(EUR, CZK, new BigDecimal("1")));
    }
}
