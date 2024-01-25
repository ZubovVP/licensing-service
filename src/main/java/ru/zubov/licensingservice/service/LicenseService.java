package ru.zubov.licensingservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.zubov.licensingservice.config.ServiceConfig;
import ru.zubov.licensingservice.model.License;
import ru.zubov.licensingservice.repository.LicenseRepository;

import java.util.Locale;
import java.util.UUID;

@Service
public class LicenseService {
    @Autowired
    private MessageSource messages;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private ServiceConfig config;

    public License getLicense(String licenseId, String organizationId) {
        License license = licenseRepository
                .findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (null == license) {
            throw new IllegalArgumentException(
                    String.format(messages.getMessage(
                                    "license.search.error.message", null, null),
                            licenseId, organizationId));
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license, Locale locale) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);

        license.withComment(String.format(messages.getMessage(
                "license.create.message", null, locale), license.getLicenseId()));
        return license;
        //        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license, Locale locale) {
        licenseRepository.save(license);
        license.withComment(String.format(messages.getMessage(
                "license.update.message", null, locale), license.getLicenseId()));
        return license.withComment(config.getProperty());
    }

    public License deleteLicense(String licenseId, Locale locale) {
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        license.withComment(String.format(messages.getMessage(
                        "license.delete.message", null, locale), licenseId));
        return license;
    }
}
