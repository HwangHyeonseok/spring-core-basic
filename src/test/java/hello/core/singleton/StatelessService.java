package hello.core.singleton;

public class StatelessService {

    // 전역(공유) 부분에 변수 두는 것은 항상 조심한다.

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
