package br.com.robson.seniorhotel.service.impl;


import br.com.robson.seniorhotel.model.UserClient;
import br.com.robson.seniorhotel.repository.UserClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserClientServiceImpl {
    @Autowired
    private UserClientRepository userClientRepository;

    public UserClient createUserClient(UserClient userClient){
        return  userClientRepository.save(userClient);
    }

    public Optional<UserClient> findUserById(Long id){
        return userClientRepository.findById(id);
    }



}
