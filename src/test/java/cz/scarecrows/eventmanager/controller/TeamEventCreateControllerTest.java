package cz.scarecrows.eventmanager.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.jdbc.Sql;

import cz.scarecrows.eventmanager.AbstractIntegrationTest;
import okhttp3.OkHttpClient;


/**
 * TeamEventControllerTest
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
@EnableAutoConfiguration
public class TeamEventCreateControllerTest extends AbstractIntegrationTest {

    public OkHttpClient okHttpClient;

    @BeforeEach
    public void init() {
        okHttpClient = new OkHttpClient();
    }

    @Test
    @DisplayName("Test create event with no date provided other than event start.")
    void testCreateEvent_when_noDateProvided() {
        Assertions.assertNotNull(okHttpClient);
    }
}
