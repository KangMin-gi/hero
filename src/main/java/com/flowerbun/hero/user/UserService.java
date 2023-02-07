package com.flowerbun.hero.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public SimplyUser hiUser(String userName) {
        User user = this.repository.findUserName(userName)
                .orElseThrow();
        return new SimplyUser(user);

    }

    public List<SimplyUser> list() {
        return this.repository.findAll().stream()
                .map(SimplyUser::new)
                .collect(Collectors.toList());
    }
}
