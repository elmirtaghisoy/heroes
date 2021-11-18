package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.User;
import az.netx.heroes.model.request.UserAddRequest;
import az.netx.heroes.model.request.UserRequest;
import az.netx.heroes.model.response.UserResponse;
import az.netx.heroes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.disabled(user.getIsEnable());
            userBuilder.password(user.getPassword());

            String[] authoritiesArr = {"ADMIN"};
            userBuilder.authorities(authoritiesArr);
        } else {
            throw new UsernameNotFoundException("User not found !!!");
        }
        return userBuilder.build();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String activateUser(UserRequest request) {
        if (request.getPassword1().equals(request.getPassword2())) {
            if (Objects.nonNull(userRepository.findByUsername(request.getUsername())))
                return "USERNAME_ALREADY_EXIST";
            User entity = objectMapper.R2E(request);
            entity.setStatus("ACTIVATED");
            entity.setPassword(passwordEncoder.encode(request.getPassword1()));
            userRepository.save(entity);
            return "SUCCESS";
        } else
            return "INVALID_PASS";
    }

    public List<UserResponse> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public String saveUser(UserAddRequest request) {
        if (Objects.nonNull(userRepository.findByUsername(request.getUsername())))
            return "USERNAME_ALREADY_EXIST";
        userRepository.save(objectMapper.AR2E(request));
        return "SUCCESS";
    }

    public UserResponse getUserById(Long id) {
        return objectMapper.E2R(userRepository.getById(id));
    }

    public void userActivity(Long id, String action) {
        User user = userRepository.getById(id);
        if (action.equalsIgnoreCase("unblock")) {
            user.setIsEnable(true);
        } else if (action.equalsIgnoreCase("block")) {
            user.setIsEnable(false);
        }
        userRepository.save(user);
    }

    public void resetUser(Long id) {
        User user = userRepository.getById(id);
        user.setPassword(passwordEncoder.encode("123"));
        user.setStatus("DEACTIVE");
        userRepository.save(user);
    }
}