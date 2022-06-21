package com.nttdata.bc19.mstransfer.api;

import com.nttdata.bc19.mstransfer.model.Transfer;
import com.nttdata.bc19.mstransfer.request.TransferRequest;
import com.nttdata.bc19.mstransfer.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/transfer")
public class TransferApi {
    @Autowired
    private ITransferService transferService;

    @PostMapping
    public Mono<Transfer> create(@RequestBody TransferRequest transferRequest){ return transferService.create(transferRequest); }

    @PutMapping
    public Mono<Transfer> update(@RequestBody Transfer transfer){ return transferService.update(transfer); }

    @GetMapping
    public Flux<Transfer> findAll(){
        return transferService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Transfer> findById(@PathVariable String id){ return transferService.findById(id); }

    /*
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return personClientService.deleteById(id);
    }
     */
}
