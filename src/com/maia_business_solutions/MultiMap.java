package com.maia_business_solutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> implements Map<K, Collection<V>>
{
  private final LinkedHashMap<K, Collection<V>> internalMap = new LinkedHashMap<>();
  
  private Class<? extends Collection<V>> clazz;
  
  public MultiMap()
  {
    this(new ArrayList<>());
  }
  
  @SuppressWarnings("unchecked")
  public MultiMap(final Collection<V> collection)
  {
    clazz = (Class<? extends Collection<V>>) collection.getClass();
  }
  
  @Override
  public int size()
  {
    return internalMap.size();
  }

  @Override
  public boolean isEmpty()
  {
    return internalMap.isEmpty();
  }

  @Override
  public boolean containsKey(Object key)
  {
    return internalMap.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value)
  {
    boolean retVal = false;
    
    final Iterator<Collection<V>> iter = values().iterator();
    
    while (retVal == false && iter.hasNext())
      retVal = iter.next().contains(value);
    
    return retVal;
  }

  @Override
  public Collection<V> get(Object key)
  {
    return internalMap.get(key);
  }
  
  public boolean put(K key, V value)
  {
    Collection<V> values = get(key);
    
    if (values == null) {
      try {
        values = clazz.newInstance();
        put(key, values);
      }
      catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    
    return values.add(value);
  }

  @Override
  public Collection<V> put(K key, Collection<V> values)
  {
    return internalMap.put(key, values);
  }

  @Override
  public Collection<V> remove(Object key)
  {
    return internalMap.remove(key);
  }

  @Override
  public void putAll(Map<? extends K, ? extends Collection<V>> map)
  {
    internalMap.putAll(map);
  }

  @Override
  public void clear()
  {
    internalMap.clear();
  }

  @Override
  public Set<K> keySet()
  {
    return internalMap.keySet();
  }

  @Override
  public Collection<Collection<V>> values()
  {
    return internalMap.values();
  }

  @Override
  public Set<Entry<K, Collection<V>>> entrySet()
  {
    return internalMap.entrySet();
  }
}