select p.id_produto, p.nome, p.descricao, p.preco, p.quantidade_estoque, f.nome from produto p
join fornecedor f on p.id_fornecedor = f.id_fornecedor
order by p.nome;


select pe.id_pedido, pe.data_pedido, pe.status, pe.valor_total, cl.nome as Nome_cliente, func.nome as Nome_funcionario from pedido pe
join cliente cl on pe.id_cliente = cl.id_cliente
join funcionario func on pe.id_funcionario = func.id_funcionario
order by pe.data_pedido;


select * from produto where quantidade_estoque < 10
order by quantidade_estoque;


select * from produto where preco > (select round(avg(preco), 2) from produto)
order by preco desc;


select count(pe.id_pedido) as qtd_pedido, cl.id_cliente, cl.nome as Nome_cliente from pedido pe
left join cliente cl on pe.id_cliente = cl.id_cliente
group by cl.id_cliente, nome_cliente order by qtd_pedido;


select count(pe.id_pedido) as qtd_pedido, nvl(sum(pe.valor_total), 0) as total_gasto, cl.id_cliente, cl.nome as Nome_cliente from pedido pe
left join cliente cl on pe.id_cliente = cl.id_cliente
group by cl.id_cliente, nome_cliente order by total_gasto desc;


select func.id_funcionario, func.nome, func.cargo, count(pe.id_pedido) as qtd_pedidos_atendidos from funcionario func
left join pedido pe on func.id_funcionario = pe.id_funcionario
group by func.id_funcionario, func.nome, func.cargo order by qtd_pedidos_atendidos desc;


select func.id_funcionario, func.nome, func.cargo, count(pe.id_pedido) as qtd_pedido, nvl(sum(pe.valor_total), 0) as faturamento
from funcionario func
left join pedido pe on func.id_funcionario = pe.id_funcionario group by id_funcionario, nome, cargo
order by faturamento;


select pr.nome AS produto, sum(ip.quantidade) as qtd_vendida from produto pr
join item_pedido ip on pr.id_produto = ip.id_produto
group by pr.nome order by qtd_vendida fetch first 1 row only;


select status, count(*) as qtd from pedido
group by status order by qtd;


select tipo_pagamento, count(id_pagamento) as qtd from pagamento
group by tipo_pagamento order by qtd desc;


select pe.id_pedido, cl.nome, pe.status, pe.valor_total from cliente cl
join pedido pe on cl.id_cliente = pe.id_cliente
left join pagamento pag on pe.id_pedido = pag.id_pedido
where pag.id_pagamento is null;


select cl.id_cliente, cl.nome, sum(pe.valor_total) as total_gasto from cliente cl
join pedido pe on cl.id = pe.id_cliente
group by cl.id_cliente, cl.nome
having total_gasto > (
    select round(avg(total_cliente), 2) from (
        select id_cliente, sum(valor_total) as total_cliente
        from pedido
        group by id_cliente
        )
    ) order by total_gasto;