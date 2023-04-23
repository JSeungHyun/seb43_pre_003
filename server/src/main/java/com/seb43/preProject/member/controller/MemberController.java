package com.seb43.preProject.member.controller;

import com.seb43.preProject.member.dto.MemberPatchDto;
import com.seb43.preProject.member.dto.MemberPostDto;
import com.seb43.preProject.member.entity.Member;
import com.seb43.preProject.member.mapper.MemberMapper;
import com.seb43.preProject.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

//@RestController
@Controller
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }



//    @GetMapping("/join")
//    public String registerMemberForm() {
//        return "member-join";
//    }
//    @PostMapping("/join") //회원가입
//    public String postMember1(@Valid MemberPostDto memberPostDto) {
//        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(memberPostDto));
//
//        System.out.println("Member Registration Successfully");
//        return "login";
//    }

    @PostMapping("/join") // 회원가입
    public ResponseEntity postMember(@Valid @RequestBody MemberPostDto memberPostDto) {
        Member member = memberService.createMember(memberMapper.memberPostDtoToMember(memberPostDto));

        return new ResponseEntity<>(
                (memberMapper.memberToMemberResponse(member)), HttpStatus.CREATED);
    }



    @GetMapping("/{member-id}/profile")// 프로필 조회
    public ResponseEntity getMember(@PathVariable("member-id") @Positive long memberId){
        Member member = memberService.findVerifiedMember(memberId);

        return new ResponseEntity<>((memberMapper.memberToMemberResponse(member)),HttpStatus.OK);

    }
    @PatchMapping("/{member-id}/profile")//프로필 수정
    public ResponseEntity patchMember(@PathVariable("member-id") @Positive long memberId,
                                      @Valid @RequestBody MemberPatchDto memberPatchDto){
        Member member = memberService.updateMember(memberPatchDto,memberId);

        return new ResponseEntity<>((memberMapper.memberToMemberResponse(member)),HttpStatus.OK);
    }
    @DeleteMapping("/{member-id}/profile/delete")// 회원탈퇴 (memberStatus 탈퇴상태로 변경 memberId는 삭제되지않음)
    public ResponseEntity deleteMember(@PathVariable("member-id") @Positive long memberId){
        Member member = memberService.deleteMember(memberId);

        return new ResponseEntity<>((memberMapper.memberToMemberResponse(member)),HttpStatus.OK);
    }

}
