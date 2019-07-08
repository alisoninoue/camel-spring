package br.com.camelspring.repository;

import br.com.camelspring.entity.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {
}
