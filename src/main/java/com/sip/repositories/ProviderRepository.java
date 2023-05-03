package com.sip.repositories;

import com.sip.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.entities.Provider;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider,Long> {

    @Query("FROM Article a WHERE a.provider.id = ?1")
    List<Article> findArticlesByProvider(long id);
}
