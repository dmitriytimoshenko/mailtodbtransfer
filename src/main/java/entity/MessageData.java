package entity;


public class MessageData {

/*
  logger.info(exchange.getIn().getHeader("Message-ID"));
        logger.info(exchange.getIn().getHeader("Date"));
        logger.info(exchange.getIn().getHeader("From"));
        logger.info(exchange.getIn().getHeader("To"));
        logger.info(exchange.getIn().getHeader("Content-Type"));
        logger.info(exchange.getIn().getBody(String.class));
*/
    private long id_md;
    private String body;

    public long getId_md() {
        return id_md;
    }

    public void setId_md(long id_md) {
        this.id_md = id_md;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
