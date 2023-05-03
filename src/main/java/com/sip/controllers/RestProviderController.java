package com.sip.controllers;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.entities.Provider;
import com.sip.repositories.ProviderRepository;

@RestController
@RequestMapping("/rest/providers")
@CrossOrigin(origins="*")
public class RestProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping("/list")
    public List<Provider> getAllProviders() {
        return (List<Provider>) providerRepository.findAll();
    }

    @PostMapping("/add")
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerRepository.save(provider);
    }

    @PutMapping("/{providerId}")
    public Provider updateProvider(@PathVariable Long providerId, @Valid @RequestBody Provider providerRequest) {
        return providerRepository.findById(providerId).map(provider -> {
            provider.setName(providerRequest.getName());
            provider.setEmail(providerRequest.getEmail());
            provider.setAddress(providerRequest.getAddress());
            return providerRepository.save(provider);
        }).orElseThrow(() -> new IllegalArgumentException("ProviderId " + providerId + " not found"));
    }


    @GetMapping("/{providerId}")
    public Provider getProvider(@PathVariable Long providerId) {
        Optional<Provider> p = providerRepository.findById(providerId);
        return p.orElseThrow(()->new NoSuchElementException("Element introuvable!"));
    }

    @DeleteMapping("/{providerId}")
    public  Provider deleteProvider(@PathVariable Long providerId) {

        Optional<Provider> p = providerRepository.findById(providerId);

        if(p.isPresent())
        {
            providerRepository.delete(p.get());
        }
        else throw new NoSuchElementException("Element introuvable!");
        return p.get();
    }



}
