package com.example.FinalProject.service;

import com.example.FinalProject.model.Person;
import com.example.FinalProject.model.Response;
import com.example.FinalProject.model.Values;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinalProjectService
{
    public Response isUnderAgeThirteen(Person person) throws HttpClientErrorException {
        Response response;

        if (person.getName() == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Sorry, you have not entered a name. Please try again.");
        } else if (person.getAge() < 0 || person.getAge() > 120) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Sorry, you have entered an invalid age. Please try again.");
        } else if (person.getAge() >= 13) {
            response = Response.builder().message(String.format("Hooray! %s is over the age of Bar Mitzvah!", person.getName())).build();
        } else {
            response = Response.builder().message(String.format("Sorry, %s is not yet over the age of Bar Mitzvah.", person.getName())).build();
        }
        return response;
    }

    public Timestamp getTime()
    {
        return new Timestamp(System.currentTimeMillis());
//        return Instant.now().toString();
    }

    public Values performOperation(Values values, String operation, double x) throws HttpClientErrorException
    {
        List<Double> newValues;
        switch (operation) {
            case ("ADD"):
                newValues = values.getValues().stream().map(i -> i += x).collect(Collectors.toList());
                break;
            case ("SUBTRACT"):
                newValues = values.getValues().stream().map(i -> i -= x).collect(Collectors.toList());
                break;
            case ("MULTIPLY"):
                newValues = values.getValues().stream().map(i -> i *= x).collect(Collectors.toList());
                break;
            case ("DIVIDE"):
                newValues = values.getValues().stream().map(i -> i /= x).collect(Collectors.toList());
                break;
            default:
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Sorry, the operation you entered is not valid. Please try again.");
        }
        return Values.builder().values(newValues).build();
    }
}