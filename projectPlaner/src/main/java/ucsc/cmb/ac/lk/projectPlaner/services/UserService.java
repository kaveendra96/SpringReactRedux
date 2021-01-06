package ucsc.cmb.ac.lk.projectPlaner.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucsc.cmb.ac.lk.projectPlaner.domain.User;
import ucsc.cmb.ac.lk.projectPlaner.exceptions.UserNameAlreadyExistsException;
import ucsc.cmb.ac.lk.projectPlaner.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setUsername(newUser.getUsername());
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UserNameAlreadyExistsException("UserName  : " + newUser.getUsername() + " already exsist");
        }
    }

}
