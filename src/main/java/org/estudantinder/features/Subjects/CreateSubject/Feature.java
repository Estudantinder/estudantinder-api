package org.estudantinder.features.Subjects.CreateSubject;

import java.io.File;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.estudantinder.entities.Subject;
import org.estudantinder.features.commom.CloudinaryUtils;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SubjectsRepository subjectsRepository;

    void throwExceptionIfSubjectAlreadyExists(Subject subject) {
        if (subject != null) {
            throw new EntityExistsException("Materia já existe");
        }
    }

    private Subject createSubject(String name, String photoUrl) {
        Subject newSubject = new Subject();

        newSubject.setName(name);
        newSubject.setPhoto(photoUrl);

        subjectsRepository.persist(newSubject);

        return newSubject;
    }

    private String uploadImage(Cloudinary cloudinary, File photo) throws IOException {
        Object secure_url = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap()).get("secure_url");

        return (String) secure_url;
    }

    public void execute(DTO data) throws Exception {
        Subject alreadyExistentSubject = subjectsRepository.findByName(data.name);

        throwExceptionIfSubjectAlreadyExists(alreadyExistentSubject);

        String photoUrl = uploadImage(CloudinaryUtils.getCloudinary(), data.photo);

        createSubject(data.name, photoUrl);
    }
}
