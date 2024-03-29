package org.estudantinder.features.Users.Images.DeleteImage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {
    
    @Inject
    UsersRepository usersRepository;
    private void treatInvalidId(User authenticatedUser ) throws Exception {
        if(authenticatedUser == null) {
            throw new NotFoundException("id não encontrado");
        } 
    }

    private void treatNullIndex(Integer imageIndex ) throws BadRequestException {
        if(imageIndex == null) {
            System.out.println("teste");
            throw new BadRequestException("indice da imagem não pode ser nulo");
        }
    }

    private void treatIndexToBig(int userPhotosLength, Integer imageIndex) throws NotFoundException {
        if(userPhotosLength < imageIndex) {
            throw new NotFoundException("indice da imagem maior do que a lista");
        }
    }

    private void treatNoPhotosToDelete(String[] userPhotos) throws NotFoundException {
        if(userPhotos == null) {
            throw new NotFoundException("Usuário não tem fotos para deletar");
        } else if (userPhotos.length == 0) {
            throw new NotFoundException("Usuário não tem fotos para deletar");
        }
    }

    private String[] deleteUserPhoto(String[] userPhotos, Integer imageIndex) {
        userPhotos[imageIndex] = null;

        return userPhotos;
    } 

    private String[] removeNullIndexes(String[] userPhotos) {
        List<String> updatedUserPhotos = Arrays.stream(userPhotos).
            filter(photo -> photo != null).collect(Collectors.toList());
        
        return updatedUserPhotos.toArray(new String[6]);
    } 

    public void execute(JsonWebToken jwt, Integer imageIndex) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(userId);

        treatInvalidId(authenticatedUser); 
        treatNullIndex(imageIndex);
        
        String[] userPhotos = authenticatedUser.getPhotos();

        treatNoPhotosToDelete(userPhotos);
        treatIndexToBig(userPhotos.length, imageIndex);

        String[] modifiedUserPhotos = deleteUserPhoto(userPhotos, imageIndex);

        String[] cleanedUserPhotos = removeNullIndexes(modifiedUserPhotos);
        
        authenticatedUser.setPhotos(cleanedUserPhotos);

        usersRepository.persist(authenticatedUser);
    }
}
