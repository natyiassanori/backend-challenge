package com.userapi.dto.mapper;

import com.userapi.dto.PhoneNumberDto;
import com.userapi.dto.ReplacePhoneNumberDto;
import com.userapi.model.PhoneNumber;
import com.userapi.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PhoneNumberMapper {

    public Set<PhoneNumberDto> toDtoSet(Set<PhoneNumber> phoneNumberSet){
        Set<PhoneNumberDto> phoneNumberDtoSet = new HashSet<PhoneNumberDto>();
        for(PhoneNumber phoneNumber : phoneNumberSet){
            phoneNumberDtoSet.add(toDto(phoneNumber));
        }
        return  phoneNumberDtoSet;
    }

    public Set<PhoneNumber> toModelSet(Set<PhoneNumberDto> phoneNumberDtoSet, User user){
        Set<PhoneNumber> phoneNumberSet = new HashSet<PhoneNumber>();
        for(PhoneNumberDto phoneNumberDto : phoneNumberDtoSet){
            phoneNumberSet.add(toModel(phoneNumberDto, user));
        }
        return  phoneNumberSet;
    }

    public PhoneNumberDto toDto(PhoneNumber phoneNumber){
        PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
        phoneNumberDto.setNumber(phoneNumber.getNumber());
        return phoneNumberDto;
    }

    public PhoneNumber toModel(PhoneNumberDto phoneNumberDto, User user){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(phoneNumberDto.getNumber());
        phoneNumber.setUser(user);
        return phoneNumber;
    }

    public PhoneNumber toModel(ReplacePhoneNumberDto replacePhoneNumberDto){
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(replacePhoneNumberDto.getNewNumber());
        return phoneNumber;
    }
}
