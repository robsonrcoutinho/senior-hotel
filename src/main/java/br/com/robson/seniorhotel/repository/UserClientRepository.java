package br.com.robson.seniorhotel.repository;

import br.com.robson.seniorhotel.model.UserClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClientRepository extends JpaRepository<UserClient, Long> {
}
