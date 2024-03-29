package ru.zubov.licensingservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.LocaleResolver;
import ru.zubov.licensingservice.TestUtils;
import ru.zubov.licensingservice.model.License;
import ru.zubov.licensingservice.service.LicenseService;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LicenseController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class LicenseControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean()
    private LicenseService licenseService;

    @MockBean
    private MessageSource messages;

    @MockBean
    private LocaleResolver localeResolver;

    @Test
    public void getLicenseByLicenseIdAndOrganizationId() throws Exception {
        String organizationId = "organizationId";
        String licenseId = "licenseId";
        when(licenseService.getLicense(licenseId, organizationId)).thenReturn(new License());
        mvc.perform(get("/v1/organization/{organizationId}/license/{licenseId}", organizationId, licenseId))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.licenseId", Matchers.equalTo("licenseId")))
//                .andExpect(jsonPath("$.description", Matchers.equalTo("Software product")))
//                .andExpect(jsonPath("$.productName", Matchers.equalTo("Ostock")))
//                .andExpect(jsonPath("$.licenseType", Matchers.equalTo("full")))
//                .andExpect(jsonPath("$.organizationId", Matchers.equalTo("organizationId")));
    }

    @Test
    public void updateLicenseTest() throws Exception {
        String organizationId = "organizationId";
        String licenseId = "licenseId";
        License license = new License();
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        Locale locale = new Locale("ru");

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        when(licenseService.updateLicense(any(), any())).thenReturn(license);
        //todo сделать проверку на контент
        mvc.perform(put("/v1/organization/{organizationId}/license", organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", locale)
                        .content(TestUtils.asJsonString(license)))
                .andExpect(status().isOk());
    }

    @Test
    public void createLicenseTest() throws Exception {
        String organizationId = "organizationId";
        String licenseId = "licenseId";
        License license = new License();
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        Locale locale = new Locale("ru");

        when(licenseService.createLicense(any(), any())).thenReturn(license);

        //todo сделать проверку на контент
        mvc.perform(post("/v1/organization/{organizationId}/license", organizationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", locale)
                        .content(TestUtils.asJsonString(license)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteLicenseTest() throws Exception {
        String organizationId = "organizationId";
        String licenseId = "licenseId";
        License license = new License();
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        Locale locale = new Locale("ru");

        when(licenseService.deleteLicense(licenseId, locale)).thenReturn(license);
        mvc.perform(delete("/v1/organization/{organizationId}/license/{licenseId}", organizationId, licenseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Accept-Language", locale)
                        .content(TestUtils.asJsonString(license)))
                .andExpect(status().isOk());
    }
}