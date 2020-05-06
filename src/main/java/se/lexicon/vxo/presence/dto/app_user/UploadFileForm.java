package se.lexicon.vxo.presence.dto.app_user;

import org.springframework.web.multipart.MultipartFile;


public class UploadFileForm {
    
    private MultipartFile image;
    private MultipartFile cv;

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
