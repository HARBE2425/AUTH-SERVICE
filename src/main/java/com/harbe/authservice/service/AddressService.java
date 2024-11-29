package com.harbe.authservice.service;

import java.util.List;
import com.harbe.authservice.dto.model.AddressDto;

public interface AddressService {
    AddressDto createAddress(long userId, AddressDto addressDto);

    AddressDto getAddressById(long id);

    public List<AddressDto> getAddressByUserId(long userId);

    AddressDto updateAddress(long userId, AddressDto addressDto, long id);

    String deleteAddress(long id);
}