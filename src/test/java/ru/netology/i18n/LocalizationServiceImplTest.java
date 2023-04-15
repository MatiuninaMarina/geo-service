package ru.netology.i18n;

import org.junit.Test;
import ru.netology.entity.Country;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalizationServiceImplTest {
    @Test
    public void testLocale() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String locale = localizationService.locale(Country.RUSSIA);
        assertThat(locale).isEqualTo("Добро пожаловать");
    }

    @Test
    public void test1Locale() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String locale = localizationService.locale(Country.USA);
        assertThat(locale).isEqualTo("Welcome");

    }
}
