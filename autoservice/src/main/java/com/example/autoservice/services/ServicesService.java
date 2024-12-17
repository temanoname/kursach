package com.example.autoservice.services;

import com.example.autoservice.models.Services;
import com.example.autoservice.repository.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesService {
    private final ServiceRepository servicesRepository;

    public ServicesService(ServiceRepository serviceRepository) {
        this.servicesRepository = serviceRepository;
    }

    public List<Services> findAll() {
        return servicesRepository.findAll();
    }
    public Services saveService(Services service) {
        return servicesRepository.save(service);
    }
    public Optional<Services> findServiceById(Long id) {
        return servicesRepository.findById(id);
    }
    public void updateService(Long id, Services service) {
        Optional<Services> existingService = findServiceById(id);
        if (existingService.isPresent()) {
            Services updatedService = existingService.get();
            updatedService.setName(service.getName());
            updatedService.setPrice(service.getPrice());
            servicesRepository.save(updatedService);
        } else {
            throw new IllegalArgumentException("Service with ID " + id + " not found.");
        }
    }
    public void deleteService(Long id) {
        servicesRepository.deleteById(id);
    }


}
