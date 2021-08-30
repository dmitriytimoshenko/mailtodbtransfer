package dao;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class MessageDataDAOImpl implements MessageDataWriterDAO {

    final String INSERT_ATTACHEMENT = "insert into attachements(id_attachement,data_type,size) values(nextval('attachements_id_attachement_seq'), ?, ?)";
    final String INSERT_ACCOUNT = "insert into accounts(id_acc, login) values(nextval('accounts_id_acc_seq'), ?)";
    final String INSERT_MESSAGE_DATA = "insert into messagedata(id_md, data_type, body) values(nextval('messagedata_id_md_seq'), ?, ?)";
    final String INSERT_MESSAGES = "insert into messages(id_message, size, isattachement, lastdate, addition_id, from_id, to_id, md_id, msg) values(nextval('messages_id_message_seq'), ?, ?, ?, ?, ?, ?, ?, ?)";


    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    Logger logger = Logger.getLogger(MessageDataDAOImpl.class);


    @Override
    public void insertExchange(Exchange exchange) {


//   Что вытаскиваем для записи
        logger.info(exchange.getIn().getHeader("Message-ID"));
        logger.info(exchange.getIn().getHeader("Date"));
        logger.info(exchange.getIn().getHeader("From"));
        logger.info(exchange.getIn().getHeader("To"));
        logger.info(exchange.getIn().getHeader("Content-Type"));
        logger.info(exchange.getIn().getBody(String.class));
// ----- Извлекаем id записей для связи ---------

        Long attachementKey = insertAttachement(exchange);
        Long messageDataKey = insertMessageData(exchange);
        List<Long> accountKeyList = insertAccount(exchange);


        for (Long accKey: accountKeyList) {
            System.out.println(accKey.longValue());
        }

        logger.info("Inserting Message...");
        jdbcTemplate.update(INSERT_MESSAGES,
                //size
                exchange.getIn().getBody().toString().length(),
                //isattachement
                exchange.getIn().getAttachments().isEmpty(),
                //lastdate
                exchange.getIn().getHeader("Date"),
                //addition_id
                attachementKey,
                //from_id
                accountKeyList.get(0),
                //to_id
                accountKeyList.get(1),
                //md_id
                messageDataKey,
                //MSG
                exchange.getIn().getHeader("Message-ID"));


    }

    //   А есть ли уже такие записи  Accounts?
    public Long isRowExistAccounts(String login) {

        List<Long> longResultSet = jdbcTemplate.query("select id_acc from accounts where login = " + "'" + login + "'", (resultSet, i) ->
                resultSet.getLong("id_acc"));
        if (longResultSet.isEmpty()) {
            return 0L;
        } else {
            return longResultSet.get(0);
        }
    }
// -----------Вставка Attachement-----------

    public Long insertAttachement(Exchange exchange) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        logger.info("Inserting attachement...");
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_ATTACHEMENT, new String[]{"id_attachement"});
            if (exchange.getIn().getAttachments() != null) {
                // Запись вложения доработать
                ps.setString(1, "null");
                ps.setInt(2, 0);
            } else {
                ps.setString(1, "null");
                ps.setInt(2, 0);
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

// -------------Вставка MessageData------------------

    public Long insertMessageData(Exchange exchange) {
        logger.info("Inserting MessageData...");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_MESSAGE_DATA, new String[]{"id_md"});
            ps.setString(1, exchange.getIn().getHeader("Content-Type").toString());
            ps.setString(2, exchange.getIn().getBody(String.class));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

//  ----------------Вставка Account------------------

    public List<Long> insertAccount(Exchange exchange) {
        logger.info("Inserting Account...");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        long id_acc_from = isRowExistAccounts(exchange.getIn().getHeader("From").toString());
        long id_acc_to = isRowExistAccounts(exchange.getIn().getHeader("To").toString());
        List<Long> returned_ids = new ArrayList<>();

        // !Тут точно надо переделать формирование коллекции!

        if (id_acc_from == 0L && id_acc_to == 0L) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_ACCOUNT, new String[]{"id_acc"});
                ps.setString(1, exchange.getIn().getHeader("From").toString());
                return ps;
            }, keyHolder);
            returned_ids.add(keyHolder.getKey().longValue());
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_ACCOUNT, new String[]{"id_acc"});
                ps.setString(1, exchange.getIn().getHeader("To").toString());
                return ps;
            }, keyHolder);
            returned_ids.add(keyHolder.getKey().longValue());

        } else if (id_acc_from != 0L ) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_ACCOUNT, new String[]{"id_acc"});
                ps.setString(1, exchange.getIn().getHeader("From").toString());
                return ps;
            }, keyHolder);
            returned_ids.add(0L);
            returned_ids.add(keyHolder.getKey().longValue());


        } else if(id_acc_to != 0L) {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(INSERT_ACCOUNT, new String[]{"id_acc"});
                ps.setString(1, exchange.getIn().getHeader("To").toString());
                return ps;
            }, keyHolder);
            returned_ids.add(keyHolder.getKey().longValue());
            returned_ids.add(0L);
/*
        } else if(id_acc_from != 0L) {
            returned_ids.add(id_acc_from);
        } else if(id_acc_to != 0L) {
            returned_ids.add(id_acc_to);
*/        }

        return returned_ids;
    }

// ----------------------------------------------------------


}