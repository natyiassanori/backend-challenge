package com.userapi.dto.mapper;

import com.userapi.dto.EmailDto;
import com.userapi.dto.ReplaceEmailDto;
import com.userapi.model.Email;
import com.userapi.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class EmailMapper {
    public Set<EmailDto> toDtoSet(Set<Email> emailSet){
        Set<EmailDto> emailDtoSet = new HashSet<EmailDto>();
        for(Email email : emailSet){
            emailDtoSet.add(toDto(email));
        }
        return  emailDtoSet;
    }

    public Set<Email> toModelSet(Set<EmailDto> emailDtoSet, User user){
        Set<Email> emailSet = new HashSet<Email>();
        for(EmailDto emailDto : emailDtoSet){
            emailSet.add(toModel(emailDto, user));
        }
        return  emailSet;
    }

    public EmailDto toDto(Email email){
        EmailDto emailDto = new EmailDto();
        emailDto.setMail(email.getMail());
        return emailDto;
    }

    public Email toModel(EmailDto emailDto, User user){
        Email email = new Email();
        email.setMail(emailDto.getMail());
        email.setUser(user);
        return email;
    }

    public Email toModel(ReplaceEmailDto replaceEmailDto){
        Email email = new Email();
        email.setMail(replaceEmailDto.getNewMail());
        return email;
    }
}
