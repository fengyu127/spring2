import com.google.gson.Gson;
import com.up.dao.Request;
import com.up.manage.model.TreeManage;
import com.up.manage.service.ImanageService;
import com.up.mybatis.dao.StudentMapper;
import com.up.mybatis.model.Student;
import com.up.mybatis.service.IStudentService;
import com.up.utils.Encrypt;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
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


            String encryptinfo = Encrypt.aesEncrypt("1e234", "llv8IckTuVw1RqaD265wrw==", "utf-8");

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


    @Test
    public void testqr() throws Exception {
        Request request = new Request();
        request.setVersion("1.0");
        request.setEncoding("UTF-8");
        request.setRequestId("11");
        request.setExpandcode("10100000001");
        request.setSignMethod("RSA");

        String expandName = "test_admin";
        String encryptedInfo = "";
        String phoneNo = "13215264578";
        Gson gson = new Gson();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phoneNo", phoneNo);

        System.out.println(gson.toJson(map));
        String s = Encrypt.aesEncrypt(gson.toJson(map), "llv8IckTuVw1RqaD265wrw==", "utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("encryptedInfo", s);
        jsonObject.put("expandName", "test_admin");
        System.out.println(jsonObject.toString());
        request.setBizContent(jsonObject.toString());

        HashMap<String, String> hashMap = Encrypt.jsontoHashmap(request);
        System.out.println(hashMap);
        String signature = Encrypt.rsaSignEncypt(hashMap.toString(), "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK6vfTpCl94zs9gn" +
                "L9TGx9GBvORoduqr00MJ46wn6Y0HeCpwyBf1XelOyOM18GvKUOIgtm1CzyJ4fk5Y" +
                "3R1Jv1JdOCDM+AZCwuVvYzc1yoEpbx01v7G2VM3cJNLFj/xCAVMrW0Jq02gty5WX" +
                "LL9s3B5AYJOfTHvnqAfreogcx2UdAgMBAAECgYBpTG2PPe8vyTjCtjR17XEr0Cne" +
                "AT6VjQfnJEHyV4jIM1VzN9LV5lk6tvwPQkdvomAJPzDYQep8W+e/MH/xFLBXZTnQ" +
                "H0z6a8rG5F+Gtpg96rPYN+LZmXh3CvwiTEHimMqVEX7G/97ZUAbpKYzE6FX344QC" +
                "mIMlHUHkHsNkLtLNYQJBAOLH8Gc/eXAqok6zMqGA5W/4+ND0+Q2r2mRToIY1z75J" +
                "PfGgbZsfE/VSI+3Mm73RJkBzwfuS73qXs+TCc6mXehkCQQDFMUBMi3F6S10jYXBC" +
                "g6eQauFOWBO8YPc66KB3bj511LNKNBXf8DLDoOuLaV09enCHNMsukImuq1GrcTZ9" +
                "L6ulAkAhngfqFkO3N+q1heTkggoA7kRcHWRp/WazZp4uJv3ztEHFdsWosBOyUwnW" +
                "b3VKzx0/gqln1KFBaAmXyKeCpVzJAkA5bimq4WXQV0it+D/or01LC0XJOm+tCpSW" +
                "jI/HmM0KJkN9VgQU73DpduGC/dHRCOrjBeYzDpd6zpx/kP5soUidAkEAuyExg2te" +
                "vtJ/EA87G3/EgIpFSxzD25mGAzKC0H7nyXbuO7Ho4WX5tN2ong7ifxgJjJvv6Zrt" +
                "mg0oK+lMlnmpGg==", "utf-8");
        request.setSignature(signature);


        JSONObject json = new JSONObject(request);

        String httpUrl="http://tdctest.95516.com/xwins/api/test ";
        CloseableHttpClient httpclient  = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(httpUrl);
        StringEntity StringEntity=new StringEntity(json.toString());
        httpPost.setEntity(StringEntity);

        CloseableHttpResponse response = httpclient.execute(httpPost);

        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);


    }


}

