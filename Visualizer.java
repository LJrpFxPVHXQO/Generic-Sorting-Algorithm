public class Visualizer {
	static final int maxHeight = 15;
	static final int minimumWidth = 6;
	static final String green = "\033[32m";
	static final String resetColor = "\033[0m";
	public static void clearConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J\033[3J");
				System.out.flush();
			}
		} catch (Exception e) {
			for (int i = 0; i < 30; i++) System.out.println();
		}
	}
	public static <T extends Comparable<T>> void visualizeSort(SortValue<T>[] arr, String stepLabel) {
		visualizeSort(arr, stepLabel, -1);
	}
	public static <T extends Comparable<T>> void visualizeSort(SortValue<T>[] arr, String stepLabel, int
	        changedIndex) {
		clearConsole();
		System.out.println(stepLabel);
		System.out.println();
		// Use one width for every column so values and their indexes stay aligned.
		int columnWidth = getColumnWidth(arr);
		int maxVal = Integer.MIN_VALUE;
		for (SortValue<T> value : arr) {
			int size = value.getSize();
			if (size > maxVal) maxVal = size;
		}
		int[] heights = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int size = arr[i].getSize();
			// Scale every bar against the largest value so the chart fits maxHeight.
			heights[i] = (maxVal <= 0) ? 1 : Math.max(1, (int) Math.round(((double) size / maxVal) * maxHeight));
		}
		for (int level = maxHeight; level >= 1; level--) {
			for (int i = 0; i < arr.length; i++) {
				if (heights[i] >= level) {
					String bar = "██";
					System.out.print(centerString(i == changedIndex ? green + bar + resetColor : bar, columnWidth));
				} else {
					System.out.print(centerString("", columnWidth));
				}
			}
			System.out.println();
		}
		for (int i = 0; i < arr.length; i++) {
			String value = arr[i].getValue().toString();
			if (i == changedIndex) value = green + value + resetColor;
			System.out.print(centerString(value, columnWidth));
		}
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			System.out.print(centerString("[" + i + "]", columnWidth));
		}
		System.out.println();
		delay(1000);
	}
	private static String centerString(String text, int width) {
		int visibleLength = text.replaceAll("\\033\\[[;\\d]*m", "").length();
		if (visibleLength >= width) return text;
		int padding = width - visibleLength;
		int leftPad = padding / 2;
		int rightPad = padding - leftPad;
		return " ".repeat(leftPad) + text + " ".repeat(rightPad);
	}
	private static <T extends Comparable<T>> int getColumnWidth(SortValue<T>[] arr) {
		int columnWidth = minimumWidth;
		for (int i = 0; i < arr.length; i++) {
			// The extra spaces keep adjacent columns visually separate.
			columnWidth = Math.max(columnWidth, arr[i].getValue().toString().length() + 2);
			columnWidth = Math.max(columnWidth, ("[" + i + "]").length() + 2);
		}
		return columnWidth;
	}
	public static void delay(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}