public class SortValue<T extends Comparable<T>> implements Comparable<SortValue<T>> {
	private T value;
	public SortValue(T value) {
		this.value = value;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public int getSize() {
		if (value instanceof Number) {
			return Math.max(1, ((Number) value).intValue());
		} else if (value instanceof String) {
			String stringValue = (String) value;
			if (stringValue.isEmpty()) return 1;
			char firstChar = Character.toUpperCase(stringValue.charAt(0));
			if (firstChar >= 'A' && firstChar <= 'Z') {
				return (firstChar - 'A') + 1;
			}
			return Math.max(1, stringValue.length());
		} else if (value != null) {
			return Math.max(1, value.toString().length());
		}
		return 1;
	}
	@Override
	public int compareTo(SortValue<T> other) {
		// Compare text without considering uppercase/lowercase, while preserving normal ordering for numbers.
		if (value instanceof String && other.value instanceof String) {
			return ((String) value).compareToIgnoreCase((String) other.value);
		}
		return this.value.compareTo(other.value);
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
}