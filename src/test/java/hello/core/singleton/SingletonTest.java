package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너 - new로 만든 객체의 참조가 다르다는 것을 확인")
    public void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성하는지
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성하는지
        MemberService memberService2 = appConfig.memberService();

        // 3. 주소(참조)가 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 객체와 memberService2 객체가 다른 것을 증명
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 주소(참조)가 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        // same 은 == 이다. (참조를 비교)
        // equal 은 .equals 이다. (값을 비교)
    }

    @Test
    @DisplayName("스프링 컨테이너는 싱글톤이 자동으로 적용된다. 그리고 순수 싱글톤의 단점도 해결한다.")
    public void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때 마다 객체를 생성하는지
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 조회 : 호출할 때 마다 객체를 생성하는지
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 3. 주소(참조)가 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 객체와 memberService2 객체가 같은 것을 증명
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
