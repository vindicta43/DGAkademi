import java.util.Scanner;

/**
 * Created by Alperen on 23.07.2022.
 */
public class QueueExample {
    static LinkedListQueue myQueue = new LinkedListQueue();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            int choice;
            System.out.println("Make a choice");
            System.out.println("1-) enqueue");
            System.out.println("2-) dequeue");
            System.out.println("3-) get size of queue");
            System.out.println("4-) check queue (isEmpty)");
            System.out.println("0-) exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            if (choice == 0)
                break;

            switch (choice) {
                case 1:
                    int element;
                    System.out.print("enqueue element: ");
                    element = sc.nextInt();
                    myQueue.enqueue(element);
                    break;

                case 2:
                    myQueue.dequeue();
                    break;

                case 3:
                    myQueue.getQueueSize();
                    break;

                case 4:
                    System.out.println(myQueue.isQueueEmpty());
                    break;

                default:
                    System.out.println("Wrong choice.");
                    break;
            }
        }
    }
}

class LinkedListQueue {
    private CustomNode front, rear;
    private int queueSize;

    public LinkedListQueue() {
        front = null;
        rear = null;
        queueSize = 0;
    }

    private class CustomNode {
        int data;
        CustomNode next;

        public CustomNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Adds a new element to the back of the queue
    public void enqueue(int element) {

        CustomNode temp = new CustomNode(element);

        // Initialization or if queue is empty
        if (rear == null) {
            front = temp;
        } else {
            rear.next = temp;
        }
        rear = temp;
        queueSize++;
        System.out.println(temp.data + " added queue.");
    }

    // Returns the element at the beginning of the queue
    public void dequeue() {

        // If queue is empty
        if (queueSize == 0) {
            System.out.println("Queue is empty");
        } else {
            CustomNode temp = front;
            front = front.next;
            queueSize--;
            System.out.println(temp.data + " removed queue.");
        }
    }

    public void getQueueSize() {
        System.out.println("Queue size: " + queueSize);
    }

    public boolean isQueueEmpty() {
        return queueSize == 0;
    }
}

