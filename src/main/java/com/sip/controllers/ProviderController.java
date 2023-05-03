package com.sip.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sip.entities.Article;
import com.sip.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.entities.Provider;
import com.sip.repositories.ProviderRepository;

@Controller
@RequestMapping("/provider")
public class ProviderController {
	
private final ProviderRepository providerRepository;
    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderRepository providerRepository, ProviderService providerService) {
        this.providerRepository = providerRepository;
        this.providerService = providerService;
    }


    @GetMapping("list")
    //@ResponseBody
    public String listProviders(Model model) {
    	List<Provider> lp = this.providerService.getAllProvider();

        model.addAttribute("providers", lp);
        
        return "provider/listProviders";

    }
    
    @GetMapping("add")
    public String showAddProviderForm(Model model) {
    	Provider provider = new Provider();// object dont la valeur des attributs par defaut
    	model.addAttribute("provider", provider);
        return "provider/addProvider";
    }
    
    @PostMapping("add")
    public String addProvider(@Valid Provider provider, BindingResult result) {
        if (result.hasErrors()) {
            return "provider/addProvider";
        }
        if(provider.getName()=="")
        	provider.setName(null);
        //providerRepository.save(provider);
        this.providerService.persistProvider(provider);
        return "redirect:list";
    }

    
    @GetMapping("delete/{id}")
    public String deleteProvider(@PathVariable("id") long id, Model model) {
    	
    	
    	//long id2 = 100L;
    	
        Provider provider = providerRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
        
        //System.out.println("suite du programme...");
        
        providerRepository.delete(provider);
        
        //model.addAttribute("providers", providerRepository.findAll());
        //return "provider/listProviders";
        return "redirect:../list";
    }
    
    
    @GetMapping("edit/{id}")
    public String showProviderFormToUpdate(@PathVariable("id") long id, Model model) {
        Provider provider = providerRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
        
        model.addAttribute("provider", provider);
        
        return "provider/updateProvider";
    }


    
    @PostMapping("update")
    public String updateProvider(@Valid Provider provider, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		/* Provider providerToUpdate = providerRepository.findById(provider.getId())
    		            .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + provider.getId()));
    		        
    		        model.addAttribute("provider", providerToUpdate);*/
            return "provider/updateProvider";
        }
    	
    	providerRepository.save(provider);
    	return"redirect:list";
    	
    }
    @GetMapping("show/{id}")
    public String showProvider(@PathVariable("id") long id, Model model) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
        List<Article> articles = providerRepository.findArticlesByProvider(id);
        for (Article a : articles)
            System.out.println("Article = " + a.getLabel());

        model.addAttribute("articles", articles);
        model.addAttribute("provider", provider);
        return "provider/showProvider";
    }

	
	

}
