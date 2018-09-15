package com.ask;

/* 
 * Author: Abhishek Kulkarni 
 * Code: Huffman code
 * */

import java.util.PriorityQueue;

public class HuffmanCode {

	// Generate priority queue based upon frequency
	// used a custom comparator nodeComparator which compares frequencies
	public static PriorityQueue<Node<Integer>> addToQueue(int freq[], int val[]) {
		PriorityQueue<Node<Integer>> pqueue = new PriorityQueue<>(new NodeComparator());
		for (int i = 0; i < freq.length; i++) {
			Node<Integer> node = new Node<>(freq[i], val[i]);
			pqueue.add(node);
		}
		return pqueue;
	}

	// generates tree based upon the node
	public static Node<Integer> generateTree(PriorityQueue<Node<Integer>> pqueue) {
		if (pqueue.isEmpty())
			return null;
		while (!pqueue.isEmpty()) {
			Node<Integer> n1 = pqueue.poll();
			Node<Integer> n2 = null;
			if (pqueue.peek() == null)
				return n1;
			n2 = pqueue.poll();
			Node<Integer> node = new Node<>((n1.frequency + n2.frequency), -1);
			if (n1.frequency < n2.frequency) {
				node.left = n1;
				node.right = n2;
			} else {
				node.left = n2;
				node.right = n1;
			}
			pqueue.add(node);
		}
		return null;
	}

	// generate Huffman codes
	public static void generateHuffmanCode(Node<Integer> node, String str) {
		if (node == null)
			return;
		if (node.left == null && node.right == null)
			System.out.println(node.element + " - " + str);
		generateHuffmanCode(node.left, (str + "0"));
		generateHuffmanCode(node.right, (str + "1"));
	}

	public static void main(String[] args) {
		int freq[] = { 9, 12, 13, 16, 45, 5 };
		int val[] = { 2, 3, 4, 5, 6, 1 };
		PriorityQueue<Node<Integer>> pqueue = addToQueue(freq, val);
		Node<Integer> node = generateTree(pqueue);
		generateHuffmanCode(node, "");
	}
}
