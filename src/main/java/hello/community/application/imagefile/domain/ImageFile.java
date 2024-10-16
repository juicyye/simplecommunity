package hello.community.application.imagefile.domain;

import hello.community.application.post.domain.Post;
import hello.community.application.user.domain.User;

public class ImageFile {
    private Long id;
    private String storeFileName;
    private String originalFileName;
    private User user;
    private Post post;
}
