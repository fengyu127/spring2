import com.google.gson.Gson;
import com.up.manage.model.TreeManage;
import com.up.manage.service.ImanageService;
import com.up.mybatis.dao.StudentMapper;
import com.up.mybatis.model.Student;
import com.up.mybatis.service.IStudentService;
import com.up.utils.Encrypt;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class Testcase {
    private static Logger logger = Logger.getLogger(Testcase.class);
    // private ApplicationContext ac = null;


    private ApplicationContext applicationContext;

    @Resource
    private StudentMapper StudentMapper;

    @Resource
    private ImanageService manageService;
/*  @Before
  public void before() {
      ac = new ClassPathXmlApplicationContext("applicationContext.xml");
      userService = (IUserService) ac.getBean("userService");
  }*/


    public void test1() {
        try {
         /*       String sign=Encrypt.rsaSignEncypt("1234", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKv1J/zvyBvfY7/F\n" +
                        "cJscFbkspqQYHwM1raa2fU9RlTVlnqEalf0RMyFpw14yK1xpFd+60e56nCO61sR3\n" +
                        "UQ8Xj4PQ1IAaWG9tzMu2PUYy+H4oJgBsUugi4yE9X+vzfTEdkVTuVcb+OEAapvWC\n" +
                        "oXQ83FAAeY5cZY7smENp8FQ3K5yZAgMBAAECgYBARp4Q5Mwm5wSRHUsvH3tbQT50\n" +
                        "3fNRrtHfHB2Q9YzRbc+ypo5kDvbOyYrWfehJXZpoi5uHCdutW1yGCZeKDaN95Ek/\n" +
                        "ZnGGp4Ul8B266+W04sFX7UXKXO53X91vNZOag6ZLNT/iu2/RiL0565i+ThFp+lIS\n" +
                        "Iv4rYqxQ143sRyOBEQJBANMEEnevuKsxvVLgC7iZmErLDnJUFoKUv8m0Y7zrH5QT\n" +
                        "4+lcnGGWaiwtRwsa1EnMJKw8cAG/oPH8vfZWZaCrD2UCQQDQnYdAkC+l9q3l7DgM\n" +
                        "B8FmHYr2PyeKaid9waznZsBQpIGLyobybEyeqKNZhCjZjpiULvF4NXBmWlEWMFrS\n" +
                        "EiclAkA56vA2saB6PJi+WohdDqtiPAGnF7Vl+fX6Iu0NOWvddOglcSnrDyHtJjBN\n" +
                        "t/t0lfeq3pvPnw1J3hk1t9kFFG6RAkEArWmlWqgA08cAu1XnKSjxPT9lmljZN5gS\n" +
                        "h9kNL7YcO2zkOx35m18nOeQZFXpksqR0dQmotDJH4u1E5lr/xAxO+QJBANHiuzpu\n" +
                        "rUFmlOyzWym7gw3SWLNxrdiTWysJSsp/zbjjnA1PYPyWEZmnez0/i5kcWJkFnZDl\n" +
                        "mcpyqePZt2tSBJM=", "utf-8");

                System.out.print(Encrypt.rsaUnSign("1234",sign,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCr9Sf878gb32O/xXCbHBW5LKak\n" +
                        "GB8DNa2mtn1PUZU1ZZ6hGpX9ETMhacNeMitcaRXfutHuepwjutbEd1EPF4+D0NSA\n" +
                        "GlhvbczLtj1GMvh+KCYAbFLoIuMhPV/r830xHZFU7lXG/jhAGqb1gqF0PNxQAHmO\n" +
                        "XGWO7JhDafBUNyucmQIDAQAB"));*/


            String encryptinfo = Encrypt.aesEncrypt("1e234", "UPUuGtOB/TYtPX432HOf7g==", "utf-8");

            String encryptinfo2 = Encrypt.aesUnEncrypt(encryptinfo, "UPUuGtOB/TYtPX432HOf7g==", "utf-8");
            System.out.print(encryptinfo2);


        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    @Test
    public void test3() {
        applicationContext = new ClassPathXmlApplicationContext("spring-mybatis.xml");

        IStudentService IStudentService = (IStudentService) this.applicationContext.getBean("IStudentService");
        Student student = IStudentService.getStudentByid(0);
        System.out.println(student.getClazz());
      /*       Map<String, IStudentService> beans = this.applicationContext.getBeansOfType(IStudentService.class);
       for (Map.Entry<String, IStudentService> entry : beans.entrySet())
       {
           IStudentService IStudentService=beans.get(entry);
           Student student=IStudentService.getStudentByid(0);
           System.out.println(student.getClazz()+entry);
       }
*/
    }

    @Test
    public void test2() {
        List<Student> s = new ArrayList<Student>();
        Student Student1 = new Student();
        Student1.setClazz("ff");
        Student1.setId(3);
        Student Student12 = new Student();
        Student12.setClazz("ff");
        Student12.setId(3);
        s.add(Student1);
        s.add(Student12);
        String str = "[{name:'a',value:'aa'},{name:'b',value:'bb'},{name:'c',value:'cc'},{name:'d',value:'dd'}]";
        JSONArray o = new JSONArray(s);
        System.out.println(o.toString());
        ;
    }


    @Test
    public void test4() {
        TreeManage TreeManage = manageService.getallManagebyid(0);
        Gson gson = new Gson();
        System.out.println(gson.toJson(TreeManage));
    }
}

