package hello.core;

import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // OCP, DIP 위반
//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        // 순수 JAVA AppConfig
//        AppConfig appconfig = new AppConfig();
//        MemberService memberService = appconfig.memberService();
//        OrderService orderService = appconfig.orderService();

        // Spring Bean 이용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //  AppConfig 클래스 안에 등록된 Bean들을 스프링 컨테이너인 applicationContext에 넣는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); //  applicationContext 컨테이너에서 빈 이름이 memberService인 것을 가져온다. 근데 그 객체의 리턴형은 MemberService 이다.
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class); //  //  applicationContext 컨테이너에서 빈 이름이 orderService인 것을 가져온다. 근데 그 객체의 리턴형은 OrderService 이다.

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }

}
