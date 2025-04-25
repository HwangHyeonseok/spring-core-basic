package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // DIP, OCP 위반
        //        MemberService memberService = new MemberServiceImpl();

        // 순수 JAVA AppConfig 사용
//        AppConfig appconfig = new AppConfig();
//        MemberService memberService = appconfig.memberService();

        // Spring Bean 사용
        // applicationContext : 스프링 컨테이너
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // AppConfig 클래스에서 Bean으로 등록한 것을 applicationContext 컨테이너에 넣는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// memberService 객체를 찾는다. 그 타입은 MemberService 타입이다.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
