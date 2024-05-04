package swe.group2.springbootjacocogradle.service;

public class ShippingService {

    public int calculateShippingFee(int weight) {
        if (weight < 0) {
            throw new IllegalStateException("Please provide correct weight");
        }
        else if (weight <= 2) {
            return 5;
        }
        else if (weight <= 5) {
            return 10;
        }
        return 15;
    }

    public int applyDiscount(int baseFee, boolean isHoliday) {
        if (isHoliday) {
            return (int) (baseFee * 0.9);  // 10% 할인
        }
        return baseFee;
    }

    public int addSpecialHandlingFee(int baseFee, boolean requiresSpecialHandling) {
        if (requiresSpecialHandling) {
            return baseFee + 5;  // 특별 처리 요금으로 5 추가
        }
        return baseFee;
    }
}
