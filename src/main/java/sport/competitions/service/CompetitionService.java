package sport.competitions.service;

import sport.competitions.db.model.Competition;

public interface CompetitionService {

    Iterable<Competition> listAll();

    void delete(Integer id);

    Competition add(Integer number, String name, String date, String place);

    Competition findByNumber(Integer number);

}
