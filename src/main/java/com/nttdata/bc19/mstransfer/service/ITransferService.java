package com.nttdata.bc19.mstransfer.service;

import com.nttdata.bc19.mstransfer.model.Transfer;
import com.nttdata.bc19.mstransfer.request.TransferRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransferService {
    Mono<Transfer> create(TransferRequest transferRequest);
    Mono<Transfer> update(Transfer transfer);
    Mono<Void>deleteById(String id);
    Mono<Transfer> findById(String id);
    Flux<Transfer> findAll();
}