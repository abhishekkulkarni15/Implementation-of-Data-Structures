package com.ask;

public class Node<T> {
	T element;
	int frequency;
	Node<T> left;
	Node<T> right;

	public Node(int frequency, T element) {
		this.element = element;
		this.frequency = frequency;
		left = null;
		right = null;
	}

	@Override
	public String toString() {
		return frequency + " - " + element;
	}
}
