package com.andre.training.infra.data.repositories;

public interface EntityDataPresenter<DomainEntity> {

    DomainEntity convertToDomain();

}
