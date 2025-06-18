/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.AgenciaDao;
import com.unincor.sistema.bancario.admin.model.dao.FuncionarioDao;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.time.LocalDate;

/**
 *
 * @author dioge
 */
public class FuncionarioService {
    public FuncionarioDao funcionarioDao = new FuncionarioDao();
    
    public void salvarFuncionario(Funcionario funcionario) throws CadastroException {
        if(funcionario == null) {
            throw new CadastroException("Funcionario informado inválido!");
        }
        
        if(funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new CadastroException("O nome não foi informado!");
        }
        
        if(funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroException("Cpf não foi informado!");
        }
        
        if(funcionarioDao.buscarFuncionarioPorCpf(funcionario.getCpf()) !=  null) {
            throw new CadastroException("Cpf já cadastrado!");
        }
        
        if(funcionarioDao.buscarFuncionarioPorEmail(funcionario.getEmail()) !=  null) {
            throw new CadastroException("E-mail já cadastrado!");
        }
        
        funcionarioDao.inserirFuncionario(funcionario);
    }
    
    public static void main(String[] args) {
        try {
            var funcionario = new Funcionario();
            funcionario.setNome("Fernando");
            funcionario.setCpf("65498711");
            funcionario.setDataNascimento(LocalDate.now());
            funcionario.setEmail("fernadao@tbnao.eo.bryan.com.br");
            funcionario.setSenhaHash("98765432das32d16");
            funcionario.setTelefone("35988220819");
            funcionario.setTurno("Noturno");
            
            FuncionarioService funcionarioService = new FuncionarioService();
            funcionarioService.salvarFuncionario(funcionario);
        } catch (CadastroException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
