import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class cd {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String first_line = br.readLine();
		String[] input = first_line.split(" ");
		int jack_amount = Integer.parseInt(input[0]);
		int jill_amount = Integer.parseInt(input[1]);

		HashSet<String> collection = new HashSet<>(1000000);

		while (jack_amount != 0 && jill_amount != 0) {
			int total = 0;

			for (int i = 0; i < jack_amount; i++) {
				collection.add(br.readLine());
			}

			for (int j = 0; j < jill_amount; j++) {
				if (collection.contains(br.readLine())) total++;
			}

			System.out.println(total);
			collection.clear();

			first_line = br.readLine();
			input = first_line.split(" ");
			jack_amount = Integer.parseInt(input[0]);
			jill_amount = Integer.parseInt(input[1]);
		}
	}
}
