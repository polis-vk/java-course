package ru.mail.polis.concurrency;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

@JCStressTest
@State
@Outcome(id = "0, 1", expect = Expect.ACCEPTABLE)
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE)
@Outcome(id = "1, 0", expect = Expect.ACCEPTABLE)
public class ConcurrencyTests1 {
    int x;
    int y;

    @Actor
    public void threadP(II_Result r) {
        x = 1;
        r.r1 = y;
    }


    @Actor
    public void threadQ(II_Result r) {
        y = 1;
        r.r2 = x;
    }

}
