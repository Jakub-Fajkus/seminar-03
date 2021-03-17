package cz.muni.fi.pa165.currency;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CurrencyConvertorImplTest {

    private static Currency CZK = Currency.getInstance("CZK");
    private static Currency EUR = Currency.getInstance("EUR");

    @Mock
    private ExchangeRateTable exchangeRateTable;

    private CurrencyConvertor currencyConvertor;

    @BeforeEach
    public void setup() {
        currencyConvertor = new CurrencyConvertorImpl(exchangeRateTable);
    }


    @Test
    public void testConvert() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(EUR, CZK))
                .thenReturn(Optional.of(new BigDecimal("0.1")));

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(currencyConvertor.convert(EUR, CZK, new BigDecimal("10.050")))
                    .isEqualTo(new BigDecimal("1.00"));
            softly.assertThat(currencyConvertor.convert(EUR, CZK, new BigDecimal("10.051")))
                    .isEqualTo(new BigDecimal("1.01"));
            softly.assertThat(currencyConvertor.convert(EUR, CZK, new BigDecimal("10.149")))
                    .isEqualTo(new BigDecimal("1.01"));
            softly.assertThat(currencyConvertor.convert(EUR, CZK, new BigDecimal("10.150")))
                    .isEqualTo(new BigDecimal("1.02"));
        });
    }

    @Test
    public void testConvertWithNullSourceCurrency() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> currencyConvertor.convert(null, CZK, BigDecimal.ONE));
    }

    @Test
    public void testConvertWithNullTargetCurrency() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> currencyConvertor.convert(EUR, null, BigDecimal.ONE));
    }

    @Test
    public void testConvertWithNullSourceAmount() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> currencyConvertor.convert(EUR, CZK, null));
    }

    @Test
    public void testConvertWithUnknownCurrency() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(EUR, CZK))
                .thenReturn(Optional.empty());
        assertThatExceptionOfType(UnknownExchangeRateException.class)
                .isThrownBy(() -> currencyConvertor.convert(EUR, CZK, BigDecimal.ONE));
    }

    @Test
    public void testConvertWithExternalServiceFailure() throws ExternalServiceFailureException {
        when(exchangeRateTable.getExchangeRate(EUR, CZK))
                .thenThrow(ExternalServiceFailureException.class);
        assertThatExceptionOfType(UnknownExchangeRateException.class)
                .isThrownBy(() -> currencyConvertor.convert(EUR, CZK, BigDecimal.ONE));
    }
}
