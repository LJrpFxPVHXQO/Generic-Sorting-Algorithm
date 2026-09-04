public class Visualizer {
    static final int maxHeight = 15;
    static final int width = 6;

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
        clearConsole();
        System.out.println(stepLabel);
        System.out.println();

        int maxVal = Integer.MIN_VALUE;
        for (SortValue<T> value : arr) {
            int size = value.getSize();
            if (size > maxVal) maxVal = size;
        }

        int[] heights = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int size = arr[i].getSize();
            heights[i] = (maxVal <= 0) ? 1 : Math.max(1, (int) Math.round(((double) size / maxVal) * maxHeight));
        }

        for (int level = maxHeight; level >= 1; level--) {
            for (int i = 0; i < arr.length; i++) {
                if (heights[i] >= level) {
                    System.out.print(centerString("██", width));
                } else {
                    System.out.print(centerString("", width));
                }
            }
            System.out.println();
        }

        for (SortValue<T> value : arr) {
            System.out.print(centerString(value.getValue().toString(), width));
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(centerString("[" + i + "]", width));
        }
        System.out.println();

        delay(1000);
    }

    private static String centerString(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        int padding = width - text.length();
        int leftPad = padding / 2;
        int rightPad = padding - leftPad;
        return " ".repeat(leftPad) + text + " ".repeat(rightPad);
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}