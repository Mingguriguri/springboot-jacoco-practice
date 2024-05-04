package io.truongbn.github.jacoco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TestShippingService {
    @Test
    public void incorrectWeight() {
        ShippingService shippingService = new ShippingService();
        assertThrows(IllegalStateException.class, () -> shippingService.calculateShippingFee(-1));
    }

    @Test
    public void firstRangeWeight() {
        ShippingService shippingService = new ShippingService();
        assertEquals(5, shippingService.calculateShippingFee(1));
    }

    @Test
    public void secondRangeWeight() {
        ShippingService shippingService = new ShippingService();
        assertEquals(10, shippingService.calculateShippingFee(4));
    }

    @Test
    public void lastRangeWeight() {
        ShippingService shippingService = new ShippingService();
        assertEquals(15, shippingService.calculateShippingFee(10));
    }

    // 아래 추가
    @Test
    public void applyHolidayDiscount() {
        ShippingService shippingService = new ShippingService();
        int baseFee = 10;
        assertEquals(9, shippingService.applyDiscount(baseFee, true));
    }

    @Test
    public void noHolidayDiscount() {
        ShippingService shippingService = new ShippingService();
        int baseFee = 10;
        assertEquals(10, shippingService.applyDiscount(baseFee, false));
    }

    @Test
    public void addSpecialHandling() {
        ShippingService shippingService = new ShippingService();
        int baseFee = 10;
        assertEquals(15, shippingService.addSpecialHandlingFee(baseFee, true));
    }

    @Test
    public void noSpecialHandling() {
        ShippingService shippingService = new ShippingService();
        int baseFee = 10;
        assertEquals(10, shippingService.addSpecialHandlingFee(baseFee, false));
    }

}
