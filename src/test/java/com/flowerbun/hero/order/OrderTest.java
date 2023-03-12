package com.flowerbun.hero.order;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("local")
class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void order번호가_0인친구는_없다() {
        RuntimeException rte = new RuntimeException("NoOrder");
        assertThatThrownBy(() -> this.orderRepository.findById(0L)
                        .orElseThrow(() -> rte))
                .isInstanceOf(rte.getClass())
                .hasMessage("NoOrder");
    }

}