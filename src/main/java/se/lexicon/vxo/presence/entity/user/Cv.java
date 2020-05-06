package se.lexicon.vxo.presence.entity.user;

import javax.persistence.*;

@Entity
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private Byte[] cv;

    public Cv(int id, Byte[] cv) {
        this.id = id;
        this.cv = cv;
    }

    public Cv(Byte[] cv) {
        this(0,cv);
    }

    public Cv(){

    }

    public int getId() {
        return id;
    }

    public Byte[] getCv() {
        return cv;
    }

    public void setCv(Byte[] cv) {
        this.cv = cv;
    }
}
