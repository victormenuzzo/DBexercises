/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analise;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author lucas
 */
public class Analise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
        } catch (SQLException ex) {
            Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
        }

        int opt = -1;
        ResultSet rs = null;
        try {
            stmt = c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql;
        sql = "CREATE OR REPLACE VIEW analiseFilme AS select idfilme as id, avg(nota) as media, count(idfilme) as qtvotos, stddev(nota) as DesvioPadrao from avaliapessoafilme group by idfilme; CREATE OR REPLACE VIEW analiseArtista AS select idartista as id, avg(nota) as media, count(idartista) as qtvoto, stddev(nota) as DesvioPadrao from avaliapessoaartistasmusicais group by idartista;";
        stmt.execute(sql);
        int i = 1;
        while (opt != 0) {
            if (i < 11) {
                System.out.println("\n\n\n\n\nQuestao " + i);
            } else {
                System.out.println("\n\n\n\n\nFIM ");
            }

            Scanner s = new Scanner(System.in);
            System.out.println("Aperte o numero do exercicio desejado (1 - 10): ");
            opt = (i <= 10) ? i++ : 0; //= Integer.parseInt(s.next());

            if (opt == 1) {
                sql = "select idfilme as id, avg(nota) as  Média, stddev(nota) as Desvio_Padrão from avaliapessoafilme group by idfilme";
                System.out.println("Filme\t\t\t\t\tMedia\t\t\tDesPAD");

                try {
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (!rs.isLast()) {
                    try {
                        rs.next();
                    } catch (SQLException ex) {
                        Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        System.out.print(rs.getString(1) + "\t" + rs.getFloat(2) + "\t\t\t" + rs.getFloat(3) + '\n');
                    } catch (SQLException ex) {
                        Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                System.out.println("");
                System.out.println("ARTISTA\t\t\t\t\tMEDIA\t\t\t\t\t\tDESVPAD");

                sql = "select idartista as id, avg(nota) as  Média, stddev(nota) as Desvio_Padrão from avaliapessoaartistasmusicais group by idartista";
                try {
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (!rs.isLast()) {
                    try {
                        rs.next();
                    } catch (SQLException ex) {
                        Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        System.out.print(rs.getString(1) + "\t" + rs.getFloat(2) + "\t\t\t\t" + rs.getFloat(3) + '\n');
                    } catch (SQLException ex) {
                        Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

            if (opt == 2) {
                try {
                    stmt = c.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                sql = "select id, media from analiseFilme where qtvotos >=2 order by media desc";
                System.out.println("filme\t\t\t\tmedia");
                try {
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    while (!rs.isLast()) {
                        rs.next();
                        System.out.println(rs.getString(1) + '\t' + rs.getFloat(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    stmt = c.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                sql = "select id, media from analiseArtista where qtvoto >=2 order by media desc";
                System.out.println("artista musical\t\t\t\t\t\t\tmedia");
                try {
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    while (!rs.isLast()) {
                        rs.next();
                        System.out.println(rs.getString(1) + "\t\t\t" + rs.getFloat(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (opt == 3) {
                try {
                    stmt = c.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                sql = "select id, qtvotos from analiseFilme order by qtvotos desc limit 10";
                System.out.println("filme\t\t\t\tcurtidas");
                try {
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    while (!rs.isLast()) {
                        rs.next();
                        System.out.print(rs.getString(1) + '\t' + rs.getInt(2) + '\n');
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                sql = "select id, qtvoto from analiseArtista order by qtvoto desc limit 10";
                System.out.println("artista\t\t\t\tcurtidas");
                try {
                    rs = stmt.executeQuery(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    while (!rs.isLast()) {
                        rs.next();
                        System.out.print(rs.getString(1) + '\t' + rs.getInt(2) + '\n');
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (opt == 4) {
                try {
                    stmt = c.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }

                sql = "create or replace view conhecenormalizada as select login, loginconhecido from pessoaconhecepessoa union select loginconhecido, login from pessoaconhecepessoa;";
                System.out.println("pessoa1\t\t\44t\tpessoa2");
                try {
                    //rs = stmt.executeQuery(sql);
                    rs = stmt.executeQuery("select * from conhecenormalizada;");
                } catch (SQLException ex) {
                    Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (!rs.isLast()) {
                    rs.next();
                    System.out.print(rs.getString(1) + '\t' + rs.getString(2) + '\n');
                }
            }

            if (opt == 5) {
                stmt = c.createStatement();
                sql = "    CREATE or replace view pessoacfilme AS  SELECT avaliapessoafilme.login, count(avaliapessoafilme.login) AS conta FROM avaliapessoafilme GROUP BY avaliapessoafilme.login;\n"
                        + "\n"
                        + "    create or replace view somados 2 as select a1.login, a1.loginconhecido, a2.conta+a3.conta as soma from conhecenormalizada as a1 inner join pessoaCfilme as a2 on a1.login = a2.login inner join pessoaCfilme as a3 on a1.loginconhecido=a3.login;\n"
                        + "\n"
                        + "    select login, loginconhecido, soma from somados2 where soma = (select max(soma) from somados2);";
                System.out.println("pessoa1\t\t\t\tpessoa2");
                rs = stmt.executeQuery(" select login, loginconhecido, soma from somados2 where soma = (select max(soma) from somados2);");
                while (!rs.isLast()) {
                    rs.next();
                    System.out.print(rs.getString(1) + '\t' + rs.getString(2) + '\n');
                }

            }

            if (opt == 6) {

                stmt = c.createStatement();
                sql = "create or replace view conhecidoVictor as select loginconhecido from conhecenormalizada where login like 'DI1802victormenuzzo'; create or replace view conhecidoShoiti as select loginconhecido from conhecenormalizada where login like 'DI1802lucastakahashi'; create or replace view conhecidoDaniel as select loginconhecido from conhecenormalizada where login like 'DI1802danielvieira';";
                //rs = stmt.executeQuery(sql);
                sql = "select loginconhecido, count(loginconhecido) from conhecenormalizada natural join conhecidoDaniel group by loginconhecido;";
                rs = stmt.executeQuery(sql);
                rs.next();
                System.out.println("Daniel :" + rs.getInt(2));
                sql = "select loginconhecido, count(loginconhecido) from conhecenormalizada natural join conhecidoShoiti group by loginconhecido;";
                rs = stmt.executeQuery(sql);
                rs.next();
                System.out.println("Shoiti :" + rs.getInt(2));
                sql = "select loginconhecido, count(loginconhecido) from conhecenormalizada natural join conhecidoVictor group by loginconhecido;";
                rs = stmt.executeQuery(sql);
                rs.next();
                System.out.println("Victor :" + rs.getInt(2));

            }

            if (opt == 7) {

                stmt = c.createStatement();
                sql = "select conta as qtdefilme, count(login) as qtpessoa from pessoaCfilme group by conta order by conta ";
                rs = stmt.executeQuery(sql);
                System.out.println("\t1---5----10----15----20\t(filmes)");
                while (!rs.isLast()) {
                    rs.next();
                    System.out.print(rs.getInt(1) + "\t"); //numero de pessoas
                    int max = rs.getInt(2); //numero de filmes
                    for (int j = 0; j < max; j++) {
                        System.out.print("*");
                    }
                    System.out.print('\n');
                }
                System.out.println("pessoas");
            }

            if (opt == 8) {

                stmt = c.createStatement();
                sql = "select conta as qtdefilme, count(login) as qtpessoa from pessoaCfilme group by conta order by conta ";
                rs = stmt.executeQuery(sql);
                System.out.println("\t1---5----10----15----20\t(pessoas)");
                while (!rs.isLast()) {
                    rs.next();
                    System.out.print(rs.getInt(1) + "\t"); //numero de filmes
                    int max = rs.getInt(2); //numero de pessoas
                    for (int j = 0; j < max; j++) {
                        System.out.print("*");
                    }
                    System.out.print('\n');
                }
                System.out.println("filmes");
            }

            if (opt == 9) {
                System.out.println("retorna as pessoas que conhecem mais pessoas:");
                stmt = c.createStatement();
                sql = "select login, count from conhecemais where count = (select max(count) from conhecemais)";
                System.out.println("pessoa\t\t\t\tconhecidos");
                rs = stmt.executeQuery(sql);
                while (!rs.isLast()) {
                    rs.next();
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                }
            }
            if (opt == 10) {
                System.out.println("retorna o numero de pessoas pertencentes a cada cidade:");
                stmt = c.createStatement();
                sql = "select cidnatal, count(cidNatal) from pessoa group by cidnatal order by count desc ";
                System.out.println("cidade natal\t\t\t\tn de pessoas");
                rs = stmt.executeQuery(sql);
                while (!rs.isLast()) {
                    rs.next();
                    System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getInt(2));
                }
            }
        }
    }
}
