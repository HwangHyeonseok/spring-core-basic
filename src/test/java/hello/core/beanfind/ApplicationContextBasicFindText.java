package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindText {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름+타입 지정으로 조회")
    public void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로만 조회")
    public void findBeanByOnlyName() {
        Object memberService = ac.getBean("memberService");
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    public void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    public void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("없는 빈 이름으로 조회 시")
    public void findBeanByNameX() {
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class); // NoSuchBeanDefinitionException 예외 발생
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }

}
