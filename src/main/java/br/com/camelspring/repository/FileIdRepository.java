package br.com.camelspring.repository;

import br.com.camelspring.entity.FileId;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FileIdRepository extends CrudRepository<FileId, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FileId> findByDateAndLayout(LocalDate date, String layout);
}
