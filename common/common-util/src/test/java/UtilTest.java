import com.example.util.RandomUtil;
import org.junit.jupiter.api.Test;

/**
 * PACKAGE_NAME
 *
 * @author xzwnp
 * 2022/6/8
 * 19:14
 */
public class UtilTest {
    @Test
    public void test(){
        System.out.println(RandomUtil.randomDatesSn(20));
        System.out.println(RandomUtil.randomDatesSn(20));
        System.out.println(RandomUtil.randomDatesSn(20));
        System.out.println(RandomUtil.randomDatesSn(20));
    }

    @Test
    public void test2(){
        System.out.println(RandomUtil.randomUUID().length());

    }
}
