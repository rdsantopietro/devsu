# devsu

#F1
Se asume que el cliente tendrá un ID autogenerado y un clienteId que se enviará al crear dado que no es claro el ejemplo de la consigna. La relacion de cuenta será con clienteId y no con id de la entidad cliente.

#F2  
#Se entiende en la consigna que llevar el registro de las transacciones es la lista de movimientos.  
Que un movimiento pueda tener valores positivos o negativos, asumimos que no puede ser nulo  
El valor actualizado de la cuenta será el guardado el último movimiento

#F3   
Se asigna un codigo http personalizado para tratar un error de negocio conocido
Se asume que se buscaran los clientes por clienteId
Se asume que para crear una cuenta, se usara un DTO específico, como el del ejemplo.

#F4  
Se asume que para el rango de fechas se enviarán desde y hasta como parámetros y que el cliente se buscará por clienteId
Se asume que para crear un movimiento, se usara un DTO específico, como el del ejemplo.

#F5  
Se aprovecha que se trabaja en la misma BD para todos los datos, para hacer una query que resuelva el reporte.
No sería posible si se trataran de una BD por MS

No se aplica asincronismo dado que no hay procesos que requieran serlo por el momento  

Algunos nombres pueden mejorarse, pero en la consigna estaban es español y se mantuvo eso  

Se intento respetar principios como el SOLID en algunas clases  

Algunas validaciones se podrían agregar pero no estaban detalladas. Algunas fueron agregadas de todas formas.  
El nombre de los MS podrían mejorarse, por ahora se escogió la entidad mas importante de cada uno  

Hay un docker compose en el raiz del proyecto donde se levantan consul, mysql, kafka





