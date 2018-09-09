package ask171730;

import java.util.Scanner;

public class BoundedQueue<T> {

	private T queue[];
	private int size;
	private int queueSize;
	private int front, rear;

	// Constructor
	BoundedQueue(int size) {
		this.size = size;
		queue = (T[]) new Object[size];
		front = 0;
		rear = 0;
		queueSize = 0;
	}

	private boolean isFull() {
		return (queueSize == size);
	}

	// Add a new element x at the end of the queue. returns false if queue is full
	// or element is not added in the queue, otherwise true
	public boolean offer(T element) {
		if (isFull())
			return false;
		queue[rear] = element;
		if (rear == (size - 1))
			rear = 0;
		else
			rear++;
		queueSize++;
		return true;
	}

	// remove and returns the element at front of the queue, otherwise null
	public T poll() {
		if (isEmpty())
			return null;
		T element = queue[front];
		queue[front] = null;
		if (front == (size - 1))
			front = 0;
		else
			front++;
		queueSize--;
		return element;
	}

	// return front element without removing it, otherwise null
	public T peek() {
		if (isEmpty())
			return null;
		return queue[front];
	}

	// returns the number of elements in the array
	public int size() {
		return queueSize;
	}

	// check if queue is empty
	public boolean isEmpty() {
		return (queueSize == 0);
	}

	// make queue size 0
	public void clear() {
		for (int i = 0; i < size; i++)
			queue[i] = null;
		rear = front = 0;
		queueSize = 0;
		size = 0;
	}

	// fill user supplied array with the elements of the queue, in queue order
	public void toArray(T[] a) {
		if (queueSize == 0)
			System.out.println("Nothing to copy");
		for (int i = 0; i < queueSize; i++) {
			if ((front + i) < size)
				a[i] = queue[front + i];
			else
				a[i] = queue[front];
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size of the queue: ");
		int size = sc.nextInt();
		BoundedQueue<Integer> queue = new BoundedQueue<>(size);
		int choice;
		boolean cont = true;
		do {
			System.out.println(
					"\n1. Offer\n2. Poll\n3. Peek\n4. Size\n5. isEmpty\n6. Clear\n7. toArray\nEnter your choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter element: ");
				Integer ele = sc.nextInt();
				if (queue.offer(ele))
					System.out.println("Element successfully inserted");
				else
					System.out.println("Queue full.");
				break;
			case 2:
				Integer element = queue.poll();
				if (element == null)
					System.out.println("Queue is empty.");
				else
					System.out.println("Element: " + element);
				break;
			case 3:
				if (queue.peek() == null)
					System.out.println("Queue is empty.");
				else
					System.out.println("Element: " + queue.peek());
				break;
			case 4:
				System.out.println("Size: " + queue.size());
				break;
			case 5:
				if (queue.isEmpty() == true)
					System.out.println("Queue is empty.");
				else
					System.out.println("Queue is not empty.");
				break;
			case 6:
				queue.clear();
				System.out.println("Queue cleared");
			case 7:
				Integer array[] = new Integer[queue.size];
				queue.toArray(array);
				for (Integer i : array)
					System.out.print(i + " ");
				break;
			default:
				System.out.println("Invalid case.");
				cont = false;
				break;
			}
		} while (cont);
		sc.close();
	}
}
