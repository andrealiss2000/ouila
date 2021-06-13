package com.lpiot.ouila.repositories;

import com.lpiot.ouila.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
