package org.estudantinder.features.Students.common;

import org.estudantinder.entities.User;
import org.estudantinder.features.commom.Student;

public class MatchReturn {
    public Student matchedStudent;
    public Long matchId;

    public static MatchReturn mapToMatchReturn(User matchedUser, Long matchId) {
        MatchReturn matchReturn = new MatchReturn();

        matchReturn.matchedStudent = Student.mapUserToStudentWithContacts(matchedUser);
        matchReturn.matchId = matchId;

        return matchReturn;
    }
}
