package hello.community.application.post.service;

import static hello.community.application.common.service.ErrorMessage.POST_NOT_FOUND;
import static hello.community.application.common.service.ErrorMessage.USER_NOT_FOUND;

import hello.community.application.common.domain.exception.CustomApiException;
import hello.community.application.common.service.port.LocalDateTimeHolder;
import hello.community.application.common.service.port.UuidRandomHolder;
import hello.community.application.post.controller.req.PostCreateReqDto;
import hello.community.application.post.domain.Post;
import hello.community.application.post.domain.PostStatus;
import hello.community.application.post.service.port.PostRepository;
import hello.community.application.user.domain.User;
import hello.community.application.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UuidRandomHolder uuidRandomHolder;
    private final LocalDateTimeHolder localDateTimeHolder;
    private final UserRepository userRepository;

    @Transactional
    public Post create(PostCreateReqDto postCreateReqDto) {
        User user = userRepository.findByUID(postCreateReqDto.getWriterUID())
                .orElseThrow(() -> new CustomApiException(USER_NOT_FOUND.getDescription()));

        Post post = Post.create(postCreateReqDto, uuidRandomHolder, localDateTimeHolder, user);
        return postRepository.save(post);
    }

    public Post getAcitvePost(String UID) {
        return postRepository.findByUIDAndStatus(UID, PostStatus.ACTIVE)
                .orElseThrow(() -> new CustomApiException(POST_NOT_FOUND.getDescription()));
    }
}
