package br.com.alysongustavoti.backendtodo.service;

import br.com.alysongustavoti.backendtodo.model.User;
import br.com.alysongustavoti.backendtodo.repository.UserRepository;
import br.com.alysongustavoti.backendtodo.service.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user){

        Optional<User> userFind = userRepository.findByEmail(user.getEmail());

        if(userFind.isPresent()){
            throw new BusinessException("User with informed email already registered.");
        }

        User userInsert =  userRepository.save(user);

        return userInsert;
    }

    public User findUser(Integer id){

        Optional<User> userFind = userRepository.findById(id);

        if(userFind.isEmpty()){
            throw new BusinessException("User not found.");
        }

        return userFind.get();

    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(User user, Integer id){

        Optional<User> userUpdate = userRepository.findById(id);

        if(userUpdate.isEmpty()){
            throw new BusinessException("User not found.");
        }

        User userConvert = userUpdate.get();

        BeanUtils.copyProperties(user, userConvert);

        User userUpdated = userRepository.save(userConvert);

        return userUpdated;

    }
}
