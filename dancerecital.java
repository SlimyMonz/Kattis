// created by Harry James Hocker
// February, 11th 2020

import java.util.Scanner;

public class dancerecital {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// sets of dancer schedules
		int sets = sc.nextInt();

		// initialize necessary variables
		String[] schedules = new String[sets];
		int[][] all_costs = new int[sets][sets];
		int[] permutations = new int[sets];
		boolean[] used = new boolean[sets];

		// get all dancer strings
		for (int i = 0; i < sets; i++) {
			schedules[i] = sc.next();
		}

		// complete all the calculations beforehand
		for (int i = 0; i < sets; i++) {
			for (int j = 0; j < sets; j++) {
				all_costs[i][j] = set_overlap(schedules[i], schedules[j]);
			}
		}

		// get answer and print it!
		int answer = find_best(permutations, all_costs, used, 0, sets);
		System.out.print(answer);
	}

	// the meat and potatoes of the permutations
	public static int find_best(int[] array, int[][] costs, boolean[] used, int k, int sets) {

		// Return the value found from this set of permutations.
		if (k == sets) return get_cost(array, costs, sets);

		// start with largest integer
		int minimum = Integer.MAX_VALUE;

		// try everything you can!
		for (int i = 0; i < sets; i++) {
			if (!used[i]) {
				array[k] = i;
				used[i] = true;
				// find the next set or get the next value
				int nextValue = find_best(array, costs, used, k+1, sets);
				// if the value is smaller, set it as the new minimum
				if (nextValue < minimum) minimum = nextValue;
				// reset used array
				used[i] = false;
			}
		}

		// Here it is! The smallest one!
		return minimum;
	}

	// Returns the cost of current permutations sets
	public static int get_cost(int[] array, int[][] costs, int sets) {
		int total = 0;
		// add quick changes between each pair of adjacent dance sets
		for (int i = 0; i < sets - 1; i++) {
			int temp = costs[array[i]][array[i+1]];
			total += temp;
		}
		// return the total costs
		return total;
	}

	// returns an integer of the common characters of "String a" existing inside "String b"
	public static int set_overlap(String a, String b) {
		// count how many characters of String A at each index exists in String B
		int amount = 0;
		for (int i = 0; i < a.length(); i++) {
			// stupid that we have to convert a character to a string this way, but whatever
			String piece = String.valueOf(a.charAt(i));
			// if String B contains the character, then increment
			if (b.contains(piece)) amount++;
		}
		return amount;
	}

}
