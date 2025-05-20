insert into address (id, address_line1, address_line2, city, postal_code)
values (901, 'xx', 'yy', 'city', '60-400');

INSERT INTO ADDRESS (id, city, address_line1, address_line2, postal_code)
VALUES (1, 'Warszawa', 'ul. Zielna 45', 'Apt 12', '00-001'),
       (2, 'Kraków', 'ul. Floriańska 22', NULL, '31-021'),
       (3, 'Wrocław', 'ul. Oławska 10', 'Piętro 3', '50-123'),
       (4, 'Poznań', 'ul. Półwiejska 42', NULL, '61-001'),
       (5, 'Gdańsk', 'ul. Długa 8', 'Lokal 15', '80-098'),
       (6, 'Łódź', 'ul. Piotrkowska 85', NULL, '90-423'),
       (7, 'Katowice', 'ul. Mariacka 15', 'Biuro 7', '40-001');

-- Insert data into DOCTOR table
INSERT INTO DOCTOR (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
VALUES (1, 'Jan', 'Kowalski', '123456789', 'jan.kowalski@example.com', 'DOC001', 'DERMATOLOGIST', 1),
       (2, 'Anna', 'Nowak', '234567890', 'anna.nowak@example.com', 'DOC002', 'OCULIST', 2),
       (3, 'Piotr', 'Wiśniewski', '345678901', 'piotr.wisniewski@example.com', 'DOC003', 'DERMATOLOGIST', 3),
       (4, 'Magdalena', 'Dąbrowska', '456789012', 'magdalena.dabrowska@example.com', 'DOC004', 'SURGEON', 4),
       (5, 'Tomasz', 'Lewandowski', '567890123', 'tomasz.lewandowski@example.com', 'DOC005', 'GP', 5);

-- Insert data into PATIENT table
INSERT INTO PATIENT (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, date_of_join)
VALUES (1, 'Krzysztof', 'Zieliński', '678901234', 'krzysztof.zielinski@example.com', 'PAT001', '1980-05-15', 2, '2025-02-15 10:00:00'),
       (2, 'Barbara', 'Szymańska', '789012345', 'barbara.szymanska@example.com', 'PAT002', '1975-11-23', 3, '2025-01-15 10:00:00'),
       (3, 'Marek', 'Wojciechowski', '890123456', 'marek.wojciechowski@example.com', 'PAT003', '1995-03-07', 4, '2025-01-17 09:15:00'),
       (4, 'Aleksandra', 'Kamińska', '901234567', 'aleksandra.kaminska@example.com', 'PAT004', '1988-07-19', 5, '2025-01-19 09:15:00'),
       (5, 'Michał', 'Kaczmarek', '012345678', 'michal.kaczmarek@example.com', 'PAT005', '1990-12-01', 6, '2025-02-21 08:45:00'),
       (6, 'Katarzyna', 'Wójcik', '123456780', 'katarzyna.wojcik@example.com', 'PAT006', '1982-08-30', 7, '2025-01-21 08:45:00');

-- Insert data into VISIT table
INSERT INTO VISIT (id, description, time, doctor_id, patient_id)
VALUES (1, 'Regularna kontrola ciśnienia krwi', '2025-03-15 10:00:00', 1, 1),
       (2, 'Badanie neurologiczne', '2025-03-16 11:30:00', 2, 2),
       (3, 'Konsultacja ortopedyczna', '2025-03-17 09:15:00', 3, 3),
       (4, 'Badanie skóry', '2025-03-18 14:45:00', 4, 4),
       (5, 'Wizyta kontrolna', '2025-03-19 12:30:00', 5, 5),
       (6, 'Konsultacja kardiologiczna', '2025-03-20 16:00:00', 1, 6),
       (7, 'Diagnostyka bólu głowy', '2025-03-21 08:45:00', 2, 1);

-- Insert data into MEDICAL_TREATMENT table
INSERT INTO MEDICAL_TREATMENT (id, description, type, visit_id)
VALUES (1, 'Pomiar ciśnienia krwi', 'RTG', 1),
       (2, 'EKG', 'EKG', 1),
       (3, 'Badanie rezonansem magnetycznym', 'USG', 2),
       (4, 'Punkcja lędźwiowa', 'EKG', 2),
       (5, 'RTG kolana', 'USG', 3),
       (6, 'Zastrzyk sterydowy', 'USG', 3),
       (7, 'Biopsja skóry', 'EKG', 4),
       (8, 'Badanie dermatoskopowe', 'USG', 4),
       (9, 'Szczepienie', 'USG', 5),
       (10, 'Badanie EKG wysiłkowe', 'EKG', 6),
       (11, 'Badanie EEG', 'EKG', 7);

ALTER TABLE doctor ALTER COLUMN id RESTART WITH 6;
ALTER TABLE patient ALTER COLUMN id RESTART WITH 7;
ALTER TABLE visit ALTER COLUMN id RESTART WITH 8;
ALTER TABLE address ALTER COLUMN id RESTART WITH 8;
ALTER TABLE medical_treatment ALTER COLUMN id RESTART WITH 12;