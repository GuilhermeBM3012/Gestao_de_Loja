create or replace trigger Atualizar_estoque
after insert on item_pedido
for each row
begin

    update produto set quantidade_estoque = quantidade_estoque - :new.quantidade
    where id_produto = :new.id_produto;

end;