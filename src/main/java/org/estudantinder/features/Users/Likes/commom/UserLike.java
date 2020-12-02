package org.estudantinder.features.Users.Likes.commom;

import org.estudantinder.entities.Likes;
import org.estudantinder.features.Users.common.User;

public class UserLike {
    public User senderUser;
    public User receiverUser;

    public static UserLike mapStudentLikeToUserLike(Likes like) {
        UserLike userLike = new UserLike();
        
        userLike.senderUser = User.mapStudentToUser(like.getSender());
        userLike.receiverUser = User.mapStudentToUser(like.getReceiver());

        return userLike;
    }
}
