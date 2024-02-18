CREATE TABLE IF NOT EXISTS organizations
(
    organization_id text NOT NULL,
    name            text,
    contact_name    text,
    contact_email   text,
    contact_phone   text,
    CONSTRAINT organizations_pkey PRIMARY KEY (organization_id)
);


CREATE TABLE IF NOT EXISTS licenses
(
    license_id      text NOT NULL,
    description     text,
    organization_id text NOT NULL,
    product_name    text NOT NULL,
    license_type    text NOT NULL,
    comment         text,
    CONSTRAINT licenses_pkey PRIMARY KEY (license_id),
    CONSTRAINT licenses_organization_id_fkey FOREIGN KEY (organization_id)
        REFERENCES organizations (organization_id)
);
