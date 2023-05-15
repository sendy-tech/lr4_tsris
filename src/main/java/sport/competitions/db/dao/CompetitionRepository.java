package sport.competitions.db.dao;

import sport.competitions.db.model.Competition;
import org.springframework.data.repository.CrudRepository;
public interface CompetitionRepository extends CrudRepository<Competition, Integer> {

    public Competition findByNumber(Integer number);
}
