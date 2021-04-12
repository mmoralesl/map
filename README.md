<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#installation">Installation</a>
    </li>
    <li>
      <a href="#implementations">Implementations</a>
      <ul>
        <li><a href="#double-key-map">Double Key Map</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

# Installation 

Add this dependency to your pom.xml file:

```
<dependency>
    <groupId>dev.morlin.map</groupId>
    <artifactId>map</artifactId>
    <version>1.0</version>
</dependency>
```

# Implementations 

To work with the map implementations of this library, create a MapProvider instance:

```JAVA
MapProvider<String, String> mapProvider = new MapProvider<String, String>();
```

## Double Key Map

It allows you to easiliy use a map which have multiple keys for the same value.

To build it, just get the factory from map provider and call to "createMap()" method.

```JAVA
MultipleKeyMapFactory<String, String> mapFactory = mapProvider.multipleKeyMap();

MultipleKeyMap<String, String> map = mapFactory.createMap();
```

### Create keys with multiple values

You have two forms to create a multiple key: using varargs or using collections.

```JAVA
MultipleKey<String> key = mapFactory.createKey("test", "test2");
```

```JAVA
Set<String> keys = new HashSet<String>();
		
keys.add("test");
		
keys.add("test2");
		
MultipleKey<String> key = mapFactory.createKey(keys);
```

If you want to get all map keys without getting a collection of "MultipleKey" you can use "allKeySet()" method.

```JAVA
Set<String> keys = map.allKeySet();
```

### Use single keys

And also, you can use one of the map keys to work with it.

```JAVA
map.putBySingleKey("key", "value");

map.containsBySingleKey("test");

map.replaceBySingleKey("key", "newValue");

map.getBySingleKey("key");

map.removeBySingleKey("key");

map.putIfAbsentBySingleKey("key", "value");

MultipleKey<String> keys = map.getMultipleKeysFromSingleKey("key");
```

# License
Distributed under the MIT License.
