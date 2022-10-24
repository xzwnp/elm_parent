import com.example.order.OrderApplication;
import com.example.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * PACKAGE_NAME
 *
 * @author xzwnp
 * 2022/6/9
 * 14:01
 */
@SpringBootTest(classes = OrderApplication.class)
public class ServiceTest {
    @Autowired
    OrderService orderService;

    @Test
    void test1() {
        orderService.generateOrder(null,null);
    }


}
