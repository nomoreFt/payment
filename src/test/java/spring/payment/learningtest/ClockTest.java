package spring.payment.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
    //Clock을 사용해서 LocalDateTime.now()를 반환하는 코드를 작성하고 테스트하라.
    @Test
    void clock() throws InterruptedException {
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime dt1 = LocalDateTime.now(clock);
        //time sleep
        Thread.sleep(1);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2).isAfter(dt1);

    }

    @Test
    void fixedClock(){
        Clock fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime d1 = LocalDateTime.now(fixedClock);
        LocalDateTime d2 = LocalDateTime.now(fixedClock);

        LocalDateTime d3 = LocalDateTime.now(fixedClock).plusHours(1);

        Assertions.assertThat(d2).isEqualTo(d1);
        Assertions.assertThat(d3).isEqualTo(d1.plusHours(1));

    }
}
