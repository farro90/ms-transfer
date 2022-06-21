package com.nttdata.bc19.mstransfer.repository;

import com.nttdata.bc19.mstransfer.model.Transfer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends ReactiveMongoRepository<Transfer, String> {
}
