import org.junit.jupiter.api.Test;

/**
 * PACKAGE_NAME
 *
 * @author xzwnp
 * 2022/5/29
 * 12:11
 */
public class Test1 {
    @Test
    public void test(){
        int a = 1;
        int b = a;
//        b++;
//        System.out.println("a = " + a);
//        System.out.println("b = " + b);
        a++;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
