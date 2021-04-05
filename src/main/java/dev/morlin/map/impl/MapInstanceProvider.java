package dev.morlin.map.impl;

import java.util.Map;

import dev.morlin.map.MultipleKeyMap;
import dev.morlin.map.beans.MultipleKey;

public class MapInstanceProvider<K, V> {

	public MultipleKeyMap<K, V> multipleKeyMap(Map<? extends MultipleKey<K>, ? extends V> m) {

		if (m == null) {
			return new MultipleKeyMapImpl<K, V>();
		}

		return new MultipleKeyMapImpl<K, V>(m);
	}

}
