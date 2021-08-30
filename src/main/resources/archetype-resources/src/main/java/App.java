

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
  /*      if (System.getProperty("config.path") == null) {
            System.setProperty("config.path", "C:/JavaProjects/source/diploma/mailtodbtransfer/target/SpamTransfer/config/");
        }
*/
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        ctx.start();
    }
}
