package org.estudantinder.features.Users.Images.ImageUpload;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.User;
import org.estudantinder.features.commom.CloudinaryCredentials;
import org.estudantinder.repositories.UsersRepository;

import io.smallrye.config.SmallRyeConfig;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    private void treatInvalidId(User authenticatedUser) throws Exception {
        if (authenticatedUser == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    private String uploadImage(Cloudinary cloudinary, File photo) throws IOException {
        Object secure_url = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap()).get("secure_url");

        return (String) secure_url;
    }

    private String[] uploadArrayOfPhotoUrls(Cloudinary cloudinary, User authenticatedUser, List<File> photos)
            throws IOException {
        String[] photoUrls;

        if (authenticatedUser.getPhotos() != null) {
            photoUrls = authenticatedUser.getPhotos();
        } else {
            photoUrls = new String[6];
        }

        for (int i = 0; i < photos.size(); i++) {
            if (photos.get(i) != null) {
                photoUrls[i] = uploadImage(cloudinary, photos.get(i));
            }
        }

        return photoUrls;
    }

    public String[] execute(JsonWebToken jwt, DTO data) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(userId);

        treatInvalidId(authenticatedUser);

        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
        CloudinaryCredentials cloudinaryCredentials = config.getConfigMapping(CloudinaryCredentials.class);

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", cloudinaryCredentials.cloudName(),
                "api_key", cloudinaryCredentials.apiKey(), "api_secret", cloudinaryCredentials.apiSecret()));

        String[] photoUrls = uploadArrayOfPhotoUrls(cloudinary, authenticatedUser,
                Arrays.asList(data.photo0, data.photo1, data.photo2, data.photo3, data.photo4, data.photo5));

        authenticatedUser.setPhotos(photoUrls);

        usersRepository.persist(authenticatedUser);

        return authenticatedUser.getPhotos();
    }
}
