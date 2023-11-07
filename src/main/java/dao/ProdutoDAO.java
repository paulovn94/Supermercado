/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.market.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.ProdutoModel;

/**
 *
 * @author aluno
 */
public class ProdutoDAO {

    Connection con = null;
    PreparedStatement pstm = null;
    
    public void Atualizar(ProdutoModel produto) {
        String sql = "update produto set nome=?,preco=?,quantidade=? where codigo=?";
        try {
            con = Conexao.createConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, produto.getNome());
            pstm.setDouble(2, produto.getPreco());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setInt(4, produto.getCodigo());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Produto Atualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro Atualizar:" + e);
        }
    }

    public List<ProdutoModel> getProduct() {
        String sql = "select * from produto";
        con = Conexao.createConnection();
       
        try {
            pstm = con.prepareStatement(sql);
            ResultSet rset = pstm.executeQuery();
            ArrayList<ProdutoModel> produtos = new ArrayList<>();
            
            while (rset.next()) {
                ProdutoModel produto = new ProdutoModel();
                
                produto.setNome(rset.getString("nome"));
                produto.setCodigo(rset.getInt("codigo"));
                produto.setPreco(rset.getDouble("preco"));
                produto.setQuantidade(rset.getInt("quantidade"));
                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro get:" + e);
            return null;
        }
    }

}
