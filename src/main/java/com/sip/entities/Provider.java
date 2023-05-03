package com.sip.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Provider {
	@Id  //clé primaire
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", nullable=false)
   // @Column(name = "name")
    private String name;
    
    @NotBlank(message = "Address is mandatory")
    @Column(name = "address", nullable=false)
    private String address;
    
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    public Provider() {
    	System.out.println("Hello From constructeur");	
    }

    public Provider(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    @Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + "]";
	}

	public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    // block d'initilisatiin d'instance
    {
    	System.out.println("Hello From Block d'instance");
    }
    
    // block d'initilisatiin d'instance
    static {
    	System.out.println("Hello From Block de classe");
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "provider")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


}

