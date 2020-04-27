package se.lexicon.vxo.presence.dto.app_user;

import org.springframework.web.multipart.MultipartFile;



public class UploadImageForm {
    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
