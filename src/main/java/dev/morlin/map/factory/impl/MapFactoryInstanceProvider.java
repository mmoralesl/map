package dev.morlin.map.factory.impl;

import dev.morlin.map.factory.MultipleKeyMapFactory;

public class MapFactoryInstanceProvider<K, V> {

	public MultipleKeyMapFactory<K, V> multipleKeyMap() {
		
		return new MultipleKeyMapFactoryImpl<K, V>();
	}

}
