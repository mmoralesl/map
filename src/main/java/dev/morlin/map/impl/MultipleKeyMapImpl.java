package dev.morlin.map.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dev.morlin.map.MultipleKeyMap;
import dev.morlin.map.beans.MultipleKey;
import dev.morlin.map.factory.MapProvider;
import dev.morlin.map.factory.MultipleKeyMapFactory;

class MultipleKeyMapImpl<K, V> implements MultipleKeyMap<K, V> {

	private Map<MultipleKey<K>, V> map;

	private MultipleKeyMapFactory<K, V> factory;

	public MultipleKeyMapImpl() {
		this.map = new HashMap<MultipleKey<K>, V>();
		init();
	}

	public MultipleKeyMapImpl(Map<? extends MultipleKey<K>, ? extends V> m) {
		this.map = new HashMap<MultipleKey<K>, V>(m);
		init();
	}

	private void init() {
		this.factory = new MapProvider<K, V>().multipleKeyMap();
	}

	public int size() {

		return map.size();
	}

	public boolean isEmpty() {

		return map.isEmpty();
	}

	public boolean containsKey(Object key) {

		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {

		return map.containsValue(value);
	}

	public V get(Object key) {

		return map.get(key);
	}

	public V put(MultipleKey<K> key, V value) {

		return map.put(key, value);
	}

	public V remove(Object key) {

		return map.remove(key);
	}

	public void putAll(Map<? extends MultipleKey<K>, ? extends V> m) {

		map.putAll(m);
	}

	public void clear() {

		map.clear();
	}

	public Set<MultipleKey<K>> keySet() {

		return map.keySet();
	}

	public Collection<V> values() {

		return map.values();
	}

	public Set<Entry<MultipleKey<K>, V>> entrySet() {

		return map.entrySet();
	}

	@Override
	public V putBySingleKey(K key, V value) {

		if (containsBySingleKey(key)) {
			return put(getMultipleKeysFromSingleKey(key), value);
		}

		return putFromSingleKey(key, value);
	}

	@Override
	public V putIfAbsentBySingleKey(K key, V value) {

		if (containsBySingleKey(key)) {
			return value;
		}

		return putFromSingleKey(key, value);
	}

	@Override
	public V replaceBySingleKey(K key, V newValue) {

		return replace(getMultipleKeysFromSingleKey(key), newValue);
	}

	@Override
	public boolean replaceBySingleKey(K key, V oldValue, V newValue) {

		return replace(getMultipleKeysFromSingleKey(key), oldValue, newValue);
	}

	@Override
	public V removeBySingleKey(K key) {

		return remove(getMultipleKeysFromSingleKey(key));
	}

	@Override
	public boolean containsBySingleKey(K key) {

		return keySet().stream().filter(it -> it.containsKey(key)).findFirst().isPresent();
	}

	@Override
	public V getBySingleKey(K key) {

		return map.entrySet().stream().filter(it -> it.getKey().containsKey(key)).findFirst().get().getValue();
	}

	@Override
	public MultipleKey<K> getMultipleKeysFromSingleKey(K key) {

		return map.keySet().stream().filter(it -> it.containsKey(key)).findFirst().get();
	}

	@Override
	public Set<K> allKeySet() {

		Set<MultipleKey<K>> multipleKeys = keySet();

		Set<K> keys = multipleKeys.stream().map(MultipleKey<K>::getKeys).flatMap(Set::stream)
				.collect(Collectors.toSet());

		return keys;
	}

	@SuppressWarnings("unchecked")
	private V putFromSingleKey(K key, V value) {
		MultipleKey<K> multipleKey = factory.createKey(key);

		return put(multipleKey, value);
	}

}