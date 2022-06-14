package com.temesgenbesha.projectmanagementsystem.convertor;

import com.temesgenbesha.projectmanagementsystem.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUsersConverter implements Converter<String, UserDTO> {



    @Override
    public UserDTO convert(String source) {
        Long id = Long.valueOf(source);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        return userDTO;
    }
}
