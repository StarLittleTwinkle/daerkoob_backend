package com.project.daerkoob.service;

import com.project.daerkoob.domain.Comment;
import com.project.daerkoob.domain.Message;
import com.project.daerkoob.model.MessageWithList;
import com.project.daerkoob.model.TransferComment;
import com.project.daerkoob.repository.CommentRepository;
import com.project.daerkoob.repository.ReviewRepository;
import com.project.daerkoob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private ReviewService reviewService;


    public CommentService(CommentRepository commentRepository , ReviewRepository reviewRepository , UserRepository userRepository , ReviewService reviewService){
        this.commentRepository = commentRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.reviewService = reviewService;
    }

    public MessageWithList deleteComment(Long reviewId , Long commentId , Long userId){
        //자 일단 지울 수 있는지 없는지 판단해서 보내야함
        //그거 하기 전에 그러면 해당 comment를 가져와야함
        Comment comment = commentRepository.findById(commentId).get();
        Message message;
        if(comment.getWriter().getId() != userId){
            message = new Message(false, "삭제에 실패했습니다.");
        }
        else{//일단 삭제되면 대댓글까지 다 삭제 되는 걸로
            commentRepository.deleteById(commentId);
            message = new Message(true , "삭제에 성공했습니다.");
        }
        List<TransferComment> transferComments = reviewService.getCommentOfReview(reviewId , userId);
        Long totalSize = 0L;
        for(TransferComment transferComment : transferComments){
            totalSize++;
            totalSize += transferComment.getNestedCount();
        }
        return new MessageWithList(totalSize , message , new ArrayList<>(transferComments));
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public MessageWithList getCommentOfReview(Long reviewId , Long userId){
        List<TransferComment> resultList = reviewService.getCommentOfReview(reviewId , userId);
        Long totalSize = 0L;
        for(TransferComment transferComment : resultList){
            totalSize++;
            totalSize += transferComment.getNestedCount();
        }
        MessageWithList result = new MessageWithList(totalSize ,new Message(true , "댓글을 성공적으로 불러왔습니다.") ,  new ArrayList<>(resultList));
        return result;
    }

//    public List<Comment> getCommentOfComment(Long commentId){ // 이건 현재는 사용을 안함 , 이것도 곧 수정해서 사용하면 될 듯
//        return commentRepository.findById(commentId).get().getComments();
//    }

    public Comment createCommentOfReviewDto(Long userId , Long reviewId, String content){
        Comment comment = new Comment();
        LocalDateTime now = LocalDateTime.now();
        comment.setReview(reviewRepository.findById(reviewId).get());
        comment.setThumbCount(0L);
        comment.setWriter(userRepository.findById(userId).get());
        comment.setContent(content);
        comment.setRegisterDate(now);
        return comment;
    }

    public Comment createCommentOfCommentDto(Long userId ,Long commentId , String content){
        Comment comment = new Comment();
        LocalDateTime now = LocalDateTime.now();
        comment.setComment(commentRepository.findById(commentId).get());
        comment.setThumbCount(0L);
        comment.setWriter(userRepository.findById(userId).get());
        comment.setContent(content);
        comment.setRegisterDate(now);
        return comment;
    }
}
