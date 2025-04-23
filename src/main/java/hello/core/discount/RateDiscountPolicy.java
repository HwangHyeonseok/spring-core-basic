package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    // 정률 할인 (10000원 10% 할인 = 1000원 할인) (20000원 10% 할인 = 2000원 할인)
    int discountPrecent = 10; // 10% 할인
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPrecent / 100;
        }
        else {
            return 0;
        }
    }
}
