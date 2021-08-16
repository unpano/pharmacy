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
}
