package com.nttdata.bc19.mstransfer.webclient.impl;

import com.nttdata.bc19.mstransfer.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransfer.model.responseWC.*;
import com.nttdata.bc19.mstransfer.webclient.IServiceWC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class ServiceWCImpl implements IServiceWC {

    //private static final String URL_GATEWAY = "${my.property.URL_GATEWAY}";

    private static final String URL_GATEWAY = "http://localhost:8080";

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<CurrentAccountPerson> findCurrentAccountPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/current-account/person/".concat(id))
                .retrieve()
                .bodyToMono(CurrentAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountPerson> findCurrentAccountPersonByAccountNumber(String accountNumber) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/current-account/findByNumberAccount/".concat(accountNumber))
                .retrieve()
                .bodyToMono(CurrentAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<SavingAccountPerson> findSavingAccountPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/saving-account/".concat(id))
                .retrieve()
                .bodyToMono(SavingAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("SAVING ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<SavingAccountPerson> findSavingAccountPersonByAccountNumber(String accountNumber) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/saving-account/findByNumberAccount/".concat(accountNumber))
                .retrieve()
                .bodyToMono(SavingAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("SAVING ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<FixedTermAccountPerson> findFixedTermAccountPersonById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/fixed-term-account/".concat(id))
                .retrieve()
                .bodyToMono(FixedTermAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("FIXED TERM ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<FixedTermAccountPerson> findFixedTermAccountPersonByAccountNumber(String accountNumber) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/fixed-term-account/findByNumberAccount/".concat(accountNumber))
                .retrieve()
                .bodyToMono(FixedTermAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("FIXED TERM ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountBusiness> findCurrentAccountBusinessById(String id) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/current-account/business/".concat(id))
                .retrieve()
                .bodyToMono(CurrentAccountBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountBusiness> findCurrentAccountBusinessByAccountNumber(String accountNumber) {
        return webClient.baseUrl(URL_GATEWAY).build().get().uri("/api/current-account/business/findByNumberAccount/".concat(accountNumber))
                .retrieve()
                .bodyToMono(CurrentAccountBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountPerson> updateCurrentAccountPerson(CurrentAccountPerson currentAccountPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/current-account/person/")
                .body(Mono.just(currentAccountPerson), CurrentAccountPerson.class)
                .retrieve()
                .bodyToMono(CurrentAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<FixedTermAccountPerson> updateFixedTermAccountPerson(FixedTermAccountPerson fixedTermAccountPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/fixed-term-account/")
                .body(Mono.just(fixedTermAccountPerson), FixedTermAccountPerson.class)
                .retrieve()
                .bodyToMono(FixedTermAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("FIXED TERM ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<SavingAccountPerson> updateSavingAccountPerson(SavingAccountPerson savingAccountPerson) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/saving-account/")
                .body(Mono.just(savingAccountPerson), SavingAccountPerson.class)
                .retrieve()
                .bodyToMono(SavingAccountPerson.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("SAVING ACCOUNT PERSON NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CurrentAccountBusiness> updateCurrentAccountBusiness(CurrentAccountBusiness currentAccountBusiness) {
        return webClient.baseUrl(URL_GATEWAY).build().put().uri("/api/current-account/business/")
                .body(Mono.just(currentAccountBusiness), CurrentAccountBusiness.class)
                .retrieve()
                .bodyToMono(CurrentAccountBusiness.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;
                    if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new ModelNotFoundException("CURRENT ACCOUNT BUSINESS NOT FOUND"));
                    }
                    return Mono.error(error);
                });
    }

}
