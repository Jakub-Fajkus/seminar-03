package cz.muni.fi.pa165.currency;

import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

/**
 * @author Jakub Fajkus
 */
@Named
public class ExchangeRateTableImpl implements ExchangeRateTable {

    //Task 03 Create class ExchangeRateTableImpl in package cz.muni.fi.pa165.currency.
    //This class will implement ExchangeRateTable interface, method getExchangeRate
    //will return fixed exchange rate 27 for conversion from EUR to CZK and empty value for
    //any other currencies.


    @Override
    public Optional<BigDecimal> getExchangeRate(Currency sourceCurrency, Currency targetCurrency) throws ExternalServiceFailureException {
        if (sourceCurrency.getCurrencyCode().equals("EUR") && targetCurrency.getCurrencyCode().equals("CZK")) {
            return Optional.of(new BigDecimal("27"));
        }

        return Optional.empty();
    }
}
