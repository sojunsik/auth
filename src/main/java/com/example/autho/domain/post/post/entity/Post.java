package com.example.autho.domain.post.post.entity;

import com.example.autho.domain.member.member.entity.Member;
import com.example.autho.global.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Post extends BaseTime {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;
    private String title;
    private String content;

}
