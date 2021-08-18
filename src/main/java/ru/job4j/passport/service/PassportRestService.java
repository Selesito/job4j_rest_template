package ru.job4j.passport.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.passport.model.Passport;

import java.util.Collections;
import java.util.List;

@Service
public class PassportRestService {
    @Value("${api-url}")
    private String url;

    private final RestTemplate client;

    public PassportRestService(RestTemplate client) {
        this.client = client;
    }

    public Passport add(Passport passport) {
        return client.postForEntity(
                url, passport, Passport.class
        ).getBody();
    }

    public boolean replace(int id, Passport passport) {
        return client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean delete(int id) {
        return client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    private List<Passport> get(String url) {
        List<Passport> list = client.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        return list == null ? Collections.emptyList() : list;
    }

    public List<Passport> findAll() {
        return get(String.format("%s/getAll", url));
    }

    public List<Passport> findUnavaliabe() {
        return get(String.format("%s/unavailable", url));
    }

    public List<Passport> findReplaceable() {
        return get(String.format("%s/findReplaceable", url));
    }

    public Passport findBySerial(int serial) {
        return client.getForEntity(
                String.format("%s/getBySerial?serial=%s", url, serial),
                Passport.class
        ).getBody();
    }
}
