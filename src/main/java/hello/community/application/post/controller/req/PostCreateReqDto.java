package hello.community.application.post.controller.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateReqDto {

    private String title;
    private String content;
    private String writerUID;
}
