package dev.morlin.map.test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import dev.morlin.map.MultipleKeyMap;
import dev.morlin.map.beans.MultipleKey;
import dev.morlin.map.factory.MapProvider;
import dev.morlin.map.factory.MultipleKeyMapFactory;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class MultipleKeyMapTest {

	private class Key {

	}

	private final String PRIMARY_MAP_KEY = "PRIMARY_MAP_KEY";

	private final String PRIMARY_MAP_VALUE = "PRIMARY_MAP_VALUE";

	private final String SECOND_MAP_KEY = "SECOND_MAP_KEY";

	private final String SECOND_MAP_VALUE = "SECOND_MAP_VALUE";

	private final String THIRD_MAP_KEY = "THIRD_MAP_KEY";

	private final Key PRIMARY_BEAN_KEY = new Key();

	private final Key SECONDARY_BEAN_KEY = new Key();

	private final Key THIRD_BEAN_KEY = new Key();

	private MultipleKeyMapFactory<String, String> simpleFactory;

	private MultipleKeyMap<String, String> simpleMap;

	private MultipleKeyMapFactory<Key, String> beanAsKeyFactory;

	private MultipleKeyMap<Key, String> beanAsKeyMap;

	@BeforeAll
	public void setUp() throws Exception {

		this.simpleFactory = new MapProvider<String, String>().multipleKeyMap();

		this.beanAsKeyFactory = new MapProvider<Key, String>().multipleKeyMap();

		this.simpleMap = simpleFactory.createMap();

		this.beanAsKeyMap = beanAsKeyFactory.createMap();

	}

	@RepeatedTest(value = 2, name = "Put by single key {currentRepetition}/{totalRepetitions}")
	@Order(1)
	@DisplayName("Put by single key")
	public void putBySingleKey() {

		simpleMap.putBySingleKey(PRIMARY_MAP_KEY, PRIMARY_MAP_VALUE);

		assertEquals(1, simpleMap.size());

		beanAsKeyMap.putBySingleKey(PRIMARY_BEAN_KEY, PRIMARY_MAP_VALUE);

		assertEquals(1, beanAsKeyMap.size());

	}

	@Test
	@Order(2)
	@DisplayName("Contains by single key")
	public void containsBySingleKey() {

		assertEquals(true, simpleMap.containsBySingleKey(PRIMARY_MAP_KEY));

		assertEquals(true, beanAsKeyMap.containsBySingleKey(PRIMARY_BEAN_KEY));

	}

	@Test
	@Order(3)
	@DisplayName("Get by single key")
	public void getBySingleKey() {

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.getBySingleKey(PRIMARY_MAP_KEY));

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.getBySingleKey(PRIMARY_BEAN_KEY));

	}

	@Test
	@Order(4)
	@DisplayName("Get multiple keys from single key")
	public void getMultipleKeysFromSingleKey() {

		MultipleKey<String> multipleKeys = simpleMap.getMultipleKeysFromSingleKey(PRIMARY_MAP_KEY);

		assertEquals(1, multipleKeys.size());

		MultipleKey<Key> multipleKeysBeanMap = beanAsKeyMap.getMultipleKeysFromSingleKey(PRIMARY_BEAN_KEY);

		assertEquals(1, multipleKeysBeanMap.size());

	}

	@Test
	@Order(5)
	@DisplayName("Contains key in multiple key")
	public void containsKeyInMultipleKey() {

		MultipleKey<String> multipleKeys = simpleMap.getMultipleKeysFromSingleKey(PRIMARY_MAP_KEY);

		assertEquals(true, multipleKeys.containsKey(PRIMARY_MAP_KEY));

		MultipleKey<Key> multipleKeysBeanMap = beanAsKeyMap.getMultipleKeysFromSingleKey(PRIMARY_BEAN_KEY);

		assertEquals(true, multipleKeysBeanMap.containsKey(PRIMARY_BEAN_KEY));

	}

	@Test
	@Order(6)
	@DisplayName("Remove by single key")
	public void removeBySingleKey() {

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.removeBySingleKey(PRIMARY_MAP_KEY));

		assertEquals(0, simpleMap.size());

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.removeBySingleKey(PRIMARY_BEAN_KEY));

		assertEquals(0, beanAsKeyMap.size());
	}

	@RepeatedTest(value = 2, name = "Put if absent by single key {currentRepetition}/{totalRepetitions}")
	@Order(7)
	@DisplayName("Put if absent by single key")
	public void putIfAbsentBySingleKey() {

		simpleMap.putIfAbsentBySingleKey(PRIMARY_MAP_KEY, PRIMARY_MAP_VALUE);

		assertEquals(1, simpleMap.size());

		beanAsKeyMap.putIfAbsentBySingleKey(PRIMARY_BEAN_KEY, PRIMARY_MAP_VALUE);

		assertEquals(1, beanAsKeyMap.size());

	}

	@Test
	@Order(8)
	@DisplayName("Replace by single key")
	public void replaceBySingleKey() {

		simpleMap.replaceBySingleKey(PRIMARY_MAP_KEY, SECOND_MAP_VALUE);

		assertEquals(SECOND_MAP_VALUE, simpleMap.getBySingleKey(PRIMARY_MAP_KEY));

		beanAsKeyMap.replaceBySingleKey(PRIMARY_BEAN_KEY, SECOND_MAP_VALUE);

		assertEquals(SECOND_MAP_VALUE, beanAsKeyMap.getBySingleKey(PRIMARY_BEAN_KEY));
	}

	@Test
	@Order(9)
	@DisplayName("Replace by single key with old value")
	public void replaceBySingleKeyWithOldValue() {

		boolean response = simpleMap.replaceBySingleKey(PRIMARY_MAP_KEY, PRIMARY_MAP_VALUE, SECOND_MAP_VALUE);

		assertEquals(false, response);

		response = simpleMap.replaceBySingleKey(PRIMARY_MAP_KEY, SECOND_MAP_VALUE, PRIMARY_MAP_VALUE);

		assertEquals(true, response);

		response = beanAsKeyMap.replaceBySingleKey(PRIMARY_BEAN_KEY, PRIMARY_MAP_VALUE, SECOND_MAP_VALUE);

		assertEquals(false, response);

		response = beanAsKeyMap.replaceBySingleKey(PRIMARY_BEAN_KEY, SECOND_MAP_VALUE, PRIMARY_MAP_VALUE);

		assertEquals(true, response);

	}

	@Test
	@Order(10)
	@DisplayName("Clean")
	public void clean() {

		simpleMap.clear();

		assertEquals(0, simpleMap.size());

		beanAsKeyMap.clear();

		assertEquals(0, beanAsKeyMap.size());

	}

	@Test
	@Order(11)
	@DisplayName("Put")
	public void put() {

		simpleMap.put(simpleFactory.createKey(PRIMARY_MAP_KEY), PRIMARY_MAP_VALUE);

		assertEquals(1, simpleMap.size());

		simpleMap.put(simpleFactory.createKey(SECOND_MAP_KEY, THIRD_MAP_KEY), SECOND_MAP_VALUE);

		assertEquals(2, simpleMap.size());

		beanAsKeyMap.put(beanAsKeyFactory.createKey(PRIMARY_BEAN_KEY), PRIMARY_MAP_VALUE);

		assertEquals(1, beanAsKeyMap.size());

		beanAsKeyMap.put(beanAsKeyFactory.createKey(SECONDARY_BEAN_KEY, THIRD_BEAN_KEY), SECOND_MAP_VALUE);

		assertEquals(2, beanAsKeyMap.size());
	}

	@Test
	@Order(12)
	@DisplayName("Contains")
	public void contains() {

		assertEquals(true, simpleMap.containsKey(simpleFactory.createKey(PRIMARY_MAP_KEY)));

		assertEquals(true, simpleMap.containsKey(simpleFactory.createKey(SECOND_MAP_KEY, THIRD_MAP_KEY)));

		assertEquals(true, beanAsKeyMap.containsKey(beanAsKeyFactory.createKey(PRIMARY_BEAN_KEY)));

		assertEquals(true, beanAsKeyMap.containsKey(beanAsKeyFactory.createKey(SECONDARY_BEAN_KEY, THIRD_BEAN_KEY)));

	}

	@Test
	@Order(13)
	@DisplayName("Get")
	public void get() {

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.get(simpleFactory.createKey(PRIMARY_MAP_KEY)));

		assertEquals(SECOND_MAP_VALUE, simpleMap.get(simpleFactory.createKey(SECOND_MAP_KEY, THIRD_MAP_KEY)));

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.get(beanAsKeyFactory.createKey(PRIMARY_BEAN_KEY)));

		assertEquals(SECOND_MAP_VALUE,
				beanAsKeyMap.get(beanAsKeyFactory.createKey(SECONDARY_BEAN_KEY, THIRD_BEAN_KEY)));

	}

	@Test
	@Order(14)
	@DisplayName("All keys set")
	public void allKeysSet() {

		assertEquals(3, simpleMap.allKeySet().size());

		assertEquals(3, beanAsKeyMap.allKeySet().size());

	}

	@Test
	@Order(15)
	@DisplayName("Replace")
	public void replace() {

		assertEquals(SECOND_MAP_VALUE,
				simpleMap.replace(simpleFactory.createKey(SECOND_MAP_KEY, THIRD_MAP_KEY), PRIMARY_MAP_VALUE));

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.getBySingleKey(SECOND_MAP_KEY));

		assertEquals(SECOND_MAP_VALUE, beanAsKeyMap
				.replace(beanAsKeyFactory.createKey(SECONDARY_BEAN_KEY, THIRD_BEAN_KEY), PRIMARY_MAP_VALUE));

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.getBySingleKey(SECONDARY_BEAN_KEY));

	}

	@Test
	@Order(16)
	@DisplayName("Remove")
	public void remove() {

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.remove(simpleFactory.createKey(PRIMARY_MAP_KEY)));

		assertEquals(1, simpleMap.size());

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.remove(beanAsKeyFactory.createKey(PRIMARY_BEAN_KEY)));

		assertEquals(1, beanAsKeyMap.size());

	}

	@Test
	@Order(17)
	@DisplayName("Contains by single key with multiple Keys")
	public void containsBySingleKeyWithMultipleKeys() {

		assertEquals(true, simpleMap.containsBySingleKey(SECOND_MAP_KEY));

		assertEquals(true, simpleMap.containsBySingleKey(THIRD_MAP_KEY));

		assertEquals(true, beanAsKeyMap.containsBySingleKey(SECONDARY_BEAN_KEY));

		assertEquals(true, beanAsKeyMap.containsBySingleKey(THIRD_BEAN_KEY));

	}

	@Test
	@Order(18)
	@DisplayName("Get by single key with multiple keys")
	public void getBySingleKeyWithMultipleKeys() {

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.getBySingleKey(SECOND_MAP_KEY));

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.getBySingleKey(THIRD_MAP_KEY));

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.getBySingleKey(SECONDARY_BEAN_KEY));

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.getBySingleKey(THIRD_BEAN_KEY));

	}

	@Test
	@Order(19)
	@DisplayName("Replace by single key with multiple keys")
	public void replaceBySingleKeyWithMultipleKeys() {

		simpleMap.replaceBySingleKey(SECOND_MAP_KEY, SECOND_MAP_VALUE);

		assertEquals(SECOND_MAP_VALUE, simpleMap.getBySingleKey(SECOND_MAP_KEY));

		simpleMap.replaceBySingleKey(THIRD_MAP_KEY, PRIMARY_MAP_VALUE);

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.getBySingleKey(THIRD_MAP_KEY));

		beanAsKeyMap.replaceBySingleKey(SECONDARY_BEAN_KEY, SECOND_MAP_VALUE);

		assertEquals(SECOND_MAP_VALUE, beanAsKeyMap.getBySingleKey(SECONDARY_BEAN_KEY));

		beanAsKeyMap.replaceBySingleKey(THIRD_BEAN_KEY, PRIMARY_MAP_VALUE);

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.getBySingleKey(THIRD_BEAN_KEY));

	}

	@Test
	@Order(20)
	@DisplayName("Replace by single key with old value with multiple keys")
	public void replaceBySingleKeyWithOldValueWithMultipleKeys() {

		boolean response = simpleMap.replaceBySingleKey(SECOND_MAP_KEY, SECOND_MAP_VALUE, PRIMARY_MAP_VALUE);

		assertEquals(false, response);

		response = simpleMap.replaceBySingleKey(SECOND_MAP_KEY, PRIMARY_MAP_VALUE, SECOND_MAP_VALUE);

		assertEquals(true, response);

		response = simpleMap.replaceBySingleKey(THIRD_MAP_KEY, PRIMARY_MAP_VALUE, SECOND_MAP_VALUE);

		assertEquals(false, response);

		response = simpleMap.replaceBySingleKey(THIRD_MAP_KEY, SECOND_MAP_VALUE, PRIMARY_MAP_VALUE);

		assertEquals(true, response);

		response = beanAsKeyMap.replaceBySingleKey(SECONDARY_BEAN_KEY, SECOND_MAP_VALUE, PRIMARY_MAP_VALUE);

		assertEquals(false, response);

		response = beanAsKeyMap.replaceBySingleKey(SECONDARY_BEAN_KEY, PRIMARY_MAP_VALUE, SECOND_MAP_VALUE);

		assertEquals(true, response);

		response = beanAsKeyMap.replaceBySingleKey(THIRD_BEAN_KEY, PRIMARY_MAP_VALUE, SECOND_MAP_VALUE);

		assertEquals(false, response);

		response = beanAsKeyMap.replaceBySingleKey(THIRD_BEAN_KEY, SECOND_MAP_VALUE, PRIMARY_MAP_VALUE);

		assertEquals(true, response);

	}

	@Test
	@Order(21)
	@DisplayName("Remove by single key with multiple keys")
	public void removeBySingleKeyWithMultipleKeys() {

		assertEquals(PRIMARY_MAP_VALUE, simpleMap.removeBySingleKey(SECOND_MAP_KEY));

		assertEquals(0, simpleMap.size());

		assertEquals(PRIMARY_MAP_VALUE, beanAsKeyMap.removeBySingleKey(SECONDARY_BEAN_KEY));

		assertEquals(0, beanAsKeyMap.size());

	}

}