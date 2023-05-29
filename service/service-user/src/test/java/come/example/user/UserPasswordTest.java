package come.example.user;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * come.example.user
 *
 * @author xzwnp
 * 2022/12/13
 * 21:50
 */
public class UserPasswordTest {
    @Test
    void name() {
        String password = "123456";
        String hashedPassword = new SimpleHash("md5", password, "salt", 3).toString();
        System.out.println(hashedPassword);
    }
}
