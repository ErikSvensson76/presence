package se.lexicon.vxo.presence.service.user;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import se.lexicon.vxo.presence.entity.user.Cv;
import se.lexicon.vxo.presence.entity.user.ProfileImage;

import java.io.IOException;

public interface FileService {
    ProfileImage saveImageFile (MultipartFile file,String email) throws IOException, FileUploadException;
    Cv saveCvFile (MultipartFile file, String email) throws IOException, FileUploadException;
}
