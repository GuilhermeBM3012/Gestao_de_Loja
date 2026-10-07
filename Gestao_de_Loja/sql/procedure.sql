create or replace procedure Atualizar_status_Pedido(pedido_id number, status_pedido VARCHAR2)
is
begin

    update pedido set status = status_pedido where id_pedido = pedido_id;

end;

EXEC pr_atualizar_status_pedido(1, 'PAGO');