package pl.piomin.services.grpc.account.repository;


import pl.piomin.services.grpc.account.model.AccountProto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AccountRepository {

    List<AccountProto.Account> accounts;
    AtomicInteger id;

    public AccountRepository(List<AccountProto.Account> accounts) {
        this.accounts = accounts;
        this.id = new AtomicInteger();
        this.id.set(accounts.size());
    }

    public List<AccountProto.Account> findAll() {
        return accounts;
    }

    public List<AccountProto.Account> findByCustomer(int customerId) {
        return accounts.stream().filter(it -> it.getCustomerId() == customerId).collect(Collectors.toList());
    }

    public AccountProto.Account findByNumber(String number) {
        return accounts.stream()
                .filter(it -> it.getNumber().equals(number))
                .findFirst()
                .orElseThrow();
    }

    public AccountProto.Account add(int customerId, String number) {
        AccountProto.Account a = AccountProto.Account.newBuilder()
                .setId(id.incrementAndGet())
                .setCustomerId(customerId)
                .setNumber(number)
                .build();
        return a;
    }

}
