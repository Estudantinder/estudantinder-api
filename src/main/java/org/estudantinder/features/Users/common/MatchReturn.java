package org.estudantinder.features.Users.common;

import org.estudantinder.entities.Student;

public class MatchReturn {
    public User matchedUser;
    public Long matchId;

    public static MatchReturn mapToMatchReturn(Student matchedStudent, Long matchId) {
        MatchReturn matchReturn = new MatchReturn();

        matchReturn.matchedUser = User.mapStudentToUserWithContacts(matchedStudent);
        matchReturn.matchId = matchId;

        return matchReturn;
    }
}
