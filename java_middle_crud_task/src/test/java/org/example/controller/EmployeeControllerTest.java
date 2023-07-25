package org.example.controller;

import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetEmployee() throws Exception {
        final ResultActions result =
                mvc.perform(
                        get("/employees")
                                .contentType(MediaType.APPLICATION_JSON));

        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCreateEmployee() throws Exception {
        final ResultActions result =
                mvc.perform(
                        post("/employees")
                                .content("{ \"id\": \"1\", \"name\": \"John\"}")
                                .contentType(MediaType.APPLICATION_JSON));

                result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        final ResultActions result =
                mvc.perform(
                        put("/employees")
                                .contentType(MediaType.APPLICATION_JSON));

        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
        final ResultActions result =
                mvc.perform(
                        delete("/employees/{id}")
                                .contentType(MediaType.APPLICATION_JSON));

        result
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}