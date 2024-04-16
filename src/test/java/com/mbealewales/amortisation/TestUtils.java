package com.mbealewales.amortisation;

import java.util.Arrays;
import java.util.List;

import com.mbealewales.amortisation.entity.AmortisationInstallment;

public class TestUtils {
    public static final List<AmortisationInstallment> UNBALLOONED_INSTALLMENTS = Arrays.asList(new AmortisationInstallment[] {
        createTestInstallment(1, 1735.15, 1610.15, 125.0, 18389.85),
        createTestInstallment(2, 1735.15, 1620.21, 114.94, 16769.64),
        createTestInstallment(3, 1735.15, 1630.34, 104.81, 15139.30),
        createTestInstallment(4, 1735.15, 1640.53, 94.62, 13498.77),
        createTestInstallment(5, 1735.15, 1650.78, 84.37, 11847.99),
        createTestInstallment(6, 1735.15, 1661.10, 74.05, 10186.89),
        createTestInstallment(7, 1735.15, 1671.48, 63.67, 8515.41),
        createTestInstallment(8, 1735.15, 1681.93, 53.22, 6833.49),
        createTestInstallment(9, 1735.15, 1692.44, 42.71, 5141.05),
        createTestInstallment(10, 1735.15, 1703.02, 32.13, 3438.03),
        createTestInstallment(11, 1735.15, 1713.66, 21.49, 1724.37),
        createTestInstallment(12, 1735.15, 1724.37, 10.78, 0.0)
     });

     public static final List<AmortisationInstallment> BALLOONED_INSTALLMENTS = Arrays.asList(new AmortisationInstallment[] {
        createTestInstallment(1, 930.07, 805.07, 125.0, 19194.93),
        createTestInstallment(2, 930.07, 810.11, 119.97, 18383.82),
        createTestInstallment(3, 930.07, 815.17, 114.91, 17569.65),
        createTestInstallment(4, 930.07, 820.26, 109.81, 16749.39),
        createTestInstallment(5, 930.07, 825.39, 104.68, 15924.00),
        createTestInstallment(6, 930.07, 830.55, 99.52, 15093.45),
        createTestInstallment(7, 930.07, 835.74, 94.33, 14257.71),
        createTestInstallment(8, 930.07, 840.96, 89.11, 13416.74),
        createTestInstallment(9, 930.07, 846.22, 83.85, 12570.52),
        createTestInstallment(10, 930.07, 851.51, 78.57, 11719.02),
        createTestInstallment(11, 930.07, 856.83, 73.24, 10862.19),
        createTestInstallment(12, 930.07, 862.19, 67.89, 10000.0)
     });

     public static final double totalInterest(List<AmortisationInstallment> installments) {
        return installments.stream().mapToDouble(i->i.getInterest()).sum();
     }

     private static AmortisationInstallment createTestInstallment(int period, double payment, double principal, double interest,
     double balance) {
        return new AmortisationInstallment(null, period, payment, principal, interest, balance, null);
    }
}
