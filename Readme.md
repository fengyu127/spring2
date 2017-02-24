**spring学习总结**

* spring是用作管理web的相关，springmvc这个框架主要用作请求分发url的控制
* hibernate和mybatis是用于数据库的

###注意的几个点
@Resource
private StudentMapper mapper; 使用接口时一定的@Resource,要不然会出现空指针异常的错误，这个地方有实例化的意思

使用hibernate的时候，喜欢把xml文件放到dao包下，但是这个xml文件不在resources中，导致编译的时候没有将此xml文件加入资源文件，执行的时候可能找不到该文件
 pom.xml 中build放入

             <resources>
                         <resource>
                             <directory>src/main/java</directory>
                             <includes>
                                 <include>**/*.xml</include>
                             </includes>
                             <filtering>true</filtering>
                         </resource>
                         <resource>
                             <directory>src/main/resources</directory>
                             <includes>
                                 <include>**/*.xml</include>
                                 <include>**/*.properties</include>
                             </includes>
                         </resource>
              </resources>
 
 
response中文乱码

                 <mvc:annotation-driven>
                     <mvc:message-converters>
                         <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                             <property name="supportedMediaTypes" value="text/plain;charset=UTF-8"></property>
                         </bean>
                     </mvc:message-converters>
                 </mvc:annotation-driven>


mybatis生成dao包等配置见generatorConfig.xml
maven命令：mybatis-generator:generate


使用postman请求是中文未乱码，html请求时中文乱码
@RequestMapping(value = "getbyid" , produces = "application/json; charset=utf-8")



       applicationContext=new ClassPathXmlApplicationContext("spring-mybatis.xml");
       IStudentService IStudentService=(IStudentService)this.applicationContext.getBean("IStudentService");
       Student student=IStudentService.getStudentByid(0);
       System.out.println(student.getClazz());

http://runjs.cn/code/lrufabfi
http://jsrun.net/tGpKp/edit
