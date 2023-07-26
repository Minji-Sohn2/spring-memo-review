package com.example.springmemoreview.comment.entity;

import com.example.springmemoreview.comment.dto.CommentRequestDto;
import com.example.springmemoreview.common.entity.Timestamped;
import com.example.springmemoreview.memo.entity.Memo;
import com.example.springmemoreview.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(CommentRequestDto requestDto, Memo memo, User user) {
        this.content = requestDto.getContent();
        this.user = user;
        this.memo = memo;
    }
}
