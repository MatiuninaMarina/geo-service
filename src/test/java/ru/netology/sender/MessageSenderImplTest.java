package ru.netology.sender;

import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class MessageSenderImplTest {

    @Test
    public void testUsa() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Location usa = new Location("City", Country.USA, "Street", 25);
        when(geoService.byIp(startsWith("96."))).thenReturn(usa);
        Location russia = new Location("City", Country.RUSSIA, "Street", 25);
        when(geoService.byIp(startsWith("172."))).thenReturn(russia);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(eq(Country.USA))).thenReturn("USA");
        when(localizationService.locale(eq(Country.RUSSIA))).thenReturn("Russia");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", "96.0.0.0");

        String send = messageSender.send(headers);
        assertThat(send).isEqualTo("USA");
    }

    @Test
    public void testRussia() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Location usa = new Location("City", Country.USA, "Street", 25);
        when(geoService.byIp(startsWith("96."))).thenReturn(usa);
        Location russia = new Location("City", Country.RUSSIA, "Street", 25);
        when(geoService.byIp(startsWith("172."))).thenReturn(russia);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        when(localizationService.locale(eq(Country.USA))).thenReturn("USA");
        when(localizationService.locale(eq(Country.RUSSIA))).thenReturn("Russia");
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("x-real-ip", "172.0.0.0");

        String send = messageSender.send(headers);
        assertThat(send).isEqualTo("Russia");

    }
}
