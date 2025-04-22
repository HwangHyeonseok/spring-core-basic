package hello.core.order;

import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.discount.OrderServiceImpl;
import hello.core.member.Grade;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import hello.core.member.Member;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    // Order 객체가 잘 생성되는지 체크
    @Test
    public void createOrder() {
        // given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
