package dao;

import org.apache.camel.Exchange;

interface MessageDataWriterDAO {
    public void insertExchange(Exchange exchange) throws Exception;

}
