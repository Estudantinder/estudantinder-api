package org.estudantinder.features.Statistics.LikesProportion;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Users;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.UsersRepository;


@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;

    @Inject
    UsersRepository usersRepository;

    public void treatUserDoesntExist(Users user) {
        if (user == null) throw new NotFoundException("User id does not exist");
    }

    public Long getUserLikesReceived(Users user) {
        return likesRepository.findByReceiver(user).stream().count();
    }

    public Long getUserLikesGiven(Users user) {
        return likesRepository.findBySender(user).stream().count();
    }

    public LikesProportionDTO execute(Long userId) {
        Users user = usersRepository.findById(userId);

        LikesProportionDTO result = new LikesProportionDTO();

        result.likes_received = getUserLikesReceived(user);
        result.likes_given = getUserLikesGiven(user);
        result.likes_proportion = (double) result.likes_received / (double) result.likes_given;


        return result;
    }
}
