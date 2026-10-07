package br.com.fiap.Util;

import br.com.fiap.Domain.*;
import br.com.fiap.Service.*;

import java.util.List;

public class JsonService {
    private ClienteService clienteService;
    private FornecedorService fornecedorService;
    private FuncionarioService funcionarioService;
    private ProdutoService produtoService;
    private PedidoService pedidoService;
    private ItemPedidoService itemPedidoService;
    private PagamentoService pagamentoService;

    public JsonService() {
        clienteService = new ClienteService();
        fornecedorService = new FornecedorService();
        funcionarioService = new FuncionarioService();
        produtoService = new ProdutoService();
        pedidoService = new PedidoService();
        itemPedidoService = new ItemPedidoService();
        pagamentoService = new PagamentoService();
    }

    public void ExportarClientes() {
        List<Cliente> clientes = clienteService.ListarTodosClientes();

        String json = JsonUtil.ConverterParaJson(clientes);

        ArquivoUtil.SalvarArquivo("clientes.json", json);
    }

    public List<Cliente> ImportarClientes() {
        String json = ArquivoUtil.LerArquivo("clientes.json");

        return JsonUtil.ConverterListaDeJson(json, Cliente.class);
    }

    public void ExportarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorService.ListarTodosFornecedores();

        String json = JsonUtil.ConverterParaJson(fornecedores);

        ArquivoUtil.SalvarArquivo("fornecedores.json", json);
    }

    public List<Fornecedor> ImportarFornecedores() {
        String json = ArquivoUtil.LerArquivo("fornecedores.json");

        return JsonUtil.ConverterListaDeJson(json, Fornecedor.class);
    }

    public void ExportarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.ListarTodosFuncionarios();

        String json = JsonUtil.ConverterParaJson(funcionarios);

        ArquivoUtil.SalvarArquivo("funcionarios.json", json);
    }

    public List<Funcionario> ImportarFuncionarios() {
        String json = ArquivoUtil.LerArquivo("funcionarios.json");

        return JsonUtil.ConverterListaDeJson(json, Funcionario.class);
    }

    public void ExportarProdutos() {
        List<Produto> produtos = produtoService.ListarTodosProdutos();

        String json = JsonUtil.ConverterParaJson(produtos);

        ArquivoUtil.SalvarArquivo("produtos.json", json);
    }

    public List<Produto> ImportarProdutos() {
        String json = ArquivoUtil.LerArquivo("produtos.json");

        return JsonUtil.ConverterListaDeJson(json, Produto.class);
    }

    public void ExportarPedidos() {
        List<Pedido> pedidos = pedidoService.ListarTodosPedidos();

        String json = JsonUtil.ConverterParaJson(pedidos);

        ArquivoUtil.SalvarArquivo("pedidos.json", json);
    }

    public List<Pedido> ImportarPedidos() {
        String json = ArquivoUtil.LerArquivo("pedidos.json");

        return JsonUtil.ConverterListaDeJson(json, Pedido.class);
    }

    public void ExportarItensPedido() {
        List<ItemPedido> itens = itemPedidoService.ListarTodosItensPedido();

        String json = JsonUtil.ConverterParaJson(itens);

        ArquivoUtil.SalvarArquivo("itens_pedido.json", json);
    }

    public List<ItemPedido> ImportarItensPedido() {
        String json = ArquivoUtil.LerArquivo("itens_pedido.json");

        return JsonUtil.ConverterListaDeJson(json, ItemPedido.class
        );
    }

    public void ExportarPagamentos() {
        List<Pagamento> pagamentos = pagamentoService.ListarTodosPagamentos();

        String json = JsonUtil.ConverterParaJson(pagamentos);

        ArquivoUtil.SalvarArquivo("pagamentos.json", json);
    }

    public List<Pagamento> ImportarPagamentos() {
        String json = ArquivoUtil.LerArquivo("pagamentos.json");

        return JsonUtil.ConverterListaDeJson(json, Pagamento.class);
    }

    public void ExportarTodos() {
        ExportarClientes();
        ExportarFornecedores();
        ExportarFuncionarios();
        ExportarProdutos();
        ExportarPedidos();
        ExportarItensPedido();
        ExportarPagamentos();

        System.out.println();
        System.out.println("Todos os dados foram exportados para JSON!");
    }
}
