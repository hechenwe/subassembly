package com.sooncode.subassembly.list_sort;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class MapSort<K, V> {

	public LinkedHashMap<K, V> sort(Map<K, V> map) {
		int mapSize = map.size();
		@SuppressWarnings("unchecked")
		Map<K, V>[] maps = new HashMap[mapSize];
		int n = 0;
		for (Entry<K, V> en : map.entrySet()) {
			K key = en.getKey();
			V value = en.getValue();
			Map<K, V> kvMap = new HashMap<>();
			kvMap.put(key, value);
			maps[n] = kvMap;
			n++;
		}

		for (int i = 0; i < mapSize; i++) {
			for (int j = i + 1; j < mapSize; j++) {
				Map<K, V> map1 = maps[i];
				Map<K, V> map2 = maps[j];

				K k1 = null;
				V v1 = null;
				for (Entry<K, V> en : map1.entrySet()) {
					k1 = en.getKey();
					v1 = en.getValue();
				}

				K k2 = null;
				V v2 = null;
				for (Entry<K, V> en : map2.entrySet()) {
					k2 = en.getKey();
					v2 = en.getValue();
				}

				if (this.compare(k1, k2, v1, v2)) {
					Map<K, V> temp = maps[i];
					maps[i] = maps[j];
					maps[j] = temp;
				}
			}
		}

		LinkedHashMap<K, V> linkedHashMap = new LinkedHashMap<>();
		for (Map<K, V> m : maps) {
			linkedHashMap.putAll(m);
		}

		return linkedHashMap;
	}

	public abstract boolean compare(K k1, K k2, V v1, V v2);

}
