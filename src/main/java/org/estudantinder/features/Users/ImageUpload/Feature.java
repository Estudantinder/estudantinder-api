package org.estudantinder.features.Users.ImageUpload;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
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

    private void treatNoProfilePhoto(File photo0) {
        if(photo0 == null) {
            throw new BadRequestException("profile photo(photo0) can't be null");
        } 
    }

    private String uploadImage(Cloudinary cloudinary, File photo) throws IOException {
        Object secure_url = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap()).get("secure_url");
        
        return (String) secure_url;
    }

    private String[] uploadArrayOfPhotoUrls (Cloudinary cloudinary, Users authenticatedUser, List<File> photos)
            throws IOException {
        String[] photoUrls = new String[6];

        for(int i = 0; i < photos.size(); i++) {
            if(photos.get(i) != null) {
                photoUrls[i] = uploadImage(cloudinary, photos.get(i));
            }
        }

        return photoUrls;
    }

    public String[] execute(JsonWebToken jwt, DTO data) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);

        treatInvalidId(authenticatedUser); 
        treatNoProfilePhoto(data.photo0);

        //can't remove credentials because of heroku deploy
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "adamaugustinsky",
            "api_key", "634655436325958",
            "api_secret", "Qn-FmfRjuUjd5wyAsK_7wFpLZBM"));


        String[] photoUrls = uploadArrayOfPhotoUrls(
            cloudinary, 
            authenticatedUser,
            Arrays.asList(
                data.photo0,
                data.photo1,
                data.photo2,
                data.photo3,
                data.photo4,
                data.photo5
            ));
        
        authenticatedUser.setPhotos(photoUrls);

        usersRepository.persist(authenticatedUser);

        return photoUrls;
    }
}
