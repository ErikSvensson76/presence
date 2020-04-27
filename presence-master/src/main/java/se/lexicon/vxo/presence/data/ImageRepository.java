package se.lexicon.vxo.presence.data;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.vxo.presence.entity.user.ProfileImage;

public interface ImageRepository extends CrudRepository<ProfileImage,Integer> {
}
