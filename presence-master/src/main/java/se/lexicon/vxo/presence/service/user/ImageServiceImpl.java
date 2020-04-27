package se.lexicon.vxo.presence.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.data.ImageRepository;
import se.lexicon.vxo.presence.entity.user.AppUser;
import se.lexicon.vxo.presence.entity.user.ProfileImage;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private AppUserRepository appUserRepository;
    private AppUserService appUserService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, AppUserRepository appUserRepository, AppUserService appUserService) {
        this.imageRepository = imageRepository;
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @Override
    public ProfileImage saveImageFile(MultipartFile file,String email) throws IOException {
        if (file.getBytes().length < 1){
            return appUserRepository.findByEmailIgnoreCase(email).orElseThrow(IllegalArgumentException::new)
                    .getProfileImage();
        }
        Byte[] bytesArray = new Byte[file.getBytes().length];
        int i = 0;
        for (byte b: file.getBytes()) {
            bytesArray[i++] = b;
        }
        ProfileImage profileImage = imageRepository.save(new ProfileImage(bytesArray));
        AppUser user = appUserRepository.findByEmailIgnoreCase(email).orElseThrow(IllegalArgumentException::new);
        user.setProfileImage(profileImage);
        appUserService.save(user);
        return profileImage;
    }


}
