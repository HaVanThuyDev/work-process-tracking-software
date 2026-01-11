package com.example.vn2_ht_student.handler;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import com.example.vn2_ht_student.exeption.ResourceNotFoundException;
import com.example.vn2_ht_student.exeption.RestTemplateException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError());
    }

//    @Override
//    public void handleError(ClientHttpResponse response) throws IOException {
//        String body = IOUtils.toString(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
//        if (response.getStatusCode().is5xxServerError()) {
//            // handle SERVER_ERROR
//            throw new RestTemplateException(body);
//        } else if (response.getStatusCode().is4xxClientError()) {
//            // handle CLIENT_ERROR
//            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
//                throw new ResourceNotFoundException(body);
//            } else {
//                throw new RestTemplateException(body);
//            }
//        }
//    }
}
