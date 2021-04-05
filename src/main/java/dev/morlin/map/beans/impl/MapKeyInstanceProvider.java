package dev.morlin.map.beans.impl;

import java.util.Collection;

import dev.morlin.map.beans.MultipleKey;

public class MapKeyInstanceProvider<K>  {

	public MultipleKey<K> multipleKey(Collection<K> keys) {

		return new MultipleKeyImpl<K>(keys);
	}

}
