package se.lexicon.vxo.presence.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.vxo.presence.entity.user.Cv;

public interface CvRepository extends CrudRepository<Cv,Integer> {
}
