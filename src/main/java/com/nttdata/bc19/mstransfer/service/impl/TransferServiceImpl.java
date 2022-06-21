package com.nttdata.bc19.mstransfer.service.impl;

import com.nttdata.bc19.mstransfer.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransfer.model.Transfer;
import com.nttdata.bc19.mstransfer.repository.ITransferRepository;
import com.nttdata.bc19.mstransfer.request.TransferRequest;
import com.nttdata.bc19.mstransfer.service.ITransferService;
import com.nttdata.bc19.mstransfer.util.LogMessage;
import com.nttdata.bc19.mstransfer.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransferServiceImpl implements ITransferService {
    private final Logger LOGGER = LoggerFactory.getLogger("TransferLog");
    private final String SAVESUCCESS = "SAVESUCCESS";
    private final String UPDATESUCCESS = "UPDATESUCCESS";
    @Autowired
    ITransferRepository iTransferRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<Transfer> create(TransferRequest transferRequest) {

        if(transferRequest.getAccountTypeSource().equals("SAVINGACCOUNT") && transferRequest.getAccountTypeDestiny().equals("SAVINGACCOUNT")){
            return clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(savingAccountPersonSource ->
                            clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(savingAccountPersonDestiny -> {
                                        savingAccountPersonSource.setAmount(savingAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateSavingAccountPerson(savingAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(savingAccountPersonSourceUpdated -> {
                                                    savingAccountPersonDestiny.setAmount(savingAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateSavingAccountPerson(savingAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(savingAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("SAVINGACCOUNT") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTPERSON")){
            return clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(savingAccountPersonSource ->
                            clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountPersonDestiny -> {
                                        savingAccountPersonSource.setAmount(savingAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateSavingAccountPerson(savingAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(savingAccountPersonSourceUpdated -> {
                                                    currentAccountPersonDestiny.setAmount(currentAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("SAVINGACCOUNT") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTBUSINESS")){
            return clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(savingAccountPersonSource ->
                            clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountBusinessDestiny -> {
                                        savingAccountPersonSource.setAmount(savingAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateSavingAccountPerson(savingAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(savingAccountPersonSourceUpdated -> {
                                                    currentAccountBusinessDestiny.setAmount(currentAccountBusinessDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountBusinessDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("SAVINGACCOUNT") && transferRequest.getAccountTypeDestiny().equals("FIXEDTERMACCOUNT")){
            return clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(savingAccountPersonSource ->
                            clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(fixedTermAccountPersonDestiny -> {
                                        savingAccountPersonSource.setAmount(savingAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateSavingAccountPerson(savingAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(savingAccountPersonSourceUpdated -> {
                                                    fixedTermAccountPersonDestiny.setAmount(fixedTermAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(fixedTermAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTPERSON") && transferRequest.getAccountTypeDestiny().equals("SAVINGACCOUNT")){
            return clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountPersonSource ->
                            clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(savingAccountPersonDestiny -> {
                                        currentAccountPersonSource.setAmount(currentAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountPersonSourceUpdated -> {
                                                    savingAccountPersonDestiny.setAmount(savingAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateSavingAccountPerson(savingAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(savingAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTPERSON") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTPERSON")){
            return clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountPersonSource ->
                            clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountPersonDestiny -> {
                                        currentAccountPersonSource.setAmount(currentAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountPersonSourceUpdated -> {
                                                    currentAccountPersonDestiny.setAmount(currentAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTPERSON") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTBUSINESS")){
            return clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountPersonSource ->
                            clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountBusinessDestiny -> {
                                        currentAccountPersonSource.setAmount(currentAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountPersonSourceUpdated -> {
                                                    currentAccountBusinessDestiny.setAmount(currentAccountBusinessDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountBusinessDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTPERSON") && transferRequest.getAccountTypeDestiny().equals("FIXEDTERMACCOUNT")){
            return clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountPersonSource ->
                            clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(fixedTermAccountPersonDestiny -> {
                                        currentAccountPersonSource.setAmount(currentAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountPersonSourceUpdated -> {
                                                    fixedTermAccountPersonDestiny.setAmount(fixedTermAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(fixedTermAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTBUSINESS") && transferRequest.getAccountTypeDestiny().equals("SAVINGACCOUNT")){
            return clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountBusinessSource ->
                            clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(savingAccountPersonDestiny -> {
                                        currentAccountBusinessSource.setAmount(currentAccountBusinessSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountBusinessSourceUpdated -> {
                                                    savingAccountPersonDestiny.setAmount(savingAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateSavingAccountPerson(savingAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(savingAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTBUSINESS") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTPERSON")){
            return clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountBusinessSource ->
                            clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountPersonDestiny -> {
                                        currentAccountBusinessSource.setAmount(currentAccountBusinessSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountBusinessSourceUpdated -> {
                                                    currentAccountPersonDestiny.setAmount(currentAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTBUSINESS") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTBUSINESS")){
            return clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountBusinessSource ->
                            clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountBusinessDestiny -> {
                                        currentAccountBusinessSource.setAmount(currentAccountBusinessSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountBusinessSourceUpdated -> {
                                                    currentAccountBusinessDestiny.setAmount(currentAccountBusinessDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountBusinessDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("CURRENTACCOUNTBUSINESS") && transferRequest.getAccountTypeDestiny().equals("FIXEDTERMACCOUNT")){
            return clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(currentAccountBusinessSource ->
                            clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(fixedTermAccountPersonDestiny -> {
                                        currentAccountBusinessSource.setAmount(currentAccountBusinessSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(currentAccountBusinessSourceUpdated -> {
                                                    fixedTermAccountPersonDestiny.setAmount(fixedTermAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(fixedTermAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("FIXEDTERMACCOUNT") && transferRequest.getAccountTypeDestiny().equals("SAVINGACCOUNT")){
            return clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(fixedTermAccountPersonSource ->
                            clientServiceWC.findSavingAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(savingAccountPersonDestiny -> {
                                        fixedTermAccountPersonSource.setAmount(fixedTermAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(fixedTermAccountPersonSourceUpdated -> {
                                                    savingAccountPersonDestiny.setAmount(savingAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateSavingAccountPerson(savingAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(savingAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("FIXEDTERMACCOUNT") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTPERSON")){
            return clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(fixedTermAccountPerson ->
                            clientServiceWC.findCurrentAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountPersonDestiny -> {
                                        fixedTermAccountPerson.setAmount(fixedTermAccountPerson.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPerson)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(fixedTermAccountPersonSourceUpdated -> {
                                                    currentAccountPersonDestiny.setAmount(currentAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountPerson(currentAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("FIXEDTERMACCOUNT") && transferRequest.getAccountTypeDestiny().equals("CURRENTACCOUNTBUSINESS")){
            return clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(fixedTermAccountPersonSource ->
                            clientServiceWC.findCurrentAccountBusinessByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(currentAccountBusinessDestiny -> {
                                        fixedTermAccountPersonSource.setAmount(fixedTermAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(fixedTermAccountSourceUpdated -> {
                                                    currentAccountBusinessDestiny.setAmount(currentAccountBusinessDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateCurrentAccountBusiness(currentAccountBusinessDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(currentAccountBusinessDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else if(transferRequest.getAccountTypeSource().equals("FIXEDTERMACCOUNT") && transferRequest.getAccountTypeDestiny().equals("FIXEDTERMACCOUNT")){
            return clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountSource())
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(fixedTermAccountPersonSource ->
                            clientServiceWC.findFixedTermAccountPersonByAccountNumber(transferRequest.getNumberAccountDestiny())
                                    .switchIfEmpty(Mono.error(new Exception()))
                                    .flatMap(fixedTermAccountPersonDestiny -> {
                                        fixedTermAccountPersonSource.setAmount(fixedTermAccountPersonSource.getAmount() - transferRequest.getAmount());
                                        return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonSource)
                                                .switchIfEmpty(Mono.error(new Exception()))
                                                .flatMap(fixedTermAccountPersonSourceUpdated -> {
                                                    fixedTermAccountPersonDestiny.setAmount(fixedTermAccountPersonDestiny.getAmount() + transferRequest.getAmount());
                                                    return clientServiceWC.updateFixedTermAccountPerson(fixedTermAccountPersonDestiny)
                                                            .switchIfEmpty(Mono.error(new Exception()))
                                                            .flatMap(fixedTermAccountPersonDestinyUpdated -> {
                                                                Transfer transfer = new Transfer();
                                                                transfer.setCreatedAt(LocalDateTime.now());
                                                                transfer.setAmount(transferRequest.getAmount());
                                                                transfer.setNumberAccountSource(transferRequest.getNumberAccountSource());
                                                                transfer.setAccountTypeSource(transferRequest.getAccountTypeSource());
                                                                transfer.setNumberAccountDestiny(transferRequest.getNumberAccountDestiny());
                                                                transfer.setAccountTypeDestiny(transferRequest.getAccountTypeDestiny());
                                                                return iTransferRepository.save(transfer);
                                                            });
                                                });
                                    })
                    );
        }
        else{
            return Mono.error(new ModelNotFoundException("Account Type incorrect"));
        }
    }

    @Override
    public Mono<Transfer> update(Transfer transfer) {
        transfer.setUpdatedAt(LocalDateTime.now());
        return iTransferRepository.save(transfer);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return iTransferRepository.deleteById(id);
    }

    @Override
    public Mono<Transfer> findById(String id) {
        return iTransferRepository.findById(id);
    }

    @Override
    public Flux<Transfer> findAll() {
        return iTransferRepository.findAll();
    }
}
