package org.estudantinder.features.Students.common;

import org.estudantinder.entities.User;
import org.estudantinder.features.commom.Student;

public class MatchReturn {
    public Student matched_student;
    public Long match_id;

    public static MatchReturn mapToMatchReturn(User matchedUser, Long matchId) {
        MatchReturn matchReturn = new MatchReturn();

        matchReturn.matched_student = Student.mapUserToStudentWithContacts(matchedUser);
        matchReturn.match_id = matchId;

        return matchReturn;
    }
}
