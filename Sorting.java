import java.util.Scanner;
import java.util.function.Function;
public class Sorting {
	private static <T extends Comparable<T>> SortValue<T>[] readUserArray(Scanner scan, int length,
	        Function<String, T> parser) {
		// Java cannot create a generic array directly, so create the raw array and cast it once.
		@SuppressWarnings("unchecked")
		SortValue<T>[] values = (SortValue<T>[]) new SortValue[length];
		for (int i = 0; i < length; i++) {
			System.out.print("Array [" + i + "]: ");
			String input = scan.nextLine().trim();
			values[i] = new SortValue<>(parser.apply(input));
		}
		return values;
	}
	private static <T extends Comparable<T>> void executeSort(SortValue<T>[] values, int algoChoice, boolean
	        ascending) {
		switch (algoChoice) {
		case 1:
			new InsertionSort<T>().sort(values, ascending, true);
			break;
		case 2:
			new SelectionSort<T>().sort(values, ascending, true);
			break;
		case 3:
			new MergeSort<T>().sort(values, ascending, true);
			break;
		case 4:
			new QuickSort<T>().sort(values, ascending, true);
			break;
		default:
			System.out.println("Invalid algorithm choice.");
			break;
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String choice;
		do {
			System.out.println("Choose a data type");
			System.out.println("1. String");
			System.out.println("2. Integer");
			System.out.print("Enter choice: ");
			int dataChoice = scan.nextInt();
			System.out.print("Enter the length of the array: ");
			int valLength = scan.nextInt();
			scan.nextLine();
			System.out.println("\nChoose a sorting algorithm");
			System.out.println("1. Insertion Sort");
			System.out.println("2. Selection Sort");
			System.out.println("3. Merge Sort");
			System.out.println("4. Quick Sort");
			System.out.print("Enter choice: ");
			int algoChoice = scan.nextInt();
			System.out.println("\nChoose either Ascending or Descending");
			System.out.println("1. Ascending");
			System.out.println("2. Descending");
			System.out.print("Enter choice: ");
			int ascChoice = scan.nextInt();
			scan.nextLine();
			System.out.println("\nEnter " + valLength + " values:");
			if (dataChoice == 1) {
				SortValue<String>[] stringValues = readUserArray(scan, valLength, s -> s);
				executeSort(stringValues, algoChoice, ascChoice == 1);
			} else if (dataChoice == 2) {
				SortValue<Integer>[] intValues = readUserArray(scan, valLength, Integer::parseInt);
				executeSort(intValues, algoChoice, ascChoice == 1);
			}
			System.out.print("\nDo you want to run another sorting visualization? (y/n): ");
			choice = scan.nextLine().trim().toLowerCase();
			System.out.println();
		} while (choice.equals("y") || choice.equals("yes"));
		System.out.println("Program finished.");
		scan.close();
	}
}