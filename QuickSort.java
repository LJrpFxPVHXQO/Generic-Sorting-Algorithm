public class QuickSort<T extends Comparable<T>> {
	public void sort(SortValue<T>[] arr, boolean ascending, boolean visualize) {
		quickSort(arr, 0, arr.length - 1, ascending, visualize);
	}
	private void quickSort(SortValue<T>[] arr, int low, int high, boolean ascending, boolean visualize) {
		if (low < high) {
			// Partitioning places the pivot at its final index and divides the remaining range.
			int pi = partition(arr, low, high, ascending);
			if (visualize) {
				Visualizer.visualizeSort(arr, "Quick Sort - Partitioning [" + low + ", " + high + "]", pi);
			}
			quickSort(arr, low, pi - 1, ascending, visualize);
			quickSort(arr, pi + 1, high, ascending, visualize);
		}
	}
	private int partition(SortValue<T>[] arr, int low, int high, boolean ascending) {
		SortValue<T> pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if ((ascending && arr[j].compareTo(pivot) <= 0) || (!ascending && arr[j].compareTo(pivot) >= 0)) {
				i++;
				SortValue<T> temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		SortValue<T> temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1;
	}
}