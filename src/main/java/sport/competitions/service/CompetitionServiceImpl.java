package sport.competitions.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sport.competitions.db.dao.CompetitionRepository;
import sport.competitions.db.model.Competition;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Iterable<Competition> listAll() {
        return competitionRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        competitionRepository.deleteById(id);
    }

    @Override
    public Competition add(Integer number, String name, String date, String place) {
        return competitionRepository.save(new Competition(number, name, date, place));
    }

    @Override
    public Competition findByNumber(Integer number) {
        return competitionRepository.findByNumber(number);
    }

}
