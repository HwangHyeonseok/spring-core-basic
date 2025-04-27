package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    @DisplayName("stateful 테스트 - 객체를 공유하기 때문에 price 변수가 공유되어 문제 발생")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);
        
        // ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        // 사용자A가 조회했을 때 10000원을 의도했는데 20000원이 나온다.
        Assertions.assertThat(price).isEqualTo(10000);
    }

    @Test
    @DisplayName("stateless 테스트")
    public void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        int userAPrice = statelessService1.order("userA", 10000);
        int userBPrice = statelessService2.order("userB", 20000);

        Assertions.assertThat(userAPrice).isEqualTo(10000);
        Assertions.assertThat(userAPrice).isNotEqualTo(userBPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

}