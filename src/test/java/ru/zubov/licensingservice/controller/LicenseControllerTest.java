package ru.zubov.licensingservice.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.zubov.licensingservice.service.LicenseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LicenseController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class LicenseControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean()
    private LicenseService licenseService;

    @Test
    public void getAllEmployeesAPI() throws Exception {

//        when(ingredientRepository.findAll()).thenReturn(List.of(ingredient));
//        doCallRealMethod().when(ingredientMapper).ingredientToIngredientDto(ingredient);


        String organizationId = "organizationId";
        String licenseId = "licenseId";
        when(licenseService.getLicense(licenseId, organizationId)).thenCallRealMethod();
        mvc.perform(get("/v1/organization/{organizationId}/license/{licenseId}", organizationId, licenseId))
                .andExpect(status().isOk());
    }


}