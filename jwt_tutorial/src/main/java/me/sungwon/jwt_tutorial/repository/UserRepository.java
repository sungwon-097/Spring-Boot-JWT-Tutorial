package me.sungwon.jwt_tutorial.repository;

import me.sungwon.jwt_tutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // extends 하는 것 만으로 find, save 관련 메소드 이용 가능
    @EntityGraph(attributePaths = "authorities") // Lazy 조회가 아닌 Eager 조회로 authorities 정보를 같이 가져옴
    Optional<User> findOneWithAuthoritiesByUsername(String username); // User 정보를 가져올 때 권한정보도 함께 가져옴
}