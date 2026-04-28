ALTER TABLE rendez_vous
    ADD COLUMN patient_id bigint not null ,
    ADD COLUMN medecin_id bigint not null;

alter table rendez_vous

add constraint fk_rendezvous_patient
foreign key (patient_id) references patient(id),

add constraint fk_rendezvous_medecin
foreign key (medecin_id) references medecin(id);

alter table dossier_medical

 ADD column patient_id bigint not null unique ,

 add constraint fk_dossier_patient
 foreign key (patient_id) references patient(id);