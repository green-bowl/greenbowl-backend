package com.greenbowl.greenbowlserver.user.adapter.in.web.controller;

import com.greenbowl.greenbowlserver.common.adapter.in.WebAdapter;
import com.greenbowl.greenbowlserver.user.adapter.in.web.mapper.UserRequestToCommandMapper;
import com.greenbowl.greenbowlserver.user.adapter.in.web.request.SignInRequest;
import com.greenbowl.greenbowlserver.user.adapter.in.web.response.SignInResponse;
import com.greenbowl.greenbowlserver.user.port.in.AuthenticationResult;
import com.greenbowl.greenbowlserver.user.port.in.command.SignInCommand;
import com.greenbowl.greenbowlserver.user.port.in.usecase.AuthenticationUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationUseCase authenticationUseCase;

    private static final String SIGN_IN = "로그인";
    private static final String SIGN_IN_DESCRIPTION = "email을 입력해 로그인을 할 수 있습니다.";
    private static final String SIGN_IN_FORM = "로그인 양식";

    @ApiOperation(value = SIGN_IN, notes = SIGN_IN_DESCRIPTION)
    @PostMapping("/login")
    public ResponseEntity<SignInResponse> signInUser
            (@RequestBody @ApiParam(value = SIGN_IN_FORM) SignInRequest signInRequest) {
        SignInCommand signInCommand = UserRequestToCommandMapper.mapToCommand(signInRequest);
        AuthenticationResult authenticationResult = authenticationUseCase.signInUser(signInCommand);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticationResult.getAccessToken());

        if (authenticationResult.isNewUser()) {
            return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(SignInResponse.from(authenticationResult));
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(SignInResponse.from(authenticationResult));
    }
}
