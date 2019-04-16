/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 *
 * @author a1937553
 */
public class CRUD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParserConfigurationException, IOException, MalformedURLException, SAXException, MalformedURLException  {
        Connection c = null;
        Statement stmt = null;
        Scanner s = new Scanner(System.in);
        System.out.println("login:");
        String login = s.next();
        s = new Scanner(System.in);
        System.out.println("nome:");
        String nome = s.nextLine();
        s = new Scanner(System.in);
        System.out.println("cidade natal:");
        String cidNatal = s.nextLine();
        s = new Scanner(System.in);
        System.out.println("Data de Nascimento:");
        String dataNasc = s.next();
        s = new Scanner (System.in);
        System.out.println("Escolha uma opção: 1- Cadastro / 2- edição / 3- listagem / 4- excluir");
        int escolha = s.nextInt();
        if(escolha == 1){
            Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                stmt = c.createStatement();
                String sql = "INSERT INTO Pessoa(login, nome, cidnatal, nascimento, funcao) VALUES ('"+login+"','"+nome+"','"+cidNatal+"', '"+dataNasc+"', 'Aluno')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
        }
        
        else if(escolha == 2){
            Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                stmt = c.createStatement();
                String sql = "UPDATE Pessoa SET login = '"+login+"', nome = '"+nome+"', cidnatal = '"+cidNatal+"', nascimento = '"+dataNasc+"' WHERE login = '"+login+"'";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
        }
        
        else if(escolha == 4){
            Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                stmt = c.createStatement();
                String sql = "DELETE FROM Pessoa WHERE login = '"+login+"'";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
        }
        
        else if(escolha == 3){
            Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                stmt = c.createStatement();
                String sql = "SELECT * from pessoa";
                ResultSet rs = stmt.executeQuery(sql);
                while(!rs.isLast()){
                    rs.next();
                    System.out.print(rs.getString(1) + " / " + rs.getString(2) +" / " +  rs.getString(3) + " / " + rs.getString(4) +" / " +  rs.getString(5));
                    System.out.println('\n');
                }
                stmt.close();
                c.close();
        }

    }
    
}
