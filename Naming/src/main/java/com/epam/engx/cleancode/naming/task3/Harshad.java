package com.epam.engx.cleancode.naming.task3;

public class Harshad {
	private static final long Length_Of_Harshad_Number = 200;

	public String printHarshadNumbers() {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i <= Length_Of_Harshad_Number; i++) {
			if (i % calculateSum(i) == 0) {
				result.append(i).append("\n");
			}
		}
		return result.toString();
	}

	private int calculateSum(int number) {
		int sum = 0;
		while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }
		return sum;
	}

}
