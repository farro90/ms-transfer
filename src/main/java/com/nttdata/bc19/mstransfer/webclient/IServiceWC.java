package com.nttdata.bc19.mstransfer.webclient;

import com.nttdata.bc19.mstransfer.model.responseWC.CurrentAccountBusiness;
import com.nttdata.bc19.mstransfer.model.responseWC.CurrentAccountPerson;
import com.nttdata.bc19.mstransfer.model.responseWC.FixedTermAccountPerson;
import com.nttdata.bc19.mstransfer.model.responseWC.SavingAccountPerson;
import reactor.core.publisher.Mono;

public interface IServiceWC {
    Mono<CurrentAccountPerson> findCurrentAccountPersonById(String id);
    Mono<CurrentAccountPerson> findCurrentAccountPersonByAccountNumber(String accountNumber);
    Mono<SavingAccountPerson> findSavingAccountPersonById(String id);
    Mono<SavingAccountPerson> findSavingAccountPersonByAccountNumber(String accountNumber);
    Mono<FixedTermAccountPerson> findFixedTermAccountPersonById(String id);
    Mono<FixedTermAccountPerson> findFixedTermAccountPersonByAccountNumber(String accountNumber);
    Mono<CurrentAccountBusiness> findCurrentAccountBusinessById(String id);
    Mono<CurrentAccountBusiness> findCurrentAccountBusinessByAccountNumber(String accountNumber);

    Mono<CurrentAccountPerson> updateCurrentAccountPerson(CurrentAccountPerson currentAccountPerson);
    Mono<SavingAccountPerson> updateSavingAccountPerson(SavingAccountPerson savingAccountPerson);
    Mono<FixedTermAccountPerson> updateFixedTermAccountPerson(FixedTermAccountPerson fixedTermAccountPerson);
    Mono<CurrentAccountBusiness> updateCurrentAccountBusiness(CurrentAccountBusiness currentAccountBusiness);
}
