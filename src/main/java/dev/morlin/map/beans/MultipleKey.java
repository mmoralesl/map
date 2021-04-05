 package dev.morlin.map.beans;

import java.util.Set;

public interface MultipleKey<K> {

	Set<K> getKeys();
	
	int size();

	boolean containsKey(K key);

	Set<K> removeKey(K key);
	
}
