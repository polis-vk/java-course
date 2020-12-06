package ru.mail.polis.concurrency;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@State
@Outcome(id = "0, 0", expect = Expect.ACCEPTABLE)
@Outcome(id = "0, 1", expect = Expect.ACCEPTABLE)
@Outcome(id = "1, 0", expect = Expect.ACCEPTABLE)
public class ConcurrencyTests2 {
    volatile int x;
//    volatile int y;

    @Actor
    public void threadP(II_Result r) {
        r.r1 = x++;
    }

    @Actor
    public void threadQ(II_Result r)  {
        r.r2 = x++;
    }

}
