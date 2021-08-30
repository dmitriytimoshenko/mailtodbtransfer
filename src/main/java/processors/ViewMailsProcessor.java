package processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;

import javax.mail.Message;

public class ViewMailsProcessor implements Processor {

    Logger logger = Logger.getLogger(ViewMailsProcessor.class);

    public void process(Exchange exchange) throws Exception {
      logger.info(exchange.getProperties());
      logger.info(exchange.getIn().getBody());
    }
}
