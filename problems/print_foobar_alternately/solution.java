class FooBar {
        
        private final int n;
        private boolean flag = false;

        public FooBar(int n) {
            this.n = n;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                if (flag)
                    wait();
                printFoo.run();
                notify();
                flag = true;
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                if (!flag) 
                    wait();
                printBar.run();
                notify();
                flag = false;
            }
        }
        
    }