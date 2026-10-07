create or replace function Calcular_valorTotal_Pedido(pedido_id number)
return number
is
    total_valor number;

begin

    select nvl(sum(quantidade * preco_unitario), 0)
    into total_valor from item_pedido
    where id_pedido = pedido_id;

    return total_valor;

end;

select id_pedido, Calcular_valorTotal_Pedido(3) as valorTot_calculdo from pedido;