import com.hom.juc.threadPool.ExcuteThread;
import com.hom.juc.threadPool.ThreadConf;
import com.hom.juc.threadPool.ThreadProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThreadConf.class})
public class ThreadPoolTest {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    ThreadProcessService threadProcessService;

    @Test
    public void test(){
        for (int i = 0;i<20;i++){
            taskExecutor.execute(new ExcuteThread(threadProcessService));
            System.out.println("int i is " + i + ", now threadpool active threads totalnum is " + taskExecutor.getActiveCount()+ "\n");
        }
    }


}
