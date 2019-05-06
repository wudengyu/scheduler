package tax.qxn.scheduler.startup;

import org.apache.catalina.startup.Tomcat;
  
public class EmbedTomcat{  
    static final int port = 9080;  
    public static void main(String[] args) throws Exception {
        String appBase=".";
        Tomcat tomcat = new Tomcat();  
        tomcat.setPort(port);  
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp("", appBase);
        //tomcat.initWebappDefaults(contextPath);
        tomcat.start();   
        tomcat.getServer().await();  
    }  
}  