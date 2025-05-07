/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioge
 */
public class ClienteDao {
    
    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes(nome, cpf, data_nascimento, email, "
                + "telefone, senha_hash) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection con = MySQL.connect();
                PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getSenhaHash());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente(null, "Diogenes", "21324654", LocalDate.now(),
                "prof.diogenes.francisco@unincor.edu.br", "4564654897", "389102312749128903");
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.inserirCliente(cliente);
    }
    
}
