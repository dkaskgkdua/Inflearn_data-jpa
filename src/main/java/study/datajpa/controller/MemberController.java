package study.datajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }
    // 트랜잭션이 없는 범위이므로 엔티티를 변경해도 DB반영이 안됨(조회용)
    @GetMapping("/members2/{id}")
    public String findMember(@PathVariable("id") Member member) {
        return member.getUsername();
    }

    /**
     * 페이징 정보가 둘 이상이면 접두사로 구분
     * public String list(
     * @Qualifier("member") Pageable memberPageable,
     * @Qualifier("order") Pageable orderPageable
     *
     */
    @GetMapping("/members")
    public Page<MemberDto> list(@PageableDefault(size = 5) Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(MemberDto::new);
    }

    @PostConstruct
    public void init() {
        for(int i = 0 ; i < 100; i++) {
            memberRepository.save(new Member("user"+i, i));
        }
    }
}
