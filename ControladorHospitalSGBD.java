package projvirtual2_hospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControladorHospitalSGBD {
    private String URL_DB = "jdbc:derby://localhost:1527/HOSPITALDB";
    private Connection conexaoDB;    
    public ControladorHospitalSGBD(){
        try {
            
            conexaoDB = DriverManager.getConnection(URL_DB,"userdb","userdb");
            System.out.println("Conexão bem sucedida");
        } catch (SQLException e){
            System.out.println("Houve erro na conexão e= " +e);           
        }
    }
    public void lerListaHospital(HospitalLista hlista){
        try {
            hlista.limpaLista();
            Statement comando = conexaoDB.createStatement();
            String sqlListaTodos = "SELECT NOME_HOSP"
                                       + ", TOTAL_LEITOS,"
                                       + " LEITOS_OCUP"
                                     + "FROM HOSPITAL_TB";
            
            ResultSet resultado;
            resultado = comando.executeQuery(sqlListaTodos);
            while(resultado.next()){
                String nome = resultado.getString(1);
                int total = resultado.getInt(2);
                int ocup = resultado.getInt(3);
                Hospital h = new Hospital(nome, total, ocup);
                hlista.insereHospital(h);
            }
            comando.close();
        } catch (SQLException e) {
        }
            System.out.println("Erro na leitura");        
    }    
    public void insereHospital(Hospital h){
        
        try {
            Statement comando = conexaoDB.createStatement();
            String sqlInsere = "INSERT INTO HOSPITAL_TB(NOME_HOSP,"
                    + "TOTAL_LEITOS,"
                    + "LEITOS_OCUP"
                    + "VALUES('" 
                    + h.getNomeHospital()
                    + "',"
                    + h.getLeitosDisponiveis()
                    + ","
                    + h.getLeitosOcupados()
                    + ")";
            System.out.println("Executando: " + sqlInsere);
            comando.executeLargeUpdate(sqlInsere);
            System.out.println("Dado inserido adequadamente");            
            comando.close();
        } catch (SQLException e) {
            System.out.println("Erro na inserção do dado");
        }        
    }
    public void removeHospital(Hospital h){
        try {
            Statement comando = conexaoDB.createStatement();
            String sqlRemove = "DELETE"
                             + "FROM HOSPITAL_TB "
                             + "WHERE NOME_HOSP =  '"
                             + h.getNomeHospital()
                             + "'";
            System.out.println("Executando: " + sqlRemove);
            comando.executeUpdate(sqlRemove);
            System.out.println("Dado removido adequadamente");
            comando.close();
        } catch (SQLException e) {
            System.out.println("Erro na remoção do dado");  
        }
    }
    public void removeHospital(){
        try {
            Statement comando = conexaoDB.createStatement();
            String sqlRemove = "DELETE"
                             + "FROM HOSPITAL_TB ";                            
            System.out.println("Executando: " + sqlRemove);
            comando.executeUpdate(sqlRemove);
            System.out.println("Dado removido adequadamente");
            comando.close();
        } catch (SQLException e) {
            System.out.println("Erro na remoção do dado");  
        }
    }
    public void alteraHospital(Hospital h){
        try {
            Statement comando = conexaoDB.createStatement();
            String sqlAltera = "UPDATE HOSPITAL_TB"
                             + " SET TOTAL_LEITOS = "
                             + "x"
                             + ","
                             + "LEITOS_OCUP = "
                             + h.getLeitosOcupados()
                             + "WHERE NOME_HOSP = '"
                             + h.getNomeHospital()
                             + "'";           
            
            System.out.println("Executando: " + sqlAltera);
            comando.executeLargeUpdate(sqlAltera);
            System.out.println("Dado alterado adequadamente");            
            comando.close();
        } catch (SQLException e) {
        }
    }
    
    
}
