package readxmlfile;
/**
 * Connection con =
 * DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani",
 * "1802Viludani", "578769");
 *
 *
 * @author dvieira
 */
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ReadXMLFile {

    public static void main(String argv[]) throws SQLException, ClassNotFoundException, ParserConfigurationException, IOException, MalformedURLException, SAXException, MalformedURLException {
        Connection c = null;
        Statement stmt = null;

        System.out.println("Table created successfully");
        ArrayDeque<Pessoa> listPessoa = Pessoa.xmlparsePerson("http://dainf.ct.utfpr.edu.br/~gomesjr/BD1/data/person.xml");
        ArrayDeque<Filme> listFilme = Filme.xmlparseFilme("http://dainf.ct.utfpr.edu.br/~gomesjr/BD1/data/movie.xml");
        ArrayDeque<ArtistaMusical> listArtistaMusical = ArtistaMusical.xmlparseArtistaMusical("http://dainf.ct.utfpr.edu.br/~gomesjr/BD1/data/music.xml");
        ArrayDeque<Conhecidos> listConhecidos = Conhecidos.xmlparseConhecidos("http://dainf.ct.utfpr.edu.br/~gomesjr/BD1/data/knows.xml");


        //------------------------------------------PESSOA INICIO----------------------------------------------
        for (int i = 0; i < listPessoa.size(); i++) {
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "INSERT INTO Pessoa(login, nome, cidnatal, funcao) VALUES ('"+listPessoa.peek().getLogin()+"','"+listPessoa.peek().getNome()+"','"+listPessoa.peek().getCidadeNatal()+"', 'Aluno')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            /*System.out.print(listPessoa.peek().getLogin() + ", ");
            System.out.print(listPessoa.peek().getNome() + ", ");
            System.out.print(listPessoa.peek().getCidadeNatal() + ", ");
            System.out.println(listPessoa.peek().getNascimento());*/

            listPessoa.add(listPessoa.remove());
        }
        //------------------------------------------PESSOA FIM----------------------------------------------
        
        //------------------------------------------FILME INICIO----------------------------------------------
        for (int i = 0; i < listFilme.size(); i++) {
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "INSERT INTO Filme(id) VALUES ('"+listFilme.peek().getMovieUri()+"')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "INSERT INTO avaliapessoafilme(login, idfilme, nota) VALUES ('"+listFilme.peek().getPerson()+"','"+listFilme.peek().getMovieUri()+"', '"+listFilme.peek().getRating()+"')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            listFilme.add(listFilme.remove());
        }
        //------------------------------------------FILME FIM----------------------------------------------
    
        //------------------------------------------ARTISTA INICIO----------------------------------------------
        for (int i = 0; i < listArtistaMusical.size(); i++) {
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "INSERT INTO artistasmusicais(id) VALUES ('"+listArtistaMusical.peek().getBandUri()+"')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "INSERT INTO avaliapessoaartistasmusicais(login, idartista, nota) VALUES ('"+listArtistaMusical.peek().getPerson()+"','"+listArtistaMusical.peek().getBandUri()+"', '"+listArtistaMusical.peek().getRating()+"')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            listArtistaMusical.add(listArtistaMusical.remove());
        }
        //------------------------------------------ARTISTA FIM----------------------------------------------
        
        //------------------------------------------CONHECIDO INICIO----------------------------------------------
        
        for (int i = 0; i < listConhecidos.size(); i++) {
            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://200.134.10.32:5432/1802Viludani", "1802Viludani", "578769");
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "INSERT INTO pessoaconhecepessoa(login, loginconhecido) VALUES ('"+listConhecidos.peek().getPerson()+"','"+listConhecidos.peek().getColleague()+"')";
                stmt.executeUpdate(sql);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            /*System.out.print(listPessoa.peek().getLogin() + ", ");
            System.out.print(listPessoa.peek().getNome() + ", ");
            System.out.print(listPessoa.peek().getCidadeNatal() + ", ");
            System.out.println(listPessoa.peek().getNascimento());*/

            listConhecidos.add(listConhecidos.remove());
        }
        //------------------------------------------CONHECIDO FIM----------------------------------------------        
    }
}