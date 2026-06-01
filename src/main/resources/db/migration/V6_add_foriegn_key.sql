ALTER TABLE patient ADD COLUMN user_id BIGINT;

ALTER TABLE patient
    ADD CONSTRAINT fk_patient_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE patient ADD CONSTRAINT uk_patient_user UNIQUE (user_id);

ALTER TABLE medecin ADD COLUMN user_id BIGINT;

ALTER TABLE medecin
    ADD CONSTRAINT fk_medecin_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE medecin ADD CONSTRAINT uk_medecin_user UNIQUE (user_id);