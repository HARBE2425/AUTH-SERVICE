package com.harbe.authservice.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.harbe.authservice.dto.model.AddressDto;
import com.harbe.authservice.entity.Address;
import lombok.AllArgsConstructor;

@Component // Đánh dấu class là Spring Bean để có thể inject
@AllArgsConstructor
public class AddressMapper {
    private ModelMapper mapper; // Inject ModelMapper để chuyển đổi giữa các object

    // Chuyển đổi từ Entity sang DTO
    public AddressDto mapToDto(Address address) {
        AddressDto addressDto = mapper.map(address, AddressDto.class);
        return addressDto;
    }

    // Chuyển đổi từ DTO sang Entity
    public Address mapToEntity(AddressDto addressDto) {
        Address address = mapper.map(addressDto, Address.class);
        return address;
    }
}
