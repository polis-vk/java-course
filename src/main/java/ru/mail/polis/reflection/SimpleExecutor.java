package ru.mail.polis.reflection;

//import org.apache.logging.log4j.core.util.ExecutorServices;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecutor implements Executor {




    @Override
    public void execute(Runnable command) {
        Executor executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                //cdl.start await
                //action Thread.sleep(3000)
                //cdl.finish down
            });
        }

        //cdl.finish await
    }
}
