package ru.netology.geo;

import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.assertj.core.api.Assertions.assertThat;

public class GeoServiceImplTest {
    @Test
    public void testByIP() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp("172.0.0.0");
        assertThat(result.getCountry()).isEqualTo(Country.RUSSIA);
    }

    @Test
    public void test1ByIP() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location result = geoService.byIp("96.0.0.0");
        assertThat(result.getCountry()).isEqualTo(Country.USA);
    }
}
