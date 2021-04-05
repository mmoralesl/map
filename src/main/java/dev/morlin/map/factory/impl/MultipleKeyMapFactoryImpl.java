package dev.morlin.map.factory.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dev.morlin.map.MultipleKeyMap;
import dev.morlin.map.beans.MultipleKey;
import dev.morlin.map.beans.impl.MapKeyInstanceProvider;
import dev.morlin.map.factory.MultipleKeyMapFactory;
import dev.morlin.map.impl.MapInstanceProvider;

class MultipleKeyMapFactoryImpl<K, V> implements MultipleKeyMapFactory<K, V> {

	@Override
	public MultipleKeyMap<K, V> createMap() {

		return new MapInstanceProvider<K, V>().multipleKeyMap(null);
	}

	@Override
	public MultipleKeyMap<K, V> createMap(Map<? extends MultipleKey<K>, ? extends V> m) {

		return new MapInstanceProvider<K, V>().multipleKeyMap(m);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MultipleKey<K> createKey(K... args) {

		Set<K> keys = new HashSet<K>();

		for (K arg : args) {
			keys.add(arg);
		}

		return createKey(keys);
	}

	@Override
	public MultipleKey<K> createKey(Collection<K> keys) {

		return new MapKeyInstanceProvider<K>().multipleKey(keys);
	}

}
