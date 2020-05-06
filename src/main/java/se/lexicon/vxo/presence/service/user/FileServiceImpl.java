package se.lexicon.vxo.presence.service.user;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.data.CvRepository;
import se.lexicon.vxo.presence.data.ImageRepository;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.entity.user.Cv;
import se.lexicon.vxo.presence.entity.user.ProfileImage;

import java.io.IOException;

import static se.lexicon.vxo.presence.exception.Exceptions.appResourceNotFoundException;

@Service
public class FileServiceImpl implements FileService {

    private ImageRepository imageRepository;
    private AppUserRepository appUserRepository;
    private AppUserService appUserService;
    private CvRepository cvRepository;

    @Autowired
    public FileServiceImpl(ImageRepository imageRepository, AppUserRepository appUserRepository, AppUserService appUserService, CvRepository cvRepository) {
        this.imageRepository = imageRepository;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.cvRepository = cvRepository;
    }

    @Override
    public ProfileImage saveImageFile(MultipartFile file,String email) throws IOException, FileUploadException {
        if (file.getBytes().length < 1){
            return appUserRepository.findByEmailIgnoreCase(email).orElseThrow(appResourceNotFoundException())
                    .getProfileImage();
        }
        if  (!file.getContentType().contains("image")) throw new FileUploadException();
        Byte[] bytesArray = new Byte[file.getBytes().length];
        int i = 0;
        for (byte b: file.getBytes()) {
            bytesArray[i++] = b;
        }
        ProfileImage profileImage = imageRepository.save(new ProfileImage(bytesArray));
        AppUser user = appUserRepository.findByEmailIgnoreCase(email).orElseThrow(appResourceNotFoundException());
        user.setProfileImage(profileImage);
        appUserService.save(user);
        return profileImage;
    }


    @Override
    public Cv saveCvFile(MultipartFile file, String email) throws IOException, FileUploadException {
        if (file.getBytes().length < 1){
            return appUserRepository.findByEmailIgnoreCase(email).orElseThrow(appResourceNotFoundException())
                    .getCv();
        }
        if  (!file.getContentType().equals("application/pdf")) throw new FileUploadException();
        Byte[] bytesArray = new Byte[file.getBytes().length];
        int i = 0;
        for (byte b: file.getBytes()) {
            bytesArray[i++] = b;
        }
        Cv cv = cvRepository.save(new Cv(bytesArray));
        AppUser user = appUserRepository.findByEmailIgnoreCase(email).orElseThrow(appResourceNotFoundException());
        user.setCv(cv);
        appUserService.save(user);
        return cv;
    }


}
