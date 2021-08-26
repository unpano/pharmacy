package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.Rate;
import isa.pharmacy.Models.WhomRates;
import isa.pharmacy.Repositories.RateRepository;
import isa.pharmacy.Services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Object addRate(Rate rate1) {
        return rateRepository.save(rate1);
    }

    @Override
    public List<Rate> findAllByUserIdAndWhomRates(Long id, WhomRates whom) {
        return rateRepository.findAllByUserIdAndWhomRates(id,whom);
    }

    @Override
    public List<Rate> findAll() {
        return rateRepository.findAll();
    }

    @Override
    public Optional<Rate> findById(Long rateId) {
        return rateRepository.findById(rateId);
    }

    @Override
    public Rate update(Rate rate1) {
        return rateRepository.save(rate1);
    }
}
