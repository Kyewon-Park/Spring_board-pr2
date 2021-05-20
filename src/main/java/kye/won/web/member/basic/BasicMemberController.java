package kye.won.web.member.basic;

import kye.won.domain.member.Member;
import kye.won.domain.member.MemberRepository;
import kye.won.web.LogInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/account/members")
@RequiredArgsConstructor
public class BasicMemberController extends LogInterceptor {
    private final MemberRepository memberRepository;

    @PostConstruct
    private void initMember(){
        log.info("post construct");
        Member member1 = new Member("member1", "pass1", "Paul");
        Member member2 = new Member("member2", "pass2", "Lee");
        memberRepository.save(member1);
        memberRepository.save(member2);
    }

    //로그인 폼
    @GetMapping("/login")
    public String loginForm(){
        return "account/loginForm";
    }

    //회원가입폼
    @GetMapping("/add")
    public String memberAddForm(){
        return "account/addMember";
    }
    @PostMapping("/add")
    public String memberAdd(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
        Member saved = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId",saved.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/account/members/{memberId}";
    }

    //회원 목록
    @GetMapping
    public String members(Model model){
        List<Member> allMembers = memberRepository.findAll();
        model.addAttribute("members",allMembers);
        return "account/members";
    }

    //회원 상세
    @GetMapping("/{memberId}")
    public String member(@PathVariable String memberId, Model model){
        Member found = memberRepository.findById(memberId);
        model.addAttribute("member",found);
        return "account/member";
    }

    //회원 수정
    @GetMapping("/{memberId}/edit")
    public String memberEditForm(@PathVariable String memberId, Model model){
        Member found = memberRepository.findById(memberId);
        model.addAttribute(found);
        return "account/editMember";
    }
    @PostMapping("/{memberId}/edit")
    public String memberEdit(@PathVariable String memberId, @ModelAttribute Member member, RedirectAttributes redirectAttributes) {
        memberRepository.updateMember(memberId, member);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/account/members/{memberId}";
    }

}
