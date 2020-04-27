package se.lexicon.vxo.presence.service.user;

import org.springframework.web.multipart.MultipartFile;
import se.lexicon.vxo.presence.entity.user.ProfileImage;

import java.io.IOException;

public interface ImageService {
    ProfileImage saveImageFile (MultipartFile file,String email) throws IOException;
}
