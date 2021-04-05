package dev.morlin.map;

import java.util.Map;
import java.util.Set;

import dev.morlin.map.beans.MultipleKey;

public interface MultipleKeyMap<K, V> extends Map<MultipleKey<K>, V> {

	V putBySingleKey(K key, V value);

	boolean containsBySingleKey(K key);

	V getBySingleKey(K key);

	MultipleKey<K> getMultipleKeysFromSingleKey(K key);

	V removeBySingleKey(K key);

	V putIfAbsentBySingleKey(K key, V value);

	V replaceBySingleKey(K key, V newValue);

	boolean replaceBySingleKey(K key, V oldValue, V newValue);

	Set<K> allKeySet();

}
