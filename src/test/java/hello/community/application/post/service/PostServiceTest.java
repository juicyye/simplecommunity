package hello.community.application.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import hello.community.application.common.domain.exception.CustomApiException;
import hello.community.application.common.service.ErrorMessage;
import hello.community.application.post.controller.req.PostCreateReqDto;
import hello.community.application.post.domain.Post;
import hello.community.application.post.domain.PostStatus;
import hello.community.application.user.domain.Role;
import hello.community.application.user.domain.User;
import hello.community.application.user.domain.UserStatus;
import hello.community.mock.FakeLocalDateTimeHolder;
import hello.community.mock.FakePostRepository;
import hello.community.mock.FakeUserRepository;
import hello.community.mock.FakeUuidRandomHolder;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private PostService postService;
    private LocalDateTime localDateTime = LocalDateTime.of(2024, 10, 16, 19, 41, 30);
    private FakePostRepository postRepository;

    @BeforeEach
    void setup() {
        FakeUserRepository userRepository = new FakeUserRepository();
        this.postRepository = new FakePostRepository();
        this.postService = new PostService(
                postRepository,
                new FakeUuidRandomHolder("포스트랜덤"),
                new FakeLocalDateTimeHolder(localDateTime),
                userRepository
        );

        User user = User.builder()
                .email("email@email.com")
                .password("password")
                .role(Role.ROLE_USER)
                .status(UserStatus.ACTIVE)
                .nickname("nickname")
                .UID("랜덤")
                .createdAt(localDateTime)
                .updatedAt(localDateTime)
                .build();
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("post를 생성할 수 있다")
    void postCreate() throws Exception {
        // given
        PostCreateReqDto postCreateReqDto = new PostCreateReqDto("title","content","랜덤");

        // when
        Post savedPost = postService.create(postCreateReqDto);

        // then
        assertThat(savedPost).isNotNull()
                .extracting("title", "content", "createdAt", "updatedAt","UID")
                .containsExactlyInAnyOrder("title","content",localDateTime,localDateTime,"포스트랜덤");
    }

    @Test
    @DisplayName("생성한 post가 active인 경우에 찾을 수 있다")
    void findActivePost() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 10, 16, 19, 41, 30);
        Post post = createPost(1L, "title", "content", 1L, PostStatus.ACTIVE, "포스트랜덤", now);
        postRepository.save(post);

        // when
        Post findPost = postService.getAcitvePost("포스트랜덤");

        // then

        assertThat(findPost).isNotNull()
                .extracting("title", "content", "createdAt", "updatedAt","UID")
                .containsExactlyInAnyOrder("title","content",now,now,"포스트랜덤");
    }

    @Test
    @DisplayName("post Deleted인 경우에 찾을 수 없다")
    void notFoundPost() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.of(2024, 10, 16, 19, 41, 30);
        Post post = createPost(1L, "title", "content", 1L, PostStatus.DELETED, "포스트랜덤", now);
        postRepository.save(post);

        // when
        assertThatThrownBy(() -> postService.getAcitvePost("포스트랜덤"))
                .isInstanceOf(CustomApiException.class)
                .hasMessage(ErrorMessage.POST_NOT_FOUND.getDescription());


        // then

    }

    private static Post createPost(long id, String title, String content, long views, PostStatus postStatus, String UID,
                                   LocalDateTime now) {
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .views(views)
                .status(postStatus)
                .UID(UID)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

}