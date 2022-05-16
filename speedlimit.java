import java.util.Scanner;

public class speedlimit {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {

		int input;

		input = sc.nextInt();

		while (input != -1) {

			int mtotal = 0;
			int htotal = 0;
			data[] array = new data[input];

			for (int i = 0; i < input; i++) {
				array[i] = new data();
				array[i].mph = sc.nextInt();
				array[i].hours = sc.nextInt();
			}

			for (int j = 0; j < input; j++) {
				mtotal += array[j].mph * (array[j].hours - htotal);
				htotal = array[j].hours;
			}

			System.out.println(mtotal + " miles");

			input = sc.nextInt();

		}
	}
}

class data {

	int mph;
	int hours;

	data() {
		this.mph = 0;
		this.hours = 0;
	}

}
