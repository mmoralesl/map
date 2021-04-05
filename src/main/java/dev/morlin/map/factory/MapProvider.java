package dev.morlin.map.factory;

import dev.morlin.map.factory.impl.MapFactoryInstanceProvider;

public class MapProvider<K, V> {

	public MultipleKeyMapFactory<K, V> multipleKeyMap() {

		return new MapFactoryInstanceProvider<K, V>().multipleKeyMap();
	}

}