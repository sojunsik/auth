package com.example.autho.domain.member.member.controller;

import com.example.autho.domain.member.member.dto.MemberDto;
import com.example.autho.domain.member.member.entity.Member;
import com.example.autho.domain.member.member.service.MemberService;
import com.example.autho.global.dto.RsData;
import com.example.autho.global.exception.ServiceException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final MemberService memberService;

    record JoinReqBody(@NotBlank @Length(min = 3) String username,
                       @NotBlank @Length(min = 3) String password,
                       @NotBlank @Length(min = 3) String nickname) {
    }

    @PostMapping("/join")
    public RsData<MemberDto> join(@RequestBody @Valid JoinReqBody body) {


        memberService.findByUsername(body.username())
                .ifPresent(member -> {
                    throw new ServiceException("400-1", "중복된 아이디입니다.");
                });


        Member member = memberService.join(body.username(), body.password(), body.nickname());

        return new RsData<>(
                "201-1",
                "회원 가입이 완료되었습니다.",
                new MemberDto(member)
        );
    }

}