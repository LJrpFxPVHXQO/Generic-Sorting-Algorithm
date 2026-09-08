import java.util.Arrays;
public class MergeSort<T extends Comparable<T>> {
	public void sort(SortValue<T>[] arr, boolean ascending, boolean visualize) {
		if (arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1, ascending, visualize);
	}
	private void mergeSort(SortValue<T>[] arr, int left, int right, boolean ascending, boolean visualize) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			// Recursively sort each half, then merge the two sorted ranges into one range.
			mergeSort(arr, left, mid, ascending, visualize);
			mergeSort(arr, mid + 1, right, ascending, visualize);
			merge(arr, left, mid, right, ascending);
			if (visualize) {
				Visualizer.visualizeSort(arr, "Merge Sort - Merging [" + left + ", " + right + "]", right);
			}
		}
	}
	private void merge(SortValue<T>[] arr, int left, int mid, int right, boolean ascending) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
		SortValue<T>[] L = Arrays.copyOfRange(arr, left, mid + 1);
		SortValue<T>[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);
		int i = 0;
		int j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if ((ascending && L[i].compareTo(R[j]) <= 0) || (!ascending && L[i].compareTo(R[j]) >= 0)) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}
}