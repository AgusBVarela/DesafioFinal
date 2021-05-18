package com.mercadolibre.varela_agustina.unit.controller;

import com.mercadolibre.varela_agustina.controller.PingController;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PingControllerTest {

    private PingController pingController;

    @BeforeEach
    void setUp() {
        pingController = new PingController();
    }

    @Test
    void ping() {
        Assert.assertEquals(pingController.ping(), "pong");
    }
}