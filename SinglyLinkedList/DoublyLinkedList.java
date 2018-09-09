package ask171730;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {

	public DoublyLinkedList() {
		super();
	}

	public static void main(String[] args) {
		int n = 10;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

		DoublyLinkedList<Integer> lst = new DoublyLinkedList<>();
		for (int i = 1; i <= n; i++) {
			lst.add(Integer.valueOf(i));
		}
		lst.printList();
		DoublyLinkedListIterator<Integer> dllIterator = lst.dllIterator();
		Scanner in = new Scanner(System.in);
		whileloop: while (in.hasNext()) {
			int com = in.nextInt();
			switch (com) {
			case 1:
				// Move to next element and print it
				if (dllIterator.hasNext()) {
					System.out.println(dllIterator.next());
				} else {
					break whileloop;
				}
				break;
			case 2:
				// Remove element
				try {
					dllIterator.remove();
					lst.printList();
				} catch (Exception e) {
					System.out.println("You should use next before removing element!");
				}
				break;
			case 3:
				if (dllIterator.hasPrevious()) {
					System.out.println(dllIterator.previous());
				} else {
					break whileloop;
				}
				break;
			case 4:
				// Add new number to list
				System.out.print("Enter the number that you want to add:");
				dllIterator.add(in.nextInt());
				lst.printList();
				break;
			default:
				// Exit loop
				break whileloop;
			}
		}
	}

	public DLLIterator dllIterator() {
		return new DLLIterator();
	}

	public void add(T x) {

		super.add(new Entry<>(x, null, tail));
	}

	public void printList() {
		System.out.print(this.size + ": ");
		for (T item : this) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	public interface DoublyLinkedListIterator<T> {

		boolean hasNext();

		boolean hasPrevious();

		T next();

		T previous();

		void add(T x);

		void remove();
	}

	static class Entry<E> extends SinglyLinkedList.Entry<E> {

		SinglyLinkedList.Entry<E> prev;

		Entry(E x, SinglyLinkedList.Entry<E> next, SinglyLinkedList.Entry<E> prev) {
			super(x, next);
			this.prev = prev;
		}
	}

	protected class DLLIterator extends SLLIterator implements DoublyLinkedListIterator<T> {

		DLLIterator() {
			super();
		}

		// Returns true if cursor has previous
		public boolean hasPrevious() {
			if (cursor == head || cursor == head.next)
				return false;
			else
				return true;
		}

		// Returns the element before the cursor
		public T previous() {
			cursor = ((Entry<T>) cursor).prev;
			ready = true;
			return cursor.element;
		}

		// Add element after the cursor and set cursor at new element
		public void add(T x) {
			Entry<T> obj = new Entry<>(x, null, null);
			if (cursor == tail) {
				tail.next = obj;
				obj.prev = cursor;
				tail = obj;

			} else {
				SinglyLinkedList.Entry<T> nxt = cursor.next;
				cursor.next = obj;
				obj.prev = cursor;
				obj.next = nxt;
				((Entry<T>) nxt).prev = obj;
			}
			cursor = obj;
			ready = false;
			size++;
		}

		// Remove element pointed by the cursor and set cursor to previous
		public void remove() {
			if (!ready) {
				throw new NoSuchElementException();
			}
			if (cursor == tail) {
				tail = ((Entry<T>) tail).prev;
				tail.next = null;
				cursor = tail;
			} else {
				((Entry<T>) cursor).prev.next = cursor.next;
				((Entry<T>) cursor.next).prev = ((Entry<T>) cursor).prev;
				cursor = ((Entry<T>) cursor).prev;
			}
			ready = false;
			size--;
		}
	}
}