package entity;

import java.sql.Timestamp;
import java.util.List;

public class Messages {

    private long id_message;
    private long size;
    private boolean isattachement;
    private Timestamp lastdate;
    private List<Attachement> attachements;
    private List<Account> accounts_from;
    private List<Account> accounts_to;
    private MessageData messageData;

    public long getId_message() {
        return id_message;
    }

    public void setId_message(long id_message) {
        this.id_message = id_message;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public boolean isIsattachement() {
        return isattachement;
    }

    public void setIsattachement(boolean isattachement) {
        this.isattachement = isattachement;
    }

    public Timestamp getLastdate() {
        return lastdate;
    }

    public void setLastdate(Timestamp lastdate) {
        this.lastdate = lastdate;
    }

    public List<Attachement> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<Attachement> attachements) {
        this.attachements = attachements;
    }

    public List<Account> getAccounts_from() {
        return accounts_from;
    }

    public void setAccounts_from(List<Account> accounts_from) {
        this.accounts_from = accounts_from;
    }

    public List<Account> getAccounts_to() {
        return accounts_to;
    }

    public void setAccounts_to(List<Account> accounts_to) {
        this.accounts_to = accounts_to;
    }

    public MessageData getMessageData() {
        return messageData;
    }

    public void setMessageData(MessageData messageData) {
        this.messageData = messageData;
    }
}
