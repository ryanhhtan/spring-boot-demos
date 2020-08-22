package com.example.springdatajdbcjooqdemo.domain.user;

import static com.example.springdatajdbcjooqdemo.jooq.tables.Addresses.ADDRESSES;
import static com.example.springdatajdbcjooqdemo.jooq.tables.Users.USERS;

import java.util.Optional;
import java.util.stream.Collectors;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * UserJooqRepository
 */
@RequiredArgsConstructor
@Component
public class UserJooqRepository {
  @NonNull
  private DSLContext create;

  public Optional<User> findById(final Long id) {
    return create.select().from(USERS).where(USERS.ID.eq(id)).fetchOptionalInto(User.class)
        .map(user -> user
            .setAddresses(create.select().from(ADDRESSES).where(ADDRESSES.USER_ID.eq(user.getId()))
                .fetchStreamInto(Address.class).collect(Collectors.toSet())));
  }
}
