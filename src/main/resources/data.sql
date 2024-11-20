INSERT INTO clients (first_name, last_name, email)
VALUES
    ('Juan', 'Pérez', 'juan.perez@example.com'),
    ('Ana', 'Gómez', 'ana.gomez@example.com'),
    ('Carlos', 'Martínez', 'carlos.martinez@example.com');

INSERT INTO airlines (name, canceled_flights, success_flights, percentage_for_refund)
VALUES
    ('Aerolíneas Argentinas', 5, 100, 0.75),
    ('LATAM Airlines', 2, 120, 0.80),
    ('JetSmart', 0, 150, 0.70);

INSERT INTO travels (plane_id, start_date, price_for_economy, price_for_first_class)
VALUES
    (1, '2024-12-01T08:00:00', 250.00, 500.00),
    (2, '2024-12-05T10:30:00', 300.00, 600.00),
    (3, '2024-12-10T14:00:00', 220.00, 450.00);

INSERT INTO planes (plane_number, airline_id)
VALUES
    (12345, 1),
    (67890, 2),
    (11223, 3);


