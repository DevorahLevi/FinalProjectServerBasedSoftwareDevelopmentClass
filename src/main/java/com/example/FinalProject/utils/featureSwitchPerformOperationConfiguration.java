package com.example.FinalProject.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@RequiredArgsConstructor
@Configuration
@ConfigurationProperties("feature-switch.perform-operation")
public class featureSwitchPerformOperationConfiguration
{
    private boolean performOperationOn;
}
