package com.tinyurl.tinyurl.services;

import com.tinyurl.tinyurl.dtos.UrlReqDto;
import com.tinyurl.tinyurl.exceptions.CustomRequestException;
import com.tinyurl.tinyurl.models.Url;

import java.util.List;

public interface UrlService {
    public Url create(UrlReqDto data) throws CustomRequestException;
    public Url delete(String urlId) throws CustomRequestException;
    public List<Url> getAll() throws CustomRequestException;
    public Url get(String urlId) throws CustomRequestException;
}
