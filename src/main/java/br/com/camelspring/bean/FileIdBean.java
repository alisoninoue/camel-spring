package br.com.camelspring.bean;

import br.com.camelspring.entity.FileId;
import br.com.camelspring.repository.FileIdRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class FileIdBean {

    private FileIdRepository repository;

    public FileIdBean(FileIdRepository repository) {
        this.repository = repository;
    }

    public String generateId() {
        Optional<FileId> optionalFileId = repository.findByDateAndLayout(LocalDate.now(), "teste1");

        FileId fileId = optionalFileId.orElseGet(() -> {
            FileId newfileId = new FileId();
            newfileId.setDate(LocalDate.now());
            newfileId.setLayout("teste1");
            newfileId.setSequencial(1L);
            return repository.save(newfileId);
        });
        Long sequencial = fileId.getSequencial();

        if (optionalFileId.isPresent()) {
            fileId.setSequencial(++sequencial);
            repository.save(fileId);
        }

        return StringUtils.leftPad(sequencial.toString(),5, '0');
    }
}
