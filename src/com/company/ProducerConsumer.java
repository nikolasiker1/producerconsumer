package com.company;

import java.util.LinkedList;

public class ProducerConsumer {

    private LinkedList<Integer> buffer = new LinkedList<>();
    private int capacity;
    private int count = 0;

    public ProducerConsumer() {
        this.capacity = 5;
    }

    public ProducerConsumer(int capacity) {
        this.capacity = capacity;
    }

    public void produce() throws InterruptedException {
        int valueToAdd = 0;


        while (true) {
            synchronized (this) {
                if (buffer.size() < capacity) {

                        System.out.println("Produced: " + valueToAdd + "");
                        buffer.add(valueToAdd++);
                        Thread.sleep(1000);

                }
                else {
                    notify();
                    wait();
                }

            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (buffer.size() > 0) {

                        int consumedInt = buffer.removeFirst();
                        System.out.println("Consumed: " + consumedInt);
                        Thread.sleep(1000);
                }
                else {
                    notify();
                    wait();
                }

            }
        }
    }

}
