-- =========================================================
-- INSERTS - CLIENTE
-- =========================================================

INSERT INTO cliente (nome, cpf, email, telefone, data_cadastro)
VALUES ('Joao Silva', '11111111111', 'joao@email.com', '11999990001', DATE '2026-01-10'),
VALUES ('Maria Santos', '22222222222', 'maria@email.com', '11999990002', DATE '2026-01-15'),
VALUES ('Carlos Oliveira', '33333333333', 'carlos@email.com', '11999990003', DATE '2026-02-05'),
VALUES ('Ana Costa', '44444444444', 'ana@email.com', '11999990004', DATE '2026-02-20'),
VALUES ('Pedro Almeida', '55555555555', 'pedro@email.com', '11999990005', DATE '2026-03-01');


-- =========================================================
-- INSERTS - FORNECEDOR
-- =========================================================

INSERT INTO fornecedor (nome, cnpj, email, telefone)
VALUES ('Tech Solutions', '11111111000101', 'contato@techsolutions.com', '1133330001'),
VALUES ('InfoTech Brasil', '22222222000102', 'contato@infotech.com', '1133330002'),
VALUES ('Global Eletronicos', '33333333000103', 'contato@global.com', '1133330003'),
VALUES ('Mega Distribuidora', '44444444000104', 'contato@mega.com', '1133330004'),
VALUES ('Brasil Importados', '55555555000105', 'contato@brasilimportados.com', '1133330005');


-- =========================================================
-- INSERTS - FUNCIONARIO
-- =========================================================

INSERT INTO funcionario (nome, cpf, cargo, salario, data_contratacao)
VALUES ('Rafael Souza', '66666666666', 'VENDEDOR', 2800.00, DATE '2025-01-10'),
VALUES ('Juliana Lima', '77777777777', 'CAIXA', 2500.00, DATE '2025-02-15'),
VALUES ('Marcos Ferreira', '88888888888', 'ESTOQUISTA', 2700.00, DATE '2025-03-20'),
VALUES ('Fernanda Alves', '99999999999', 'SUPERVISOR', 4200.00, DATE '2024-10-05'),
VALUES ('Lucas Mendes', '12345678901', 'GERENTE', 6500.00, DATE '2024-06-01');


-- =========================================================
-- INSERTS - PRODUTO
-- =========================================================

INSERT INTO produto (nome, descricao, preco, quantidade_estoque, id_fornecedor)
VALUES ('Notebook Dell', 'Notebook para uso profissional', 4500.00, 20, 1),
VALUES ('Mouse Logitech', 'Mouse sem fio', 120.00, 50, 2),
VALUES ('Teclado Mecânico', 'Teclado mecanico RGB', 350.00, 30, 3),
VALUES ('Monitor LG', 'Monitor 24 polegadas', 900.00, 15, 4),
VALUES ('Headset Gamer', 'Headset com microfone', 250.00, 40, 5);


-- =========================================================
-- INSERTS - PEDIDO
-- =========================================================

INSERT INTO pedido (data_pedido, status, valor_total, id_funcionario, id_cliente)
VALUES (DATE '2026-03-05', 'PENDENTE', 4620.00, 1, 1),
VALUES (DATE '2026-03-06', 'PAGO', 350.00, 2, 2),
VALUES (DATE '2026-03-07', 'ENVIADO', 900.00, 1, 3),
VALUES (DATE '2026-03-08', 'ENTREGUE', 500.00, 4, 1),
VALUES (DATE '2026-03-09', 'CANCELADO', 250.00, 5, 4);


-- =========================================================
-- INSERTS - ITEM_PEDIDO
-- =========================================================

INSERT INTO item_pedido (quantidade, preco_unitario, id_pedido, id_produto)
VALUES (1, 4500.00, 1, 1),
VALUES (1, 120.00, 1, 2),
VALUES (1, 350.00, 2, 3),
VALUES (1, 900.00, 3, 4),
VALUES (2, 250.00, 4, 5);


-- =========================================================
-- INSERTS - PAGAMENTO
-- =========================================================

INSERT INTO pagamento (tipo_pagamento, valor, data_pagamento, status, id_pedido)
VALUES ('PIX', 4620.00, DATE '2026-03-05', 'APROVADO', 1),
VALUES ('CARTAO', 350.00, DATE '2026-03-06', 'APROVADO', 2),
VALUES ('BOLETO', 900.00, DATE '2026-03-07', 'PENDENTE', 3),
VALUES ('PIX', 500.00, DATE '2026-03-08', 'APROVADO', 4),
VALUES ('CARTAO', 250.00, DATE '2026-03-09', 'ESTORNADO', 5);


-- =========================================================
-- CONFIRMAR ALTERAÇÕES
-- =========================================================

COMMIT;