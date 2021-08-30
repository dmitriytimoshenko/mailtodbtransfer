package processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendProcessor implements Processor {
    int txtCount = 0;
    int xmlCount = 0;
    int otherCount = 0;
    int allCount = 0;

    public void process(Exchange exchange) throws Exception {

        System.out.println(exchange.getIn().getHeaders().toString());

        if(isTxt(exchange.getIn().getHeader("CamelFileName").toString())) {
            txtCount ++;
        }

        if (isXml(exchange.getIn().getHeader("CamelFileName").toString()) == true) {
            xmlCount ++;
        }

        if (isXml(exchange.getIn().getHeader("CamelFileName").toString()) != true && isTxt(exchange.getIn().getHeader("CamelFileName").toString()) != true) {
            otherCount ++;
        }

        allCount = txtCount + xmlCount + otherCount;

        System.out.println("All .txt masseges: " + txtCount);
        System.out.println("All .xml masseges: " + xmlCount);
        System.out.println("All other masseges: " + otherCount);
        System.out.println("All masseges: " + allCount);

        exchange.getOut().getHeaders().toString();
    }

    public boolean isTxt(String s) {
        Pattern p = Pattern.compile("^.*txt$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    public boolean isXml(String s) {
        Pattern p = Pattern.compile("^.*xml$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
