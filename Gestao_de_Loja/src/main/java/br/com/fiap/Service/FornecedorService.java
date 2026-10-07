package br.com.fiap.Service;

import br.com.fiap.Domain.Fornecedor;
import br.com.fiap.Exception.FornecedorNaoEncontradoException;
import br.com.fiap.Exception.IDNaoPodeSerMenorQueZeroException;
import br.com.fiap.Exception.NaoPodeSerNuloException;
import br.com.fiap.Repository.FornecedorRepository;

import java.time.LocalDate;
import java.util.List;

public class FornecedorService {
    private FornecedorRepository fornecedorRepository;

    public FornecedorService(){fornecedorRepository = new FornecedorRepository();}

    public void InserirFornecedor(Fornecedor fornecedor){
        if (fornecedor == null)
            throw new NaoPodeSerNuloException("Fornecedor não poder ser nulo!!! ");

        if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome do fornecedor é obrigatório!!! ");

        if (fornecedor.getEmail() != null && !fornecedor.getEmail().trim().isEmpty()) {
            if (!fornecedor.getEmail().contains("@"))
                throw new IllegalArgumentException("E-mail inválido!!! ");
        }

        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty())
            throw new IllegalArgumentException("CPF do fornecedor é obrigatório!!! ");

        if (fornecedor.getCnpj().length() != 14)
            throw new IllegalArgumentException("CPF deve possuir 14 caracteres!!! ");

        if (fornecedor.getTelefone() != null && !fornecedor.getTelefone().trim().isEmpty()) {
            if (fornecedor.getTelefone().length() < 8)
                throw new IllegalArgumentException("Telefone inválido!!! ");
        }

        Fornecedor fornecedorExistente = fornecedorRepository.BuscarFornecedorPorCNPJ(fornecedor.getCnpj());

        if (fornecedorExistente != null)
            throw new IllegalArgumentException("Já exites um fornecdor com esse cnpj!!! ");

        fornecedorRepository.InserirFornecedor(fornecedor);
    }

    public Fornecedor BuscarFornecedorPorID(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Fornecedor fornecedor = fornecedorRepository.BuscarFornecedorPorID(id);

        if (fornecedor == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!!! ");

        return fornecedor;
    }

    public List<Fornecedor> ListarTodosFornecedores(){
        return fornecedorRepository.ListarTodosFornecedores();
    }

    public void RemoverFornecedor(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Fornecedor fornecedor = fornecedorRepository.BuscarFornecedorPorID(id);

        if (fornecedor == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!!! ");

        fornecedorRepository.RemoverFornecedor(id);
    }

    public void AtualizarFornecedor(Fornecedor fornecedor, int opcao){
        if (fornecedor == null)
            throw new NaoPodeSerNuloException("Fornecedor não poder ser nulo!!! ");

        if (fornecedor.getIdFornecedor() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Fornecedor fornecedorExistente = fornecedorRepository.BuscarFornecedorPorID(fornecedor.getIdFornecedor());

        if (fornecedorExistente == null)
            throw new FornecedorNaoEncontradoException("Fornecedor não encontrado!!! ");

        switch (opcao) {
            case 1:
                if (fornecedor.getNome() == null || fornecedor.getNome().trim().isEmpty())
                    throw new IllegalArgumentException("Nome do fornecedor é obrigatório!!! ");

                break;

            case 2:
                if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty())
                    throw new IllegalArgumentException("Cnpj do fornecedor é obrigatório!!! ");

                if (fornecedor.getCnpj().length() != 14)
                    throw new IllegalArgumentException("Cnpj deve possuir 14 caracteres!!! ");

                Fornecedor fornExistente = fornecedorRepository.BuscarFornecedorPorCNPJ(fornecedor.getCnpj());

                if (fornExistente != null && fornExistente.getIdFornecedor() != fornecedor.getIdFornecedor())
                    throw new IllegalArgumentException("Esse cnpj já está cadastrado para outro fornecedor!!! ");

                break;

            case 3:
                if (fornecedor.getEmail() != null && !fornecedor.getEmail().trim().isEmpty()) {
                    if (!fornecedor.getEmail().contains("@"))
                        throw new IllegalArgumentException("E-mail inválido!!! ");
                }

                break;

            case 4:
                if (fornecedor.getTelefone() != null && !fornecedor.getTelefone().trim().isEmpty()) {
                    if (fornecedor.getTelefone().length() < 8)
                        throw new IllegalArgumentException("Telefone inválido!!! ");
                }

                break;

            default:
                throw new IllegalArgumentException("Opção inválida!!! ");
        }

        fornecedorRepository.AtualizarFornecedor(fornecedor, opcao);
    }
}
