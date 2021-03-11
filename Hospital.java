package projvirtual2_hospital;

public class Hospital {
    private String nomeHospital;
    private int    leitosDisponiveis;
    private int    leitosOcupados;
    
    private HospitaisJFrame HospitalView;

    public Hospital(String nomeHospital, int leitosDisponiveis, int leitosOcupados) {
        this.nomeHospital = nomeHospital;
        this.leitosDisponiveis = leitosDisponiveis;
        this.leitosOcupados = leitosOcupados;
    }
    public int leitosDisponiveis(){
        return leitosDisponiveis - leitosOcupados;
    }
    
    public String toString(){
        return (nomeHospital + " - "+leitosDisponiveis() + " Leitos");
    }
    
    public String getNomeHospital() {
        return nomeHospital;
    }

    public int getLeitosDisponiveis() {
        return leitosDisponiveis;
    }

    public void setLeitosDisponiveis(int leitosDisponiveis) {
        this.leitosDisponiveis = HospitalView.obterHospital().getLeitosDisponiveis();
    }    

    public int getLeitosOcupados() {
        return leitosOcupados;
    }

    public void setLeitosOcupados(int leitosOcupados) {
        this.leitosOcupados = HospitalView.obterHospital().getLeitosOcupados();
    }
    
    

    
}
