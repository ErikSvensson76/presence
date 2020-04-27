package se.lexicon.vxo.presence.entity.user;

import javax.persistence.*;

@Entity
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private Byte[] image;

    public ProfileImage(int id,Byte[] image){
        this.id = id;
        this.image = image;
    }

    public ProfileImage(Byte[] image){
        this(0,image);
    }

    public ProfileImage() {
    }

    public int getId() {
        return id;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
