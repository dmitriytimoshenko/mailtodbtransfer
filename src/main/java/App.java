import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        /*CamelContext context = new DefaultCamelContext();
        context.start();
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        context.stop();
        */
        Main main = new Main();
        main.configure();

        try {
            main.run(args);
            System.out.println(main.getConfigurationClasses());

        } catch (Exception e) {
            System.out.println("Ошибка работы контекста Camel");
        }

    }
}
