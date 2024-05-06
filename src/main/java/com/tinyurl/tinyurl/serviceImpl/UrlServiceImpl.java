package com.tinyurl.tinyurl.serviceImpl;

import com.tinyurl.tinyurl.dtos.UrlReqDto;
import com.tinyurl.tinyurl.exceptions.CustomRequestException;
import com.tinyurl.tinyurl.models.Url;
import com.tinyurl.tinyurl.repositories.UrlRepository;
import com.tinyurl.tinyurl.services.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
@Slf4j
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    public Url create(UrlReqDto data) throws CustomRequestException {
        if (!isValidURL(data.getUrl())) {
            throw new CustomRequestException(HttpStatus.BAD_REQUEST, "Invalid URL");
        }
        String shortUrl;
        while (true) {
            shortUrl = generateShortURL();
            boolean checkExists = urlRepository.existsByUrlId(shortUrl);
            if (!checkExists) {
                break;
            }
        }
        Url url = new Url();
        url.setUrlId(shortUrl);
        url.setOriginalUrl(data.getUrl());
        urlRepository.save(url);
        return url;
    }

    @Override
    public Url delete(String urlId) throws CustomRequestException {
        Url url = urlRepository.findByUrlId(urlId);
        if (url == null) {
            throw new CustomRequestException(HttpStatus.NOT_FOUND, "Url not found");
        }
        urlRepository.delete(url);
        return url;
    }

    @Override
    public List<Url> getAll() throws CustomRequestException {
        return urlRepository.findAll();
    }

    @Override
    public Url get(String urlId) throws CustomRequestException {
        Url url = urlRepository.findByUrlId(urlId);
        if (url == null) {
            throw new CustomRequestException(HttpStatus.NOT_FOUND, "Url not found");
        }
        return url;
    }


    private boolean isValidURL(String url) {
        String regex = "^(https?|ftp)://(www\\.)?([\\w.-]+)+(:\\d+)?(/([\\w/_.]*)?)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    private long generateRandomNumber(long min, long max) {
        SecureRandom random = new SecureRandom();
        return min + ((long) (random.nextDouble() * (max - min)));
    }

    // Generate a short URL of 6 to 7 characters using Base64 encoding
    private String generateShortURL() {
        long randomNumber = generateRandomNumber(1000000L, 9999999L); // Generate a random 7-digit number
        byte[] bytes = String.valueOf(randomNumber).getBytes(); // Convert the number to bytes
        String shortURL = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes); // Encode to Base64
        return shortURL.substring(0, 7); // Trim to 7 characters
    }
}
