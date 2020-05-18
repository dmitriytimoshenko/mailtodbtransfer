package processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.camel.model.dataformat.YAMLTypeFilterType.regexp;

public class SendProcessor implements Processor {
    int txtCount = 0;
    int xmlCount = 0;
    int otherCount = 0;
    int allCount = 0;

    @Override
    public void process(Exchange exchange) throws Exception {

        System.out.println(exchange.getIn().getHeaders().toString());

        if(isTxt(exchange.getIn().getHeader("CamelFileName").toString()) == true) {
            txtCount =+ 1;
        } else if (isXml(exchange.getIn().getHeader("CamelFileName").toString()) == true) {
            xmlCount =+ 1;
        } else {
            otherCount =+ 1;
        }

        allCount =+ 1;

        System.out.println("All .txt masseges: " + txtCount);
        System.out.println("All .xml masseges: " + xmlCount);
        System.out.println("All other masseges: " + otherCount);
        System.out.println("All masseges: " + allCount);
    }

    public boolean isTxt(String s) {
        Pattern p = Pattern.compile("^.*txt$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    public boolean isXml(String s) {
        Pattern p = Pattern.compile("^.*txt$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
