package com.econovation.tcono.domain.heart;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.econovation.tcono.domain.post.Post;
import com.econovation.tcono.domain.post.PostRepository;
import com.econovation.tcono.domain.user.User;
import com.econovation.tcono.domain.user.UserRepository;
import com.econovation.tcono.web.dto.HeartRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HeartMutationResolver implements GraphQLMutationResolver {
    private static final String NOT_FOUND_USER_MESSAGE = "존재하지 않은 회원입니다.";
    private UserRepository userRepository;
    private PostRepository postRepository;
    private HeartRepository heartRepository;
    private Post post;

    /**
     * 특정 User가 Post에 좋아요를 누름 -> true 반환
     * @param
     * @return boolean
     */
    public boolean isHeartByUser(HeartRequestDto heartAddRequestDto) {
        // 존재하는 User인지 조사
        User user = userRepository.findById(heartAddRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER_MESSAGE));

        // 존재하는 Post인지 조사
        Post post = postRepository.findById(heartAddRequestDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_USER_MESSAGE));

        //해당 User가 heart를 눌렀는지 조사
        Optional<Heart> findHeart = heartRepository.findHeartByUserIdAndPostid(heartAddRequestDto.getUserId(), heartAddRequestDto.getPostId());

        return findHeart.isPresent();
    }

    public int updateHeartState(HeartRequestDto heartRequestDto) {
        //좋아요 누름 -> true 반환
        boolean isHeart = isHeartByUser(heartRequestDto);

        //좋아요 삭제
//        if (isHeart) {
//            heartRepository.deleteHeartByUserIdAndPostId(heartRequestDto.getUserId(),heartRequestDto.getPostId());
//            return post.decreaseHearts();
//        } else { // 좋아요 추가
//            Post post=postRepository.getById(heartRequestDto.getPostId());
//            heartRepository.save(heartRequestDto.toEntity(post));
//            return post.increaseHearts();
//        }
        return 1;
    }
}