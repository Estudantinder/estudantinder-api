package org.estudantinder.features.Statistics.Dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Course;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.School;
import org.estudantinder.entities.User;
import org.estudantinder.features.Statistics.Dashboard.DTO.DashboardDTO;
import org.estudantinder.features.Statistics.Dashboard.DTO.UsersPerCourseDTO;
import org.estudantinder.features.Statistics.Dashboard.DTO.UsersPerSchoolDTO;
import org.estudantinder.features.Statistics.Dashboard.DTO.UsersPerSchoolYearDTO;
import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.SchoolsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    SchoolsRepository schoolsRepository;

    @Inject
    MatchsRepository matchsRepository;

    private UsersPerCourseDTO createUsersPerCourseDTO(List<User> users, Course course) {
        UsersPerCourseDTO usersPerCourseDTO = new UsersPerCourseDTO();
        usersPerCourseDTO.courseName = course.getName();

        usersPerCourseDTO.numberOfUsers = users.stream().filter(user -> user.getCourse() == course).count();

        return usersPerCourseDTO;
    }

    private UsersPerSchoolDTO createUsersPerSchoolDTO(List<User> users, School school) {
        UsersPerSchoolDTO usersPerSchoolDTO = new UsersPerSchoolDTO();
        usersPerSchoolDTO.schoolName = school.getName();

        usersPerSchoolDTO.numberOfUsers = users.stream().filter(user -> user.getCourse().getSchool() == school).count();

        return usersPerSchoolDTO;
    }

    private UsersPerSchoolYearDTO createUsersPerSchoolYearDTO(List<User> users, int schoolYear) {
        UsersPerSchoolYearDTO usersPerSchoolYearDTO = new UsersPerSchoolYearDTO();
        usersPerSchoolYearDTO.schoolYear = schoolYear;

        usersPerSchoolYearDTO.numberOfUsers = users.stream().filter(user -> user.getSchool_year() == schoolYear)
                .count();

        return usersPerSchoolYearDTO;
    }

    private List<UsersPerCourseDTO> getUsersPerCourse(List<User> users) {
        List<UsersPerCourseDTO> usersPerCourse = new ArrayList<UsersPerCourseDTO>();

        coursesRepository.listAll().stream()
                .forEach(course -> usersPerCourse.add(createUsersPerCourseDTO(users, course)));

        return usersPerCourse;
    }

    private List<UsersPerSchoolDTO> getUsersPerSchool(List<User> users) {
        List<UsersPerSchoolDTO> usersPerSchool = new ArrayList<UsersPerSchoolDTO>();

        schoolsRepository.listAll().stream()
                .forEach(school -> usersPerSchool.add(createUsersPerSchoolDTO(users, school)));

        return usersPerSchool;
    }

    private List<UsersPerSchoolYearDTO> getUsersPerSchoolYear(List<User> users) {
        List<UsersPerSchoolYearDTO> usersPerSchoolYear = new ArrayList<UsersPerSchoolYearDTO>();

        int[] schoolYears = { 1, 2, 3 };

        for (int schoolYear : schoolYears)
            usersPerSchoolYear.add(createUsersPerSchoolYearDTO(users, schoolYear));

        return usersPerSchoolYear;
    }

    private Double getMatchPercent(Long totalAmountOfUsers) {
        List<Match> matchs = matchsRepository.listAll();

        Set<Long> usersThatHaveMatched = new TreeSet<Long>();

        matchs.stream().forEach(match -> {
            usersThatHaveMatched.add(match.getLike().getSender().getId());
            usersThatHaveMatched.add(match.getLike().getReceiver().getId());
            usersThatHaveMatched.add(match.getMutualLike().getSender().getId());
            usersThatHaveMatched.add(match.getMutualLike().getReceiver().getId());
        });

        return (double) (usersThatHaveMatched.size() / (double) totalAmountOfUsers);

    }

    public DashboardDTO execute() {
        DashboardDTO dashboardDTO = new DashboardDTO();

        dashboardDTO.usersPerSchoolYear = getUsersPerSchoolYear(usersRepository.listAll());
        dashboardDTO.usersPerSchool = getUsersPerSchool(usersRepository.listAll());
        dashboardDTO.usersPerCourse = getUsersPerCourse(usersRepository.listAll());
        
        dashboardDTO.userMatchPercent = getMatchPercent(usersRepository.streamAll().count());

        return dashboardDTO;
    }
}
