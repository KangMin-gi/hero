package com.flowerbun.hero;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HeroApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void canLoadLomBokDependencies() {
        String firstValue = MyEnum.FIRST.getFirstValue();
        assertThat(firstValue)
                .isEqualTo("first");
    }

    @Getter
    @AllArgsConstructor
    public enum MyEnum {
        FIRST("first");
        private String firstValue;
    }

}
