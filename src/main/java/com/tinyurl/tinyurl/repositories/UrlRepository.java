package com.tinyurl.tinyurl.repositories;

import com.tinyurl.tinyurl.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,String> {
    boolean existsByUrlId(String urlId);

    Url findByUrlId(String urlId);
}
