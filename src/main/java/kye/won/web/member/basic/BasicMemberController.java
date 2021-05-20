package kye.won.web.member.basic;

import kye.won.domain.member.Member;
import kye.won.domain.member.MemberRepository;
import kye.won.web.LogInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/account/members")
@RequiredArgsConstructor
public class BasicMemberController extends LogInterceptor {
    private final MemberRepository memberRepository;

    //회원 목록
    @GetMapping
    public String members(Model model){
        List<Member> allMembers = memberRepository.findAll();
        model.addAttribute("members",allMembers);
        return "account/members";
    }

    //회원 상세
    @GetMapping("/{memberId}")
    public String member(@PathVariable Long memberId, Model model){
        Member found = memberRepository.findById(memberId);
        model.addAttribute("member",found);
        return "account/member";
    }

    //회원가입폼
    @GetMapping("/add")
    public String memberForm(){
        return "account/addMember";
    }
    @PostMapping("/add")
    public String memberAdd(@ModelAttribute Member member, RedirectAttributes redirectAttributes){
        Member saved = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId",saved.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/account/members/{memberId}";
    }



}
