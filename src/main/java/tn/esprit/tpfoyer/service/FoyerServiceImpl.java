package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private static final Logger logger = LoggerFactory.getLogger(FoyerServiceImpl.class);
    private final FoyerRepository foyerRepository;

    public List<Foyer> retrieveAllFoyers() {
        logger.info("Récupération de tous les foyers");
        return foyerRepository.findAll();
    }

    public Foyer retrieveFoyer(Long foyerId) {
        logger.info("Récupération du foyer avec ID: {}", foyerId);
        return foyerRepository.findById(foyerId).orElse(null);
    }

    public Foyer addFoyer(Foyer f) {
        logger.info("Ajout d'un nouveau foyer: {}", f);
        return foyerRepository.save(f);
    }

    public Foyer modifyFoyer(Foyer foyer) {
        logger.info("Modification du foyer: {}", foyer);
        return foyerRepository.save(foyer);
    }

    public void removeFoyer(Long foyerId) {
        logger.info("Suppression du foyer avec ID: {}", foyerId);
        foyerRepository.deleteById(foyerId);
    }
}
