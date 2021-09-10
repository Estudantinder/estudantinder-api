package org.estudantinder.features.Reports.DeleteReport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.User;
import org.estudantinder.repositories.ReportsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    ReportsRepository reportsRepository;

    @Inject
    UsersRepository usersRepository;

    public void treatUserNotFound(User user) {
        if(user == null) throw new NotFoundException("Usuário não encontrada");
    }

    public void execute(Long userId) throws Exception {
        User user = usersRepository.findById(userId);

        treatUserNotFound(user);

        reportsRepository.findByUser(user)
            .forEach(deletedReport -> reportsRepository.delete(deletedReport));
    }
}
