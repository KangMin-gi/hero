package com.flowerbun.hero.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    public void 리스트_검증() {
        List<SimplyUser> list = this.service.list();
        assertThat(list.stream().map(SimplyUser::getUserName).collect(Collectors.toList()))
                .contains("Name1", "지훈킴");
    }

    @Test
    public void 이미있는_이름은_데이트를_간다() {
        String testUserName = "Name1";
       SimplyUser su = this.service.hiUser(testUserName);

        assertThat(testUserName)
                .isEqualTo(su.getUserName());
    }

    @Test
    public void 없는_이름은_오류를_뱉어() {
        String noUserName = "dnmkjg kzka,12,.5";
        assertThatThrownBy(() -> this.service.hiUser(noUserName))
                .isInstanceOf(NoSuchElementException.class);
    }
}