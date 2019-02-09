package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Iterator;

public interface PLIteratorMaker<E> {
	Iterator<Position<E>> makeIterator(PositionalList<E> pl);
}
