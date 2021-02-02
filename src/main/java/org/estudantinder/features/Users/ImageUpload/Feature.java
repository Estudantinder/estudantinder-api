package org.estudantinder.features.Users.ImageUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Users;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {
    
    @Inject
    UsersRepository usersRepository;
    private void treatInvalidId(Users authenticatedUser ) throws Exception {
        if(authenticatedUser == null) {
            throw new NotFoundException("jwt id not valid");
        } 
    }
    private String uploadImage(Cloudinary cloudinary, InputStream photoData, String photoName) throws IOException {
        File createdPhoto = createFile(photoData, photoName);

        cloudinary.uploader().upload(photoName + ".jpg", ObjectUtils.asMap("public_id", photoName));
        String url = cloudinary.url().generate(photoName+ ".jpg");
        
        createdPhoto.delete();
        
        return url;
    }

    private File createFile(InputStream photoData, String photoName) throws IOException {
        File file = new File(photoName+ ".jpg");
        copyInputStreamToFile(photoData, file);

        return file;
    }

    private static void copyInputStreamToFile(InputStream input, File file) throws IOException {
        try (OutputStream output = new FileOutputStream(file, false)) {
            input.transferTo(output);
        }
    }

    private String[] uploadArrayOfPhotoUrls (Cloudinary cloudinary, DTO data, Users authenticatedUser )
            throws IOException {
        List<String> listOfPhotoUrls = new ArrayList<>(); 
        
        if(data.photo0 != null) {
            String url = uploadImage(cloudinary, data.photo0, authenticatedUser.getName() + "0");
            listOfPhotoUrls.add(url);
        } if(data.photo1 != null) {
            String url = uploadImage(cloudinary, data.photo1, authenticatedUser.getName() + "1");
            listOfPhotoUrls.add(url);
        } if(data.photo2 != null) {
            String url = uploadImage(cloudinary, data.photo2, authenticatedUser.getName() + "2");
            listOfPhotoUrls.add(url);
        } if(data.photo3 != null) {
            String url = uploadImage(cloudinary, data.photo3, authenticatedUser.getName() + "3");
            listOfPhotoUrls.add(url);
        } if(data.photo4 != null) {
            String url = uploadImage(cloudinary, data.photo4, authenticatedUser.getName() + "4");
            listOfPhotoUrls.add(url);
        } if(data.photo5 != null) {
            String url = uploadImage(cloudinary, data.photo5, authenticatedUser.getName() + "5");
            listOfPhotoUrls.add(url);
        }

        String[] photoUrls = listOfPhotoUrls.toArray(new String[listOfPhotoUrls.size()]);

        return photoUrls;
    }

    public String[] execute(JsonWebToken jwt, DTO data) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);

        treatInvalidId(authenticatedUser); 

        //can't remove credentials because of heroku deploy
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "adamaugustinsky",
            "api_key", "634655436325958",
            "api_secret", "Qn-FmfRjuUjd5wyAsK_7wFpLZBM"));

        String[] photoUrls = uploadArrayOfPhotoUrls(cloudinary, data, authenticatedUser);
        authenticatedUser.setPhotos(photoUrls);

        usersRepository.persist(authenticatedUser);

        return photoUrls;
    }
}
