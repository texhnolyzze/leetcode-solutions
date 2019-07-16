import java.util.concurrent.atomic.AtomicInteger;


class Foo {

    
        public Foo() {}
        private final AtomicInteger n = new AtomicInteger(); 

        public synchronized void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            n.incrementAndGet();
            notifyAll();
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {
            while (n.get() != 1) {
                wait();
            }
            printSecond.run();
            n.incrementAndGet();
            notify();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {
            while (n.get() != 2) {
                wait();
            }
            printThird.run();
        }
        
}