package com.bconnelly.gdax.notifier.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Bryan on 7/25/2017.
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class EthControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetUpdatesSuccess() throws Exception {
        this.mvc.perform(get("/gdax/getUpdates/10")).andExpect(status().isOk());
    }

    @Test
    public void testGetUpdatesBadNumber() throws Exception {
        this.mvc.perform(get("/gdax/getUpdates/badnumber!!!")).andExpect(status().isBadRequest());
    }

    @Test
    public void testGetLastN() throws Exception {
        this.mvc.perform(get("/gdax/getLastN/10")).andExpect(status().isOk());
    }

    @Test
    public void testGetLastNBadNumber() throws Exception {
        this.mvc.perform(get("/gdax/getLastN/badaf")).andExpect(status().isBadRequest());
    }

}