package isa.pharmacy.Services.Impl;

import isa.pharmacy.Models.MedsOrder;
import isa.pharmacy.Models.MedsQuantity;
import isa.pharmacy.Models.Pharmacy;
import isa.pharmacy.Repositories.OrderRepository;
import isa.pharmacy.Repositories.PharmacyRepository;
import isa.pharmacy.Services.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {


        @Autowired
        private OrderRepository orderRepository;

        @Autowired
        private PharmacyRepository pharmacyRepository;





        @Override
        public List<MedsOrder> findAllByPharmacyId(Long id) throws AccessDeniedException {

            List<MedsOrder> prom =  orderRepository.findAll();
            List<MedsOrder> returnProms = new ArrayList<>();

            for( MedsOrder p : prom)
            {
                Pharmacy ph = p.getPharmacy();
                if (ph.getId() == id)
                {
                    returnProms.add(p);
                    System.out.println(p.getDate());
                }

            }


            return returnProms;

        }


        @Override
        public List<MedsQuantity> getMedsByOrderId(Long id)
        {
            MedsOrder order = orderRepository.findById(id).get();

            return order.getLekovi();
        }


        @Override
        public MedsOrder add(MedsOrder p)
        {
            return orderRepository.save(p);
        }

        @Override
        public Optional<MedsOrder> findById(Long id) {
            return orderRepository.findById(id);
        }


    }
