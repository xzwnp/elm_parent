import com.example.entity.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

/**
 * PACKAGE_NAME
 *
 * @author xiaozhiwei
 * 2022/12/3
 * 13:48
 */
public class ResultTest {
    @Test
    void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        R.ChainedHashMap map = new R.ChainedHashMap();
        map.put("info","123");
        map.put("total",5);
        String s = objectMapper.writeValueAsString(R.success(map));
        System.out.println(s);
    }
}
