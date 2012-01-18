Data Structures
===============
This project contains some useful but less common data structures (well, at least one as of now :)

Order Statistic Tree Map
------------------------
`org.aorzecho.ds.OrderStatisticTreeMap` is a TreeMap implementation, based on java.util.TreeMap code, augmented with order statistic data. It allows to retrieve elements with given rank (i.e. *i-th* element in order) with guaranteed log(n) time cost. Algorithms are adaptations of those in Cormen, Leiserson, Rivest and Stein's *Introduction to Algorithms*.

A sample use case for this data structure would be a news site allowing access to all historical news. The older news can be acessed using pagination, what can be done using  e.g. sorted array, but then the updates would be highly inefficient. OrderStatisticTreeMap provides efficient updates while allowing log(n) time access to the sub-map starting at position *i*, e.g. `ostm.tailMap(ostm.getIthiKey(i),true)`  


Free use of this software is granted under the terms of the GNU General Public License (GPL)
