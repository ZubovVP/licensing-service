package ru.zubov.licensingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.zubov.licensingservice.model.License;

import java.util.Locale;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LicenseService {
    private final MessageSource messages;

    public License getLicense(String licenseId, String organizationId) {
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        return getResponseMessage(license, organizationId, locale, "license.create.message");
    }

    public String updateLicense(License license, String organizationId, Locale locale) {
        return getResponseMessage(license, organizationId, locale, "license.update.message");
    }

    public String deleteLicense(String licenseId, String organizationId, Locale locale) {
         return String.format(messages.getMessage(
                 "license.delete.message", null, locale), licenseId, organizationId);
    }

    private String getResponseMessage(License license, String organizationId, Locale locale, String nameOperation) {
        String responseMessage = null;
        if (license != null) {
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messages.getMessage(nameOperation, null, locale), license);
        }
        return responseMessage;
    }
}
