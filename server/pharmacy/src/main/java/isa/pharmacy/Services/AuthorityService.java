package isa.pharmacy.Services;

import isa.pharmacy.Models.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> findById(Long id);
    List<Authority> findByName(String name);
}
