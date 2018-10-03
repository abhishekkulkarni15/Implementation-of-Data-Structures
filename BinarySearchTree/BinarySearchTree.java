package com.ask;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {

	static class Entry<T> {
		T element;
		Entry<T> left, right;

		public Entry(T x, Entry<T> left, Entry<T> right) {
			this.element = x;
			this.left = left;
			this.right = right;
		}
	}

	Entry<T> root;
	int size;// Size of the tree
	Stack<Entry<T>> stack; // Stack declaration to keep track of parent nodes

	public BinarySearchTree() {
		root = null;
		size = 0;
		stack = new Stack<Entry<T>>();
	}

	// finds the node with element x or gives the node at which the search failed.
	private Entry<T> find(T x) {
		stack.push(null);
		return find(root, x);
	}

	private Entry<T> find(Entry<T> t, T x) {
		int compareResult;
		if ((t == null) || (t.element.compareTo(x) == 0))
			return t;
		while (true) {
			compareResult = t.element.compareTo(x);
			if (compareResult == 0)
				break;
			else if (compareResult == 1) {
				if (t.left == null)
					break;
				else {
					stack.push(t);
					t = t.left;
				}
			} else {
				if (t.right == null)
					break;
				else {
					stack.push(t);
					t = t.right;
				}
			}
		}
		return t;
	}

	// Returns the element if found and null if the element is not found.
	public boolean contains(T x) {
		return (get(x) != null);
	}

	public T get(T x) {
		T returnedElement = find(x).element;
		if (returnedElement == x)
			return returnedElement;
		else
			return null;
	}

	// add elemenst to Binary Search Tree
	public boolean add(T x) {
		if (size == 0) {
			root = new Entry<T>(x, null, null);
			size = 1;
			return true;
		} else {
			Entry<T> t = find(x);
			int compareResult = t.element.compareTo(x);
			if (compareResult == 0) {
				t.element = x;
				return false;
			} else {
				if (compareResult == 1)
					t.left = new Entry<T>(x, null, null);
				else
					t.right = new Entry<T>(x, null, null);
				size++;
				return true;
			}
		}
	}

	// Remove elements to the Binaray Search Tree
	public T remove(T x) {
		if (root == null)
			return null;
		Entry<T> t = find(x);
		if (t.element.compareTo(x) != 0)
			return null;
		T result = t.element;
		if (t.left == null || t.right == null)
			bypass(t);
		else {
			stack.push(t);
			Entry<T> minRight = find(t.right, x);
			t.element = minRight.element;
			bypass(minRight);
		}
		size--;
		return result;
	}

	// Bypass the node that was removed
	private void bypass(Entry<T> t) {
		Entry<T> parent = stack.peek();
		Entry<T> child = (t.left == null) ? t.right : t.left;
		if (parent == null)
			root = child;
		else if (parent.left == t)
			parent.left = child;
		else
			parent.right = child;
	}

	// least element in the BST
	public T min() {
		if (size == 0)
			return null;
		Entry<T> current = root;
		while (current.left != null)
			current = current.left;
		return current.element;
	}

	// Largest element in the BST
	public T max() {
		if (size == 0)
			return null;
		Entry<T> current = root;
		while (current.right != null)
			current = current.right;
		return current.element;
	}

	// Converting the elements from the tree to an array
	public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		addToArray(root, arr, -1);
		return arr;
	}

	// adding elements to the array
	private int addToArray(Entry<T> entry, Comparable[] arr, int index) {
		if (entry == null)
			return index;
		index = addToArray(entry.left, arr, index);
		arr[++index] = entry.element;
		index = addToArray(entry.right, arr, index);
		return index;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("1. Add  2. Remove  3. Search  4. ToArray  5. PrintTree  6. Min  7. Max  8. Exit");
			int x = in.nextInt();
			switch (x) {
			case 1:
				System.out.println("Enter the element to be added: ");
				int num = in.nextInt();
				bst.add(num);
				bst.printTree();
				break;
			case 2:
				System.out.println("Enter the element to be removed: ");
				num = in.nextInt();
				bst.remove(num);
				bst.printTree();
				break;
			case 3:
				System.out.println("Enter the element to be searched: ");
				num = in.nextInt();
				System.out.println(bst.contains(num));
				break;
			case 4:
				Comparable[] arr = bst.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < bst.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				break;
			case 5:
				bst.printTree();
				break;
			case 6:
				System.out.println(bst.min());
				break;
			case 7:
				System.out.println(bst.max());
				break;
			case 8:
				System.out.println("Exit(0)");
				in.close();
				System.exit(0);
				break;
			default:
				System.out.println("Enter proper choice.");
				break;
			}
		}
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}
}
