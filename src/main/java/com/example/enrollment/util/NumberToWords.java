package com.example.enrollment.util;

import java.math.BigDecimal;

public class NumberToWords {
    private static final String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
    private static final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

    public static String convert(double n) {
        if (n < 0) return "Minus " + convert(-n);
        if (n < 20) return units[(int) n];
        if (n < 100) return tens[(int) n / 10] + ((n % 10 != 0) ? " " : "") + units[(int) n % 10];
        if (n < 1000) return units[(int) n / 100] + " Hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
        if (n < 1000000) return convert(n / 1000) + " Thousand" + ((n % 1000 != 0) ? " " : "") + convert(n % 1000);
        return String.format("%.2f", n);
    }
    
    public static String convertAmount(BigDecimal amount) {
        String words = convert(amount.longValue()) + " Pesos";
        int centavos = amount.remainder(BigDecimal.ONE).multiply(new BigDecimal(100)).intValue();
        if (centavos > 0) {
            words += " & " + centavos + "/100";
        } else {
            words += " & 00/100 Only";
        }
        return words;
    }

	public static Object convertAmount(Double amount) {
		// TODO Auto-generated method stub
		return null;
	}
}