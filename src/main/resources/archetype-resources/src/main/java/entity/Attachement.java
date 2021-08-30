package entity;

public class Attachement {
    long id_attachement;
    String data_type;
    long size;

    public long getId_attachement() {
        return id_attachement;
    }

    public void setId_attachement(long id_attachement) {
        this.id_attachement = id_attachement;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
