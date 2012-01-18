/*
 * Copyright (c) 2012 Arkadiusz Orzechowski
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. 
*/
package org.aorzecho.ds;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.aorzecho.ds.java.util.TreeMap;

/**
 * A Red-Black tree implementation, augmented with order statistic data. 
 * 
 * The implementation is based on {@link java.util.TreeMap} and provides 
 * access to i-th element <tt>getIthValue/Key</tt> with guaranteed log(n) 
 * time cost. There is memory overhead of one <tt>int</tt> per tree node,
 * for keeping size of the branch. Algorithms are adaptations of those in
 * Cormen, Leiserson, Rivest and Stein's <I>Introduction to Algorithms</I>.
 * 
 * @author Arkadiusz Orzechowski
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class OrderStatisticTreeMap<K, V> implements Map<K, V> {

	/**
	 * This class is a wrapper over modified java.util.TreeMap implementation.
	 * The intent is not to expose the hacked impementation too much, maybe
	 * will come up with a nicer solution to extend TreeMap someday...
	 */
	private org.aorzecho.ds.java.util.TreeMap<K, V> implementation;

	/**
	 * Constructs a new, empty tree map, using the natural ordering of its keys.
	 * All keys inserted into the map must implement the {@link Comparable}
	 * interface. Furthermore, all such keys must be <i>mutually comparable</i>:
	 * <tt>k1.compareTo(k2)</tt> must not throw a <tt>ClassCastException</tt>
	 * for any keys <tt>k1</tt> and <tt>k2</tt> in the map. If the user attempts
	 * to put a key into the map that violates this constraint (for example, the
	 * user attempts to put a string key into a map whose keys are integers),
	 * the <tt>put(Object key, Object value)</tt> call will throw a
	 * <tt>ClassCastException</tt>.
	 */
	public OrderStatisticTreeMap() {
		this.implementation = new TreeMap<K, V>();
	}

	/**
	 * Constructs a new, empty tree map, ordered according to the given
	 * comparator. All keys inserted into the map must be <i>mutually
	 * comparable</i> by the given comparator: <tt>comparator.compare(k1,
	 * k2)</tt> must not throw a <tt>ClassCastException</tt> for any keys
	 * <tt>k1</tt> and <tt>k2</tt> in the map. If the user attempts to put a key
	 * into the map that violates this constraint, the <tt>put(Object
	 * key, Object value)</tt> call will throw a <tt>ClassCastException</tt>.
	 * 
	 * @param comparator
	 *            the comparator that will be used to order this map. If
	 *            <tt>null</tt>, the {@linkplain Comparable natural ordering} of
	 *            the keys will be used.
	 */
	public OrderStatisticTreeMap(Comparator<? super K> comparator) {
		this.implementation = new TreeMap<K, V>(comparator);
	}

	/**
	 * Constructs a new tree map containing the same mappings as the given map,
	 * ordered according to the <i>natural ordering</i> of its keys. All keys
	 * inserted into the new map must implement the {@link Comparable}
	 * interface. Furthermore, all such keys must be <i>mutually comparable</i>:
	 * <tt>k1.compareTo(k2)</tt> must not throw a <tt>ClassCastException</tt>
	 * for any keys <tt>k1</tt> and <tt>k2</tt> in the map. This method runs in
	 * n*log(n) time.
	 * 
	 * @param m
	 *            the map whose mappings are to be placed in this map
	 * @throws ClassCastException
	 *             if the keys in m are not {@link Comparable}, or are not
	 *             mutually comparable
	 * @throws NullPointerException
	 *             if the specified map is null
	 */
	public OrderStatisticTreeMap(Map<? extends K, ? extends V> m) {
		this.implementation = new TreeMap<K, V>(m);
	}

	/**
	 * Constructs a new tree map containing the same mappings and using the same
	 * ordering as the specified sorted map. This method runs in linear time.
	 * 
	 * @param m
	 *            the sorted map whose mappings are to be placed in this map,
	 *            and whose comparator is to be used to sort this map
	 * @throws NullPointerException
	 *             if the specified map is null
	 */
	public OrderStatisticTreeMap(SortedMap<K, ? extends V> m) {
		this.implementation = new TreeMap<K, V>(m);
	}

	/**
	 * @see TreeMap#size()
	 */
	public int size() {
		return implementation.size();
	}

	/**
	 * @see TreeMap#isEmpty()
	 */
	public boolean isEmpty() {
		return implementation.isEmpty();
	}

	/**
	 * @see TreeMap#containsKey()
	 */
	public boolean containsKey(Object key) {
		return implementation.containsKey(key);
	}

	/**
	 * @see TreeMap#containsValue()
	 */
	public boolean containsValue(Object value) {
		return implementation.containsValue(value);
	}

	/**
	 * @see TreeMap#get()
	 */
	public V get(Object key) {
		return implementation.get(key);
	}

	/**
	 * @see TreeMap#put()
	 */
	public V put(K key, V value) {
		return implementation.put(key, value);
	}

	/**
	 * @see TreeMap#remove()
	 */
	public V remove(Object key) {
		return implementation.remove(key);
	}

	/**
	 * @see TreeMap#putAll()
	 */
	public void putAll(Map<? extends K, ? extends V> m) {
		implementation.putAll(m);
	}

	/**
	 * @see TreeMap#clear()
	 */
	public void clear() {
		implementation.clear();
	}

	/**
	 * @see TreeMap#keySet()
	 */
	public Set<K> keySet() {
		return implementation.keySet();
	}

	/**
	 * @see TreeMap#values()
	 */
	public Collection<V> values() {
		return implementation.values();
	}

	/**
	 * @see TreeMap#entrySet()
	 */
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return implementation.entrySet();
	}

	/**
	 * Returns i-th value in order of this tree. The implementation provides guaranteed
	 * log(n) time cost for this operation. 
	 *    
	 * @param index between 1 and size()
	 * @return i-th value in order
	 */
	public V getIthValue(int index) {
		return implementation.getIthValue(index);
	}

	/**
	 * Returns i-th key in order of this tree. The implementation provides guaranteed
	 * log(n) time cost for this operation. 
	 *    
	 * @param index between 0 and size()-1
	 * @return i-th value in order
	 */
	public K getIthKey(int index) {
		return implementation.getIthKey(index);
	}
}
