package ch.fhnw.brew.business.service;

import ch.fhnw.brew.data.domain.User;
import ch.fhnw.brew.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) throws Exception {
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new Exception("User already exists with username " + user.getUserName());
        }
        return userRepository.save(user);
    }

    public User editUser(Integer id, User updatedUser) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("User does not exist");
        }
        userRepository.deleteById(id);
    }

    public boolean changePassword(Integer id, String oldPw, String newPw) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
        if (user.getPassword().equals(oldPw)) {
            user.setPassword(newPw);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Integer id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }
}
