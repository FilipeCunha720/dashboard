package com.example.dashboard;

import com.example.dashboard.domain.DashboardEntity;
import com.example.dashboard.dto.Dashboard;
import com.example.dashboard.repository.DashboardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class DashboardControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DashboardRepository dashboardRepository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void findAllDashboards() throws Exception {

        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        // given
        given(dashboardRepository.findAll())
                .willReturn(List.of(new DashboardEntity(1, "Title", timestamp, timestamp)));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/dashboards")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                mapper.writeValueAsString(List.of(new Dashboard(1, "Title", timestamp, timestamp)))
        );
    }


    @Test
    public void findDashboardById() throws Exception {

        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        // given
        given(dashboardRepository.findById(anyInt()))
                .willReturn(Optional.of(new DashboardEntity(1, "Title", timestamp, timestamp)));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/dashboards/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                mapper.writeValueAsString(new Dashboard(1, "Title", timestamp, timestamp))
        );
    }

    @Test
    public void findDashboardByIdNotFound() throws Exception {

        Date date = new Date();

        Timestamp timestamp = new Timestamp(date.getTime());

        // given
        given(dashboardRepository.findById(anyInt()))
                .willReturn(Optional.empty());

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/dashboards/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getErrorMessage()).isEqualTo("Id not found");
    }
}
