
import com.example.demo.FilePoccess;
import com.example.demo.testdb;
import org.junit.Test;


public class crawlertest {
    @Test
    public void test() throws Exception {

       System.out.println(testdb.getStringRandom().toString());
    }
    @Test
    public void file(){
        FilePoccess.toArrayByFileReader1("D:\\workPlace\\src\\main\\java\\com\\example\\demo\\lll.txt");
    }

}
