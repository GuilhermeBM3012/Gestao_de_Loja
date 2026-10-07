package br.com.fiap.Service;

import br.com.fiap.Domain.Funcionario;
import br.com.fiap.Exception.FuncionarioNaoEncontradoException;
import br.com.fiap.Exception.IDNaoPodeSerMenorQueZeroException;
import br.com.fiap.Exception.NaoPodeSerNuloException;
import br.com.fiap.Repository.FuncionarioRepository;

import java.time.LocalDate;
import java.util.List;

public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(){funcionarioRepository = new FuncionarioRepository();}

    public void InserirFuncionario(Funcionario funcionario){
        if (funcionario == null)
            throw new NaoPodeSerNuloException("Funcionário não pode ser nulo!!! ");

        if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome do funcionário é obrigatório!!! ");

        if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty())
            throw new IllegalArgumentException("Cpf do funcionário é obrigatório!!! ");

        if (funcionario.getCpf().length() != 11)
            throw new IllegalArgumentException("Cpf deve possuir 11 caracteres!!! ");

        if (funcionario.getCargo() == null)
            throw new IllegalArgumentException("Cargo do funcionário é obrigatório!!! ");

        if (funcionario.getSalario() <= 0)
            throw new IllegalArgumentException("Salário deve ser maior que zero!!! ");

        if (funcionario.getDataContratacao() != null)
            throw new IllegalArgumentException("Data de contratação é obrigatória!!! ");

        if (funcionario.getDataContratacao().isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Data de contratação não pode ser futura!!! ");

        Funcionario funcionarioExistente = funcionarioRepository.BuscarFuncionarioPorCPF(funcionario.getCpf());

        if (funcionarioExistente != null)
            throw new IllegalArgumentException("Já existe um funcionário com esse cpf!!! ");

        funcionarioRepository.InserirFuncionario(funcionario);
    }

    public Funcionario BuscarFuncionarioPorID(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Funcionario funcionario = funcionarioRepository.BuscarFuncionarioPorID(id);

        if (funcionario == null)
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!!! ");

        return funcionario;
    }

    public List<Funcionario> ListarTodosFuncionarios(){
        return funcionarioRepository.ListarTodosFuncionarios();
    }

    public void RemoverFuncionario(int id){
        if (id <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Funcionario funcionario = funcionarioRepository.BuscarFuncionarioPorID(id);

        if (funcionario == null)
            throw new FuncionarioNaoEncontradoException("Funcionário não encontrado!!! ");

        funcionarioRepository.RemoverFuncionario(id);
    }

    public void AtualizarFuncionario(Funcionario funcionario, int opcao){
        if (funcionario == null)
            throw  new NaoPodeSerNuloException("Funcionário não pode ser nulo!!! ");

        if (funcionario.getIdFuncionario() <= 0)
            throw new IDNaoPodeSerMenorQueZeroException("ID tem que ser maior que zero!!! ");

        Funcionario funcionarioExistente = funcionarioRepository.BuscarFuncionarioPorID(funcionario.getIdFuncionario());

        if (funcionarioExistente == null)
            throw new FuncionarioNaoEncontradoException("Funcionário não foi encontrado!!! ");

        switch (opcao) {
            case 1:
                if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty())
                    throw new IllegalArgumentException("Nome do funcionário é obrigatório!!! ");

                break;

            case 2:
                if (funcionario.getCpf() == null || funcionario.getCpf().trim().isEmpty())
                    throw new IllegalArgumentException("Cpf do funcionário é obrigatório!!! ");

                if (funcionario.getCpf().length() != 11)
                    throw new IllegalArgumentException("Cpf deve possuir 11 caracteres!!! ");

                Funcionario funcExistente = funcionarioRepository.BuscarFuncionarioPorCPF(funcionario.getCpf());

                if (funcExistente != null && funcExistente.getIdFuncionario() != funcionario.getIdFuncionario())
                    throw new IllegalArgumentException("Esse CPF já está cadastrado para outro funcionário!!! ");

                break;

            case 3:
                if (funcionario.getCargo() == null)
                    throw new IllegalArgumentException("Cargo do funcionário é obrigatório!!! ");

                break;

            case 4:
                if (funcionario.getSalario() <= 0)
                    throw new IllegalArgumentException("Salário deve ser maior que zero!!! ");

                break;

            case 5:
                if (funcionario.getDataContratacao() != null)
                    throw new IllegalArgumentException("Data de contratação é obrigatória!!! ");

                if (funcionario.getDataContratacao().isAfter(LocalDate.now()))
                    throw new IllegalArgumentException("Data de contratação não pode ser futura!!! ");

                break;

            default:
                throw new IllegalArgumentException("Opção inválida!!! ");
        }

        funcionarioRepository.AtualizarFuncionario(funcionario, opcao);
    }
}
