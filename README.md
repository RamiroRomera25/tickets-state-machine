# Sistema de Gestión de Pasajes Aéreos

## Objetivo
Desarrollar una API para gestionar los pasajes de vuelos, que permita gestionar el estado de los pasajes, realizar compras, verificar disponibilidad y administrar el estado de los vuelos. Cada pasaje tendrá un ciclo de vida gestionado por una máquina de estado que refleje los posibles estados de un pasaje, como "Reservado", "Pagado", "Cancelado", "Abordado", "Completado", entre otros.

## Requerimientos

### 1. Modelo de Datos

### Aerolineas
Cada vuelo tendra una aerolinea y tendra un total recaudado, vuelos cancelados, vuelos realizados y porcentaje de reembolso.
Este porcentaje de reembolso vendra dado de forma tal que si es 20% y el pasaje se reembola tres dias antes del vuelo se hara un reembolso del 60% del valor del pasaje.
Este porcentaje varia altamente entre aerolineas y el calculo del porcentaje que realmente devolveran sera calculado por cuantos dias faltan por el vuelo y cuanto porcentaje tiene de reembolso.

Ej: Faltan 5 dias para el vuelo, la aerolinea devuelve el 3.21% y me pasaje vale 3500 sopes.

3.21 * 5 = 16,05%
3500 * 0,8395 = 2938,25 es el total a devolver.


#### Pasaje:
- **ID** (único)
- **Cliente** (información básica como nombre, email, etc.)
- **Vuelo** (origen, destino, fecha y hora del vuelo)
- **Estado** (debe ser gestionado por una máquina de estado)
- **Fecha de compra**
- **Fecha de abordaje**
- **Precio**

### 2. Máquina de Estado para un Pasaje
La máquina de estado de un pasaje debe tener al menos los siguientes estados:
- **Reservado:** El pasaje ha sido reservado por el cliente, pero aún no ha sido pagado.
- **Pagado:** El cliente ha pagado el pasaje.
- **Cancelado:** El pasaje ha sido cancelado por el cliente o la aerolínea.
- **Abordado:** El cliente ha abordado el vuelo.
- **Completado:** El vuelo ha finalizado y el pasaje ha sido completado.

Las transiciones entre estos estados pueden estar influenciadas por eventos como:
- Pago realizado
- Vuelo cancelado
- Abordaje realizado
- Vuelo completado

### 3. API Endpoints

Los siguientes endpoints deben ser parte de la API REST:

- **POST /pasajes:** Crear un nuevo pasaje para un vuelo especifico (Reserva)
- **GET /pasajes/{id}:** Obtener el detalle de un pasaje específico
- **POST /pasajes/{id}/pagar:** Realizar el pago de un pasaje
- **POST /pasajes/{id}/reembolsar:** Realizar el reembolso de un pasaje con la especificacion de la aerolinea a la cual pernetece el avion
- **POST /pasajes/{id}/cancelar:** Cancelar un pasaje
- **POST /pasajes/{id}/abordar:** Marcar que el pasaje ha sido abordado
- **POST /pasajes/{id}/completar:** Marcar que el vuelo ha sido completado

### 4. Validaciones y Reglas de Negocio
- Un pasaje no puede ser marcado como "Abordado" si no está en el estado "Pagado".
- Un pasaje no puede ser cancelado si ya ha sido "Abordado" o "Completado".
- Los pasajes no pueden ser modificados una vez que estén "Completados".
- Verificar disponibilidad de vuelos antes de hacer una reserva.

## Escenarios de uso
- Un usuario puede reservar un pasaje, lo que coloca el estado en "Reservado".
- El usuario paga el pasaje, lo que cambia el estado a "Pagado".
- Si el usuario no paga antes de un tiempo determinado, el sistema puede cancelar el pasaje automáticamente o el cliente puede cancelarlo manualmente.
- Una vez abordado, el pasaje pasa a estado "Abordado", y cuando el vuelo se completa, el estado del pasaje se marca como "Completado".

## Consideraciones adicionales
- El sistema debe manejar diferentes vuelos y destinos, cada uno con una disponibilidad limitada de asientos.
- El sistema debe ser escalable para manejar un número grande de pasajes y vuelos.
- Implementar el manejo de excepciones y errores en caso de intentos inválidos de transición de estados (por ejemplo, intentar pagar un pasaje que ya ha sido cancelado).
