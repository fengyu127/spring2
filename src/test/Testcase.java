import com.up.mybatis.dao.StudentMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/7.
 */

    @RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
    @ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

    public class Testcase {
        private static Logger logger = Logger.getLogger(Testcase.class);
        //  private ApplicationContext ac = null;


        @Resource
        private StudentMapper StudentMapper;

//  @Before
//  public void before() {
//      ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//      userService = (IUserService) ac.getBean("userService");
//  }

        @Test
        public void test1() {
            String d=StudentMapper.getclazzbyid(4);
            // System.out.println(user.getUserName());
            // logger.info("值："+user.getUserName());
             System.out.println(d);
        }
    }

