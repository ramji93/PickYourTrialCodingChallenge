package com.pickyourtrail.challenges;

import java.util.Scanner;

public class MaximumDifference {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		int[] a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		scanner.close();
		
		MaximumDifference mDifference = new MaximumDifference();
		mDifference.findMaximumDifference(n, a);

	}

	private void findMaximumDifference(int n, int[] a) {
		int min = a[0];
		int maxDiff = -1;

		for (int i = 1; i < n; i++) {

			if (a[i] > min && (a[i] - min) > maxDiff) {

				maxDiff = a[i] - min;
			} else if (a[i] < min) {

				min = a[i];
			}

		}

		System.out.println("The maximum difference with lower index element is:" + maxDiff);
	}

}
