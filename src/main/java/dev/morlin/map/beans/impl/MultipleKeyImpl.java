package dev.morlin.map.beans.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import dev.morlin.map.beans.MultipleKey;

class MultipleKeyImpl<K> implements MultipleKey<K> {

	private Set<K> keys;

	public MultipleKeyImpl(Collection<K> keys) {
		this.keys = new HashSet<K>(keys);
	}

	@Override
	public Set<K> getKeys() {
		return keys;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj.getClass() == MultipleKeyImpl.class) {

			try {

				@SuppressWarnings("unchecked")
				MultipleKeyImpl<K> parsedObj = (MultipleKeyImpl<K>) obj;

				return parsedObj.getKeys().stream().filter(it -> keys.contains(it)).findFirst().isPresent();

			} catch (Exception e) {
			}

		}

		return keys.contains(obj);
	}

	@Override
	public boolean containsKey(K key) {

		return keys.contains(key);
	}

	@Override
	public int hashCode() {

		return keys.stream().mapToInt(K::hashCode).reduce((k, k2) -> k ^ k2).getAsInt();
	}

	@Override
	public Set<K> removeKey(K key) {

		keys.remove(key);

		return keys;
	}

	@Override
	public int size() {

		return keys.size();
	}

}