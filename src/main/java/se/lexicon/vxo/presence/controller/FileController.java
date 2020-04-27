package se.lexicon.vxo.presence.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.lexicon.vxo.presence.data.AppUserRepository;
import se.lexicon.vxo.presence.data.ImageRepository;
import se.lexicon.vxo.presence.dto.app_user.UploadImageForm;
import se.lexicon.vxo.presence.security.AppUserPrincipal;
import se.lexicon.vxo.presence.service.user.AppUserService;
import se.lexicon.vxo.presence.service.user.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
public class FileController {

    private AppUserService appUserService;
    private AppUserRepository appUserRepository;
    private ImageRepository imageRepository;
    private ImageService imageService;

    @Autowired
    public FileController(AppUserService appUserService, AppUserRepository appUserRepository, ImageRepository imageRepository, ImageService imageService) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    @PostMapping("/processFile")
    public String processImage(@ModelAttribute("form") UploadImageForm form,  @AuthenticationPrincipal AppUserPrincipal principal) throws IOException {
        imageService.saveImageFile(form.getImage(),principal.getUsername());
        return "redirect:/users/"+principal.getUsername();
    }



    @GetMapping("/image/{id}")
    public void renderImage(@PathVariable int id, HttpServletResponse response) throws IOException {
        byte[] image = new byte[imageRepository.findById(id).get().getImage().length];

        int i = 0;
        for (Byte b: imageRepository.findById(id).get().getImage()) {
            image[i++] = b;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
    }


    }

