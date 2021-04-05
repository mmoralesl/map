package dev.morlin.map.factory;

import java.util.Collection;
import java.util.Map;

import dev.morlin.map.MultipleKeyMap;
import dev.morlin.map.beans.MultipleKey;

public interface MultipleKeyMapFactory<K, V> extends AbstractMapFactory<MultipleKeyMap<K, V>> {

	MultipleKeyMap<K, V> createMap(Map<? extends MultipleKey<K>, ? extends V> m);

	@SuppressWarnings("unchecked")
	MultipleKey<K> createKey(K... args);

	MultipleKey<K> createKey(Collection<K> keys);
}
