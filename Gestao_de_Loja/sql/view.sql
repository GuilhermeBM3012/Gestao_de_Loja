create or replace view vw_pedidos_detalhados as
    select p.id_pedido, p.data_pedido, p.status, p.valor_total, c.nome AS cliente, f.nome AS funcionario
    from pedido p
    join cliente c on p.id_cliente = c.id_cliente
    join funcionario f on p.id_funcionario = f.id_funcionario;