package ru.job4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService service;


    @Test
    @WithMockUser
    public void shouldReturnCreateView() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnError() throws Exception {
        this.mockMvc.perform(get("/editAccident/1"))
                .andDo(print())
                .andExpect(view().name("errors/404"));
    }

    @Test
    @WithMockUser
    public void shouldReturnEdit() throws Exception {
        Accident accident = new Accident(
                1,
                "Парковка в неположеном месте",
                "Припарковался на газоне",
                "Ул. Московская д.1",
                new AccidentType(1, "Машина и человек"),
                Set.of(new Rule(1, "Статья 1"), new Rule(2, "Статья 2"))
        );
        when(service.findById(accident.getId())).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/editAccident/" + accident.getId()))
                .andDo(print())
                .andExpect(view().name("editAccident"));
    }
}