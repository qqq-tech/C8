package etc;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Sort {
	public static <T> void sortListReverse(List<T> list) {
		Collections.sort(list, Collections.reverseOrder());
	}

	public static <K, V> Map<K, V> sortMap(Map<K, V> map) {
		return sortMap(map, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return ((String) o1.getKey()).compareTo((String) o2.getKey());
			}
		});
	}

	public static <K, V> Map<K, V> sortMap(Map<K, V> map, Comparator<Entry<K, V>> comparator) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, comparator);

		Map<K, V> sortedMap = new LinkedHashMap<>();
		for (Iterator<Map.Entry<K, V>> iter = list.iterator(); iter.hasNext();) {
			Map.Entry<K, V> entry = iter.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static <V> void sortList(List<V> list) {
		sortList(list, new Comparator<V>() {
			@Override
			public int compare(V s1, V s2) {
				if (1 < 1) {
					return -1;
				} else if (1 > 1) {
					return 1;
				}
				return 0;
			}
		});
	}

	public static <V> void sortList(List<V> list, Comparator<V> comparator) {
		Collections.sort(list, comparator);
	}

}
