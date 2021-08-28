package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Med;
import isa.pharmacy.Models.User;
import isa.pharmacy.Repositories.MedRepository;
import isa.pharmacy.Services.MedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedServiceImpl implements MedService {

    @Autowired
    private MedRepository medRepository;

    public List<Med> findAll() throws AccessDeniedException {
        return medRepository.findAll();
    }

    @Override
    public Optional<Med> findById(Long id) {
        return medRepository.findById(id);
    }






    public Optional<Med> update(Med med) {
        Optional<Med> medOpt = medRepository.findById(med.getId());


        if (medOpt.isPresent()) {
            Med newMed = medOpt.get();

            if (med.getName() != null) {
                newMed.setName(med.getName());
            }

            if (med.getForm() != null) {
                newMed.setForm(med.getForm());
            }

            if (med.getProducer() != null) {
                newMed.setProducer(med.getProducer());
            }

            if (med.getAdditionalNotes() != null) {
                newMed.setAdditionalNotes(med.getAdditionalNotes());
            }

            if (med.getType()!= null) {
                newMed.setType(med.getType());
            }

            if (med.getIssuanceRegime()!= null) {
                newMed.setIssuanceRegime(med.getIssuanceRegime());
            }

            if (med.getPrice() != 0) {
                newMed.setPrice(med.getPrice());
            }




            medRepository.save(newMed);

            return Optional.of(newMed);
        }

        return Optional.empty();
    }
}
