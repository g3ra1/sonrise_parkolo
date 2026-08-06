package org.example.sonrise_parkolo.service;

import org.example.sonrise_parkolo.dto.input.UserInputDTO;
import org.example.sonrise_parkolo.dto.output.UserOutputDTO;

public interface UserService {

    String createUser(UserInputDTO userInputDTO);

    UserOutputDTO updateUser(UserInputDTO userInputDTO);

    String deleteUser(Long id);

    UserOutputDTO getUser(Long id);
}
