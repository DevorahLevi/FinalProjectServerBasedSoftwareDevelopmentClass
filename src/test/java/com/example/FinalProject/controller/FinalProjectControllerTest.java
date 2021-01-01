package com.example.FinalProject.controller;

import com.example.FinalProject.service.FinalProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinalProjectControllerTest
{
    @Mock
    private FinalProjectService mockFinalProjectService;

    @InjectMocks
    private FinalProjectController finalProjectController;

    @Test
    public void getTime_shouldCallServiceAndReturnItsResults()
    {
        when(mockFinalProjectService.getTime()).thenReturn(Timestamp.valueOf("2021-01-01 03:16:50.8500000"));
        Timestamp actual = finalProjectController.getTime();
        verify(mockFinalProjectService).getTime();
        Timestamp expected = Timestamp.valueOf("2021-01-01 03:16:50.8500000");
        assertThat(actual).isEqualTo(expected);
    }
}