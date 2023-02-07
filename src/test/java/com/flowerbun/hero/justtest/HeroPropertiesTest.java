package com.flowerbun.hero.justtest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
public class HeroPropertiesTest {

    @Value(value = "${profile.env}")
    String profileEnv;

    @Value(value = "${jpa.hibernate.naming.physical-strategy")
    String strategy;

    @Test
    public void profileEnv와_profile의_실제_이름은_똑같다_hamcrest() {
        assertThat("local", equalTo(this.profileEnv));
    }

    @Test
    public void profileEnv와_profile의_실제_이름은_똑같다_assertJ() {
        assertThat("local")
            .isEqualTo(this.profileEnv);
    }

    @Test
    public void jpaNamingStrategy() {
        System.out.println("strategy = " + strategy);
    }

}
