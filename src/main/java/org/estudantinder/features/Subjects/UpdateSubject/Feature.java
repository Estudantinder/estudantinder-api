package org.estudantinder.features.Subjects.UpdateSubject;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.estudantinder.entities.Subject;
import org.estudantinder.features.commom.CloudinaryUtils;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SubjectsRepository subjectsRepository;

    void throwExceptionIfSubjectNotFound(Subject subject) {
        if (subject == null) {
            throw new NotFoundException("Materia não encontrada");
        }
    }

    Subject updateSubject(Subject subject, Optional<String> name, Optional<String> photoUrl) {
        if (name.isPresent())
            subject.setName(name.get());
        if (photoUrl.isPresent())
            subject.setPhoto(photoUrl.get());

        subjectsRepository.persist(subject);

        return subject;
    }

    private String uploadImage(Cloudinary cloudinary, File photo) throws IOException {
        Object secure_url = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap()).get("secure_url");

        return (String) secure_url;
    }

    public Subject execute(Long id, DTO data) throws Exception {
        Subject subject = subjectsRepository.findById(id);

        System.out.println(subject.getPhoto());

        throwExceptionIfSubjectNotFound(subject);

        Optional<String> optionalName = Optional.ofNullable(data.name);
        Optional<String> optionalPhotoUrl = Optional.empty();

        if (data.photo != null) {

            String photoUrl = uploadImage(CloudinaryUtils.getCloudinary(), data.photo);
            optionalPhotoUrl = Optional.ofNullable(photoUrl);
        }

        return updateSubject(subject, optionalName, optionalPhotoUrl);
    }

}