package az.netx.heroes.repository;

import az.netx.heroes.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAllByRefObjectId(Long objId);
}
