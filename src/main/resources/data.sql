INSERT INTO clients (first_name, last_name, email)
VALUES
    ('Juan', 'Pérez', 'juan.perez@example.com'),
    ('Ana', 'Gómez', 'ana.gomez@example.com'),
    ('Carlos', 'Martínez', 'carlos.martinez@example.com');

INSERT INTO airlines (name, canceled_flights, success_flights, percentage_for_refund, total_raised)
VALUES
    ('Aerolíneas Argentinas', 5, 100, 0.75, 0),
    ('LATAM Airlines', 2, 120, 0.80, 0),
    ('JetSmart', 0, 150, 0.70, 0);

INSERT INTO planes (plane_number, airline_id, capacity)
VALUES
    (12345, 1, 25),
    (67890, 2, 100),
    (11223, 3, 50);

INSERT INTO travels (start_date, price_for_economy, price_for_first_class, plane_id)
VALUES
    ('2024-12-01T08:00:00', 250.00, 500.00, 1),
    ('2024-12-05T10:30:00', 300.00, 600.00, 2),
    ('2024-12-10T14:00:00', 220.00, 450.00, 3);

