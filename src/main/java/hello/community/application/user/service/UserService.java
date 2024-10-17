package hello.community.application.user.service;

import hello.community.application.common.service.port.LocalDateTimeHolder;
import hello.community.application.common.service.port.LocalInstantHolder;
import hello.community.application.common.service.port.UuidRandomHolder;
import hello.community.application.user.controller.req.UserCreateReqDto;
import hello.community.application.user.domain.User;
import hello.community.application.user.service.port.LocalPasswordEncoder;
import hello.community.application.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final LocalDateTimeHolder localDateTimeHolder;
    private final LocalInstantHolder instantHolder;
    private final LocalPasswordEncoder passwordEncoder;
    private final UuidRandomHolder uuidRandomHolder;

    @Transactional
    public User create(UserCreateReqDto userCreateReqDto) {
        String encodedPassword = passwordEncoder.encode(userCreateReqDto.getPassword());

        User user = User.create(userCreateReqDto, encodedPassword, uuidRandomHolder,localDateTimeHolder);

        return userRepository.save(user);
    }
}
